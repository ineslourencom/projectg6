package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.ScrumBoardCategory;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceUpdateUsStatus;
import switchtwentyone.project.dto.ChangeUsCategoryDTO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceUpdateUsStatusTest {

    @Mock
    ServiceUpdateUsStatus serviceUpdateUsStatus;
    @Mock
    SprintRepository sprintRepository;
    @Mock
    UserStoryRepository userStoryRepository;
    @InjectMocks
    ApplicationServiceUpdateUsStatus applicationServiceUpdateUsStatus;

    @Test
    void updateUsStatus() {

        int projectID=1;
        double usID=1.2;

        ProjectID projectIDOne = new ProjectID(projectID);

        Sprint sprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectIDOne)).thenReturn(Optional.of(sprint));

        UserStoryID userStoryID=UserStoryID.ofDouble(usID);

        ChangeUsCategoryDTO categoryDTO =mock(ChangeUsCategoryDTO.class);
        categoryDTO.category="In progress";

        ScrumBoardCategory scrumBoardCategory= ScrumBoardCategory.dbConverter("In progress");

        Sprint sprintUpdated=mock(Sprint.class);
        when(serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID,sprint,scrumBoardCategory)).thenReturn(sprintUpdated);


        boolean result=applicationServiceUpdateUsStatus.updateUsStatus(projectID,usID,categoryDTO);

        verify(sprintRepository,times(1)).saveSprint(sprintUpdated);

        assertTrue(result);
    }
    @Test
    void doesntUpdateUsStatus() {


        int projectID=1;
        double usID=1.2;

        ProjectID projectIDOne = new ProjectID(projectID);

        Sprint sprint = mock(Sprint.class);
        when(sprintRepository.findRunningSprintByProjectID(projectIDOne)).thenReturn(Optional.of(sprint));

        UserStoryID userStoryID=UserStoryID.ofDouble(usID);

        ChangeUsCategoryDTO categoryDTO =mock(ChangeUsCategoryDTO.class);
        categoryDTO.category="In progress";

        ScrumBoardCategory scrumBoardCategory= ScrumBoardCategory.dbConverter("In progress");

        Sprint sprintUpdated=mock(Sprint.class);
        when(serviceUpdateUsStatus.doesUsIDExistOnRunningSprintAndUpdateNewStatus(userStoryID,sprint,scrumBoardCategory)).thenThrow(new IllegalArgumentException());


        assertThrows(IllegalArgumentException.class, () -> applicationServiceUpdateUsStatus.updateUsStatus(projectID,usID,categoryDTO));
        verify(sprintRepository,times(0)).saveSprint(any());
    }

    @Test
    void doesntFindSprint() {


        int projectID=1;
        double usID=1.2;

        ProjectID projectIDOne = new ProjectID(projectID);


        when(sprintRepository.findRunningSprintByProjectID(projectIDOne)).thenReturn(Optional.empty());

        ChangeUsCategoryDTO categoryDTO =mock(ChangeUsCategoryDTO.class);
        categoryDTO.category="In progress";

        boolean result=applicationServiceUpdateUsStatus.updateUsStatus(projectID,usID,categoryDTO);

        verify(sprintRepository,times(0)).saveSprint(any());
        verify(serviceUpdateUsStatus,times(0)).doesUsIDExistOnRunningSprintAndUpdateNewStatus(any(),any(),any());
        assertFalse(result);
    }
}