package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.dto.NewAccountInfoDTO;

import static org.junit.jupiter.api.Assertions.*;

class NewAccountInfoDTOTest {

    @Test
    void ensureEqualsItself() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer","1234","1234");

        assertEquals(dto, dto);
    }


    @Test
    void NewAccountInfoDTOWithSameAttributesAreEqual() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        NewAccountInfoDTO dto2 = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");

        assertEquals(dto, dto2);

    }

    @Test
    void NewAccountDTOWIthDifferentEmailAreDifferent() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        NewAccountInfoDTO dto2 = new NewAccountInfoDTO("joao@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");

        assertNotEquals(dto, dto2);

    }

    @Test
    void NewAccountDTOWIthDifferentPasswordAreDifferent() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        NewAccountInfoDTO dto2 = new NewAccountInfoDTO("joao@isep.ipp.pt", "Lino", "Engineer", "134", "1234");

        assertNotEquals(dto, dto2);

    }

    @Test
    void NewAccountDTOWIthDifferentNameAreDifferent() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        NewAccountInfoDTO dto2 = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino1", "Engineer", "1234", "1234");

        assertNotEquals(dto, dto2);

    }

    @Test
    void NewAccountDTOWIthDifferentFunctionAreDifferent() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino1", "Engineer2", "1234", "1234");
        NewAccountInfoDTO dto2 = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino1", "Engineer", "1234", "1234");

        assertNotEquals(dto, dto2);

    }


    @Test
    void NewAccountDTOWIthDifferentPhotoAreDifferent() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino1", "Engineer2", "12345", "12345");
        NewAccountInfoDTO dto2 = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino1", "Engineer2", "12345", "1234");

        assertNotEquals(dto, dto2);

    }

    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        Object somethingElse = new Object();

        assertNotEquals(dto,somethingElse);
    }

    @Test
    void ensureDoesNotEqualNull() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");

        assertNotEquals(null,dto);
    }

    @Test
    void ensureEqualObjectsHaveDifferentHashCodes() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        NewAccountInfoDTO dto2 = new NewAccountInfoDTO("joao@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        int hashOne = dto.hashCode();
        int hashTwo = dto2.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }

    @Test
    void getEmail() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        String email = dto.getEmail();
        assertEquals("lino@isep.ipp.pt",email);
    }

    @Test
    void getName() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        String name = dto.getName();
        assertEquals("Lino",name);
    }

    @Test
    void getFunction() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        String function = dto.getFunction();
        assertEquals("Engineer",function);
    }

    @Test
    void getPassword() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        String password = dto.getPassword();
        assertEquals("1234",password);
    }

    @Test
    void getPhoto() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        String photo = dto.getPhoto();
        assertEquals("1234",photo);
    }

    @Test
    void setPhoto() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        dto.setPhoto("12");
        String photo = dto.getPhoto();
        assertEquals("12",photo);
    }

    @Test
    void setName() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        dto.setName("Lino2");
        String name = dto.getName();
        assertEquals("Lino2",name);
    }

    @Test
    void setEmail() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        dto.setEmail("lino@isep.ipp.pt");
        String Email = dto.getEmail();
        assertEquals("lino@isep.ipp.pt",Email);
    }

    @Test
    void setFunction() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        dto.setFunction("Anything");
        String Function = dto.getFunction();
        assertEquals("Anything",Function);
    }

    @Test
    void setPassword() {
        NewAccountInfoDTO dto = new NewAccountInfoDTO("lino@isep.ipp.pt", "Lino", "Engineer", "1234", "1234");
        dto.setPassword("123");
        String Password = dto.getPassword();
        assertEquals("123",Password);
    }


}