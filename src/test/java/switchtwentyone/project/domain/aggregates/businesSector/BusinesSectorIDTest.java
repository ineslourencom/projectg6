package switchtwentyone.project.domain.aggregates.businesSector;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import static org.junit.jupiter.api.Assertions.*;

class BusinesSectorIDTest {


    @Test
    void BusinessSectorIDWithSameIDAreEquals() {
        CAE cae = CAE.of("00000");

        BusinesSectorID businesSectorIDOne = BusinesSectorID.of(cae);
        BusinesSectorID businesSectorIDTwo = BusinesSectorID.of(cae);

        assertEquals(businesSectorIDOne, businesSectorIDTwo);
    }

    @Test
    void BusinessSectorIDWithDifferentIDAreEquals() {
        CAE cae = CAE.of("00000");
        CAE caeTwo = CAE.of("00001");

        BusinesSectorID businesSectorIDOne = BusinesSectorID.of(cae);
        BusinesSectorID businesSectorIDTwo = BusinesSectorID.of(caeTwo);

        assertNotEquals(businesSectorIDOne, businesSectorIDTwo);
    }

    @Test
    void BusinessSectorIDEqualToItself() {
        CAE cae = CAE.of("00000");

        BusinesSectorID businesSectorIDOne = BusinesSectorID.of(cae);

        assertEquals(businesSectorIDOne, businesSectorIDOne);
    }

    @Test
    void BusinessSectorIDWithSameIDHaveSameHashCode() {
        CAE cae = CAE.of("00000");

        BusinesSectorID businesSectorIDOne = BusinesSectorID.of(cae);
        BusinesSectorID businesSectorIDTwo = BusinesSectorID.of(cae);

        assertEquals(businesSectorIDOne.hashCode(), businesSectorIDTwo.hashCode());
    }

    @Test
    void BusinessSectorIDWithDifferentIDHaveDifferentHashCode() {
        CAE cae = CAE.of("00000");
        CAE caeTwo = CAE.of("00001");
        BusinesSectorID businesSectorIDOne = BusinesSectorID.of(cae);
        BusinesSectorID businesSectorIDTwo = BusinesSectorID.of(caeTwo);

        assertNotEquals(businesSectorIDOne.hashCode(), businesSectorIDTwo.hashCode());
    }

    @Test
    void BusinessSectorIDNotEqualsWithDifferentObjects() {
        CAE cae = CAE.of("00000");

        BusinesSectorID businesSectorIDOne = BusinesSectorID.of(cae);

        assertNotEquals(businesSectorIDOne, cae);
    }

    @Test
    void BusinessSectorEqualsNotNull() {
        CAE cae = CAE.of("00000");

        BusinesSectorID businesSectorIDOne = BusinesSectorID.of(cae);

        assertNotEquals(businesSectorIDOne, null);
    }

    @Test
    void BusinessSectorSameIdentity() {
        CAE cae = CAE.of("00000");

        BusinesSectorID businesSectorIDOne = BusinesSectorID.of(cae);
        BusinesSectorID businesSectorIDTwo = BusinesSectorID.of(cae);

        boolean result = businesSectorIDOne.sameValueAs(businesSectorIDTwo);

        assertTrue(result);
    }


    @Test
    void getCAEAsString() {
        //Arrange
        String SCAE = "00000";
        CAE cae = CAE.of(SCAE);
        BusinesSectorID businesSectorIDOne = BusinesSectorID.of(cae);

        //Act
        String result = businesSectorIDOne.getBusinessSectorIDAsSting();

        //Assert
        assertEquals(result, SCAE);
    }
}