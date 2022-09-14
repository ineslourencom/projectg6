package switchtwentyone.project.interfaceAdapters.controllers.implControllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentyone.project.applicationServices.implAppServices.*;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.dto.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    @Mock
    ApplicationServiceCreateProject appServCreateProj;

    @Mock
    private ApplicationServiceListProjects serviceListProjects;

    @Mock
    private ApplicationServiceProject service;


    @InjectMocks
    ProjectController projectController;


    @Test
    void createProjectSuccess() {

        ProjectInfoNecessaryToCreateDTO projectInfo = new ProjectInfoNecessaryToCreateDTO();
        projectInfo.name = "Banana";
        projectInfo.description = "Split";
        projectInfo.startDate = LocalDate.of(2002, 8, 24);
        projectInfo.endDate = LocalDate.of(2002, 10, 24);
        projectInfo.sprintDuration = 2;
        projectInfo.budget = 50000;
        projectInfo.CustomerID = 257715347;
        projectInfo.ProjectTypologyID = "project typology";
        projectInfo.BusinessSectorID = "00000";


        ProjectInfoReturnedWhenCreatedDTO projectInfoReturnedWhenCreatedDTO = mock(ProjectInfoReturnedWhenCreatedDTO.class);
        projectInfoReturnedWhenCreatedDTO.projectID = 23;

        when(appServCreateProj.createProject(projectInfo)).thenReturn(projectInfoReturnedWhenCreatedDTO);

        Link link = linkTo(methodOn(ProjectController.class).getProjectDetails(projectInfoReturnedWhenCreatedDTO.projectID)).withSelfRel();
        projectInfoReturnedWhenCreatedDTO.add(link);

        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.CREATED).body(projectInfoReturnedWhenCreatedDTO);

        ResponseEntity<Object> result = projectController.createProject(projectInfo);

        assertEquals(expected, result);
    }


    @Test
    void createProjectUnsuccess() {

        ProjectInfoNecessaryToCreateDTO info = mock(ProjectInfoNecessaryToCreateDTO.class);
        when(appServCreateProj.createProject(info)).thenThrow(new IllegalArgumentException("Banana"));
        BusinessErrorMessage msg = new BusinessErrorMessage("Banana", BusinessErrorMessage.UNPROCESSABLE_ENTITY);
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(msg);

        ResponseEntity<Object> result = projectController.createProject(info);

        assertEquals(expected, result);
    }


    @Test
    void getProjectDetails() {

        ProjectInfoDTO dto = new ProjectInfoDTO();
        when(service.getProjectDetails(1)).thenReturn(Optional.of(dto));
        ResponseEntity<Object> expected = new ResponseEntity<>(dto, HttpStatus.OK);

        ResponseEntity<Object> result = projectController.getProjectDetails(1);
        assertEquals(expected, result);

    }

    @Test
    void getProjectDetailsNotFound() {


        when(service.getProjectDetails(1)).thenReturn(Optional.empty());
        ResponseEntity<Object> expected = new ResponseEntity<>("Project not found", HttpStatus.NOT_FOUND);

        ResponseEntity<Object> result = projectController.getProjectDetails(1);
        assertEquals(expected, result);

    }

    @Test
    void getProjectDetailsBadRequest() {
        String exceptionMessage = "Test exception massage";

        when(service.getProjectDetails(1)).thenThrow(new IllegalArgumentException(exceptionMessage));
        ResponseEntity<Object> expected = new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);

        ResponseEntity<Object> result = projectController.getProjectDetails(1);
        assertEquals(expected, result);

    }

    @Test
    void editProject() {

        ProjectInfoDTO dto = new ProjectInfoDTO();
        ProjectInfoDTO response = new ProjectInfoDTO();
        response.code = 1;
        when(service.editProject(1, dto)).thenReturn(Optional.of(response));
        ResponseEntity<Object> expected = new ResponseEntity<>(response, HttpStatus.OK);
        ResponseEntity<Object> result = projectController.editProject(1, dto);
        assertEquals(expected, result);
    }

    @Test
    void editProjectNotFound() {
        ProjectInfoDTO dto = new ProjectInfoDTO();
        when(service.editProject(1, dto)).thenReturn(Optional.empty());
        ResponseEntity<Object> expected = new ResponseEntity<>("Project not found", HttpStatus.NOT_FOUND);
        ResponseEntity<Object> result = projectController.editProject(1, dto);
        assertEquals(expected, result);

    }

    @Test
    void editProjectBadRequest() {
        String exceptionMessage = "Test exception massage";
        ProjectInfoDTO dto = new ProjectInfoDTO();
        ProjectInfoDTO response = new ProjectInfoDTO();
        response.code = 1;
        when(service.editProject(1, dto)).thenThrow(new IllegalArgumentException(exceptionMessage));
        ResponseEntity<Object> expected = new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
        ;
        ResponseEntity<Object> result = projectController.editProject(1, dto);
        assertEquals(expected, result);

    }


    @Test
    void getListOfProjectsByRoleTest() {

        List<ProjectDTO> projList = new ArrayList<>();
        ProjectDTO dto = mock(ProjectDTO.class);
        projList.add(dto);
        ResponseEntity expected = new ResponseEntity<>(projList, HttpStatus.OK);
        when(serviceListProjects.getListOfAllocatedProjects("salome.rp@hotmail.com", "PROJECT_MANAGER")).thenReturn(projList);

        ResponseEntity result = projectController.getListOfProjectsByEmailAndRole("salome.rp@hotmail.com", "PROJECT_MANAGER");


        assertEquals(expected, result);

    }

    @Test
    void getListOfProjectsBadEmail() {

        String massage = "Exception massage";
        BusinessErrorMessage msg = new BusinessErrorMessage(massage, BusinessErrorMessage.UNPROCESSABLE_ENTITY);
        ResponseEntity expected = new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);
        when(serviceListProjects.getListOfAllocatedProjects("salome.rp@hotmail.com", "PROJECT_MANAGER")).thenThrow(new IllegalArgumentException(massage));

        ResponseEntity result = projectController.getListOfProjectsByEmailAndRole("salome.rp@hotmail.com", "PROJECT_MANAGER");

        assertEquals(expected.getStatusCode(), result.getStatusCode());

    }

    @Test
    void noProjectsFoundForEmail() {

        List<ProjectDTO> list = new ArrayList<>();
        when(serviceListProjects.getListOfAllocatedProjects("salome.rp@hotmail.com", "PROJECT_MANAGER")).thenReturn(list);

        ResponseEntity expected = new ResponseEntity<>(list, HttpStatus.NOT_FOUND);

        ResponseEntity result = projectController.getListOfProjectsByEmailAndRole("salome.rp@hotmail.com", "PROJECT_MANAGER");

        assertEquals(expected.getStatusCode(), result.getStatusCode());

    }


    @Test
    void getListOfProjectsWithAuth() {
        List<ProjectDTO> projList = new ArrayList<>();
        ProjectDTO dtoOne = mock(ProjectDTO.class);
        ProjectDTO dtoTwo = mock(ProjectDTO.class);
        ProjectDTO dtoThree = mock(ProjectDTO.class);
        projList.add(dtoOne);
        projList.add(dtoTwo);
        projList.add(dtoThree);
        when(serviceListProjects.getAllProjects("andre@isep.ipp.pt")).thenReturn(projList);
        ResponseEntity<Object> expected = new ResponseEntity<>(projList, HttpStatus.OK);
        ResponseEntity<Object> result = projectController.getListOfAllProjects("andre@isep.ipp.pt");
        assertEquals(expected, result);
    }

    @Test
    void getListOfProjectsBadRequestWithAuth() {
        String massage = "Exception massage";
        when(serviceListProjects.getAllProjects("andre@isep.ipp.pt")).thenThrow(new IllegalArgumentException(massage));
        BusinessErrorMessage msg = new BusinessErrorMessage(massage, BusinessErrorMessage.UNPROCESSABLE_ENTITY);

        ResponseEntity<Object> expected = new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);

        ResponseEntity<Object> result = projectController.getListOfAllProjects("andre@isep.ipp.pt");

        assertEquals(expected.getStatusCode(), result.getStatusCode());

    }

    @Test
    void getListOfProjectsNotFoundWithAuth() {
        List<ProjectDTO> projList = new ArrayList<>();
        when(serviceListProjects.getAllProjects("andre@isep.ipp.pt")).thenReturn(projList);
        ResponseEntity<Object> expected = new ResponseEntity<>(projList, HttpStatus.NOT_FOUND);
        ResponseEntity<Object> result = projectController.getListOfAllProjects("andre@isep.ipp.pt");
        assertEquals(expected, result);
    }

    @Test
    void getListOfProjects() {
        List<ProjectDTO> projList = new ArrayList<>();
        ProjectDTO dtoOne = mock(ProjectDTO.class);
        ProjectDTO dtoTwo = mock(ProjectDTO.class);
        ProjectDTO dtoThree = mock(ProjectDTO.class);
        projList.add(dtoOne);
        projList.add(dtoTwo);
        projList.add(dtoThree);
        when(serviceListProjects.getAllProjects()).thenReturn(projList);
        ResponseEntity<Object> expected = new ResponseEntity<>(projList, HttpStatus.OK);
        ResponseEntity<Object> result = projectController.getListOfAllProjects();
        assertEquals(expected, result);
    }

    @Test
    void getListOfProjectsBadRequest() {
        String massage = "Exception massage";
        when(serviceListProjects.getAllProjects()).thenThrow(new IllegalArgumentException(massage));
        BusinessErrorMessage msg = new BusinessErrorMessage(massage, BusinessErrorMessage.UNPROCESSABLE_ENTITY);

        ResponseEntity<Object> expected = new ResponseEntity<>(msg, HttpStatus.UNPROCESSABLE_ENTITY);

        ResponseEntity<Object> result = projectController.getListOfAllProjects();

        assertEquals(expected.getStatusCode(), result.getStatusCode());

    }


    @Test
    void getListOfProjectsByUser() {
        Email email = Email.of("joao@isep.ipp.pt");
        List<ProjectDTO> listProjects = new ArrayList<>();
        when(serviceListProjects.getListOfAllocatedProjects(email)).thenReturn(listProjects);

        ResponseEntity expected = new ResponseEntity(listProjects, HttpStatus.OK);

        ResponseEntity result = projectController.getListOfProjectsByUser("joao@isep.ipp.pt");

        assertEquals(expected, result);


    }

    @Test
    void getListOfProjectsByUser_Failure() {
        Email email = Email.of("joao@isep.ipp.pt");
        List<ProjectDTO> listProjects = new ArrayList<>();
        when(serviceListProjects.getListOfAllocatedProjects(email)).thenThrow(new IllegalArgumentException("Banana"));

        ResponseEntity expected = new ResponseEntity("Banana", HttpStatus.BAD_REQUEST);

        ResponseEntity result = projectController.getListOfProjectsByUser("joao@isep.ipp.pt");

        assertEquals(expected, result);


    }
}