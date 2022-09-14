
package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyFactory;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.ProjectTypologyDTO;
import switchtwentyone.project.dto.mapper.ProjectTypologyMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectTypologyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ApplicationServiceCreateProjectTypologyTest {

    @Mock
    ProjectTypologyFactory projectTypologyFactory;
    @Mock
    ProjectTypologyRepository projectTypologyRepository;
    @InjectMocks
    ApplicationServiceCreateProjectTypology applicationServiceCreateProjectTypology;

    @Test
    void createAndSaveProjectTypology() {

        String stringName = "Fixed Cost";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(stringName);
        MockedStatic<NoNumberNoSymbolString> nameClasse = mockStatic(NoNumberNoSymbolString.class);
        nameClasse.when(() -> NoNumberNoSymbolString.of(stringName)).thenReturn(name);

        String stringDescription = "Description";
        Describable description = Text.write(stringDescription);
        MockedStatic<Text> descriptionClass = mockStatic(Text.class);
        descriptionClass.when(() -> Text.write(stringDescription)).thenReturn(description);

        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(name);
        MockedStatic<ProjectTypologyID> projectTypologyIDMockedStatic = mockStatic(ProjectTypologyID.class);
        projectTypologyIDMockedStatic.when(() -> ProjectTypologyID.of(name)).thenReturn(projectTypologyID);


        ProjectTypology projectTypology = mock(ProjectTypology.class);
        when(projectTypologyFactory.createProjectTypology(projectTypologyID, description)).thenReturn(projectTypology);
        ProjectTypology projectTypologySaved = mock(ProjectTypology.class);
        when(projectTypologyRepository.saveProjectTypology(projectTypology)).thenReturn(projectTypologySaved);
        ProjectTypologyDTO expected = ProjectTypologyMapper.toSingleDTO(projectTypologySaved);

        ProjectTypologyDTO result = applicationServiceCreateProjectTypology.createAndSaveProjectTypology(stringName,stringDescription);

        nameClasse.close();
        descriptionClass.close();
        projectTypologyIDMockedStatic.close();

        assertEquals(expected, result);
    }


    @Test
    void createAndSaveProjectTypology_Failure() {

        String stringName = "Fixed Cost";
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(stringName);
        MockedStatic<NoNumberNoSymbolString> nameClasse = mockStatic(NoNumberNoSymbolString.class);
        nameClasse.when(() -> NoNumberNoSymbolString.of(stringName)).thenReturn(name);

        String stringDescription = "Description";
        Describable description = Text.write(stringDescription);
        MockedStatic<Text> descriptionClass = mockStatic(Text.class);
        descriptionClass.when(() -> Text.write(stringDescription)).thenReturn(description);

        ProjectTypologyID projectTypologyID = ProjectTypologyID.of(name);
        MockedStatic<ProjectTypologyID> projectTypologyIDMockedStatic = mockStatic(ProjectTypologyID.class);
        projectTypologyIDMockedStatic.when(() -> ProjectTypologyID.of(name)).thenReturn(projectTypologyID);

        ProjectTypology projectTypology = mock(ProjectTypology.class);
        Optional<ProjectTypology> optionalProjectTypology = Optional.of(projectTypology);
        when(projectTypologyRepository.findProjectTypologyByID(projectTypologyID)).thenReturn(optionalProjectTypology);

        assertThrows(IllegalArgumentException.class, () -> applicationServiceCreateProjectTypology.createAndSaveProjectTypology(stringName,stringDescription));

        nameClasse.close();
        descriptionClass.close();
        projectTypologyIDMockedStatic.close();

    }
}

