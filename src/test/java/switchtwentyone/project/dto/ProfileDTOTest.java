package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.account.*;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProfileDTOTest {


    @Test
    void ensureEqualsItself() {
        ProfileDTO dto = new ProfileDTO("dev","developer");

        assertEquals(dto, dto);
    }
    @Test
    void Equals_Test_SameObject() {
        String profileID = "dev";
        String description = "developer";

        ProfileDTO profileDTO = new ProfileDTO(profileID, description);

        assertEquals(profileDTO, profileDTO);
        assertEquals(profileDTO.hashCode(), profileDTO.hashCode());
    }

    @Test
    void Equals_Test_EqualObject() {
        String profileID = "dev";
        String description = "developer";

        ProfileDTO profileDTOOne = new ProfileDTO(profileID, description);
        ProfileDTO profileDTOTwo = new ProfileDTO(profileID, description);

        assertEquals(profileDTOOne, profileDTOTwo);
    }

    @Test
    void Equals_Test_DifferentObjectWithDifferentProfileType() {
        String profileIDOne = "dev";
        String profileIDTwo = "director";
        String description = "developer";

        ProfileDTO profileDTOOne = new ProfileDTO(profileIDOne, description);
        ProfileDTO profileDTOTwo = new ProfileDTO(profileIDTwo, description);

        assertNotEquals(profileDTOOne, profileDTOTwo);
    }

    @Test
    void Equals_Test_DifferentObjectWithDifferentDescription() {
        String profileIDOne = "dev";
        String descriptionOne = "developer";
        String descriptionTwo = "PO";

        ProfileDTO profileDTOOne = new ProfileDTO(profileIDOne, descriptionOne);
        ProfileDTO profileDTOTwo = new ProfileDTO(profileIDOne, descriptionTwo);

        assertNotEquals(profileDTOOne, profileDTOTwo);
    }

    @Test
    void Equals_Test_DifferentObjectWithDifferentID() {
        String profileIDOne = "dev";
        String profileIDTwo = "user";
        String description = "developer";

        ProfileDTO profileDTOOne = new ProfileDTO(profileIDOne, description);
        ProfileDTO profileDTOTwo = new ProfileDTO(profileIDTwo, description);

        assertNotEquals(profileDTOOne, profileDTOTwo);
    }

    @Test
    void Equals_Test_DifferentTypesOfObject() {
        AccountCreatable accountFactory = new AccountFactory();
        ProfileType profType = ProfileType.of("dev");
        String profileID = "dev";
        String profileType = "dev";
        String description = "developer";
        Email newEmail = Email.of("marta@isep.ipp.pt");
        AccountID newAccountID = AccountID.of(newEmail);
        Password newPass = Password.of("teste123", 1);
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of("Lino");
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of("Dev");
        Photo photo = Photo.of(":)");
        ProfileID profileIDObject = new ProfileID(profType);
        ProfileDTO profileDTO = new ProfileDTO(profileID, description);
        Account newAccount = accountFactory.createAccount(newAccountID, newEmail, name, function, photo, newPass, profileIDObject);

        boolean result = profileDTO.equals(newAccount);

        assertFalse(result);
    }

    @Test
    void Equals_Test_Null() {
        ProfileType profType = ProfileType.of("dev");
        String profileID = "dev";
        String description = "developer";

        ProfileDTO profileDTO = new ProfileDTO(profileID, description);

        assertNotEquals(null, profileDTO);
    }

    @Test
    void profileDTO_Test_SameAttributesHaveSameHashCode() {
        String profileID = "dev";
        String description = "developer";
        ProfileDTO profileDTOOne = new ProfileDTO(profileID, description);
        ProfileDTO profileDTOTwo = new ProfileDTO(profileID, description);

        int resultOne = profileDTOOne.hashCode();
        int resultTwo = profileDTOTwo.hashCode();

        assertEquals(resultOne, resultTwo);
    }


    @Test
    void profileDTO_Test_DifferentDescriptionHaveDifferentHashCode() {
        String profileID = "dev";
        String descriptionOne = "developer";
        String descriptionTwo = "Product Owner";
        ProfileDTO profileDTOOne = new ProfileDTO(profileID, descriptionOne);
        ProfileDTO profileDTOTwo = new ProfileDTO(profileID, descriptionTwo);

        int resultOne = profileDTOOne.hashCode();
        int resultTwo = profileDTOTwo.hashCode();

        assertNotEquals(resultOne, resultTwo);
    }

    @Test
    void profileDTO_Test_DifferentIDHaveDifferentHashCode() {
        String profileIDOne = "dev";
        String profileIDTwo = "PO";
        String description = "developer";
        ProfileDTO profileDTOOne = new ProfileDTO(profileIDOne, description);
        ProfileDTO profileDTOTwo = new ProfileDTO(profileIDTwo, description);

        int resultOne = profileDTOOne.hashCode();
        int resultTwo = profileDTOTwo.hashCode();

        assertNotEquals(resultOne, resultTwo);
    }



}