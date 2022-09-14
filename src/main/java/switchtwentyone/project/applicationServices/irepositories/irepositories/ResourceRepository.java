package switchtwentyone.project.applicationServices.irepositories.irepositories;

import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.Resource;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.domain.aggregates.resource.Role;
import switchtwentyone.project.domain.valueObjects.Period;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ResourceRepository {

    List<Resource> findAllActiveResourcesByEmailAndRole(Email email, Role role, LocalDate date);

    List<Resource> findAllByProjectID(ProjectID projectID);

    List<Resource> findAllByAccountID(Email accountID);

    Resource save(Resource resource);

    Optional<Resource> findActiveResource
            (Role role, ProjectID projectID, LocalDate date);

    boolean deleteResources(Role role, ProjectID projectID, Period period);

    boolean existsByAssociatedAccountAndStartDateBeforeAndEndDateAfterAndProjectID(AccountID accountID, ProjectID projectID, LocalDate localDate);

    Optional<Resource> findActiveResourceByAccountAndProjectID
            (AccountID accountID, ProjectID projectID, LocalDate date);

    List<Resource> findAllActiveResourcesByProjectID(ProjectID projectID, LocalDate date);

    List<Resource> findAllActiveResourcesByEmail(Email email, LocalDate date);

    Optional<Resource> findResourceByID(ResourceID resourceID);

}
