package switchtwentyone.project.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ResourceDTOTest {

    @Test
    void testEqualsToItself() {
        ResourceDTO dto = new ResourceDTO();
        dto.projectID =1;
        dto.currency = "EUR";
        dto.resourceID = 1;
        dto.costPerHourValue =2.5;
        dto.startDate = LocalDate.of(2022,5,1);
        dto.endDate = LocalDate.of(2022,05,31);
        dto.percentageOfAllocation =0.5;
        dto.associatedRole = "Developer";
        dto.associatedAccount = "luis@isep.ipp.pt";

        boolean result = dto.equals(dto);

        assertTrue(result);
    }
    @Test
    void testEqualsEqualObject() {

        ResourceDTO dto = new ResourceDTO();
        dto.projectID =1;
        dto.currency = "EUR";
        dto.resourceID = 1;
        dto.costPerHourValue =2.5;
        dto.startDate = LocalDate.of(2022,5,1);
        dto.endDate = LocalDate.of(2022,05,31);
        dto.percentageOfAllocation =0.5;
        dto.associatedRole = "Developer";
        dto.associatedAccount = "luis@isep.ipp.pt";

        ResourceDTO dtoTwo = new ResourceDTO();
        dtoTwo.projectID =1;
        dtoTwo.currency = "EUR";
        dtoTwo.resourceID = 1;
        dtoTwo.costPerHourValue =2.5;
        dtoTwo.startDate = LocalDate.of(2022,5,1);
        dtoTwo.endDate = LocalDate.of(2022,05,31);
        dtoTwo.percentageOfAllocation =0.5;
        dtoTwo.associatedRole = "Developer";
        dtoTwo.associatedAccount = "luis@isep.ipp.pt";


        boolean result = dto.equals(dtoTwo);

        assertTrue(result);
    }

    @Test
    void ResourceDTOWithDifferentAtributes() {
        ResourceDTO dto = new ResourceDTO();
        dto.projectID =2;
        dto.currency = "USD";
        dto.resourceID = 1;
        dto.costPerHourValue =2.5;
        dto.startDate = LocalDate.of(2022,5,1);
        dto.endDate = LocalDate.of(2022,05,31);
        dto.percentageOfAllocation =0.5;
        dto.associatedRole = "Developer";
        dto.associatedAccount = "luis@isep.ipp.pt";

        ResourceDTO dtoTwo = new ResourceDTO();
        dtoTwo.projectID =1;
        dtoTwo.currency = "EUR";
        dtoTwo.resourceID = 1;
        dtoTwo.costPerHourValue =2.5;
        dtoTwo.startDate = LocalDate.of(2022,5,1);
        dtoTwo.endDate = LocalDate.of(2022,05,15);
        dtoTwo.percentageOfAllocation =0.8;
        dtoTwo.associatedRole = "Developer";
        dtoTwo.associatedAccount = "andre@isep.ipp.pt";

        boolean result = dto.equals(dtoTwo);

        assertFalse(result);
    }

    @Test
    void testEqualsNotNull() {
        ResourceDTO dto = new ResourceDTO();
        dto.projectID =2;
        dto.currency = "USD";
        dto.resourceID = 1;
        dto.costPerHourValue =2.5;
        dto.startDate = LocalDate.of(2022,5,1);
        dto.endDate = LocalDate.of(2022,05,31);
        dto.percentageOfAllocation =0.5;
        dto.associatedRole = "Developer";
        dto.associatedAccount = "luis@isep.ipp.pt";

        boolean result = dto.equals(null);

        assertFalse(result);
    }

    @Test
    void testEqualsDifferentObjects(){
        ResourceDTO dto = new ResourceDTO();
        dto.projectID =2;
        dto.currency = "USD";
        dto.resourceID = 1;
        dto.costPerHourValue =2.5;
        dto.startDate = LocalDate.of(2022,5,1);
        dto.endDate = LocalDate.of(2022,05,31);
        dto.percentageOfAllocation =0.5;
        dto.associatedRole = "Developer";
        dto.associatedAccount = "luis@isep.ipp.pt";
        ProjectTypologyDTO projectTypologyDTOOne = new ProjectTypologyDTO();
        projectTypologyDTOOne.name = "yupi";
        projectTypologyDTOOne.description = "yeah";

        assertNotEquals(dto,projectTypologyDTOOne);

    }
    @Test
    void testEqualsEqualHash() {

        ResourceDTO dto = new ResourceDTO();
        dto.projectID =1;
        dto.currency = "EUR";
        dto.resourceID = 1;
        dto.costPerHourValue =2.5;
        dto.startDate = LocalDate.of(2022,5,1);
        dto.endDate = LocalDate.of(2022,05,31);
        dto.percentageOfAllocation =0.5;
        dto.associatedRole = "Developer";
        dto.associatedAccount = "luis@isep.ipp.pt";

        ResourceDTO dtoTwo = new ResourceDTO();
        dtoTwo.projectID =1;
        dtoTwo.currency = "EUR";
        dtoTwo.resourceID = 1;
        dtoTwo.costPerHourValue =2.5;
        dtoTwo.startDate = LocalDate.of(2022,5,1);
        dtoTwo.endDate = LocalDate.of(2022,05,31);
        dtoTwo.percentageOfAllocation =0.5;
        dtoTwo.associatedRole = "Developer";
        dtoTwo.associatedAccount = "luis@isep.ipp.pt";


        assertEquals(dto.hashCode(),dtoTwo.hashCode());
    }

    @Test
    void testEqualsDifferentHash() {
        ResourceDTO dto = new ResourceDTO();
        dto.projectID =2;
        dto.currency = "USD";
        dto.resourceID = 1;
        dto.costPerHourValue =2.5;
        dto.startDate = LocalDate.of(2022,5,1);
        dto.endDate = LocalDate.of(2022,05,31);
        dto.percentageOfAllocation =0.5;
        dto.associatedRole = "Developer";
        dto.associatedAccount = "luis@isep.ipp.pt";

        ResourceDTO dtoTwo = new ResourceDTO();
        dtoTwo.projectID =1;
        dtoTwo.currency = "EUR";
        dtoTwo.resourceID = 1;
        dtoTwo.costPerHourValue =2.5;
        dtoTwo.startDate = LocalDate.of(2022,5,1);
        dtoTwo.endDate = LocalDate.of(2022,05,15);
        dtoTwo.percentageOfAllocation =0.8;
        dtoTwo.associatedRole = "Developer";
        dtoTwo.associatedAccount = "andre@isep.ipp.pt";


        assertNotEquals(dto.hashCode(),dtoTwo.hashCode());
    }
}