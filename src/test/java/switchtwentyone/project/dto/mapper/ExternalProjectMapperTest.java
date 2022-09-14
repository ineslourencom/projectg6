package switchtwentyone.project.dto.mapper;

import org.junit.jupiter.api.Test;
import switchtwentyone.project.dto.ProjectDTO;
import switchtwentyone.project.externaldto.ExternalProjectDTO;
import switchtwentyone.project.externaldto.assemblers.ExternalProjectMapper;

import static org.junit.jupiter.api.Assertions.*;

class ExternalProjectMapperTest {


    @Test
    void toDTOTest() {

        ExternalProjectDTO externalDTO = new ExternalProjectDTO();
        externalDTO.name ="Name";
        externalDTO.description = "Desc";
        externalDTO.id = "A123";
        externalDTO.status= "Planned";
        externalDTO.sprintDuration =2;
        externalDTO.plannedSprints = 3;
        externalDTO.budgetAmount=123;
        externalDTO.budgetCurrency= "EUR";
        externalDTO.businessSector ="IT";
        externalDTO.customerName ="PTH";
        externalDTO.customerVat ="12345";
        externalDTO.typology ="Fixed Cost";
        externalDTO.startDate ="01/03/2021" ;
        externalDTO.endDate ="31/07/2021";

        ProjectDTO expected = new ProjectDTO();
        expected.code = "EXT_A123";
        expected.name = "Name";
        expected.description = "Desc";



        ExternalProjectMapper mapper = new ExternalProjectMapper();


        ProjectDTO result = mapper.toDTO(externalDTO);

        assertEquals(expected, result);

    }
}