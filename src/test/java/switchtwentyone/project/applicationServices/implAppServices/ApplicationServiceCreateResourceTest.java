package switchtwentyone.project.applicationServices.implAppServices;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.*;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.services.ServiceValidateResource;
import switchtwentyone.project.domain.services.SprintValidationService;
import switchtwentyone.project.domain.valueObjects.LimitedPercentage;
import switchtwentyone.project.domain.valueObjects.Monetary;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.dto.NewProductOwnerDTO;
import switchtwentyone.project.dto.NewResourceDTO;
import switchtwentyone.project.dto.NewScrumMasterDTO;
import switchtwentyone.project.dto.ResourceDTO;
import switchtwentyone.project.dto.mapper.ResourceMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ResourceRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class ApplicationServiceCreateResourceTest {
    @MockBean
    ProjectRepository projectRepository;

    @MockBean
    SprintRepository sprintRepository;

    @MockBean
    ResourceRepository resourceRepository;

    @MockBean
    AccountRepository accountRepository;
    @MockBean
    ResourceMapper mapper;
    @MockBean
    ServiceValidateResource resourceService;
    @MockBean
    SprintValidationService sprintService;

    @MockBean
    ResourceFactory resourceFactory;
    @Autowired
    ApplicationServiceCreateResource appService;

    @Test
    void shouldThrowExceptionWhenProjectIsNotFound() {
        NewResourceDTO dto = new NewResourceDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.associatedRole = "DEVELOPER";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";

        Exception e = assertThrows(EntityNotFoundException.class, () -> appService.createResource(dto, 1));
        assertEquals("Project not found", e.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenAccountIDIsInvalid() {
        NewResourceDTO dto = new NewResourceDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.associatedRole = "DEVELOPER";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        when(projectRepository.findById(new ProjectID(1))).thenReturn(Optional.of(project));

        Exception e = assertThrows(EntityNotFoundException.class, () -> appService.createResource(dto, 1));
        assertEquals("Account not found", e.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenPeriodIsInvalid() {
        NewResourceDTO dto = new NewResourceDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.associatedRole = "DEVELOPER";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        when(projectRepository.findById(new ProjectID(1))).thenReturn(Optional.of(project));
        when(accountRepository.existsByAccountID(AccountID.of(Email.of(dto.associatedAccount)))).thenReturn(true);

        Exception e = assertThrows(IllegalArgumentException.class, () -> appService.createResource(dto, 1));
        assertEquals("Invalid allocation period", e.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenPercentageOfAllocationIsGreaterThanHundred() {
        NewResourceDTO dto = new NewResourceDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.associatedRole = "DEVELOPER";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        List<Resource> resourcesOfAccount = mock(List.class);
        when(resourceRepository.findAllByAccountID(Email.of("andre@isep.ipp.pt"))).thenReturn(resourcesOfAccount);
        Project project = mock(Project.class);
        when(projectRepository.findById(new ProjectID(1))).thenReturn(Optional.of(project));
        when(project.containsPeriod(Period.between(dto.startDate, dto.endDate))).thenReturn(true);
        when(accountRepository.existsByAccountID(AccountID.of(Email.of(dto.associatedAccount)))).thenReturn(true);

        Exception e = assertThrows(IllegalArgumentException.class, () -> appService.createResource(dto, 1));
        assertEquals("Allocation exceeds maximum", e.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenResourceIsRepeated() {
        NewResourceDTO dto = new NewResourceDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.associatedRole = "DEVELOPER";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        when(projectRepository.findById(new ProjectID(1))).thenReturn(Optional.of(project));
        when(project.containsPeriod(Period.between(dto.startDate, dto.endDate))).thenReturn(true);
        List<Resource> resourcesOfAccount = mock(List.class);
        when(resourceRepository.findAllByAccountID(Email.of("andre@isep.ipp.pt"))).thenReturn(resourcesOfAccount);
        when(resourceService.validatePercentageOfAllocation(resourcesOfAccount, Period.between(dto.startDate, dto.endDate), LimitedPercentage.percentage(100))).thenReturn(true);
        when(accountRepository.existsByAccountID(AccountID.of(Email.of(dto.associatedAccount)))).thenReturn(true);

        Exception e = assertThrows(IllegalArgumentException.class, () -> appService.createResource(dto, 1));
        assertEquals("Resource is already allocated to project", e.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenRoleIsRepeated() {
        NewResourceDTO dto = new NewResourceDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.associatedRole = "SCRUM_MASTER";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;

        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        when(projectRepository.findById(new ProjectID(1))).thenReturn(Optional.of(project));
        when(project.containsPeriod(Period.between(dto.startDate, dto.endDate))).thenReturn(true);
        List<Resource> resourcesOfAccount = mock(List.class);
        when(resourceRepository.findAllByAccountID(Email.of(dto.associatedAccount))).thenReturn(resourcesOfAccount);
        when(resourceService.validatePercentageOfAllocation(resourcesOfAccount, Period.between(dto.startDate, dto.endDate), LimitedPercentage.percentage(100))).thenReturn(true);
        List<Resource> resourcesOfProject = mock(List.class);
        when(resourceRepository.findAllByProjectID(new ProjectID(1))).thenReturn(resourcesOfProject);
        when(resourceService.validateResourceIsNotRepeated(resourcesOfProject, Period.between(dto.startDate, dto.endDate), Email.of(dto.associatedAccount))).thenReturn(true);
        when(accountRepository.existsByAccountID(AccountID.of(Email.of(dto.associatedAccount)))).thenReturn(true);

        Exception e = assertThrows(IllegalArgumentException.class, () -> appService.createResource(dto, 1));
        assertEquals("Role is already taken in period", e.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenRoleIsSMAndSprintIsRunning() {
        NewResourceDTO dto = new NewResourceDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.associatedRole = "SCRUM_MASTER";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        when(projectRepository.findById(new ProjectID(1))).thenReturn(Optional.of(project));
        when(project.containsPeriod(Period.between(dto.startDate, dto.endDate))).thenReturn(true);
        List<Resource> resourcesOfAccount = mock(List.class);
        when(resourceRepository.findAllByAccountID(Email.of(dto.associatedAccount))).thenReturn(resourcesOfAccount);
        when(resourceService.validatePercentageOfAllocation(resourcesOfAccount, Period.between(dto.startDate, dto.endDate), LimitedPercentage.percentage(100))).thenReturn(true);
        List<Resource> resourcesOfProject = mock(List.class);
        when(resourceRepository.findAllByProjectID(new ProjectID(1))).thenReturn(resourcesOfProject);
        when(resourceService.validateResourceIsNotRepeated(resourcesOfProject, Period.between(dto.startDate, dto.endDate), Email.of(dto.associatedAccount))).thenReturn(true);
        when(resourceService.validateRoleIsUniqueInPeriod(resourcesOfProject, Period.between(dto.startDate, dto.endDate), new Role(Erole.SCRUM_MASTER, NoNumberNoSymbolString.of(Erole.SCRUM_MASTER.getDescription())))).thenReturn(true);
        when(accountRepository.existsByAccountID(AccountID.of(Email.of(dto.associatedAccount)))).thenReturn(true);

        Exception e = assertThrows(IllegalArgumentException.class, () -> appService.createResource(dto, 1));
        assertEquals("Resource cannot be allocated in the course of a Sprint", e.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenRoleIsDevAndSprintIsRunning() {
        NewResourceDTO dto = new NewResourceDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.associatedRole = "DEVELOPER";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        when(projectRepository.findById(new ProjectID(1))).thenReturn(Optional.of(project));
        when(project.containsPeriod(Period.between(dto.startDate, dto.endDate))).thenReturn(true);
        List<Resource> resourcesOfAccount = mock(List.class);
        when(resourceRepository.findAllByAccountID(Email.of(dto.associatedAccount))).thenReturn(resourcesOfAccount);
        when(resourceService.validatePercentageOfAllocation(resourcesOfAccount, Period.between(dto.startDate, dto.endDate), LimitedPercentage.percentage(100))).thenReturn(true);
        List<Resource> resourcesOfProject = mock(List.class);
        when(resourceRepository.findAllByProjectID(new ProjectID(1))).thenReturn(resourcesOfProject);
        when(resourceService.validateResourceIsNotRepeated(resourcesOfProject, Period.between(dto.startDate, dto.endDate), Email.of(dto.associatedAccount))).thenReturn(true);
        when(resourceService.validateRoleIsUniqueInPeriod(resourcesOfProject, Period.between(dto.startDate, dto.endDate), new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of(Erole.DEVELOPER.getDescription())))).thenReturn(true);
        when(accountRepository.existsByAccountID(AccountID.of(Email.of(dto.associatedAccount)))).thenReturn(true);

        Exception e = assertThrows(IllegalArgumentException.class, () -> appService.createResource(dto, 1));
        assertEquals("Resource cannot be allocated in the course of a Sprint", e.getMessage());

    }


    @Test
    void ensureResourceIsSaved() {
        //Arrange
        NewResourceDTO dto = new NewResourceDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.associatedRole = "DEVELOPER";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        List<Resource> resourcesOfAccount = mock(List.class);
        when(resourceRepository.findAllByAccountID(Email.of(dto.associatedAccount))).thenReturn(resourcesOfAccount);
        when(resourceService.validatePercentageOfAllocation(resourcesOfAccount, Period.between(dto.startDate, dto.endDate), LimitedPercentage.percentage(100))).thenReturn(true);
        List<Resource> resourcesOfProject = mock(List.class);
        when(resourceRepository.findAllByProjectID(new ProjectID(1))).thenReturn(resourcesOfProject);
        when(resourceService.validateResourceIsNotRepeated(resourcesOfProject, Period.between(dto.startDate, dto.endDate), Email.of(dto.associatedAccount))).thenReturn(true);
        when(resourceService.validateRoleIsUniqueInPeriod(resourcesOfProject, Period.between(dto.startDate, dto.endDate), new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of(Erole.DEVELOPER.getDescription())))).thenReturn(true);
        List<Sprint> sprints = mock(List.class);
        when(sprintRepository.findAllSprintsByProjectID(new ProjectID(1))).thenReturn(sprints);
        when(sprintService.validateSprintRules(Period.between(dto.startDate, dto.endDate), sprints)).thenReturn(true);
        Project project = mock(Project.class);
        when(projectRepository.findById(new ProjectID(1))).thenReturn(Optional.of(project));
        when(accountRepository.existsByAccountID(AccountID.of(Email.of(dto.associatedAccount)))).thenReturn(true);
        when(project.containsPeriod(Period.between(dto.startDate, dto.endDate))).thenReturn(true);
        Resource resource = mock(Resource.class);
        when(resourceFactory.createResource(eq(AccountID.of(Email.of(dto.associatedAccount))),
                any(ResourceID.class),
                eq(new Role(Erole.valueOf(dto.associatedRole),
                        NoNumberNoSymbolString.of(Erole.valueOf(dto.associatedRole).getDescription()))),
                eq(Period.between(dto.startDate, dto.endDate)),
                eq(LimitedPercentage.percentage(100)),
                eq(new ProjectID(1)),
                eq(Monetary.of(dto.costPerHourValue, Currency.getInstance(dto.currency))))).thenReturn(resource);
        //Act
        appService.createResource(dto, 1);

        //Assert
        verify(resourceRepository, times(1)).save(resource);

    }

    @Test
    void ensureReturnsDTO() {
        //Arrange
        NewResourceDTO dto = new NewResourceDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.associatedRole = "DEVELOPER";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        List<Resource> resourcesOfAccount = mock(List.class);
        when(resourceRepository.findAllByAccountID(Email.of(dto.associatedAccount))).thenReturn(resourcesOfAccount);
        when(resourceService.validatePercentageOfAllocation(resourcesOfAccount, Period.between(dto.startDate, dto.endDate), LimitedPercentage.percentage(100))).thenReturn(true);
        List<Resource> resourcesOfProject = mock(List.class);
        when(resourceRepository.findAllByProjectID(new ProjectID(1))).thenReturn(resourcesOfProject);
        when(resourceService.validateResourceIsNotRepeated(resourcesOfProject, Period.between(dto.startDate, dto.endDate), Email.of(dto.associatedAccount))).thenReturn(true);
        when(resourceService.validateRoleIsUniqueInPeriod(resourcesOfProject, Period.between(dto.startDate, dto.endDate), new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of(Erole.DEVELOPER.getDescription())))).thenReturn(true);
        List<Sprint> sprints = mock(List.class);
        when(sprintRepository.findAllSprintsByProjectID(new ProjectID(1))).thenReturn(sprints);
        when(sprintService.validateSprintRules(Period.between(dto.startDate, dto.endDate), sprints)).thenReturn(true);
        Project project = mock(Project.class);
        when(projectRepository.findById(new ProjectID(1))).thenReturn(Optional.of(project));
        when(project.containsPeriod(Period.between(dto.startDate, dto.endDate))).thenReturn(true);
        when(accountRepository.existsByAccountID(AccountID.of(Email.of(dto.associatedAccount)))).thenReturn(true);
        Resource resource = mock(Resource.class);
        Resource savedResource = mock(Resource.class);
        when(resourceFactory.createResource(eq(AccountID.of(Email.of(dto.associatedAccount))),
                any(ResourceID.class),
                eq(new Role(Erole.valueOf(dto.associatedRole),
                        NoNumberNoSymbolString.of(Erole.valueOf(dto.associatedRole).getDescription()))),
                eq(Period.between(dto.startDate, dto.endDate)),
                eq(LimitedPercentage.percentage(100)),
                eq(new ProjectID(1)),
                eq(Monetary.of(dto.costPerHourValue, Currency.getInstance(dto.currency))))).thenReturn(resource);
        ResourceDTO expected = mock(ResourceDTO.class);
        when(mapper.toDTO(savedResource)).thenReturn(expected);
        when(resourceRepository.save(resource)).thenReturn(savedResource);
        //Act
        ResourceDTO result = appService.createResource(dto, 1);

        //Assert
        assertSame(expected, result);


    }


    @Test
    void defineProductOwnerShouldThrowExceptionWhenProjectIsNotFound() {
        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";

        Exception e = assertThrows(EntityNotFoundException.class, () -> appService.defineProductOwner(dto, 1));
        assertEquals("Project not found", e.getMessage());
    }

    @Test
    void defineProductOwnerShouldThrowExceptionWhenAccountIsNotFound() {
        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        ProjectID projectID = new ProjectID(1);
        when(projectRepository.findById(projectID)).thenReturn(Optional.of(project));

        Exception e = assertThrows(EntityNotFoundException.class, () -> appService.defineProductOwner(dto, 1));
        assertEquals("Account not found", e.getMessage());
    }

    @Test
    void defineProductOwnerShouldThrowExceptionAllocationPeriodIsInvalid() {


        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        ProjectID projectID = new ProjectID(1);
        when(projectRepository.findById(projectID)).thenReturn(Optional.of(project));
        Email email = Email.of(dto.associatedAccount);
        AccountID accountID = AccountID.of(email);
        when(accountRepository.existsByAccountID(accountID)).thenReturn(true);

        Exception e = assertThrows(IllegalArgumentException.class, () -> appService.defineProductOwner(dto, 1));
        assertEquals("Invalid allocation period", e.getMessage());
    }

    @Test
    void defineProductOwnerShouldThrowExceptionWhenAllocationIsExcessive() {

        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        ProjectID projectID = new ProjectID(1);
        when(projectRepository.findById(projectID)).thenReturn(Optional.of(project));
        Email email = Email.of(dto.associatedAccount);
        AccountID accountID = AccountID.of(email);
        when(accountRepository.existsByAccountID(accountID)).thenReturn(true);
        when(project.containsPeriod(Period.between(dto.startDate, dto.endDate))).thenReturn(true);

        Exception e = assertThrows(IllegalArgumentException.class, () -> appService.defineProductOwner(dto, 1));
        assertEquals("Allocation exceeds maximum", e.getMessage());
    }


    @Test
    void defineProductOwnerShouldThrowExceptionWhenUserIsOnTheTeam() {
        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        when(projectRepository.findById(new ProjectID(1))).thenReturn(Optional.of(project));
        when(project.containsPeriod(Period.between(dto.startDate, dto.endDate))).thenReturn(true);
        List<Resource> resourcesOfAccount = mock(List.class);
        when(resourceRepository.findAllByAccountID(Email.of("andre@isep.ipp.pt"))).thenReturn(resourcesOfAccount);
        when(resourceService.validatePercentageOfAllocation(resourcesOfAccount, Period.between(dto.startDate, dto.endDate), LimitedPercentage.percentage(100))).thenReturn(true);
        when(accountRepository.existsByAccountID(AccountID.of(Email.of(dto.associatedAccount)))).thenReturn(true);

        Exception e = assertThrows(IllegalArgumentException.class, () -> appService.defineProductOwner(dto, 1));
        assertEquals("Resource is already allocated to project", e.getMessage());
    }


    @Test
    void defineProductOwnerShouldDeleteProductOwners() {

        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        Role role = new Role(Erole.PRODUCT_OWNER,
                NoNumberNoSymbolString.of(Erole.PRODUCT_OWNER.getDescription()));
        Period period = (Period.between(dto.startDate, dto.endDate));
        ProjectID projectID = new ProjectID(1);
        Email email = Email.of(dto.associatedAccount);
        AccountID accountID = AccountID.of(email);
        when(projectRepository.findById(projectID)).thenReturn(Optional.of(project));
        when(project.containsPeriod(period)).thenReturn(true);
        List<Resource> resourcesOfAccount = mock(List.class);
        when(resourceRepository.findAllByAccountID(email)).thenReturn(resourcesOfAccount);
        when(resourceService.validatePercentageOfAllocation(resourcesOfAccount, period, LimitedPercentage.percentage(100))).thenReturn(true);
        List<Resource> resourcesOfProject = mock(List.class);
        when(resourceRepository.findAllByProjectID(projectID)).thenReturn(resourcesOfProject);
        when(resourceService.validateResourceIsNotRepeated(resourcesOfProject, period, email)).thenReturn(true);
        when(accountRepository.existsByAccountID(accountID)).thenReturn(true);

        appService.defineProductOwner(dto, 1);

        verify(resourceRepository, times(1)).deleteResources(role, projectID, period);

    }

    @Test
    void ensureNoChangesToResourcesAreMadeWhenRoleIsUniqueInPeriod() {
        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        Role role = new Role(Erole.PRODUCT_OWNER,
                NoNumberNoSymbolString.of(Erole.PRODUCT_OWNER.getDescription()));
        Period period = (Period.between(dto.startDate, dto.endDate));
        ProjectID projectID = new ProjectID(1);
        Email email = Email.of(dto.associatedAccount);
        AccountID accountID = AccountID.of(email);
        when(projectRepository.findById(projectID)).thenReturn(Optional.of(project));
        when(project.containsPeriod(period)).thenReturn(true);
        List<Resource> resourcesOfAccount = mock(List.class);
        when(resourceRepository.findAllByAccountID(email)).thenReturn(resourcesOfAccount);
        when(resourceService.validatePercentageOfAllocation(resourcesOfAccount, period, LimitedPercentage.percentage(100))).thenReturn(true);
        List<Resource> resourcesOfProject = mock(List.class);
        when(resourceRepository.findAllByProjectID(projectID)).thenReturn(resourcesOfProject);
        when(resourceService.validateResourceIsNotRepeated(resourcesOfProject, period, email)).thenReturn(true);
        when(accountRepository.existsByAccountID(accountID)).thenReturn(true);
        when(resourceService.validateRoleIsUniqueInPeriod(resourcesOfProject, period, role)).thenReturn(true);
        Resource currentProductOwner = mock(Resource.class);
        when(resourceRepository.findActiveResource(role, projectID, dto.startDate)).thenReturn(Optional.of(currentProductOwner));

        appService.defineProductOwner(dto, 1);

        verify(resourceService, times(1)).validateRoleIsUniqueInPeriod(resourcesOfProject, period, role);
        verify(resourceRepository, never()).findActiveResource(role, projectID, dto.startDate);
        verify(currentProductOwner, never()).changeEndDate(any());
        verify(resourceRepository, never()).save(currentProductOwner);

    }

    @Test
    void ensureChangesAreMadeToResourceWhenRoleIsNotUniqueInPeriod() {
        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        Role role = new Role(Erole.PRODUCT_OWNER,
                NoNumberNoSymbolString.of(Erole.PRODUCT_OWNER.getDescription()));
        Period period = (Period.between(dto.startDate, dto.endDate));
        ProjectID projectID = new ProjectID(1);
        Email email = Email.of(dto.associatedAccount);
        AccountID accountID = AccountID.of(email);
        when(projectRepository.findById(projectID)).thenReturn(Optional.of(project));
        when(project.containsPeriod(period)).thenReturn(true);
        List<Resource> resourcesOfAccount = mock(List.class);
        when(resourceRepository.findAllByAccountID(email)).thenReturn(resourcesOfAccount);
        when(resourceService.validatePercentageOfAllocation(resourcesOfAccount, period, LimitedPercentage.percentage(100))).thenReturn(true);
        List<Resource> resourcesOfProject = mock(List.class);
        when(resourceRepository.findAllByProjectID(projectID)).thenReturn(resourcesOfProject);
        when(resourceService.validateResourceIsNotRepeated(resourcesOfProject, period, email)).thenReturn(true);
        when(accountRepository.existsByAccountID(accountID)).thenReturn(true);
        Resource currentProductOwner = mock(Resource.class);
        when(resourceRepository.findActiveResource(role, projectID, dto.startDate)).thenReturn(Optional.of(currentProductOwner));

        appService.defineProductOwner(dto, 1);

        verify(resourceService, times(1)).validateRoleIsUniqueInPeriod(resourcesOfProject, period, role);
        verify(resourceRepository, times(1)).findActiveResource(role, projectID, dto.startDate);
        verify(currentProductOwner, times(1)).changeEndDate(dto.startDate.minusDays(1));
        verify(resourceRepository, times(1)).save(currentProductOwner);
    }

    @Test
    void ensureNewResourceIsSaved() {

        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        Role role = new Role(Erole.PRODUCT_OWNER,
                NoNumberNoSymbolString.of(Erole.PRODUCT_OWNER.getDescription()));
        Period period = (Period.between(dto.startDate, dto.endDate));
        ProjectID projectID = new ProjectID(1);
        Email email = Email.of(dto.associatedAccount);
        AccountID accountID = AccountID.of(email);
        when(projectRepository.findById(projectID)).thenReturn(Optional.of(project));
        when(project.containsPeriod(period)).thenReturn(true);
        List<Resource> resourcesOfAccount = mock(List.class);
        when(resourceRepository.findAllByAccountID(email)).thenReturn(resourcesOfAccount);
        when(resourceService.validatePercentageOfAllocation(resourcesOfAccount, period, LimitedPercentage.percentage(100))).thenReturn(true);
        List<Resource> resourcesOfProject = mock(List.class);
        when(resourceRepository.findAllByProjectID(projectID)).thenReturn(resourcesOfProject);
        when(resourceService.validateResourceIsNotRepeated(resourcesOfProject, period, email)).thenReturn(true);
        when(accountRepository.existsByAccountID(accountID)).thenReturn(true);
        Resource newProductOwner = mock(Resource.class);
        when(resourceFactory.createResource(eq(accountID),
                any(ResourceID.class),
                eq(role),
                eq(period),
                eq(LimitedPercentage.percentage(100)),
                eq(new ProjectID(1)),
                eq(Monetary.of(dto.costPerHourValue, Currency.getInstance(dto.currency))))).thenReturn(newProductOwner);

        appService.defineProductOwner(dto, 1);

        verify(resourceRepository, times(1)).save(newProductOwner);
    }

    @Test
    void ensureDTOIsReturned() {

        NewProductOwnerDTO dto = new NewProductOwnerDTO();
        dto.associatedAccount = "andre@isep.ipp.pt";
        dto.startDate = LocalDate.of(1993, 7, 13);
        dto.endDate = LocalDate.of(1993, 8, 13);
        dto.percentageOfAllocation = 1;
        dto.costPerHourValue = 10;
        dto.currency = "EUR";
        Project project = mock(Project.class);
        Role role = new Role(Erole.PRODUCT_OWNER,
                NoNumberNoSymbolString.of(Erole.PRODUCT_OWNER.getDescription()));
        Period period = (Period.between(dto.startDate, dto.endDate));
        ProjectID projectID = new ProjectID(1);
        Email email = Email.of(dto.associatedAccount);
        AccountID accountID = AccountID.of(email);
        when(projectRepository.findById(projectID)).thenReturn(Optional.of(project));
        when(project.containsPeriod(period)).thenReturn(true);
        List<Resource> resourcesOfAccount = mock(List.class);
        when(resourceRepository.findAllByAccountID(email)).thenReturn(resourcesOfAccount);
        when(resourceService.validatePercentageOfAllocation(resourcesOfAccount, period, LimitedPercentage.percentage(100))).thenReturn(true);
        List<Resource> resourcesOfProject = mock(List.class);
        when(resourceRepository.findAllByProjectID(projectID)).thenReturn(resourcesOfProject);
        when(resourceService.validateResourceIsNotRepeated(resourcesOfProject, period, email)).thenReturn(true);
        when(accountRepository.existsByAccountID(accountID)).thenReturn(true);
        Resource newProductOwner = mock(Resource.class);
        Resource savedProductOwner = mock(Resource.class);
        when(resourceFactory.createResource(eq(accountID),
                any(ResourceID.class),
                eq(role),
                eq(period),
                eq(LimitedPercentage.percentage(100)),
                eq(new ProjectID(1)),
                eq(Monetary.of(dto.costPerHourValue, Currency.getInstance(dto.currency))))).thenReturn(newProductOwner);
        ResourceDTO expected = mock(ResourceDTO.class);
        when(resourceRepository.save(newProductOwner)).thenReturn(savedProductOwner);
        when(mapper.toDTO(savedProductOwner)).thenReturn(expected);

        ResourceDTO result = appService.defineProductOwner(dto, 1);

        assertEquals(expected, result);
    }


    @Test
    void updateScrumMaster() {
        //Arrange
        int projIDInt = 1;
        NewScrumMasterDTO dto = new NewScrumMasterDTO();
        dto.associatedAccount = "luis@isep.ipp.pt";
        dto.percentageOfAllocation = 0.5;
        dto.startDate = "2022-06-01";
        dto.endDate = "2022-06-07";
        ProjectID projectID = new ProjectID(projIDInt);

        Email newSMAccount = Email.of(dto.associatedAccount);
        Role scrumMaster = new Role(Erole.SCRUM_MASTER, NoNumberNoSymbolString.of(Erole.SCRUM_MASTER.getDescription()));
        Role developer = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of(Erole.DEVELOPER.getDescription()));
        Period resourcePeriod = Period.between(LocalDate.parse(dto.startDate),LocalDate.parse( dto.endDate));
        LocalDate newStartDate = resourcePeriod.getStartingDate();
// fazer mock ao projeto
        Project project = mock(Project.class);
//encontrar o projeto pelo id
        when(projectRepository.findById(projectID)).thenReturn(Optional.of(project));
        //validar as datas do recurso estão dentro do projeto
        when(project.containsPeriod(resourcePeriod)).thenReturn(true);
//mock do SM actual
        Resource mockactualSM = mock(Resource.class);
//encontrar o SM actual
        when(resourceRepository.findActiveResource(scrumMaster, projectID, LocalDate.parse(dto.startDate))).thenReturn(Optional.of(mockactualSM));
//-------Entrar no metodo privado transformar sm em developer
        //alterar a data de fim do recurso e gravar

//Encontrar todos os roles da account para verificar a percentagem de alocaçao
        AccountID lastSM = mock(AccountID.class);
        Email lastSMEmail = Email.of("andre@isep.ipp.pt");
        when(mockactualSM.getAssociatedAccount()).thenReturn(lastSM);
        when(lastSM.getID()).thenReturn(lastSMEmail);

        Resource mockOne = mock(Resource.class);

        List<Resource> allRoles = new ArrayList<>();
        allRoles.add(mockOne);

        when(resourceRepository.findAllByAccountID(lastSMEmail)).thenReturn(allRoles);

        LimitedPercentage allocation = LimitedPercentage.decimal(0.5);
        when(mockactualSM.getPercentageOfAllocation()).thenReturn(allocation);
        when(resourceService.validatePercentageOfAllocation(allRoles, resourcePeriod, allocation)).thenReturn(true);
//Validar o sprint
        Sprint mockSprint = mock(Sprint.class);
        List<Sprint> allSprints = new ArrayList<>();
        allSprints.add(mockSprint);
        when(sprintRepository.findAllSprintsByProjectID(projectID)).thenReturn(allSprints);
        when(sprintService.validateSprintRules(resourcePeriod, allSprints)).thenReturn(true);
//criar o novo papel de developer
        ResourceID newDeveloperID = new ResourceID();
        ResourceID secondResourceID = new ResourceID();
        MockedStatic<ResourceID> resourceIDMockedStatic = mockStatic(ResourceID.class);
        resourceIDMockedStatic.when(() -> ResourceID.create()).thenReturn(newDeveloperID, secondResourceID);

        Monetary cost = Monetary.of(12.5, Currency.getInstance("EUR"));
        when(mockactualSM.getCostPerHour()).thenReturn(cost);

        Resource newDeveloper = mock(Resource.class);
        when(resourceFactory.createResource(lastSM, newDeveloperID, developer, resourcePeriod, allocation, projectID, cost)).thenReturn(newDeveloper);
// Procurar o novo scrum master no metodo pricipal
        Resource lastDeveloper = mock(Resource.class);
        AccountID newScrumMasterID = AccountID.of(newSMAccount);
//encontra o novo scrum master
        when(resourceRepository.findActiveResourceByAccountAndProjectID(newScrumMasterID, projectID, newStartDate)).thenReturn(Optional.of(lastDeveloper));
// entra no segundo metodo privado
        //Termina o papel
        when(mockactualSM.changeEndDate(newStartDate.minusDays(1))).thenReturn(true);

        when(resourceRepository.findActiveResource(scrumMaster, projectID, LocalDate.parse(dto.startDate))).thenReturn(Optional.of(lastDeveloper));
//Encontrar todos os roles da account para verificar a percentagem de alocaçao

        Resource mockTwo = mock(Resource.class);
        List<Resource> resourcesList = new ArrayList<>();
        resourcesList.add(mockTwo);
        when(lastDeveloper.getAssociatedAccount()).thenReturn(newScrumMasterID);


        when(resourceRepository.findAllByAccountID(newSMAccount)).thenReturn(resourcesList);
        LimitedPercentage newAllocation = LimitedPercentage.decimal(0.5);
        when(lastDeveloper.getPercentageOfAllocation()).thenReturn(newAllocation);
        Monetary costTwo = Monetary.of(12.5, Currency.getInstance("EUR"));
        when(lastDeveloper.getCostPerHour()).thenReturn(costTwo);
        when(resourceService.validatePercentageOfAllocation(resourcesList, resourcePeriod, newAllocation)).thenReturn(true);


        Resource newScrumMaster = mock(Resource.class);
        when(resourceFactory.createResource(newScrumMasterID, secondResourceID, scrumMaster,
                resourcePeriod, newAllocation, projectID, costTwo)).thenReturn(newScrumMaster);


        ResourceDTO expected = mock(ResourceDTO.class);
        when(mapper.toDTO(newScrumMaster)).thenReturn(expected);
        //Act
        ResourceDTO result = appService.updateScrumMaster(dto, projIDInt);
        resourceIDMockedStatic.close();
        //Assert
        assertEquals(expected, result);


    }

    @Test
    void getAllResourcesByProjectID() {
        int pid = 1;
        ProjectID projectID = new ProjectID(pid);


        Resource dev = mock(Resource.class);
        Resource scrum = mock(Resource.class);

        List<Resource> resources = new ArrayList<>();
        resources.add(dev);
        resources.add(scrum);

        when(resourceRepository.findAllByProjectID(projectID)).thenReturn(resources);

        ResourceDTO devDTO = mock(ResourceDTO.class);
        ResourceDTO scrumDTO = mock(ResourceDTO.class);

        List<ResourceDTO> expected = new ArrayList<>();
        when(mapper.toDTO(dev)).thenReturn(devDTO);
        expected.add(devDTO);
        when(mapper.toDTO(scrum)).thenReturn(scrumDTO);
        expected.add(scrumDTO);

        List<ResourceDTO> result = appService.getAllResourcesByProjectID(pid);

        assertEquals(expected, result);

    }

    @Test
    void testGetActiveResourcesByProjectID() {
        int pid = 1;
        LocalDate date = LocalDate.of(2022,7,6);
        ProjectID projectID = new ProjectID(pid);


        Resource dev = mock(Resource.class);
        Resource scrum = mock(Resource.class);

        List<Resource> resources = new ArrayList<>();
        resources.add(dev);
        resources.add(scrum);

        when(resourceRepository.findAllActiveResourcesByProjectID(projectID,date)).thenReturn(resources);

        ResourceDTO devDTO = mock(ResourceDTO.class);
        ResourceDTO scrumDTO = mock(ResourceDTO.class);

        List<ResourceDTO> expected = new ArrayList<>();
        when(mapper.toDTO(dev)).thenReturn(devDTO);
        expected.add(devDTO);
        when(mapper.toDTO(scrum)).thenReturn(scrumDTO);
        expected.add(scrumDTO);

        List<ResourceDTO> result = appService.getAllActiveResourcesByProjectID(pid,date);

        assertEquals(expected, result);
    }

    @Test
    void testUpdateScrumMaster_ExceedsAllocation() {

        //Arrange
        int projIDInt = 1;
        NewScrumMasterDTO dto = new NewScrumMasterDTO();
        dto.associatedAccount = "luis@isep.ipp.pt";
        dto.percentageOfAllocation = 0.5;
        dto.startDate = "2022-06-01";
        dto.endDate = "2022-06-07";
        ProjectID projectID = new ProjectID(projIDInt);

        Email newSMAccount = Email.of(dto.associatedAccount);
        Role scrumMaster = new Role(Erole.SCRUM_MASTER, NoNumberNoSymbolString.of(Erole.SCRUM_MASTER.getDescription()));
        Role developer = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of(Erole.DEVELOPER.getDescription()));
        Period resourcePeriod = Period.between(LocalDate.parse(dto.startDate),LocalDate.parse( dto.endDate));
        LocalDate newStartDate = resourcePeriod.getStartingDate();
// fazer mock ao projeto
        Project project = mock(Project.class);
        Project projectTwo = mock(Project.class);
//encontrar o projeto pelo id
        when(projectRepository.findById(projectID)).thenReturn(Optional.of(project));
        //validar as datas do recurso estão dentro do projeto
        when(project.containsPeriod(resourcePeriod)).thenReturn(true);
//mock do SM actual
        Resource mockactualSM = mock(Resource.class);
//encontrar o SM actual
        when(resourceRepository.findActiveResource(scrumMaster, projectID, LocalDate.parse(dto.startDate))).thenReturn(Optional.of(mockactualSM));
//-------Entrar no metodo privado transformar sm em developer
        //alterar a data de fim do recurso e gravar

//Encontrar todos os roles da account para verificar a percentagem de alocaçao
        AccountID lastSM = mock(AccountID.class);
        Email lastSMEmail = Email.of("andre@isep.ipp.pt");
        when(mockactualSM.getAssociatedAccount()).thenReturn(lastSM);
        when(lastSM.getID()).thenReturn(lastSMEmail);

        Resource mockOne = mock(Resource.class);

        List<Resource> allRoles = new ArrayList<>();
        allRoles.add(mockOne);

        when(resourceRepository.findAllByAccountID(lastSMEmail)).thenReturn(allRoles);

        LimitedPercentage allocation = LimitedPercentage.decimal(0.5);
        when(mockactualSM.getPercentageOfAllocation()).thenReturn(allocation);
        when(resourceService.validatePercentageOfAllocation(allRoles, resourcePeriod, allocation)).thenReturn(false);

        //Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> appService.updateScrumMaster(dto,projIDInt));
        //Assert
        assertEquals("Allocation exceeds maximum", e.getMessage());

    }

    @Test
    void testUpdateScrumMaster_ValidateSprintException() {

        //Arrange
        int projIDInt = 1;
        NewScrumMasterDTO dto = new NewScrumMasterDTO();
        dto.associatedAccount = "luis@isep.ipp.pt";
        dto.percentageOfAllocation = 0.5;
        dto.startDate = "2022-06-01";
        dto.endDate = "2022-06-07";
        ProjectID projectID = new ProjectID(projIDInt);

        Email newSMAccount = Email.of(dto.associatedAccount);
        Role scrumMaster = new Role(Erole.SCRUM_MASTER, NoNumberNoSymbolString.of(Erole.SCRUM_MASTER.getDescription()));
        Role developer = new Role(Erole.DEVELOPER, NoNumberNoSymbolString.of(Erole.DEVELOPER.getDescription()));
        Period resourcePeriod = Period.between(LocalDate.parse(dto.startDate),LocalDate.parse( dto.endDate));
        LocalDate newStartDate = resourcePeriod.getStartingDate();
// fazer mock ao projeto
        Project project = mock(Project.class);
        Project projectTwo = mock(Project.class);
//encontrar o projeto pelo id
        when(projectRepository.findById(projectID)).thenReturn(Optional.of(project));
        //validar as datas do recurso estão dentro do projeto
        when(project.containsPeriod(resourcePeriod)).thenReturn(true);
//mock do SM actual
        Resource mockactualSM = mock(Resource.class);
//encontrar o SM actual
        when(resourceRepository.findActiveResource(scrumMaster, projectID, LocalDate.parse(dto.startDate))).thenReturn(Optional.of(mockactualSM));
//-------Entrar no metodo privado transformar sm em developer
        //alterar a data de fim do recurso e gravar

//Encontrar todos os roles da account para verificar a percentagem de alocaçao
        AccountID lastSM = mock(AccountID.class);
        Email lastSMEmail = Email.of("andre@isep.ipp.pt");
        when(mockactualSM.getAssociatedAccount()).thenReturn(lastSM);
        when(lastSM.getID()).thenReturn(lastSMEmail);

        Resource mockOne = mock(Resource.class);

        List<Resource> allRoles = new ArrayList<>();
        allRoles.add(mockOne);

        when(resourceRepository.findAllByAccountID(lastSMEmail)).thenReturn(allRoles);

        LimitedPercentage allocation = LimitedPercentage.decimal(0.5);
        when(mockactualSM.getPercentageOfAllocation()).thenReturn(allocation);
        when(resourceService.validatePercentageOfAllocation(allRoles, resourcePeriod, allocation)).thenReturn(true);
//Validar o sprint
        Sprint mockSprint = mock(Sprint.class);
        List<Sprint> allSprints = new ArrayList<>();
        allSprints.add(mockSprint);
        when(sprintRepository.findAllSprintsByProjectID(projectID)).thenReturn(allSprints);
        when(sprintService.validateSprintRules(resourcePeriod, allSprints)).thenReturn(false);

        //Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> appService.updateScrumMaster(dto,projIDInt));
        //Assert
        assertEquals("Resource cannot be allocated in the course of a Sprint", e.getMessage());

    }
}