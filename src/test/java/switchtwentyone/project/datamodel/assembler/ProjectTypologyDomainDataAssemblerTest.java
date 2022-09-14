package switchtwentyone.project.datamodel.assembler;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyFactory;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.datamodel.ProjectTypologyJPA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectTypologyDomainDataAssemblerTest {

    @Mock
    ProjectTypologyFactory projectTypologyFactory;

    @InjectMocks
    ProjectTypologyDomainDataAssembler projectTypologyDomainDataAssembler;

    @Test
    void toData() {
        ProjectTypology projectTypologyOne = mock(ProjectTypology.class);
        ProjectTypology projectTypologyTwo = mock(ProjectTypology.class);
        when(projectTypologyOne.getProjectTypologyIDAsString()).thenReturn("Fixed Cost");
        when(projectTypologyOne.getDescriptionAsString()).thenReturn("example");
        when(projectTypologyTwo.getProjectTypologyIDAsString()).thenReturn("Fixed Cost");
        when(projectTypologyTwo.getDescriptionAsString()).thenReturn("example");

        ProjectTypologyJPA projectTypologyJPAOne = projectTypologyDomainDataAssembler.toData(projectTypologyOne);
        ProjectTypologyJPA projectTypologyJPATwo = projectTypologyDomainDataAssembler.toData(projectTypologyTwo);

        assertEquals(projectTypologyJPAOne, projectTypologyJPATwo);
    }


    @Test
    void toDomain() {

        ProjectTypologyJPA projectTypologyJPAOne = mock(ProjectTypologyJPA.class);
        ProjectTypologyJPA projectTypologyJPATwo = mock(ProjectTypologyJPA.class);
        ProjectTypologyID projectTypologyID = mock(ProjectTypologyID.class);
        when(projectTypologyJPAOne.getProjectTypologyID()).thenReturn(projectTypologyID);
        when(projectTypologyJPATwo.getProjectTypologyID()).thenReturn(projectTypologyID);
        when(projectTypologyJPAOne.getDescription()).thenReturn("Example");
        Describable descriptionOne = Text.write("Example");
        when(projectTypologyJPATwo.getDescription()).thenReturn("Example");
        Describable descriptionTwo = Text.write("Example");
        ProjectTypology projectTypologyOne = mock(ProjectTypology.class);
        ProjectTypology projectTypologyTwo = mock(ProjectTypology.class);
        when(projectTypologyFactory.createProjectTypology(projectTypologyID, descriptionOne)).thenReturn(projectTypologyOne);
        when(projectTypologyFactory.createProjectTypology(projectTypologyID, descriptionTwo)).thenReturn(projectTypologyTwo);

        ProjectTypology projectTypologyResultOne = projectTypologyDomainDataAssembler.toDomain(projectTypologyJPAOne);
        ProjectTypology projectTypologyResultTwo = projectTypologyDomainDataAssembler.toDomain(projectTypologyJPATwo);

        assertEquals(projectTypologyResultOne, projectTypologyResultTwo);
    }



}