package switchtwentyone.project.infrastructure.persistence.ijparepositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.datamodel.ResourceJPA;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface IResourceJPARepository
        extends CrudRepository<ResourceJPA, ResourceID> {

    @Query("select r from ResourceJPA r " +
            "where r.associatedAccount = ?1 and r.startDate < ?2 and r.endDate > ?3 and r.role = ?4")
    List<ResourceJPA> findByAssociatedAccountAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndRole(AccountID associatedAccount, LocalDate date, LocalDate endDate, String role);

    List<ResourceJPA> findByProjectID(ProjectID projectID);

    List<ResourceJPA> findByAssociatedAccount_Id(Email id);

    @Query("select r from ResourceJPA r " +
            "where r.associatedAccount = ?1 and r.startDate < ?2 and r.endDate > ?3 and r.projectID = ?4")
    List<ResourceJPA> findByAssociatedAccountAndStartDateBeforeAndEndDateAfterAndProjectID(AccountID associatedAccount, LocalDate date, LocalDate endDate, ProjectID projectID);



    Optional<ResourceJPA> findByRoleAndProjectIDAndAndStartDateLessThanEqualAndEndDateGreaterThanEqual
            (String role, ProjectID projectID, LocalDate startDate, LocalDate endDate);


    List<ResourceJPA> findAllActiveResourcesByProjectIDAndAndStartDateLessThanEqualAndEndDateGreaterThanEqual
            (ProjectID projectID, LocalDate startDate, LocalDate endDate);
    void deleteByRoleAndProjectIDAndStartDateGreaterThanEqualAndStartDateLessThanEqual
            (String role, ProjectID projectID, LocalDate startDate, LocalDate endDate);



    boolean existsByRoleAndProjectIDAndStartDateGreaterThanEqualAndStartDateLessThanEqual
            (String role, ProjectID projectID, LocalDate startDate, LocalDate endDate);

      boolean existsByAssociatedAccountAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProjectID(AccountID accountID, LocalDate startDate, LocalDate endDate, ProjectID projectID);



    Optional<ResourceJPA> findByAssociatedAccountAndProjectIDAndAndStartDateLessThanEqualAndEndDateGreaterThanEqual
            (AccountID accountID, ProjectID projectID, LocalDate startDate, LocalDate endDate);

    @Query("select r from ResourceJPA r " +
            "where r.associatedAccount = ?1 and r.startDate < ?2 and r.endDate > ?3")
    List<ResourceJPA> findByAssociatedAccountAndStartDateLessThanEqualAndEndDateGreaterThanEqual(AccountID associatedAccount, LocalDate date, LocalDate endDate);
}
