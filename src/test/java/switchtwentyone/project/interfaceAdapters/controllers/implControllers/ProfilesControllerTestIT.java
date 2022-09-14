package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateProfile;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetProfileList;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceUpdateProfile;
import switchtwentyone.project.domain.aggregates.account.*;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.AccountDTO;
import switchtwentyone.project.dto.NewProfileInfoDTO;
import switchtwentyone.project.dto.ProfileDTO;
import switchtwentyone.project.dto.mapper.ProfileDomainDTOMapper;
import switchtwentyone.project.externaldto.ExternalProfileDTO;
import switchtwentyone.project.infrastructure.webclient.irestrepositories.IProfileRestRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class ProfilesControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ApplicationServiceCreateProfile appServCreateProfile;

    @Autowired
    ApplicationServiceGetProfileList applicationServiceGetProfileList;

    @Autowired
    ApplicationServiceUpdateProfile applicationServiceUpdateProfile;

    @MockBean
    private IProfileRestRepository restRepository;


    @Test
    void createProfileTrueIntegration() throws Exception {
        NewProfileInfoDTO newProfileInfoDTO = new NewProfileInfoDTO("newProfile", "description");
        ProfileID prfID = ProfileID.ofProfileType("newProfile");
        Text descr = Text.write("description");

        Profile newProfile = new Profile(prfID, descr);
        ProfileDTO newProfileDTO = ProfileDomainDTOMapper.toDTO(newProfile);
        newProfileDTO.profileID = String.valueOf(prfID);
        newProfileDTO.description = String.valueOf(descr);

        String postValue = objectMapper.writeValueAsString(newProfileInfoDTO);


        MvcResult result =  mockMvc
                .perform(MockMvcRequestBuilders
                        //We say that we want to do a post
                        .post("/profiles/")
                        //We declare the content type.
                        .contentType(MediaType.APPLICATION_JSON)
                        // we set the content.
                        .content(postValue))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
    }


    @Test
    void getOurProfilesTest() throws Exception {

        ProfileDTO prfDTOOne = new ProfileDTO("User", "Account type with the standard permissions level.");
        ProfileDTO prfDTOTwo = new ProfileDTO("Administrator",
                "It's responsible for managing user accounts and association to available profiles. " +
                        "Manages resources and general configurations. " +
                        "Account type must be created when the system is installed.");
        ProfileDTO prfDTOThree = new ProfileDTO("Director",
                "It's an employee with special management functions and consequently special permissions " +
                        "in the project administration sphere.");
        ProfileDTO prfDTOFour = new ProfileDTO("Visitor",
                "Profile that is automaticaly created when you create an account."  +
                        "The only permission is to ask for other profile.");

        List<ProfileDTO> expected = Arrays.asList(prfDTOOne, prfDTOTwo, prfDTOThree, prfDTOFour);



        MvcResult result = this.mockMvc
                .perform(get("/local/profiles"))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();

        assertNotNull(resultContent);

        assertEquals(objectMapper.writeValueAsString(expected), resultContent);

    }
    @Test
    void getProfilesTest() throws Exception {

        ProfileDTO prfDTOOne = new ProfileDTO("User", "Account type with the standard permissions level.");
        ProfileDTO prfDTOTwo = new ProfileDTO("Administrator",
                "It's responsible for managing user accounts and association to available profiles. " +
                        "Manages resources and general configurations. " +
                        "Account type must be created when the system is installed.");
        ProfileDTO prfDTOThree = new ProfileDTO("Director",
                "It's an employee with special management functions and consequently special permissions " +
                        "in the project administration sphere.");
        ProfileDTO prfDTOFour = new ProfileDTO("Visitor",
                "Profile that is automaticaly created when you create an account."  +
                        "The only permission is to ask for other profile.");
        ProfileDTO prfDTOFive = new ProfileDTO("External - External",
                "From external source");

        ExternalProfileDTO externalProfile = new ExternalProfileDTO();
        externalProfile.name = "External";
        List<ExternalProfileDTO> externalProfiles = new ArrayList<>();
        externalProfiles.add(externalProfile);
        when(restRepository.getAllProfiles()).thenReturn(CollectionModel.of(externalProfiles));
        List<ProfileDTO> expected = new ArrayList<>();
        expected.add(prfDTOOne);
        expected.add(prfDTOTwo);
        expected.add(prfDTOThree);
        expected.add(prfDTOFour);
        expected.add(prfDTOFive);



        MvcResult result = this.mockMvc
                .perform(get("/profiles"))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();

        assertNotNull(resultContent);

        assertEquals(objectMapper.writeValueAsString(expected), resultContent);

    }

    @Test
    void updateProfile() throws Exception {

        //Verify if responseStatus is OK or cod 200.
        Email email = Email.of("js@mymail.com");

        ProfileID profileID = ProfileID.ofProfileType("User");

        ProfileDTO profileDTO = new ProfileDTO("User", "Account type with the standard permissions level.");

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.accountID = "js@mymail.com";
        accountDTO.email = "js@mymail.com";
        accountDTO.name = "Joao Silva";
        accountDTO.function = "Anything";
        accountDTO.photo = "Picture";


        Optional<AccountDTO> accountDTOOptional = Optional.of(accountDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/accounts/js@mymail.com/profiles")
                        .content(objectMapper.writeValueAsString(profileDTO))
                        .accept("application/json")

                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();


        assertEquals(200, result.getResponse().getStatus());

    }

    @Test
    void updateProfileTwo() throws Exception {

        //If return DTO is what is expected.


        ProfileDTO profileDTO = new ProfileDTO("User", "Account type with the standard permissions level.");

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.accountID = "js@mymail.com";
        accountDTO.email = "js@mymail.com";
        accountDTO.name = "Joao Silva";
        accountDTO.function = "Anything";
        accountDTO.photo = "Picture";
        accountDTO.profileID="User";

        AccountCreatable accountFactory = new AccountFactory();
        Email emailTestOne = Email.of("js@mymail.com");
        AccountID accountIDTestOne = AccountID.of(emailTestOne);
        NoNumberNoSymbolString nameTestOne = NoNumberNoSymbolString.of("Joao Silva");
        NoNumberNoSymbolString functionTestOne = NoNumberNoSymbolString.of("Anything");
        Photo photoTestOne = Photo.of("Picture");
        Password passwordTestOne = Password.of("testing01", 2);
        ProfileID profileIDTestOne = ProfileID.ofProfileType("User");
        Account testAccountOne = accountFactory.createAccount(accountIDTestOne, emailTestOne, nameTestOne,
                functionTestOne, photoTestOne, passwordTestOne, profileIDTestOne);
        Optional<AccountDTO> accountDTOOptional = Optional.empty();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/accounts/js@mymail.com/profiles")
                        .content(objectMapper.writeValueAsString(profileDTO))
                        .accept("application/json")

                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String responseEntity =result.getResponse().getContentAsString();
        String dtoExpected = objectMapper.writeValueAsString(accountDTOOptional);

        ResponseEntity<Object> expected=ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseEntity);


        assertEquals(responseEntity,dtoExpected);
    }
}