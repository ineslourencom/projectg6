package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Link;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceChangePriorityOfUserStories;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.dto.USWithPriorityDTO;
import switchtwentyone.project.dto.mapper.UserStoryMapper;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.UserStoryController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@SpringBootTest
class ApplicationServiceChangeUserStoryPriorityTest {
    @Autowired
    ApplicationServiceChangeUserStoryPriority applicationServiceChangeUserStoryPriority;

    @MockBean
    ProjectRepository projectRepository;

    @MockBean
    ApplicationServiceViewProductBacklog applicationServiceViewProductBacklog;

    @MockBean
    UserStoryRepository userStoryRepository;

    @MockBean
    ServiceChangePriorityOfUserStories serviceChangePriorityOfUserStories;


    @Test
    void changePriorityOfUserStory() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);
        List<UserStory> productBacklog = new ArrayList<>();
        productBacklog.add(userStoryOne);
        productBacklog.add(userStoryTwo);

        when(applicationServiceViewProductBacklog.aggregateProductBacklogData(projectID)).thenReturn(productBacklog);

        double usID = 1.001;
        UserStoryID userStoryID = UserStoryID.ofDouble(usID);

        when(userStoryRepository.findByID(userStoryID)).thenReturn(Optional.of(userStoryOne));

        int newPriority = 2;

        when(serviceChangePriorityOfUserStories.findTrueNewPriority(productBacklog, newPriority)).thenReturn(1);

        UserStory userStoryThree = mock(UserStory.class);
        List<UserStory> allUserStories = new ArrayList<>();
        allUserStories.add(userStoryOne);
        allUserStories.add(userStoryTwo);
        allUserStories.add(userStoryThree);

        when(userStoryRepository.findAllUSByProjectID(projectID)).thenReturn(allUserStories);

        List<UserStory> newProductBacklog = new ArrayList<>();
        newProductBacklog.add(userStoryOne);
        newProductBacklog.add(userStoryTwo);
        newProductBacklog.add(userStoryThree);

        when(serviceChangePriorityOfUserStories.changePriorityOfUserStories(allUserStories, userStoryOne, 1)).thenReturn(newProductBacklog);

        PositiveNumber storyNumber = PositiveNumber.of(1);
        when(userStoryOne.getStoryNumber()).thenReturn(storyNumber);

        Text statment = Text.write("statment");
        when(userStoryOne.getStatement()).thenReturn(statment);

        when(userStoryOne.getProjectID()).thenReturn(projectID);
        when(userStoryOne.getUsID()).thenReturn(userStoryID);

        USWithPriorityDTO expected = UserStoryMapper.toPriorityDTO(userStoryOne);
        Link link = linkTo(methodOn(UserStoryController.class).getUserStoryById(projID, usID)).withSelfRel();
        expected.add(link);


        USWithPriorityDTO result = applicationServiceChangeUserStoryPriority.changePriorityOfUserStory(projID, usID, 2);

        assertEquals(expected, result);
    }

    @Test
    void changePriorityOfUserStory_ProjectDoesntExist() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(false);

        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);
        List<UserStory> productBacklog = new ArrayList<>();
        productBacklog.add(userStoryOne);
        productBacklog.add(userStoryTwo);

        when(applicationServiceViewProductBacklog.aggregateProductBacklogData(projectID)).thenReturn(productBacklog);

        double usID = 1.001;
        UserStoryID userStoryID = UserStoryID.ofDouble(usID);

        when(userStoryRepository.findByID(userStoryID)).thenReturn(Optional.of(userStoryOne));

        int newPriority = 2;

        when(serviceChangePriorityOfUserStories.findTrueNewPriority(productBacklog, newPriority)).thenReturn(1);

        UserStory userStoryThree = mock(UserStory.class);
        List<UserStory> allUserStories = new ArrayList<>();
        allUserStories.add(userStoryOne);
        allUserStories.add(userStoryTwo);
        allUserStories.add(userStoryThree);

        when(userStoryRepository.findAllUSByProjectID(projectID)).thenReturn(allUserStories);

        List<UserStory> newProductBacklog = new ArrayList<>();
        newProductBacklog.add(userStoryOne);
        newProductBacklog.add(userStoryTwo);
        newProductBacklog.add(userStoryThree);

        when(serviceChangePriorityOfUserStories.changePriorityOfUserStories(allUserStories, userStoryOne, 1)).thenReturn(newProductBacklog);

        PositiveNumber storyNumber = PositiveNumber.of(1);
        when(userStoryOne.getStoryNumber()).thenReturn(storyNumber);

        Text statment = Text.write("statment");
        when(userStoryOne.getStatement()).thenReturn(statment);

        when(userStoryOne.getProjectID()).thenReturn(projectID);
        when(userStoryOne.getUsID()).thenReturn(userStoryID);

        USWithPriorityDTO expected = UserStoryMapper.toPriorityDTO(userStoryOne);

        assertThrows(IllegalArgumentException.class,
                () -> applicationServiceChangeUserStoryPriority.changePriorityOfUserStory(projID, usID, 2));

    }


    @Test
    void changePriorityOfUserStory_UserStoryIsNotInProductBacklog() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);
        UserStory another = mock(UserStory.class);
        List<UserStory> productBacklog = new ArrayList<>();
        productBacklog.add(userStoryOne);
        productBacklog.add(userStoryTwo);

        when(applicationServiceViewProductBacklog.aggregateProductBacklogData(projectID)).thenReturn(productBacklog);

        double usID = 1.001;
        UserStoryID userStoryID = UserStoryID.ofDouble(usID);

        when(userStoryRepository.findByID(userStoryID)).thenReturn(Optional.of(another));

        int newPriority = 2;

        when(serviceChangePriorityOfUserStories.findTrueNewPriority(productBacklog, newPriority)).thenReturn(1);

        UserStory userStoryThree = mock(UserStory.class);
        List<UserStory> allUserStories = new ArrayList<>();
        allUserStories.add(userStoryOne);
        allUserStories.add(userStoryTwo);
        allUserStories.add(userStoryThree);

        when(userStoryRepository.findAllUSByProjectID(projectID)).thenReturn(allUserStories);

        List<UserStory> newProductBacklog = new ArrayList<>();
        newProductBacklog.add(userStoryOne);
        newProductBacklog.add(userStoryTwo);
        newProductBacklog.add(userStoryThree);

        when(serviceChangePriorityOfUserStories.changePriorityOfUserStories(allUserStories, userStoryOne, 1)).thenReturn(newProductBacklog);

        PositiveNumber storyNumber = PositiveNumber.of(1);
        when(userStoryOne.getStoryNumber()).thenReturn(storyNumber);

        Text statment = Text.write("statment");
        when(userStoryOne.getStatement()).thenReturn(statment);

        when(userStoryOne.getProjectID()).thenReturn(projectID);
        when(userStoryOne.getUsID()).thenReturn(userStoryID);

        USWithPriorityDTO expected = UserStoryMapper.toPriorityDTO(userStoryOne);

        assertThrows(IllegalArgumentException.class,
                () -> applicationServiceChangeUserStoryPriority.changePriorityOfUserStory(projID, usID, 2));

    }

    @Test
    void changePriorityOfUserStory_UserStoryDoesntExist() {
        int projID = 1;
        ProjectID projectID = new ProjectID(projID);
        when(projectRepository.existsById(projectID)).thenReturn(true);

        UserStory userStoryOne = mock(UserStory.class);
        UserStory userStoryTwo = mock(UserStory.class);
        List<UserStory> productBacklog = new ArrayList<>();
        productBacklog.add(userStoryOne);
        productBacklog.add(userStoryTwo);

        when(applicationServiceViewProductBacklog.aggregateProductBacklogData(projectID)).thenReturn(productBacklog);

        double usID = 1.001;
        UserStoryID userStoryID = UserStoryID.ofDouble(usID);

        when(userStoryRepository.findByID(userStoryID)).thenReturn(Optional.empty());

        int newPriority = 2;

        when(serviceChangePriorityOfUserStories.findTrueNewPriority(productBacklog, newPriority)).thenReturn(1);

        UserStory userStoryThree = mock(UserStory.class);
        List<UserStory> allUserStories = new ArrayList<>();
        allUserStories.add(userStoryOne);
        allUserStories.add(userStoryTwo);
        allUserStories.add(userStoryThree);

        when(userStoryRepository.findAllUSByProjectID(projectID)).thenReturn(allUserStories);

        List<UserStory> newProductBacklog = new ArrayList<>();
        newProductBacklog.add(userStoryOne);
        newProductBacklog.add(userStoryTwo);
        newProductBacklog.add(userStoryThree);

        when(serviceChangePriorityOfUserStories.changePriorityOfUserStories(allUserStories, userStoryOne, 1)).thenReturn(newProductBacklog);

        PositiveNumber storyNumber = PositiveNumber.of(1);
        when(userStoryOne.getStoryNumber()).thenReturn(storyNumber);

        Text statment = Text.write("statment");
        when(userStoryOne.getStatement()).thenReturn(statment);

        when(userStoryOne.getProjectID()).thenReturn(projectID);
        when(userStoryOne.getUsID()).thenReturn(userStoryID);

        USWithPriorityDTO expected = UserStoryMapper.toPriorityDTO(userStoryOne);

        assertThrows(IllegalArgumentException.class,
                () -> applicationServiceChangeUserStoryPriority.changePriorityOfUserStory(projID, usID, 2));

    }
}