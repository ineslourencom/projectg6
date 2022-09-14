package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.valueObjects.Text;

import static org.junit.jupiter.api.Assertions.*;

class NewUserStoryDomainDTOTest {

    @Test
    void testEqualsToItSelf() {

        NewUserStoryDomainDTO dto= new NewUserStoryDomainDTO();
        boolean result= dto.equals(dto);
        assertTrue(result);
    }

    @Test
    void testEqualsEqualObject() {

        NewUserStoryDomainDTO dto= new NewUserStoryDomainDTO();
        NewUserStoryDomainDTO dtoTwo= new NewUserStoryDomainDTO();

        boolean result= dto.equals(dtoTwo);
        assertTrue(result);
    }

    @Test
    void newUserStoryWithDiferentStatement() {

        NewUserStoryDomainDTO dto= new NewUserStoryDomainDTO();
        dto.statement = Text.write("ola");
        dto.detail = Text.write("detail");

        NewUserStoryDomainDTO dtoTwo= new NewUserStoryDomainDTO();
        dtoTwo.statement = Text.write("hello");
        dtoTwo.detail = Text.write("detail");

        boolean result= dto.equals(dtoTwo);
        assertFalse(result);
    }

    @Test
    void newUserStoryWithDiferentDetail() {

        NewUserStoryDomainDTO dto= new NewUserStoryDomainDTO();
        dto.statement = Text.write("ola");
        dto.detail = Text.write("detalhe");

        NewUserStoryDomainDTO dtoTwo= new NewUserStoryDomainDTO();
        dtoTwo.statement = Text.write("ola");
        dtoTwo.detail = Text.write("detail");

        boolean result= dto.equals(dtoTwo);
        assertFalse(result);
    }

    @Test
    void testEqualsObjectOfDiferentClasses() {

        NewUserStoryDomainDTO dto= new NewUserStoryDomainDTO();
        dto.statement = Text.write("ola");
        dto.detail = Text.write("detail");

        AccountID accountID = AccountID.of(Email.of("luis@isep.ipp.pt"));


        boolean result= dto.equals(accountID);
        assertFalse(result);
    }

    @Test
    void testEqualsNotNull() {

        NewUserStoryDomainDTO dto= new NewUserStoryDomainDTO();
        dto.statement = Text.write("ola");
        dto.detail = Text.write("detalhe");

        boolean result= dto.equals(null);
        assertFalse(result);
    }

    @Test
    void testEqualsEqualHas(){
        NewUserStoryDomainDTO dto= new NewUserStoryDomainDTO();
        NewUserStoryDomainDTO dtoTwo= new NewUserStoryDomainDTO();

        assertEquals(dto.hashCode(),dtoTwo.hashCode());
    }

    @Test
    void testEqualsDifferentHash() {

        NewUserStoryDomainDTO dto= new NewUserStoryDomainDTO();
        dto.statement = Text.write("ola");
        dto.detail = Text.write("detalhes");

        NewUserStoryDomainDTO dtoTwo= new NewUserStoryDomainDTO();
        dtoTwo.statement = Text.write("hello");
        dtoTwo.detail = Text.write("detail");

        assertNotEquals(dto.hashCode(),dtoTwo.hashCode());
    }

}