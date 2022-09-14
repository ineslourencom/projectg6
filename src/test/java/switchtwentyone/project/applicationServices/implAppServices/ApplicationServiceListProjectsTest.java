package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentyone.project.applicationServices.irepositories.iwebrepositories.ProjectWebRepository;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.Erole;
import switchtwentyone.project.domain.aggregates.resource.Resource;
import switchtwentyone.project.domain.aggregates.resource.Role;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.ProjectDTO;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ResourceRepository;
import switchtwentyone.project.externaldto.assemblers.ExternalProjectMapper;
import switchtwentyone.project.externaldto.ExternalProjectDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceListProjectsTest {

    @Mock
    private ResourceRepository resourceRepository;
    @Mock
    private ProjectRepository projRepo;
    @Mock
    private ProjectWebRepository webRepository;
    @Mock
    private ExternalProjectMapper mapper;
    @InjectMocks
    private ApplicationServiceListProjects appServcListAllocProj;

    @Test
    void getListOfAllocatedProjects() {
        Resource r1 = mock(Resource.class);
        Resource r2 = mock(Resource.class);
        Resource r3 = mock(Resource.class);
        List resources = new ArrayList<>();
        resources.add(r1);
        resources.add(r2);
        resources.add(r3);
        String role = "DEVELOPER";
        Email e = Email.of("salome@isep.ipp.pt");
        Role r = new Role(Erole.valueOf(role),
                NoNumberNoSymbolString.of(Erole.valueOf(role).getDescription()));
        Project p1 = mock(Project.class);
        Project p2 = mock(Project.class);
        Project p3 = mock(Project.class);

        ProjectID id1 = mock(ProjectID.class);
        ProjectID id2 = mock(ProjectID.class);
        ProjectID id3 = mock(ProjectID.class);
        when(r1.getProjectID()).thenReturn(id1);
        when(r2.getProjectID()).thenReturn(id2);
        when(r3.getProjectID()).thenReturn(id3);
        when(resourceRepository.findAllActiveResourcesByEmailAndRole(e, r, LocalDate.now())).thenReturn(resources);
        when(projRepo.findById(id1)).thenReturn(Optional.of(p1));
        when(projRepo.findById(id2)).thenReturn(Optional.of(p2));
        when(projRepo.findById(id3)).thenReturn(Optional.of(p3));
        List<ProjectDTO> expected = new ArrayList<>();
        ProjectDTO dto1 = new ProjectDTO();
        ProjectDTO dto2 = new ProjectDTO();
        ProjectDTO dto3 = new ProjectDTO();
        expected.add(dto1);
        expected.add(dto2);
        expected.add(dto3);
        String name1 = "Pineapple";
        String name2 = "Mango";
        String name3 = "Coconut";
        String description1 = "Sour";
        String description2 = "Sweet";
        String description3 = "Salty";
        String iid1 = "1";
        String iid2 = "2";
        String iid3 = "3";
        dto1.name = name1;
        dto2.name = name2;
        dto3.name = name3;
        dto1.description = description1;
        dto2.description = description2;
        dto3.description = description3;
        dto1.code = iid1;
        dto2.code = iid2;
        dto3.code = iid3;

        when(p1.getNameAsString()).thenReturn(name1);
        when(p2.getNameAsString()).thenReturn(name2);
        when(p3.getNameAsString()).thenReturn(name3);

        when(p1.getDescriptionAsString()).thenReturn(description1);
        when(p2.getDescriptionAsString()).thenReturn(description2);
        when(p3.getDescriptionAsString()).thenReturn(description3);

        when(p1.getIDAsInt()).thenReturn(1);
        when(p2.getIDAsInt()).thenReturn(2);
        when(p3.getIDAsInt()).thenReturn(3);

        List<ProjectDTO> result = appServcListAllocProj.getListOfAllocatedProjects("salome@isep.ipp.pt", "DEVELOPER");

        assertEquals(expected, result);
    }
//TODO: these tests are not properly isolated
    @Test
    void getAllProjectsTest() {
        List<Project> projects = new ArrayList<>();
        Project p1 = mock(Project.class);
        Project p2 = mock(Project.class);
        Project p3 = mock(Project.class);
        projects.add(p1);
        projects.add(p2);
        projects.add(p3);
        when(projRepo.findAll()).thenReturn(projects);
        List<ProjectDTO> expected = new ArrayList<>();
        ProjectDTO dto1 = new ProjectDTO();
        ProjectDTO dto2 = new ProjectDTO();
        ProjectDTO dto3 = new ProjectDTO();
        expected.add(dto1);
        expected.add(dto2);
        expected.add(dto3);
        String name1 = "Pineapple";
        String name2 = "Mango";
        String name3 = "Coconut";
        String description1 = "Sour";
        String description2 = "Sweet";
        String description3 = "Salty";
        String iid1 = "1";
        String iid2 = "2";
        String iid3 = "3";
        dto1.name = name1;
        dto2.name = name2;
        dto3.name = name3;
        dto1.description = description1;
        dto2.description = description2;
        dto3.description = description3;
        dto1.code = iid1;
        dto2.code = iid2;
        dto3.code = iid3;

        when(p1.getNameAsString()).thenReturn(name1);
        when(p2.getNameAsString()).thenReturn(name2);
        when(p3.getNameAsString()).thenReturn(name3);

        when(p1.getDescriptionAsString()).thenReturn(description1);
        when(p2.getDescriptionAsString()).thenReturn(description2);
        when(p3.getDescriptionAsString()).thenReturn(description3);

        when(p1.getIDAsInt()).thenReturn(1);
        when(p2.getIDAsInt()).thenReturn(2);
        when(p3.getIDAsInt()).thenReturn(3);

        ExternalProjectDTO exDTOOne = mock(ExternalProjectDTO.class);
        ExternalProjectDTO exDTOTwo = mock(ExternalProjectDTO.class);
        List<ExternalProjectDTO> list = List.of(exDTOOne,exDTOTwo );
        when(webRepository.getProjects()).thenReturn(list);
        ProjectDTO dtoFour = mock(ProjectDTO.class);
        ProjectDTO dtoFive = mock(ProjectDTO.class);
        when(mapper.toDTO(exDTOOne)).thenReturn(dtoFour);
        when(mapper.toDTO(exDTOTwo)).thenReturn(dtoFive);
        expected.add(dtoFour);
        expected.add(dtoFive);
        List<ProjectDTO> result = appServcListAllocProj.getAllProjects("andre@isep.ipp.pt");


        assertEquals(expected, result);
    }

    @Test
    void getAllProjects() {
        List<Project> projects = new ArrayList<>();
        Project p1 = mock(Project.class);
        Project p2 = mock(Project.class);
        Project p3 = mock(Project.class);
        projects.add(p1);
        projects.add(p2);
        projects.add(p3);
        when(projRepo.findAll()).thenReturn(projects);
        List<ProjectDTO> expected = new ArrayList<>();
        ProjectDTO dto1 = new ProjectDTO();
        ProjectDTO dto2 = new ProjectDTO();
        ProjectDTO dto3 = new ProjectDTO();
        expected.add(dto1);
        expected.add(dto2);
        expected.add(dto3);
        String name1 = "Pineapple";
        String name2 = "Mango";
        String name3 = "Coconut";
        String description1 = "Sour";
        String description2 = "Sweet";
        String description3 = "Salty";
        String iid1 = "1";
        String iid2 = "2";
        String iid3 = "3";
        dto1.name = name1;
        dto2.name = name2;
        dto3.name = name3;
        dto1.description = description1;
        dto2.description = description2;
        dto3.description = description3;
        dto1.code = iid1;
        dto2.code = iid2;
        dto3.code = iid3;

        when(p1.getNameAsString()).thenReturn(name1);
        when(p2.getNameAsString()).thenReturn(name2);
        when(p3.getNameAsString()).thenReturn(name3);

        when(p1.getDescriptionAsString()).thenReturn(description1);
        when(p2.getDescriptionAsString()).thenReturn(description2);
        when(p3.getDescriptionAsString()).thenReturn(description3);

        when(p1.getIDAsInt()).thenReturn(1);
        when(p2.getIDAsInt()).thenReturn(2);
        when(p3.getIDAsInt()).thenReturn(3);
        List<ProjectDTO> result = appServcListAllocProj.getAllProjects();

        assertEquals(expected, result);
    }
}