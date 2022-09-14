package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceGetAccount;
import switchtwentyone.project.datamodel.AccountJPA;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.AccountShortDTO;
import switchtwentyone.project.dto.AccountShortDomainDTO;
import switchtwentyone.project.dto.mapper.AccountMapper;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IAccountJPARepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IProfileJPARepository;
import switchtwentyone.project.applicationServices.implAppServices.ApplicationServiceCreateAccount;
import switchtwentyone.project.domain.aggregates.account.*;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.AccountDTO;
import switchtwentyone.project.dto.NewAccountInfoDTO;
import switchtwentyone.project.datamodel.assembler.AccountDomainDataAssembler;
import switchtwentyone.project.datamodel.assembler.ProfileDomainDataAssembler;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProfileRepository;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static switchtwentyone.project.interfaceAdapters.controllers.implControllers.UserStoryControllerIntegrationTests.asJsonString;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AccountControllerIntegrationTest {

    @Autowired
    ApplicationServiceCreateAccount appServiceCreateAccount;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    IProfileJPARepository profileJPARepository;
    @Autowired
    ProfileDomainDataAssembler profileAssembler;
    @Autowired
    IAccountJPARepository accountJPARepository;
    @Autowired
    AccountDomainDataAssembler accountDomainDataAssembler;
    @Autowired
    AccountFactory accountFactory;
    @Autowired
    AccountController accountController;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ApplicationServiceGetAccount applicationServiceGetAccount;
    @Autowired
    AccountCreatable accountFactoryInterface;


    @Test
    void createAccount_Test_Success() throws Exception {
        //Arrange
        ProfileID testProfileID = ProfileID.ofProfileType("Visitor");
        Text testDescription = Text.write("Default profile");
        Profile testProfile = Profile.of(testProfileID, testDescription);
        Profile resultTestProfileSave = profileRepository.save(testProfile);

        String email = "luis@isep.ipp.pt";
        NewAccountInfoDTO newAccountInfoDTO = new NewAccountInfoDTO("luis@isep.ipp.pt", "Luis", "Developer", "testing01", ":)");

        AccountDTO testAccountDTO = new AccountDTO();
        testAccountDTO.accountID = "luis@isep.ipp.pt";
        testAccountDTO.email = "luis@isep.ipp.pt";
        testAccountDTO.name = "Luis";
        testAccountDTO.function = "Developer";
        testAccountDTO.photo = ":)";
        testAccountDTO.profileID = "Visitor";
        String expected = objectMapper.writeValueAsString(testAccountDTO);

        //Act
        String result = mockMvc
                .perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newAccountInfoDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertEquals(expected, result);
    }


    @Test
    void findAccountInfo_Test_long_ReturnOk() throws Exception {
        //Arrange
        AccountCreatable accountFactory = new AccountFactory();
        Email emailTestOne = Email.of("luis@isep.ipp.pt");
        AccountID accountIDTestOne = AccountID.of(emailTestOne);
        NoNumberNoSymbolString nameTestOne = NoNumberNoSymbolString.of("Luis");
        NoNumberNoSymbolString functionTestOne = NoNumberNoSymbolString.of("Engineer");
        Photo photoTestOne = Photo.of("Picture");
        Password passwordTestOne = Password.of("testing01", 2);
        ProfileID profileIDTestOne = ProfileID.ofProfileType("User");
        Account testAccountOne = accountFactory.createAccount(accountIDTestOne, emailTestOne, nameTestOne,
                functionTestOne, photoTestOne, passwordTestOne, profileIDTestOne);

        Email emailTestTwo = Email.of("andre@isep.ipp.pt");
        AccountID accountIDTestTwo = AccountID.of(emailTestTwo);
        NoNumberNoSymbolString nameTestTwo = NoNumberNoSymbolString.of("Andre");
        NoNumberNoSymbolString functionTestTwo = NoNumberNoSymbolString.of("Accounting");
        Photo photoTestTwo = Photo.of("Picture");
        Password passwordTestTwo = Password.of("testing05", 2);
        ProfileID profileIDTestTwo = ProfileID.ofProfileType("Administrator");
        Account testAccountTwo = accountFactory.createAccount(accountIDTestTwo, emailTestTwo, nameTestTwo,
                functionTestTwo, photoTestTwo, passwordTestTwo, profileIDTestTwo);

        Optional<AccountJPA> testAccountOneJPA = Optional.of(accountDomainDataAssembler.toData(testAccountOne));
        Optional<AccountJPA> testAccountTwoJPA = Optional.of(accountDomainDataAssembler.toData(testAccountTwo));
        accountJPARepository.save(testAccountOneJPA.get());
        accountJPARepository.save(testAccountTwoJPA.get());

        AccountDTO testAccountDTO = AccountMapper.toDTO(testAccountTwo);
        String email = "andre@isep.ipp.pt";

        String expected = objectMapper.writeValueAsString(testAccountDTO);
        //Act
        String result = mockMvc
                .perform(MockMvcRequestBuilders.get("/accounts/" + email + "?type=long")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        //Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void findAccountInfo_Test_long_ReturnNotFound() throws Exception {
        //Arrange
        Email emailTestOne = Email.of("luis@isep.ipp.pt");
        AccountID accountIDTestOne = AccountID.of(emailTestOne);
        NoNumberNoSymbolString nameTestOne = NoNumberNoSymbolString.of("Luis");
        NoNumberNoSymbolString functionTestOne = NoNumberNoSymbolString.of("Engineer");
        Photo photoTestOne = Photo.of("Picture");
        Password passwordTestOne = Password.of("testing01", 2);
        ProfileID profileIDTestOne = ProfileID.ofProfileType("User");

        Account testAccountOne = accountFactoryInterface.createAccount(accountIDTestOne, emailTestOne, nameTestOne, functionTestOne, photoTestOne, passwordTestOne, profileIDTestOne);

        Email emailTestTwo = Email.of("andre@isep.ipp.pt");
        AccountID accountIDTestTwo = AccountID.of(emailTestTwo);
        NoNumberNoSymbolString nameTestTwo = NoNumberNoSymbolString.of("André");
        NoNumberNoSymbolString functionTestTwo = NoNumberNoSymbolString.of("Accounting");
        Photo photoTestTwo = Photo.of("Picture");
        Password passwordTestTwo = Password.of("testing05", 2);
        ProfileID profileIDTestTwo = ProfileID.ofProfileType("Administrator");

        Account testAccountTwo = accountFactoryInterface.createAccount(accountIDTestTwo, emailTestTwo, nameTestTwo, functionTestTwo, photoTestTwo, passwordTestTwo, profileIDTestTwo);

        Optional<AccountJPA> testAccountOneJPA = Optional.of(accountDomainDataAssembler.toData(testAccountOne));
        Optional<AccountJPA> testAccountTwoJPA = Optional.of(accountDomainDataAssembler.toData(testAccountTwo));
        accountJPARepository.save(testAccountOneJPA.get());
        accountJPARepository.save(testAccountTwoJPA.get());

        String email = "marta@isep.ipp.pt";

        MvcResult result2 = mockMvc
                .perform(MockMvcRequestBuilders.get("/accounts/" + email + "?type=long")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContent = result2.getResponse().getContentAsString();

        assertNotNull(resultContent);
        assertEquals("", resultContent);
    }

    @Test
    void findAccountInfo_Test_long_Exception() throws Exception {
        //Arrange
        Email emailTestOne = Email.of("luis@isep.ipp.pt");
        AccountID accountIDTestOne = AccountID.of(emailTestOne);
        NoNumberNoSymbolString nameTestOne = NoNumberNoSymbolString.of("Luis");
        NoNumberNoSymbolString functionTestOne = NoNumberNoSymbolString.of("Engineer");
        Photo photoTestOne = Photo.of("Picture");
        Password passwordTestOne = Password.of("testing01", 2);
        ProfileID profileIDTestOne = ProfileID.ofProfileType("User");

        Account testAccountOne = accountFactoryInterface.createAccount(accountIDTestOne, emailTestOne, nameTestOne, functionTestOne, photoTestOne, passwordTestOne, profileIDTestOne);

        Email emailTestTwo = Email.of("andre@isep.ipp.pt");
        AccountID accountIDTestTwo = AccountID.of(emailTestTwo);
        NoNumberNoSymbolString nameTestTwo = NoNumberNoSymbolString.of("André");
        NoNumberNoSymbolString functionTestTwo = NoNumberNoSymbolString.of("Accounting");
        Photo photoTestTwo = Photo.of("Picture");
        Password passwordTestTwo = Password.of("testing05", 2);
        ProfileID profileIDTestTwo = ProfileID.ofProfileType("Administrator");

        Account testAccountTwo = accountFactoryInterface.createAccount(accountIDTestTwo, emailTestTwo, nameTestTwo, functionTestTwo, photoTestTwo, passwordTestTwo, profileIDTestTwo);

        Optional<AccountJPA> testAccountOneJPA = Optional.of(accountDomainDataAssembler.toData(testAccountOne));
        Optional<AccountJPA> testAccountTwoJPA = Optional.of(accountDomainDataAssembler.toData(testAccountTwo));
        accountJPARepository.save(testAccountOneJPA.get());
        accountJPARepository.save(testAccountTwoJPA.get());

        AccountDTO testAccountDTO = AccountMapper.toDTO(testAccountTwo);
        String email = "martaipp.pt";


        MvcResult result2 = mockMvc
                .perform(MockMvcRequestBuilders.get("/accounts/" + email + "?type=long")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();


        String resultContent = result2.getResponse().getContentAsString();

        assertNotNull(resultContent);
        assertEquals("Email format is invalid", resultContent);

    }

    @Test
    void findAccountInfo_Test_short_Success() throws Exception {
        Email emailTestOne = Email.of("kevin@isep.ipp.pt");
        AccountID accountIDTestOne = AccountID.of(emailTestOne);
        NoNumberNoSymbolString nameTestOne = NoNumberNoSymbolString.of("Kevin");
        NoNumberNoSymbolString functionTestOne = NoNumberNoSymbolString.of("Minion");
        Photo photoTestOne = Photo.of("Picture");
        Password passwordTestOne = Password.of("B4n4n4!!", 2);
        ProfileID profileIDTestOne = ProfileID.ofProfileType("User");
        Account testAccountOne = accountFactoryInterface.createAccount(accountIDTestOne, emailTestOne, nameTestOne,
                functionTestOne, photoTestOne, passwordTestOne, profileIDTestOne);
        Email emailTestTwo = Email.of("andre@isep.ipp.pt");
        AccountID accountIDTestTwo = AccountID.of(emailTestTwo);
        NoNumberNoSymbolString nameTestTwo = NoNumberNoSymbolString.of("Andre");
        NoNumberNoSymbolString functionTestTwo = NoNumberNoSymbolString.of("Engineer");
        Photo photoTestTwo = Photo.of("Picture");
        Password passwordTestTwo = Password.of("testing05", 2);
        ProfileID profileIDTestTwo = ProfileID.ofProfileType("Administrator");
        Account testAccountTwo = accountFactoryInterface.createAccount(accountIDTestTwo, emailTestTwo, nameTestTwo,
                functionTestTwo, photoTestTwo, passwordTestTwo, profileIDTestTwo);
        Optional<AccountJPA> testAccountOneJPA = Optional.of(accountDomainDataAssembler.toData(testAccountOne));
        Optional<AccountJPA> testAccountTwoJPA = Optional.of(accountDomainDataAssembler.toData(testAccountTwo));
        accountJPARepository.save(testAccountOneJPA.get());
        accountJPARepository.save(testAccountTwoJPA.get());
        AccountShortDTO testAccountShortDTO = AccountMapper.toShortDTO(testAccountOne);
        String email = "kevin@isep.ipp.pt";
        String expected = objectMapper.writeValueAsString(testAccountShortDTO);
        //Act&Assert
        String result = mockMvc
                .perform(MockMvcRequestBuilders.get("/accounts/" + email + "?type=short")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("kevin@isep.ipp.pt")))
                .andExpect(jsonPath("$.name", is("Kevin")))
                .andExpect(jsonPath("$.jobTitle", is("Minion")))
                .andExpect(jsonPath("$.photo", is("Picture")))
                .andReturn()
                .getResponse()
                .getContentAsString();
        //Assert
        assertNotNull(result);
    }

    @Test
    void findAccountInfo_Test_short_ReturnNotFound() throws Exception {
        Email emailTestOne = Email.of("kevin@isep.ipp.pt");
        AccountID accountIDTestOne = AccountID.of(emailTestOne);
        NoNumberNoSymbolString nameTestOne = NoNumberNoSymbolString.of("Kevin");
        NoNumberNoSymbolString functionTestOne = NoNumberNoSymbolString.of("Minion");
        Photo photoTestOne = Photo.of("Picture");
        Password passwordTestOne = Password.of("B4n4n4!!", 2);
        ProfileID profileIDTestOne = ProfileID.ofProfileType("User");
        Account testAccountOne = accountFactoryInterface.createAccount(accountIDTestOne, emailTestOne, nameTestOne,
                functionTestOne, photoTestOne, passwordTestOne, profileIDTestOne);
        Email emailTestTwo = Email.of("andre@isep.ipp.pt");
        AccountID accountIDTestTwo = AccountID.of(emailTestTwo);
        NoNumberNoSymbolString nameTestTwo = NoNumberNoSymbolString.of("Andre");
        NoNumberNoSymbolString functionTestTwo = NoNumberNoSymbolString.of("Engineer");
        Photo photoTestTwo = Photo.of("Picture");
        Password passwordTestTwo = Password.of("testing05", 2);
        ProfileID profileIDTestTwo = ProfileID.ofProfileType("Administrator");
        Account testAccountTwo = accountFactoryInterface.createAccount(accountIDTestTwo, emailTestTwo, nameTestTwo,
                functionTestTwo, photoTestTwo, passwordTestTwo, profileIDTestTwo);
        Optional<AccountJPA> testAccountOneJPA = Optional.of(accountDomainDataAssembler.toData(testAccountOne));
        Optional<AccountJPA> testAccountTwoJPA = Optional.of(accountDomainDataAssembler.toData(testAccountTwo));
        accountJPARepository.save(testAccountOneJPA.get());
        accountJPARepository.save(testAccountTwoJPA.get());
        AccountShortDTO testAccountShortDTO = AccountMapper.toShortDTO(testAccountOne);
        String email = "marta@isep.ipp.pt";
        String expected = "";
        //Act
        String result = mockMvc
                .perform(MockMvcRequestBuilders.get("/accounts/" + email + "?type=short")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();
        //Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void findAccountInfo_Test_short_Exception() throws Exception {
        Email emailTestOne = Email.of("kevin@isep.ipp.pt");
        AccountID accountIDTestOne = AccountID.of(emailTestOne);
        NoNumberNoSymbolString nameTestOne = NoNumberNoSymbolString.of("Kevin");
        NoNumberNoSymbolString functionTestOne = NoNumberNoSymbolString.of("Minion");
        Photo photoTestOne = Photo.of("Picture");
        Password passwordTestOne = Password.of("B4n4n4!!", 2);
        ProfileID profileIDTestOne = ProfileID.ofProfileType("User");
        Account testAccountOne = accountFactoryInterface.createAccount(accountIDTestOne, emailTestOne, nameTestOne,
                functionTestOne, photoTestOne, passwordTestOne, profileIDTestOne);
        Email emailTestTwo = Email.of("andre@isep.ipp.pt");
        AccountID accountIDTestTwo = AccountID.of(emailTestTwo);
        NoNumberNoSymbolString nameTestTwo = NoNumberNoSymbolString.of("Andre");
        NoNumberNoSymbolString functionTestTwo = NoNumberNoSymbolString.of("Engineer");
        Photo photoTestTwo = Photo.of("Picture");
        Password passwordTestTwo = Password.of("testing05", 2);
        ProfileID profileIDTestTwo = ProfileID.ofProfileType("Administrator");
        Account testAccountTwo = accountFactoryInterface.createAccount(accountIDTestTwo, emailTestTwo, nameTestTwo,
                functionTestTwo, photoTestTwo, passwordTestTwo, profileIDTestTwo);
        Optional<AccountJPA> testAccountOneJPA = Optional.of(accountDomainDataAssembler.toData(testAccountOne));
        Optional<AccountJPA> testAccountTwoJPA = Optional.of(accountDomainDataAssembler.toData(testAccountTwo));
        accountJPARepository.save(testAccountOneJPA.get());
        accountJPARepository.save(testAccountTwoJPA.get());
        AccountShortDTO testAccountShortDTO = AccountMapper.toShortDTO(testAccountOne);
        String email = "martaisep.ipp.pt";
        String expected = "Email format is invalid";
        //Act
        String result = mockMvc
                .perform(MockMvcRequestBuilders.get("/accounts/" + email + "?type=short")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();
        //Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void findAccountInfo_Test_coconutas_BadQuery() throws Exception {
        Email emailTestOne = Email.of("kevin@isep.ipp.pt");
        AccountID accountIDTestOne = AccountID.of(emailTestOne);
        NoNumberNoSymbolString nameTestOne = NoNumberNoSymbolString.of("Kevin");
        NoNumberNoSymbolString functionTestOne = NoNumberNoSymbolString.of("Minion");
        Photo photoTestOne = Photo.of("Picture");
        Password passwordTestOne = Password.of("B4n4n4!!", 2);
        ProfileID profileIDTestOne = ProfileID.ofProfileType("User");
        Account testAccountOne = accountFactoryInterface.createAccount(accountIDTestOne, emailTestOne, nameTestOne,
                functionTestOne, photoTestOne, passwordTestOne, profileIDTestOne);
        Email emailTestTwo = Email.of("andre@isep.ipp.pt");
        AccountID accountIDTestTwo = AccountID.of(emailTestTwo);
        NoNumberNoSymbolString nameTestTwo = NoNumberNoSymbolString.of("Andre");
        NoNumberNoSymbolString functionTestTwo = NoNumberNoSymbolString.of("Engineer");
        Photo photoTestTwo = Photo.of("Picture");
        Password passwordTestTwo = Password.of("testing05", 2);
        ProfileID profileIDTestTwo = ProfileID.ofProfileType("Administrator");
        Account testAccountTwo = accountFactoryInterface.createAccount(accountIDTestTwo, emailTestTwo, nameTestTwo,
                functionTestTwo, photoTestTwo, passwordTestTwo, profileIDTestTwo);
        Optional<AccountJPA> testAccountOneJPA = Optional.of(accountDomainDataAssembler.toData(testAccountOne));
        Optional<AccountJPA> testAccountTwoJPA = Optional.of(accountDomainDataAssembler.toData(testAccountTwo));
        accountJPARepository.save(testAccountOneJPA.get());
        accountJPARepository.save(testAccountTwoJPA.get());
        AccountShortDTO testAccountShortDTO = AccountMapper.toShortDTO(testAccountOne);
        String email = "kevin@isep.ipp.pt";
        String expected = "code #001";
        //Act
        String result = mockMvc
                .perform(MockMvcRequestBuilders.get("/accounts/" + email + "?type=coconuts")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();
        //Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }


    @Test
    void updateAccount_Test_ReturnOk() throws Exception {
        //Arrange
        Email emailTestOne = Email.of("luis@isep.ipp.pt");
        AccountID accountIDTestOne = AccountID.of(emailTestOne);
        NoNumberNoSymbolString nameTestOne = NoNumberNoSymbolString.of("Luis");
        NoNumberNoSymbolString functionTestOne = NoNumberNoSymbolString.of("Engineer");
        Photo photoTestOne = Photo.of("Picture");
        Password passwordTestOne = Password.of("testing01", 2);
        ProfileID profileIDTestOne = ProfileID.ofProfileType("User");
        Account testAccountOne = accountFactoryInterface.createAccount(accountIDTestOne, emailTestOne, nameTestOne,
                functionTestOne, photoTestOne, passwordTestOne, profileIDTestOne);

        Email emailTestTwo = Email.of("andre@isep.ipp.pt");
        AccountID accountIDTestTwo = AccountID.of(emailTestTwo);
        NoNumberNoSymbolString nameTestTwo = NoNumberNoSymbolString.of("Andre");
        NoNumberNoSymbolString functionTestTwo = NoNumberNoSymbolString.of("Accounting");
        Photo photoTestTwo = Photo.of("Picture");
        Password passwordTestTwo = Password.of("testing05", 2);
        ProfileID profileIDTestTwo = ProfileID.ofProfileType("Administrator");
        Account testAccountTwo = accountFactoryInterface.createAccount(accountIDTestTwo, emailTestTwo, nameTestTwo,
                functionTestTwo, photoTestTwo, passwordTestTwo, profileIDTestTwo);

        Optional<AccountJPA> testAccountOneJPA = Optional.of(accountDomainDataAssembler.toData(testAccountOne));
        Optional<AccountJPA> testAccountTwoJPA = Optional.of(accountDomainDataAssembler.toData(testAccountTwo));
        accountJPARepository.save(testAccountOneJPA.get());
        accountJPARepository.save(testAccountTwoJPA.get());

        String email = "luis@isep.ipp.pt";
        AccountShortDomainDTO newDetailsDomainDTO = new AccountShortDomainDTO();
        newDetailsDomainDTO.email = "luis@isep.ipp.pt";
        newDetailsDomainDTO.name = "Luis";
        newDetailsDomainDTO.jobTitle = "Developer";
        newDetailsDomainDTO.photo = ":)";

        AccountShortDTO newTestAccountOneDTO = new AccountShortDTO();
        newTestAccountOneDTO.email = "luis@isep.ipp.pt";
        newTestAccountOneDTO.name = "Luis";
        newTestAccountOneDTO.jobTitle = "Developer";
        newTestAccountOneDTO.photo = ":)";
        Link link = linkTo(methodOn(AccountController.class).findAccountInfo(email, "short")).withSelfRel();
        newTestAccountOneDTO.add(link);

        String expected = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newTestAccountOneDTO);

        //Act & Assert
        String result = String.valueOf(mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/accounts/" + email + "?type=short")
                        .content(asJsonString(newDetailsDomainDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email",is("luis@isep.ipp.pt")))
                .andExpect(jsonPath("$.name", is("Luis")))
                .andExpect(jsonPath("$.jobTitle", is("Developer")))
                .andExpect(jsonPath("$.photo", is(":)")))
                .andExpect(jsonPath("$._links.self.href", is(link.getHref()))));
    }


    @Test
    void updateAccount_Test_ReturnNotFound() throws Exception {
        //Arrange
        Email emailTestOne = Email.of("luis@isep.ipp.pt");
        AccountID accountIDTestOne = AccountID.of(emailTestOne);
        NoNumberNoSymbolString nameTestOne = NoNumberNoSymbolString.of("Luis");
        NoNumberNoSymbolString functionTestOne = NoNumberNoSymbolString.of("Engineer");
        Photo photoTestOne = Photo.of("Picture");
        Password passwordTestOne = Password.of("testing01", 2);
        ProfileID profileIDTestOne = ProfileID.ofProfileType("User");
        Account testAccountOne = accountFactoryInterface.createAccount(accountIDTestOne, emailTestOne, nameTestOne,
                functionTestOne, photoTestOne, passwordTestOne, profileIDTestOne);

        Email emailTestTwo = Email.of("andre@isep.ipp.pt");
        AccountID accountIDTestTwo = AccountID.of(emailTestTwo);
        NoNumberNoSymbolString nameTestTwo = NoNumberNoSymbolString.of("Andre");
        NoNumberNoSymbolString functionTestTwo = NoNumberNoSymbolString.of("Accounting");
        Photo photoTestTwo = Photo.of("Picture");
        Password passwordTestTwo = Password.of("testing05", 2);
        ProfileID profileIDTestTwo = ProfileID.ofProfileType("Administrator");
        Account testAccountTwo = accountFactoryInterface.createAccount(accountIDTestTwo, emailTestTwo, nameTestTwo,
                functionTestTwo, photoTestTwo, passwordTestTwo, profileIDTestTwo);

        Optional<AccountJPA> testAccountOneJPA = Optional.of(accountDomainDataAssembler.toData(testAccountOne));
        Optional<AccountJPA> testAccountTwoJPA = Optional.of(accountDomainDataAssembler.toData(testAccountTwo));
        accountJPARepository.save(testAccountOneJPA.get());
        accountJPARepository.save(testAccountTwoJPA.get());

        String email = "kevin@isep.ipp.pt";
        AccountShortDomainDTO newDetailsDomainDTO = new AccountShortDomainDTO();
        newDetailsDomainDTO.email = "kevin@isep.ipp.pt";
        newDetailsDomainDTO.name = "Kevin";
        newDetailsDomainDTO.jobTitle = "Developer";
        newDetailsDomainDTO.photo = ":)";

        AccountShortDTO newTestAccountOneDTO = new AccountShortDTO();
        newTestAccountOneDTO.email = "luis@isep.ipp.pt";
        newTestAccountOneDTO.name = "Luis";
        newTestAccountOneDTO.jobTitle = "Developer";
        newTestAccountOneDTO.photo = ":)";

        String expected = "";

        //Act
        String result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/accounts/" + email + "?type=short")
                        .content(asJsonString(newDetailsDomainDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertEquals(expected, result);
    }


    @Test
    void updateAccount_Test_ExceptionEmailFormat() throws Exception {
        //Arrange
        Email emailTestOne = Email.of("luis@isep.ipp.pt");
        AccountID accountIDTestOne = AccountID.of(emailTestOne);
        NoNumberNoSymbolString nameTestOne = NoNumberNoSymbolString.of("Luis");
        NoNumberNoSymbolString functionTestOne = NoNumberNoSymbolString.of("Engineer");
        Photo photoTestOne = Photo.of("Picture");
        Password passwordTestOne = Password.of("testing01", 2);
        ProfileID profileIDTestOne = ProfileID.ofProfileType("User");
        Account testAccountOne = accountFactoryInterface.createAccount(accountIDTestOne, emailTestOne, nameTestOne,
                functionTestOne, photoTestOne, passwordTestOne, profileIDTestOne);

        Email emailTestTwo = Email.of("andre@isep.ipp.pt");
        AccountID accountIDTestTwo = AccountID.of(emailTestTwo);
        NoNumberNoSymbolString nameTestTwo = NoNumberNoSymbolString.of("Andre");
        NoNumberNoSymbolString functionTestTwo = NoNumberNoSymbolString.of("Accounting");
        Photo photoTestTwo = Photo.of("Picture");
        Password passwordTestTwo = Password.of("testing05", 2);
        ProfileID profileIDTestTwo = ProfileID.ofProfileType("Administrator");
        Account testAccountTwo = accountFactoryInterface.createAccount(accountIDTestTwo, emailTestTwo, nameTestTwo,
                functionTestTwo, photoTestTwo, passwordTestTwo, profileIDTestTwo);

        Optional<AccountJPA> testAccountOneJPA = Optional.of(accountDomainDataAssembler.toData(testAccountOne));
        Optional<AccountJPA> testAccountTwoJPA = Optional.of(accountDomainDataAssembler.toData(testAccountTwo));
        accountJPARepository.save(testAccountOneJPA.get());
        accountJPARepository.save(testAccountTwoJPA.get());

        String email = "kevinisep.ipp.pt";
        AccountShortDomainDTO newDetailsDomainDTO = new AccountShortDomainDTO();
        newDetailsDomainDTO.email = "kevinisep.ipp.pt";
        newDetailsDomainDTO.name = "Kevin";
        newDetailsDomainDTO.jobTitle = "Developer";
        newDetailsDomainDTO.photo = ":)";

        AccountShortDTO newTestAccountOneDTO = new AccountShortDTO();
        newTestAccountOneDTO.email = "luis@isep.ipp.pt";
        newTestAccountOneDTO.name = "Luis";
        newTestAccountOneDTO.jobTitle = "Developer";
        newTestAccountOneDTO.photo = ":)";

        String expected = "Email format is invalid";

        //Act
        String result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/accounts/" + email + "?type=short")
                        .content(asJsonString(newDetailsDomainDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void updateAccount_Test_ExceptionEmailMissMatch() throws Exception {
        //Arrange
        Email emailTestOne = Email.of("luis@isep.ipp.pt");
        AccountID accountIDTestOne = AccountID.of(emailTestOne);
        NoNumberNoSymbolString nameTestOne = NoNumberNoSymbolString.of("Luis");
        NoNumberNoSymbolString functionTestOne = NoNumberNoSymbolString.of("Engineer");
        Photo photoTestOne = Photo.of("Picture");
        Password passwordTestOne = Password.of("testing01", 2);
        ProfileID profileIDTestOne = ProfileID.ofProfileType("User");
        Account testAccountOne = accountFactoryInterface.createAccount(accountIDTestOne, emailTestOne, nameTestOne,
                functionTestOne, photoTestOne, passwordTestOne, profileIDTestOne);

        Email emailTestTwo = Email.of("andre@isep.ipp.pt");
        AccountID accountIDTestTwo = AccountID.of(emailTestTwo);
        NoNumberNoSymbolString nameTestTwo = NoNumberNoSymbolString.of("Andre");
        NoNumberNoSymbolString functionTestTwo = NoNumberNoSymbolString.of("Accounting");
        Photo photoTestTwo = Photo.of("Picture");
        Password passwordTestTwo = Password.of("testing05", 2);
        ProfileID profileIDTestTwo = ProfileID.ofProfileType("Administrator");
        Account testAccountTwo = accountFactoryInterface.createAccount(accountIDTestTwo, emailTestTwo, nameTestTwo,
                functionTestTwo, photoTestTwo, passwordTestTwo, profileIDTestTwo);

        Optional<AccountJPA> testAccountOneJPA = Optional.of(accountDomainDataAssembler.toData(testAccountOne));
        Optional<AccountJPA> testAccountTwoJPA = Optional.of(accountDomainDataAssembler.toData(testAccountTwo));
        accountJPARepository.save(testAccountOneJPA.get());
        accountJPARepository.save(testAccountTwoJPA.get());

        String email = "andre@isep.ipp.pt";
        AccountShortDomainDTO newDetailsDomainDTO = new AccountShortDomainDTO();
        newDetailsDomainDTO.email = "kevin@isep.ipp.pt";
        newDetailsDomainDTO.name = "Kevin";
        newDetailsDomainDTO.jobTitle = "Developer";
        newDetailsDomainDTO.photo = ":)";

        AccountShortDTO newTestAccountOneDTO = new AccountShortDTO();
        newTestAccountOneDTO.email = "luis@isep.ipp.pt";
        newTestAccountOneDTO.name = "Luis";
        newTestAccountOneDTO.jobTitle = "Developer";
        newTestAccountOneDTO.photo = ":)";

        String expected = "Security error";

        //Act
        String result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/accounts/" + email + "?type=short")
                        .content(asJsonString(newDetailsDomainDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertNotNull(result);
        assertEquals(expected, result);
    }

}