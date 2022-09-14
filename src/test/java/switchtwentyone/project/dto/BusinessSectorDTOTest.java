package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorDTOTest {


    @Test
    void BusinessSectorDTOWithSameAtributesAreEqual() {
        BusinessSectorDTO businessSectorDTOOne = new BusinessSectorDTO();
        businessSectorDTOOne.code = "00000";
        businessSectorDTOOne.description = "SuperHomem";
        BusinessSectorDTO businessSectorDTOTwo = new BusinessSectorDTO();
        businessSectorDTOTwo.code = "00000";
        businessSectorDTOTwo.description = "SuperHomem";

        assertEquals(businessSectorDTOOne, businessSectorDTOTwo);
    }

    @Test
    void BusinessSectorDTOWithDifferentCodeAreDifferent() {

        BusinessSectorDTO businessSectorDTOOne = new BusinessSectorDTO();
        businessSectorDTOOne.code = "00001";
        businessSectorDTOOne.description = "SuperHomem";
        BusinessSectorDTO businessSectorDTOTwo = new BusinessSectorDTO();
        businessSectorDTOTwo.code = "00000";
        businessSectorDTOTwo.description = "SuperHomem";

        assertNotEquals(businessSectorDTOOne, businessSectorDTOTwo);
    }

    @Test
    void BusinessSectorDTOWithDifferentDescriptionAreDifferent() {

        BusinessSectorDTO businessSectorDTOOne = new BusinessSectorDTO();
        businessSectorDTOOne.code = "00000";
        businessSectorDTOOne.description = "SuperHomem";
        BusinessSectorDTO businessSectorDTOTwo = new BusinessSectorDTO();
        businessSectorDTOTwo.code = "00000";
        businessSectorDTOTwo.description = "Joker";

        assertNotEquals(businessSectorDTOOne, businessSectorDTOTwo);
    }

    @Test
    void BusinessSectorDTOWithSameAtributesHaveSameHashCode() {

        BusinessSectorDTO businessSectorDTOOne = new BusinessSectorDTO();
        businessSectorDTOOne.code = "00000";
        businessSectorDTOOne.description = "SuperHomem";
        BusinessSectorDTO businessSectorDTOTwo = new BusinessSectorDTO();
        businessSectorDTOTwo.code = "00000";
        businessSectorDTOTwo.description = "SuperHomem";

        assertEquals(businessSectorDTOOne.hashCode(), businessSectorDTOTwo.hashCode());
    }

    @Test
    void BusinessSectorWithDifferentAtributesHaveDifferentHashCode() {

        BusinessSectorDTO businessSectorDTOOne = new BusinessSectorDTO();
        businessSectorDTOOne.code = "00000";
        businessSectorDTOOne.description = "SuperHomem";
        BusinessSectorDTO businessSectorDTOTwo = new BusinessSectorDTO();
        businessSectorDTOTwo.code = "00001";
        businessSectorDTOTwo.description = "SuperHomem";

        assertNotEquals(businessSectorDTOOne.hashCode(), businessSectorDTOTwo.hashCode());
    }

    @Test
    void BusinessSectorDTONotEqualsWithDifferentObjects() {
        BusinessSectorDTO businessSectorDTOOne = new BusinessSectorDTO();
        businessSectorDTOOne.code = "00000";
        businessSectorDTOOne.description = "SuperHomem";
        int comparator = 7;


        assertNotEquals(businessSectorDTOOne, comparator);
    }

    @Test
    void BusinessSectorEqualsNotNull() {
        BusinessSectorDTO businessSectorDTOOne = new BusinessSectorDTO();
        businessSectorDTOOne.code = "00000";
        businessSectorDTOOne.description = "SuperHomem";

        assertNotEquals(businessSectorDTOOne, null);
    }


}