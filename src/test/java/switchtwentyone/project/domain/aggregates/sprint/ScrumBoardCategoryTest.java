package switchtwentyone.project.domain.aggregates.sprint;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.resource.Erole;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.resource.Role;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import static org.junit.jupiter.api.Assertions.*;

class ScrumBoardCategoryTest {

    @Test
    void testSameObjectIsEqual() {
        //Arrange
        ScrumBoardCategory cat = ScrumBoardCategory.toDo();

        //Act
        boolean result = cat.equals(cat);

        //Assert
        assertTrue(result);
    }

    @Test
    void testObjectsAreEqualSameCategory() {
        //Arrange
        ScrumBoardCategory cat = ScrumBoardCategory.inProgress();
        ScrumBoardCategory cat1 = ScrumBoardCategory.inProgress();

        //Act
        boolean result = cat.equals(cat1);

        //Assert
        assertTrue(result);
    }

    @Test
    void testObjectsAreNotEqualDifferentCategory() {
        //Arrange
        ScrumBoardCategory cat = ScrumBoardCategory.inProgress();
        ScrumBoardCategory cat1 = ScrumBoardCategory.done();

        //Act
        boolean result = cat.equals(cat1);

        //Assert
        assertFalse(result);
    }
    @Test
    void testNotEqualToDifferentObject() {
        //Arrange
        ScrumBoardCategory cat = ScrumBoardCategory.rejected();
        ResourceID testTwo = new ResourceID(1);

        //Act
        boolean result = cat.equals(testTwo);

        //Assert
        assertFalse(result);
    }

    @Test
    void testNotEqualToNull() {
        //Arrange
        ScrumBoardCategory cat = ScrumBoardCategory.codeReview();

        //Act
        boolean result = cat.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void testSameObjectHasSameHash() {
        //Arrange
        ScrumBoardCategory cat = ScrumBoardCategory.codeReview();
        ScrumBoardCategory cat1 = ScrumBoardCategory.codeReview();

        //Assert
        assertEquals(cat.hashCode(), cat1.hashCode());

    }

    @Test
    void testDifferentObjectsHaveDifferentHash() {
        //Arrange
        ScrumBoardCategory cat = ScrumBoardCategory.codeReview();
        ScrumBoardCategory cat1 = ScrumBoardCategory.done();
        //Assert
        assertNotEquals(cat.hashCode(), cat1.hashCode());
    }


    @Test
    void sameValueAsNull() {
        //Arrange
        ScrumBoardCategory cat = ScrumBoardCategory.codeReview();
        //Act
        boolean result = cat.sameValueAs(null);
        //Assert
        assertFalse(result);
    }

    @Test
    void sameValueAsSameCategory(){
        //Arrange
        ScrumBoardCategory cat = ScrumBoardCategory.codeReview();
        ScrumBoardCategory cat1 = ScrumBoardCategory.codeReview();
        //Act
        boolean result = cat.sameValueAs(cat1);
        //Assert
        assertTrue(result);
    }

    @Test
    void notSameValueAsDifferentCategory(){
        //Arrange
        ScrumBoardCategory cat = ScrumBoardCategory.codeReview();
        ScrumBoardCategory cat1 = ScrumBoardCategory.done();
        //Act
        boolean result = cat.sameValueAs(cat1);
        //Assert
        assertFalse(result);
    }

}