package switchtwentyone.project.domain.aggregates.businesSector;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import static org.junit.jupiter.api.Assertions.*;

class BusinessTest {

    @Test
    void ensureCreationSucceeds() {
        CAE cae = CAE.of("12345");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        Business business = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));

        assertNotNull(business);
    }

    @Test
    void ensureIsEqualToItself() {
        CAE cae = CAE.of("12345");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);

        Business business = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));

        assertEquals(business, business);

    }

    @Test
    void ensureIsEqualToEqualObject() {
        CAE cae = CAE.of("12345");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);

        Business businessOne = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));
        Business businessTwo = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));

        assertEquals(businessOne, businessTwo);
    }

    @Test
    void ensureIsNotEqualToDifferentObject() {
        CAE cae = CAE.of("12345");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        CAE caeTwo = CAE.of("00000");
        BusinesSectorID businesSectorIDTwo = BusinesSectorID.of(caeTwo);

        Business businessOne = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));
        Business businessTwo = new Business(businesSectorIDTwo, NoNumberNoSymbolString.of("snakeoil"));

        assertNotEquals(businessOne, businessTwo);
    }

    @Test
    void ensureIsNotEqualToInstanceOfOtherClass() {
        CAE cae = CAE.of("12345");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);

        Business business = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));
        Object somethingElse = new Object();

        assertNotEquals(business, somethingElse);

    }

    @Test
    void ensureIsNotEqualToNull() {
        CAE cae = CAE.of("12345");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        Business business = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));

        assertNotEquals(null, business);
    }

    @Test
    void ensureSameValueIsTrueForObjectWithSameValues() {
        CAE cae = CAE.of("12345");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        Business businessOne = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));
        Business businessTwo = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));

        boolean result = businessOne.sameIdentityAs(businessTwo);

        assertTrue(result);

    }

    @Test
    void ensureSameValueIsFalseForObjectWithDifferentValues() {
        CAE cae = CAE.of("12345");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        CAE caeTwo = CAE.of("00000");
        BusinesSectorID businesSectorIDTwo = BusinesSectorID.of(caeTwo);

        Business businessOne = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));
        Business businessTwo = new Business(businesSectorIDTwo, NoNumberNoSymbolString.of("snakeoil"));

        boolean result = businessOne.sameIdentityAs(businessTwo);

        assertFalse(result);
    }

    @Test
    void ensureSameValueIsFalseForNullReference() {
        CAE cae = CAE.of("12345");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        Business business = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));

        boolean result = business.sameIdentityAs(null);

        assertFalse(result);
    }

    @Test
    void ensureHashCodeIsEqualForEqualObjects() {
        CAE cae = CAE.of("12345");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        Business businessOne = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));
        Business businessTwo = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));
        int hashOne = businessOne.hashCode();
        int hasTwo = businessTwo.hashCode();

        assertEquals(hashOne, hasTwo);

    }

    @Test
    void ensureHashCodeIsDifferentForDifferentObjects() {
        CAE cae = CAE.of("12345");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        CAE caeTwo = CAE.of("00000");
        BusinesSectorID businesSectorIDTwo = BusinesSectorID.of(caeTwo);
        Business businessOne = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));
        Business businessTwo = new Business(businesSectorIDTwo, NoNumberNoSymbolString.of("snakeoil"));
        int hashOne = businessOne.hashCode();
        int hasTwo = businessTwo.hashCode();

        assertNotEquals(hashOne, hasTwo);
    }


    @Test
    void testgetIDAsString() {
        //Arrange
        CAE cae = CAE.of("12345");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        Business businessOne = new Business(businesSectorID, NoNumberNoSymbolString.of("snakeoil"));

        //Act
        String businessOneString = businessOne.getIDAsString();

        //Assert
        boolean result = businessOneString.equalsIgnoreCase("12345");
        assertTrue(result);
    }


    @Test
    void getDescriptionAsString() {
        CAE cae = CAE.of("12345");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        String expected = "snakeoil";
        Business businessOne = new Business(businesSectorID, NoNumberNoSymbolString.of(expected));

        String result = businessOne.getDescriptionAsString();

        assertEquals(expected, result);
    }

    @Test
    void getID() {
        CAE cae = CAE.of("12345");
        BusinesSectorID businesSectorID = BusinesSectorID.of(cae);
        String name = "snakeoil";
        Business businessOne = new Business(businesSectorID, NoNumberNoSymbolString.of(name));

        BusinesSectorID result = businessOne.getID();

        assertEquals(result, businesSectorID);
    }
}