

package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import switchtwentyone.project.domain.aggregates.account.*;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.AccountAndStatusDTO;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional


public class ViewAllAccountsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    AccountController accountController;

    @Autowired
    AccountRepository accountRepo;



    @Test
    void getListOfAllAccountsAndStatus() throws Exception {
     //Create Account
        Email email = Email.of("lino@isep.ipp.pt");
        AccountID ID = AccountID.of(email);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Engineer");
        Photo photo = Photo.of("1234abcd");
        Password password = Password.of("1234abcde", 1);
        ProfileID profileID = new ProfileID(ProfileType.of("Visitor"));
        accountRepo.createAndSaveAccount(ID,email,name, function, photo, password, profileID);

        AccountAndStatusDTO dto1 = new AccountAndStatusDTO();
        dto1.email = "lino@isep.ipp.pt";
        dto1.active = false;

        List<AccountAndStatusDTO> expected = new ArrayList<>();
        expected.add(dto1);


        String result = mockMvc
                .perform(MockMvcRequestBuilders.get("/accountsInfo")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(objectMapper.writeValueAsString(expected),result);

    }

}


