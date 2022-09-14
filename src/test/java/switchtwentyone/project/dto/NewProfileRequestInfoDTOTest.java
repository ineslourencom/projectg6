package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Transactional
public class NewProfileRequestInfoDTOTest {

    @Test
    void ensureEqualsItself() {
        NewProfileRequestInfoDTO dto = new NewProfileRequestInfoDTO( "Andreia@isep.ipp.pt", "dev");

        assertEquals(dto, dto);
    }
    @Test
    void ensureDoesNotEqualDifferentObject() {
        NewProfileRequestInfoDTO dtoOne = new NewProfileRequestInfoDTO( "Andreia@isep.ipp.pt", "dev");
        NewProfileRequestInfoDTO dtoTwo = new NewProfileRequestInfoDTO( "Luis@isep.ipp.pt", "dev");

        assertNotEquals(dtoOne, dtoTwo);
    }

    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        NewProfileRequestInfoDTO dtoOne = new NewProfileRequestInfoDTO( "Andreia@isep.ipp.pt", "dev");
        Object somethingElse = new Object();

        assertNotEquals(dtoOne, somethingElse);
    }

    @Test
    void ensureDoesNotEqualNull() {
        NewProfileRequestInfoDTO dtoOne = new NewProfileRequestInfoDTO( "Andreia@isep.ipp.pt", "dev");

        assertNotEquals(dtoOne, null);
    }
    @Test
    void ensureEqualObjectsHaveEqualHashCodes() {
        NewProfileRequestInfoDTO dtoOne = new NewProfileRequestInfoDTO( "Andreia@isep.ipp.pt", "dev");
        NewProfileRequestInfoDTO dtoTwo = new NewProfileRequestInfoDTO( "Andreia@isep.ipp.pt", "dev");

        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void ensureDifferentObjectsHaveDifferentHashCodes() {
        NewProfileRequestInfoDTO dtoOne = new NewProfileRequestInfoDTO( "Andreia@isep.ipp.pt", "dev");
        NewProfileRequestInfoDTO dtoTwo = new NewProfileRequestInfoDTO( "Luis@isep.ipp.pt", "dev");

        int hashOne = dtoOne.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }

    @Test
    void NewProfileRequestInfoDTOWithSameAttributesAreNotEqual(){
        //Act
        NewProfileRequestInfoDTO dtoOne = new NewProfileRequestInfoDTO("Andreia@isep.ipp.pt", "dev");
        NewProfileRequestInfoDTO dtoTwo = new NewProfileRequestInfoDTO( "Luis@isep.ipp.pt", "dev");

        //Assert
        assertNotEquals(dtoOne, dtoTwo);
    }

    @Test
    void getEmail() {
        String email = "lino@isep.ipp.pt";
        String profileType="dev";
        NewProfileRequestInfoDTO newProfileRequestInfoDTO = new NewProfileRequestInfoDTO(email,profileType);


        String result= newProfileRequestInfoDTO.getEmail();

        assertEquals(result, newProfileRequestInfoDTO.getEmail());
    }
    @Test
    void getProfileType() {
        String email = "lino@isep.ipp.pt";
        String profileType="dev";
        NewProfileRequestInfoDTO newProfileRequestInfoDTO = new NewProfileRequestInfoDTO(email,profileType);


        String result= newProfileRequestInfoDTO.getProfileType();

        assertEquals(result, newProfileRequestInfoDTO.getProfileType());
    }

}
