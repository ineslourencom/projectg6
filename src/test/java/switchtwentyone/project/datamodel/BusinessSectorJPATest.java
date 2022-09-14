package switchtwentyone.project.datamodel;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.CAE;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorJPATest {

    @Test
    void getBusinesSectorID() {
        //arrange
        CAE cae = CAE.of("82990");
        BusinesSectorID id = BusinesSectorID.of(cae);
        String name= "Name";

        BusinessSectorJPA jpa = new BusinessSectorJPA(id, name);

        //act
        BusinesSectorID result = jpa.getBusinesSectorID();

        //assert
        assertEquals(id, result);
    }

    @Test
    void getName() {
        //arrange
        CAE cae = CAE.of("82990");
        BusinesSectorID id = BusinesSectorID.of(cae);
        String name= "Name";

        BusinessSectorJPA jpa = new BusinessSectorJPA(id, name);

        //act
        String result = jpa.getName();

        //assert
        assertEquals(result, name);
    }

    @Test
    void setBusinesSectorID() {
        //arrange
        CAE cae = CAE.of("82990");
        CAE cae2 = CAE.of("82910");

        BusinesSectorID id = BusinesSectorID.of(cae);
        BusinesSectorID id2 = BusinesSectorID.of(cae);

        String name= "Name";

        BusinessSectorJPA jpa = new BusinessSectorJPA(id, name);

        //act
        jpa.setBusinesSectorID(id2);
        BusinesSectorID result = jpa.getBusinesSectorID();

        //assert
        assertEquals(id2, result);
    }

    @Test
    void setName() {
        //arrange
        CAE cae = CAE.of("82990");
        BusinesSectorID id = BusinesSectorID.of(cae);
        String name= "Name";
        String name2= "Name2";

        BusinessSectorJPA jpa = new BusinessSectorJPA(id, name);

        //act
        jpa.setName(name2);
        String result = jpa.getName();

        //assert
        assertEquals(result, name2);
    }

    @Test
    void testSameJPAAreEqual() {
        //arrange
        CAE cae = CAE.of("82990");
        BusinesSectorID id = BusinesSectorID.of(cae);
        String name= "Name";

        BusinessSectorJPA jpa = new BusinessSectorJPA(id, name);
        //Act
        boolean result = jpa.equals(jpa);

        //Assert
        assertTrue(result);
    }
    @Test
    void testSameJPAAreEqual_return() {
        //arrange
        CAE cae = CAE.of("82990");
        BusinesSectorID id = BusinesSectorID.of(cae);
        String name= "Name";

        BusinessSectorJPA jpa = new BusinessSectorJPA(id, name);
        BusinessSectorJPA jpa2 = new BusinessSectorJPA(id, name);


        //Assert
        assertEquals(jpa, jpa2 );
    }

    @Test
    void testSameJPAHaveSameHashCode() {
        //arrange
        CAE cae = CAE.of("82990");
        BusinesSectorID id = BusinesSectorID.of(cae);
        String name= "Name";

        BusinessSectorJPA jpa = new BusinessSectorJPA(id, name);
        BusinessSectorJPA jpa2 = new BusinessSectorJPA(id, name);

        //Assert
        assertEquals(jpa, jpa2 );
        //Assert
        assertEquals(jpa.hashCode(), jpa2.hashCode());
    }

    @Test
    void testJPANotEqualToNull() {
        //arrange
        CAE cae = CAE.of("82990");
        BusinesSectorID id = BusinesSectorID.of(cae);
        String name= "Name";

        BusinessSectorJPA jpa = new BusinessSectorJPA(id, name);
        //act
        boolean result = jpa.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void testJPANotEqualToOtherObject() {
        //arrange
        CAE cae = CAE.of("82990");
        BusinesSectorID id = BusinesSectorID.of(cae);
        String name= "Name";

        BusinessSectorJPA jpa = new BusinessSectorJPA(id, name);
        PositiveNumber number = PositiveNumber.of(1);

        //Act
        boolean result = jpa.equals(number);

        //Assert
        assertFalse(result);
    }

    @Test
    void testJPEqualToItself() {
        //arrange
        CAE cae = CAE.of("82990");
        BusinesSectorID id = BusinesSectorID.of(cae);
        String name= "Name";

        BusinessSectorJPA jpa = new BusinessSectorJPA(id, name);
        //act
        boolean result = jpa.equals(jpa);
        //Assert
        assertTrue(result);
    }

    @Test
    void testDifferentJPAshaveDifferentHashcodes() {
        //arrange
        CAE cae = CAE.of("82991");
        BusinesSectorID id = BusinesSectorID.of(cae);
        CAE cae2 = CAE.of("82990");
        BusinesSectorID id2 = BusinesSectorID.of(cae2);
        String name= "Name";
        String name2= "Name2";


        BusinessSectorJPA jpa = new BusinessSectorJPA(id, name);
        BusinessSectorJPA jpa1 = new BusinessSectorJPA(id2, name2);


        //Assert
        assertNotEquals(jpa.hashCode(), jpa1.hashCode());
    }

    @Test
    void testToString(){
        //arrange
        CAE cae = CAE.of("82990");
        BusinesSectorID id = BusinesSectorID.of(cae);
        String name= "Name";

        BusinessSectorJPA jpa = new BusinessSectorJPA(id, name);
        //act

        String expected = "BusinessSectorJPA(" +
                "businesSectorID=" + id.toString() +
                ", name=" + "Name" +
                ')';
        //act
        String result = jpa.toString();

        assertEquals(expected, result);

    }
}