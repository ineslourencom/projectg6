package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddUSToSprintInfoDTOTest {

    @Test
    void ensureEqualsItself() {
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO(1.1, 1);

        assertEquals(dto, dto);
    }

    @Test
    void ensureDoesNotEqualDifferentObject() {
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO(1.2, 1);
        AddUSToSprintInfoDTO dtoTwo = new AddUSToSprintInfoDTO(1.1, 1);

        assertNotEquals(dto, dtoTwo);
    }

    @Test
    void ensureDoesNotEqualInstanceOfOtherClass() {
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO(1.1, 1);
        Object somethingElse = new Object();

        assertNotEquals(dto, somethingElse);
    }

    @Test
    void ensureDoesNotEqualNull() {
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO( 1.1, 1);

        assertNotEquals( null, dto);
    }

    @Test
    void ensureEqualObjectsHaveEqualHashCodes() {
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO( 1.1, 1);
        AddUSToSprintInfoDTO dtoTwo = new AddUSToSprintInfoDTO( 1.1, 1);
        int hashOne = dto.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertEquals(hashOne, hashTwo);
    }

    @Test
    void ensureDifferentObjectsHaveDifferentHashCodes() {
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO( 1.2, 1);
        AddUSToSprintInfoDTO dtoTwo = new AddUSToSprintInfoDTO( 1.1, 1);
        int hashOne = dto.hashCode();
        int hashTwo = dtoTwo.hashCode();

        assertNotEquals(hashOne, hashTwo);
    }


    @Test
    void getUSID(){
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO( 1.1, 1);
        double projID = dto.getUsID();
        assertEquals(1.1, projID);
    }

    @Test
    void getEffort(){
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO( 1.1, 1);
        int projID = dto.getEffort();
        assertEquals(1, projID);
    }


    @Test
    void NewUSInfoDTOWithSameAttributesAreEqual(){
        //Act
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO( 1.1, 1);
        AddUSToSprintInfoDTO dtoTwo = new AddUSToSprintInfoDTO( 1.1, 1);

        //Assert
        assertEquals(dto, dtoTwo);
    }



    @Test
    void setUSID(){
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO( 1.1, 1);
        dto.setUsID(1.2);
        double actual = dto.getUsID();
        assertEquals(1.2, actual);
    }

    @Test
    void setEffort(){
        AddUSToSprintInfoDTO dto = new AddUSToSprintInfoDTO( 1.1, 1);
        dto.setEffort(2);
        int actual = dto.getEffort();
        assertEquals(2, actual);
    }

}