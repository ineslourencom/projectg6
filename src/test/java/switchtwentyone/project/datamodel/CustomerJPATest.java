package switchtwentyone.project.datamodel;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.customer.NIF;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;

import static org.junit.jupiter.api.Assertions.*;

class CustomerJPATest {

    @Test
    void getNIF() {
        //arrange
        NIF nif = NIF.of(233141626);
        CustomerID id = CustomerID.of(nif);
        String name= "Name";

        CustomerJPA jpa = new CustomerJPA(id, name);

        //act
        CustomerID result =  jpa.getNIF();

        //assert
        assertEquals(id, result);
    }

    @Test
    void getName() {
        //arrange
        NIF nif = NIF.of(233141626);
        CustomerID id = CustomerID.of(nif);
        String name= "Name";

        CustomerJPA jpa = new CustomerJPA(id, name);

        //act
        String result =  jpa.getName();

        //assert
        assertEquals(name, result);
    }

    @Test
    void setNIF() {
        //arrange
        NIF nif = NIF.of(233141626);
        CustomerID id = CustomerID.of(nif);
        String name= "Name";

        CustomerJPA jpa = new CustomerJPA(id, name);

        //act
        CustomerID result =  jpa.getNIF();

        //assert
        assertEquals(id, result);
    }

    @Test
    void setName() {
        //arrange
        NIF nif = NIF.of(233141626);
        CustomerID id = CustomerID.of(nif);
        String name= "Name";

        CustomerJPA jpa = new CustomerJPA(id, name);

        //act
        jpa.setName("Name2");
        String result =  jpa.getName();

        //assert
        assertEquals("Name2", result);
    }

    @Test
    void testSameJPAAreEqual() {
        //arrange
        NIF nif = NIF.of(233141626);
        CustomerID id = CustomerID.of(nif);
        String name= "Name";

        CustomerJPA jpa = new CustomerJPA(id, name);
        //Act
        boolean result = jpa.equals(jpa);

        //Assert
        assertTrue(result);
    }
    @Test
    void testSameJPAAreEqual_return() {
        //arrange
        NIF nif = NIF.of(233141626);
        CustomerID id = CustomerID.of(nif);
        String name= "Name";

        CustomerJPA jpa = new CustomerJPA(id, name);
        CustomerJPA jpa2 = new CustomerJPA(id, name);


        //Assert
        assertEquals(jpa, jpa2 );
    }

    @Test
    void testSameJPAHaveSameHashCode() {
        //arrange
        NIF nif = NIF.of(233141626);
        CustomerID id = CustomerID.of(nif);
        String name= "Name";

        CustomerJPA jpa = new CustomerJPA(id, name);
        CustomerJPA jpa2 = new CustomerJPA(id, name);

        //Assert
        assertEquals(jpa.hashCode(), jpa2.hashCode());
    }

    @Test
    void testJPANotEqualToNull() {
        //arrange
        NIF nif = NIF.of(233141626);
        CustomerID id = CustomerID.of(nif);
        String name= "Name";

        CustomerJPA jpa = new CustomerJPA(id, name);
        //act
        boolean result = jpa.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void testJPANotEqualToOtherObject() {
        //arrange
        NIF nif = NIF.of(233141626);
        CustomerID id = CustomerID.of(nif);
        String name= "Name";

        CustomerJPA jpa = new CustomerJPA(id, name);
        PositiveNumber number = PositiveNumber.of(1);

        //Act
        boolean result = jpa.equals(number);

        //Assert
        assertFalse(result);
    }

    @Test
    void testJPEqualToItself() {
        //arrange
        NIF nif = NIF.of(233141626);
        CustomerID id = CustomerID.of(nif);
        String name= "Name";

        CustomerJPA jpa = new CustomerJPA(id, name);

        //act
        boolean result = jpa.equals(jpa);
        //Assert
        assertTrue(result);
    }

    @Test
    void testDifferentJPAshaveDifferentHashcodes() {
        //arrange
        NIF nif = NIF.of(208622322);
        NIF nif2 = NIF.of(233141626);

        CustomerID id = CustomerID.of(nif);
        CustomerID id2 = CustomerID.of(nif2);

        String name= "Name";
        String name2 = "Name2";

        //act
        CustomerJPA jpa = new CustomerJPA(id, name);
        CustomerJPA jpa1 = new CustomerJPA(id2, name2);


        //Assert
        assertNotEquals(jpa.hashCode(), jpa1.hashCode());
    }

    @Test
    void testToString(){
        //arrange
        NIF nif = NIF.of(233141626);
        CustomerID id = CustomerID.of(nif);
        String name= "Name";

        CustomerJPA jpa = new CustomerJPA(id, name);

        String expected = "CustomerJPA(" +
                "NIF=" + id.toString() +
                ", name=" + "Name" +
                ')';
        //act
        String result = jpa.toString();

        assertEquals(expected, result);

    }
}