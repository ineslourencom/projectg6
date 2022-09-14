package switchtwentyone.project.domain.aggregates.resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testEqualsSameObject() {
        //Arrange
        Role test = new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of("Product Owner"));

        //Act
        boolean result = test.equals(test);

        //Assert
        assertTrue(result);

    }

    @Test
    void testEqualsObjectSameRoles() {
        //Arrange
        Role testOne = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        Role testTwo = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));

        //Act
        boolean result = testOne.equals(testTwo);

        //Assert
        assertTrue(result);
    }

    @Test
    void testEqualsObjectDifferentRoles() {
        //Arrange
        Role testOne = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        Role testTwo = new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of("Product Owner"));

        //Act
        boolean result = testOne.equals(testTwo);

        //Assert
        assertFalse(result);
    }
    @Test
    void testEqualsDifferentObjects() {
        //Arrange
        Role testOne = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        ResourceID testTwo = new ResourceID(1);

        //Act
        boolean result = testOne.equals(testTwo);

        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsNotNullObject() {
        //Arrange
        Role test = new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of("Product Owner"));

        //Act
        boolean result = test.equals(null);

        //Assert
        assertNotNull(result);
    }

    @Test
    void testHashCodeSameHash() {
        //Arrange
        Role testOne = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        Role testTwo = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));

        //Assert
        assertEquals(testOne.hashCode(),testTwo.hashCode());

    }

    @Test
    void testHashCodeDifferentHash() {
        //Arrange
        Role testOne = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        Role testTwo = new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of("Product Owner"));
        //Assert
        assertNotEquals(testOne.hashCode(),testTwo.hashCode());
    }


    @Test
    void sameValueAsNull() {
        //Arrange
        Role testOne = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        //Act
        boolean result = testOne.sameValueAs(null);
        //Assert
        assertFalse(result);
    }

    @Test
    void sameValueAsSameRole(){
        //Arrange
        Role testOne = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        Role testTwo = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        //Act
        boolean result = testOne.sameValueAs(testTwo);
        //Assert
        assertTrue(result);
    }

    @Test
    void sameValueAsDifferentRole(){
        //Arrange
        Role testOne = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        Role testTwo = new Role(Erole.PRODUCT_OWNER, NoNumberNoSymbolString.of("Product Owner"));
        //Act
        boolean result = testOne.sameValueAs(testTwo);
        //Assert
        assertFalse(result);
    }

    @Test
    void getRoleAsString() {
        Role testRole = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Developer"));
        String expected = "DEVELOPER";

        String result  = testRole.getRoleAsString();

        assertEquals(expected, result);
    }

    @Test
    void getDescriptionAsString() {
        Role testRole = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of("Team member -Developer"));
        String expected = "Team member -Developer";
        String result  = testRole.getDescriptionAsString();

        assertEquals(expected, result);

    }
}
