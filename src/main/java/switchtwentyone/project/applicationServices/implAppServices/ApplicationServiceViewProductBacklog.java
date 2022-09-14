package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import switchtwentyone.project.applicationServices.irepositories.irepositories.*;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceFindUS;
import switchtwentyone.project.dto.USShortDTO;
import switchtwentyone.project.dto.mapper.UserStoryMapper;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.UserStoryController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ApplicationServiceViewProductBacklog {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private UserStoryRepository userStoryRepository;
    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private ServiceFindUS serviceFindUS;


    /**
     * This Method generates the Project Backlog of a given Project.
     *
     * @param id    project identifier
     * @param email user identifier
     * @return backlog list of user stories ordered by priority
     */
    public List<USShortDTO> getProductBacklog(int id, String email) {
        ProjectID projectID = new ProjectID(id);
        Email userEmail = Email.of(email);
        List<UserStory> productBacklog;
        List<USShortDTO> userStoryShortDTOS = new ArrayList<>();

        if (isValidRequest(projectID, userEmail)) {
            productBacklog = aggregateProductBacklogData(projectID);
            for (int index = 0; index < productBacklog.size(); index++) {
                USShortDTO dto = UserStoryMapper.toShortDTO(productBacklog.get(index));
                dto.priority = index + 1;
                Link link = linkTo(methodOn(UserStoryController.class).getUserStoryById(dto.projID, dto.usID)).withRel("changePriority");
                Link linkDecompose = linkTo(methodOn(UserStoryController.class).getUserStoryById(dto.projID, dto.usID)).withRel("decomposeUS");
                dto.add(link);
                dto.add(linkDecompose);

                userStoryShortDTOS.add(dto);
            }
        }
        return userStoryShortDTOS;
    }

    /**
     * This method generates productBacklog of the object UserStory order by priority
     *
     * @param projectID project identifier
     * @return backlog list of user stories ordered by priority
     */
    protected List<UserStory> aggregateProductBacklogData(ProjectID projectID) {

        List<UserStory> usListA = findUSNonDecomposedByProjectID(projectID);
        List<UserStory> usListB = findUSinRunningSprintByProjectID(projectID);
        List<UserStory> usListC = findDoneUSByProjectID(projectID);
        List<UserStory> productBacklog = serviceFindUS.compileProductBacklog(usListA, usListB, usListC);
        serviceFindUS.orderListOfUSByPriority(productBacklog);
        return productBacklog;
    }


    /**
     * This method executes two validations.
     * -> the project exists
     * -> the user requesting this info has permission to do so i.e.:
     * .is an active resource in the project
     * OR      .is an Administrator
     * OR      .is a Director
     *
     * @param projectID project identifier
     * @param userEmail user requesting info identifier
     * @return true if validation successfull, false if otherwise
     */
    private boolean isValidRequest(ProjectID projectID, Email userEmail) {
        boolean valid = false;
        boolean existsAccount = false;
        boolean isDirector = false;
        boolean isAdministrator = false;
        AccountID accountID = AccountID.of(userEmail);
        boolean existsProject = projectRepository.existsById(projectID);
        boolean isActiveInProject = resourceRepository.existsByAssociatedAccountAndStartDateBeforeAndEndDateAfterAndProjectID(accountID, projectID, LocalDate.now());
        Optional<Account> userAccount = accountRepository.findByAccountID(accountID);
        if (userAccount.isPresent()) {
            existsAccount = true;
            isDirector = userAccount.get().existsProfile(ProfileID.ofProfileType("Director"));
            isAdministrator = userAccount.get().existsProfile(ProfileID.ofProfileType("Administrator"));
        }

        if (existsProject && existsAccount && (isActiveInProject || isDirector || isAdministrator)) {
            valid = true;
        }
        return valid;
    }

    /**
     * This method finds all non-decomposed User Stories in a project.
     *
     * @param projectID project identifier
     * @return a list of all non-decomposed User Stories in a project, an empty
     * list if none exist
     */
    private List<UserStory> findUSNonDecomposedByProjectID(ProjectID projectID) {
        return userStoryRepository.findAllByProjectIDNotDecomposed(projectID);
    }

    /**
     * This method finds all User Stories in the running sprint of a project.
     *
     * @param projectID project identifier
     * @return a list of all User Stories in the running sprint of a given project,
     * empty list if no running sprint exists
     */
    private List<UserStory> findUSinRunningSprintByProjectID(ProjectID projectID) {
        List<UserStoryID> usIDList = findUSIDinRunningSprintByProjectID(projectID);
        return createListOfUSFromListOfUSID(usIDList);
    }

    /**
     * This method finds all User Stories in the running sprint of a project.
     *
     * @param projectID project identifier
     * @return a list of UserStoryIDs of all User Stories in the running sprint
     * of a given project, an empty list if no running sprint exists
     */
    protected List<UserStoryID> findUSIDinRunningSprintByProjectID(ProjectID projectID) {
        Optional<Sprint> runningSprint = sprintRepository.findRunningSprintByProjectID(projectID);
        return serviceFindUS.findUSIDinSprint(runningSprint);
    }

    /**
     * This method finds all DONE User Stories of a project.
     *
     * @param projectID project identifier
     * @return a list of all DONE User Stories in a project, an empty
     * list if none exist
     */
    protected List<UserStory> findDoneUSByProjectID(ProjectID projectID) {
        List<UserStoryID> usIDList = findDoneUSIDByProjectID(projectID);
        return createListOfUSFromListOfUSID(usIDList);
    }

    /**
     * This method finds all DONE User Stories of a project.
     *
     * @param projectID project identifier
     * @return a list of UserStoryIDs of all DONE User Stories in a project, an
     * empty list if none exist
     */
    private List<UserStoryID> findDoneUSIDByProjectID(ProjectID projectID) {
        List<Sprint> sprintList = sprintRepository.findAllSprintsByProjectID(projectID);
        return serviceFindUS.findDoneUSIDinSprint(sprintList);
    }

    /**
     * This method creates a list of User Stories from the list of their
     * corresponding UserStoryIDs.
     *
     * @param userStoryIDs list of UserStory identifiers
     * @return a list of UserStory objects
     */
    protected List<UserStory> createListOfUSFromListOfUSID(List<UserStoryID> userStoryIDs) {
        List<UserStory> userStoryList = new ArrayList<>();
        for (UserStoryID usID : userStoryIDs) {
            Optional<UserStory> usOpt = userStoryRepository.findByID(usID);
            usOpt.ifPresent(userStoryList::add);
        }
        return userStoryList;
    }

}
