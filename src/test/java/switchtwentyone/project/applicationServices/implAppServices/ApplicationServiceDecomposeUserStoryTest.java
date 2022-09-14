package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceDecomposeUS;
import switchtwentyone.project.dto.ChildUserStoryDTO;
import switchtwentyone.project.dto.USDTO;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;
import switchtwentyone.project.dto.mapper.UserStoryMapper;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceDecomposeUserStoryTest {

    @Mock
    private ProjectRepository projRepo;
    @Mock
    private UserStoryRepository usRepo;
    @Mock
    private ServiceDecomposeUS decomposeUSService;
    @InjectMocks
    ApplicationServiceDecomposeUserStory appService;

    @Test
    void decomposeUserStoryProjectNotFoundTest() {
        ProjectID pid = mock(ProjectID.class);
        double usid = 2;
        Optional<Project> empty = Optional.empty();
        UserStory parent = mock(UserStory.class);
        Optional<UserStory> opt = Optional.of(parent);
        when(usRepo.findByID(UserStoryID.ofDouble(usid))).thenReturn(opt);
        List<ChildUserStoryDTO> newUserStories = new ArrayList<>();
        ChildUserStoryDTO dto1 = new ChildUserStoryDTO();
        ChildUserStoryDTO dto2 = new ChildUserStoryDTO();
        dto1.detail = "ABC";
        dto1.statement = "DEF";
        dto2.detail = "GHI";
        dto2.statement = "JKL";
        newUserStories.add(dto1);
        newUserStories.add(dto2);

        Exception e = assertThrows(EntityNotFoundException.class, () -> appService.decomposeUserStory(
                usid,
                newUserStories, 9700));

        String expected = "Project not found";
        String result = e.getMessage();

        assertEquals(expected, result);
    }

    @Test
    void decomposeUserStoryParentUserStoryNotFoundTest() {

        double usid = 2;
        when(usRepo.findByID(any())).thenReturn(Optional.empty());

        List<ChildUserStoryDTO> newUserStories = new ArrayList<>();
        ChildUserStoryDTO dto1 = new ChildUserStoryDTO();
        ChildUserStoryDTO dto2 = new ChildUserStoryDTO();
        dto1.detail = "ABC";
        dto1.statement = "DEF";
        dto2.detail = "GHI";
        dto2.statement = "JKL";
        newUserStories.add(dto1);
        newUserStories.add(dto2);
        Exception e = assertThrows(EntityNotFoundException.class, () -> appService.decomposeUserStory(
                usid,
                newUserStories, 1));
        String expected = "User Story not found";
        String result = e.getMessage();

        assertEquals(expected, result);

    }


    @Test
    void decomposeUserStoryTestingRepositoryInvocations1() {

        double usid = 2;
        Project project = mock(Project.class);
        UserStory parent = mock(UserStory.class);

        when(usRepo.findByID(UserStoryID.ofDouble(usid))).thenReturn(Optional.of(parent));
        when(projRepo.findById(new ProjectID(1))).thenReturn(Optional.of(project));
        List<ChildUserStoryDTO> newUserStories = new ArrayList<>();
        ChildUserStoryDTO dto1 = new ChildUserStoryDTO();
        ChildUserStoryDTO dto2 = new ChildUserStoryDTO();
        dto1.detail = "ABC";
        dto1.statement = "DEF";
        dto2.detail = "GHI";
        dto2.statement = "JKL";
        newUserStories.add(dto1);
        newUserStories.add(dto2);
        List<UserStory> backlog = mock(List.class);
        when(usRepo.findAllUSByProjectID(new ProjectID(1))).thenReturn(backlog);
        List<UserStory> children = new ArrayList<>();
        UserStory childOne = mock(UserStory.class);
        UserStory childTwo = mock(UserStory.class);
        children.add(childOne);
        children.add(childTwo);
        when(decomposeUSService.generateChildren(eq(backlog), any(List.class), eq(new ProjectID(1)), eq(parent))).thenReturn(children);
        USDTO dto = new USDTO();
        MockedStatic<UserStoryMapper> mock = mockStatic(UserStoryMapper.class);
        when(UserStoryMapper.toDTO(parent)).thenReturn( dto);
        when(UserStoryMapper.toDTO(childOne)).thenReturn( dto);
        when(UserStoryMapper.toDTO(childTwo)).thenReturn( dto);

        appService.decomposeUserStory(usid, newUserStories, 1);
        mock.close();
        verify(usRepo, times(1)).saveAll(children);

    }
    @Test
    void decomposeUserStoryTestingRepositoryInvocations2() {
        ProjectID pid = new ProjectID(1);
        double usid = 2;
        Project project = mock(Project.class);
        UserStory parent = mock(UserStory.class);

        when(usRepo.findByID(UserStoryID.ofDouble(usid))).thenReturn(Optional.of(parent));
        when(projRepo.findById(pid)).thenReturn(Optional.of(project));
        List<ChildUserStoryDTO> newUserStories = new ArrayList<>();
        ChildUserStoryDTO dto1 = new ChildUserStoryDTO();
        ChildUserStoryDTO dto2 = new ChildUserStoryDTO();
        dto1.detail = "ABC";
        dto1.statement = "DEF";
        dto2.detail = "GHI";
        dto2.statement = "JKL";
        newUserStories.add(dto1);
        newUserStories.add(dto2);
        List<UserStory> backlog = new ArrayList<>();
        backlog.add(parent);
        when(usRepo.findAllUSByProjectID(pid)).thenReturn(backlog);
        List<UserStory> children = new ArrayList<>();
        UserStory childOne = mock(UserStory.class);
        UserStory childTwo = mock(UserStory.class);
        children.add(childOne);
        children.add(childTwo);
        when(decomposeUSService.generateChildren(eq(backlog), any(List.class), eq(pid), eq(parent))).thenReturn(children);
        USDTO dto = new USDTO();
        MockedStatic<UserStoryMapper> mock = mockStatic(UserStoryMapper.class);
        when(UserStoryMapper.toDTO(parent)).thenReturn( dto);
        when(UserStoryMapper.toDTO(childOne)).thenReturn( dto);
        when(UserStoryMapper.toDTO(childTwo)).thenReturn( dto);
        appService.decomposeUserStory(usid, newUserStories, 1);
        mock.close();

        verify(usRepo, times(1)).save(parent);
    }

    @Test
    void decomposeUserStoryTestingReturn() {
        ProjectID pid = new ProjectID(1);
        double usid = 2;
        Project project = mock(Project.class);
        UserStory parent = mock(UserStory.class);

        when(usRepo.findByID(UserStoryID.ofDouble(usid))).thenReturn(Optional.of(parent));
        when(projRepo.findById(new ProjectID(1))).thenReturn(Optional.of(project));
        List<ChildUserStoryDTO> newUserStories = new ArrayList<>();
        ChildUserStoryDTO dto1 = new ChildUserStoryDTO();
        ChildUserStoryDTO dto2 = new ChildUserStoryDTO();
        dto1.detail = "ABC";
        dto1.statement = "DEF";
        dto2.detail = "GHI";
        dto2.statement = "JKL";
        newUserStories.add(dto1);
        newUserStories.add(dto2);
        List<UserStory> backlog = mock(List.class);
        when(usRepo.findAllUSByProjectID(pid)).thenReturn(backlog);
        List<UserStory> children = new ArrayList<>();
        UserStory childOne = mock(UserStory.class);
        UserStory childTwo = mock(UserStory.class);
        children.add(childOne);
        children.add(childTwo);
        USDTO parentDTO = new USDTO();
        USDTO childOneDTO = new USDTO();
        USDTO childTwoDTO = new USDTO();
        List<USDTO> expected = new ArrayList<>();
        expected.add(parentDTO);
        expected.add(childOneDTO);
        expected.add(childTwoDTO);
        when(decomposeUSService.generateChildren(eq(backlog), any(List.class), eq(pid), eq(parent))).thenReturn(children);
        MockedStatic<UserStoryMapper> mock = mockStatic(UserStoryMapper.class);
        when(UserStoryMapper.toDTO(parent)).thenReturn( parentDTO);
        when(UserStoryMapper.toDTO(childOne)).thenReturn( childOneDTO);
        when(UserStoryMapper.toDTO(childTwo)).thenReturn( childTwoDTO);

        List <USDTO> result = appService.decomposeUserStory(usid, newUserStories, 1);
        mock.close();

      assertEquals(expected, result);
    }

}