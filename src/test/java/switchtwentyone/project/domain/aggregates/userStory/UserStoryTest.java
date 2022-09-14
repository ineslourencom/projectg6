package switchtwentyone.project.domain.aggregates.userStory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryTest {

    UserStory newUserStory1;
    UserStory newUserStory2;
    UserStory newUserStory3;
    UserStoryID usID1;
    UserStoryID usID2;
    UserStoryID usID3;
    PositiveNumber usNumber1;
    PositiveNumber usNumber2;
    PositiveNumber usNumber3;
    int usPriority1;
    int usPriority2;
    int usPriority3;
    Text statement1;
    Text statement2;
    Text statement3;
    Text detail;
    ProjectID projID1;


    @BeforeEach
    void arrange() {
        projID1 = new ProjectID(1);
        usNumber1 = PositiveNumber.of(1);
        usID1 = UserStoryID.ofProjIDAndUsNumber(projID1, usNumber1);
        statement1 = Text.write("This is statement 1");
        detail = Text.write("This is a detail");
        usPriority1 = 1;
        newUserStory1 = new UserStory(usID1, usNumber1, statement1, detail, usPriority1, projID1);

        newUserStory2 = new UserStory(usID1, usNumber1, statement1, detail, usPriority1, projID1);

        projID1 = new ProjectID(1);
        usNumber3 = PositiveNumber.of(3);
        usID3 = UserStoryID.ofProjIDAndUsNumber(projID1, usNumber3);
        statement3 = Text.write("This is statement 3");
        detail = Text.write("This is a detail");
        usPriority3 = 3;
        newUserStory3 = new UserStory(usID3, usNumber3, statement3, detail, usPriority3, projID1);

    }


    @Test
    void testIsUSEqual() {
        boolean result = newUserStory1.equals(newUserStory2);
        assertTrue(result);
        assertEquals(newUserStory1.hashCode(), newUserStory1.hashCode());
    }

    @Test
    void testIsUSDifferent() {
        boolean result = newUserStory1.equals(newUserStory3);
        assertFalse(result);
    }


    @Test
    void isCodeNotHigher() {
        boolean result = newUserStory1.isCodeHigher(1);
        assertFalse(result);
    }

    @Test
    void isCodeHigher() {
        boolean result = newUserStory1.isCodeHigher(2);
        assertTrue(result);
    }


    @Test
    void testUserStoryIsNotEqualToObjectOfDifferentClass() {
        boolean result = newUserStory2.equals(new Object());
        assertFalse(result);

    }

    @Test
    void testHasSameNumber() {
        boolean result = newUserStory2.hasNumber(1);
        assertTrue(result);

    }

    @Test
    void testHasDifferentNumber() {
        boolean result = newUserStory2.hasNumber(5);
        assertFalse(result);

    }

    @Test
    void testDefineParenthoodSuccess() {
        boolean result = newUserStory3.defineParenthood(newUserStory2);

        assertTrue(result);
        assertNotEquals(newUserStory3, newUserStory2);
    }


    @Test
    void testDefineParenthoodFailForRepetition() {
        newUserStory3.defineParenthood(newUserStory1);

        boolean result = newUserStory3.defineParenthood(newUserStory2);

        assertFalse(result);
    }

    @Test
    void testDefineParenthoodFailForEquality() {

        boolean result = newUserStory3.defineParenthood(newUserStory3);

        assertFalse(result);
        assertEquals(newUserStory3, newUserStory3);

    }

    @Test
    void testDefinePriority() {
        newUserStory2.setPriority(usPriority3);

        assertEquals(3, newUserStory2.getPriority());
    }

    @Test
    void testToString() {

        String expected = "UserStory: 3 Statement: This is statement 3 Priority: 3";

        String actual = newUserStory3.toString();

        assertEquals(expected, actual);
    }


    @Test
    void getStatement() {
        Text expected = Text.write("This is statement 1");
        Text result = newUserStory1.getStatement();

        assertEquals(expected, result);
    }


    @Test
    void getPriority() {
        int expected = 1;
        assertEquals(expected, newUserStory1.getPriority()); }

    @Test
    void getStoryNumber() {
        PositiveNumber expected = PositiveNumber.of(1);
        assertEquals(expected, newUserStory1.getStoryNumber());
    }


    @Test
    void hasProjectID() {
        //Act
        boolean result = newUserStory1.hasProjectID(projID1);

        //Assert
        assertTrue(result);
    }

    @Test
    void hasntProjectID() {
        //Act
        boolean result = newUserStory1.hasProjectID(new ProjectID(10));

        //Assert
        assertFalse(result);
    }

    @Test
    void getUsID() {
        //Act
        UserStoryID result = newUserStory1.getUsID();

        //Assert
        assertEquals(result, usID1);
    }

    @Test
    void testGetStoryNumber() {
        //Act
        PositiveNumber result = newUserStory1.getStoryNumber();

        //Assert
        assertEquals(result, usNumber1);
    }


    @Test
    void testGetStatement() {
        //Act
        Text result = newUserStory1.getStatement();

        //Assert
        assertEquals(result, statement1);
    }

    @Test
    void getDetail() {
        //Act
        Text result = newUserStory1.getDetail();

        //Assert
        assertEquals(result, detail);
    }


    @Test
    void getIsDecomposed() {
        //Act
        boolean result = newUserStory1.getIsDecomposed();

        //Assert
        assertFalse(result);
    }

    @Test
    void getParent() {
        //Act
        UserStoryID result = newUserStory1.getParent();

        //Assert
        assertNull(result);

    }

    @Test
    void testGetPriority() {
        //Act
        int result = newUserStory1.getPriority();

        //Assert
        assertEquals(result, usPriority1);

    }


    @Test
    void getProjectID() {
        //Act
        ProjectID result = newUserStory1.getProjectID();

        //Assert
        assertEquals(result, projID1);

    }

    @Test
    void hasIDTest() {
        ProjectID projID = new ProjectID(1);
        PositiveNumber usNumber = PositiveNumber.of(1);
        UserStoryID id = UserStoryID.ofProjIDAndUsNumber(projID, usNumber);

        boolean result = newUserStory1.hasID(id);

        assertTrue(result);

    }

    @Test
    void  doesNotHaveIDTest() {

        boolean result = newUserStory1.hasID(usID3);
        assertFalse(result);

    }

    @Test
    void  doesNotHaveIDWhenIdIsNullTest() {
        boolean result = newUserStory1.hasID(null);
        assertFalse(result);
    }
}