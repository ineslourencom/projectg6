package switchtwentyone.project.interfaceAdapters.repositories.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ResourceRepository;
import switchtwentyone.project.domain.aggregates.resource.ResourceID;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IResourceJPARepository;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.resource.Resource;
import switchtwentyone.project.domain.aggregates.resource.Role;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.datamodel.ResourceJPA;
import switchtwentyone.project.datamodel.assembler.ResourceDomainDataAssembler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ResourceRepositoryImpl implements ResourceRepository {


    @Autowired
    IResourceJPARepository resourceJPARepository;
    @Autowired
    ResourceDomainDataAssembler resourceAssembler;

    /**
     * Finds all resources off a certain account, with the same active role in a given date,
     * regardless of the project they belong to.
     *
     * @param email the ID of the account
     * @param role  the role the resource should detain
     * @param date  date at which the resource must be active
     * @return a list of Resources meeting the criteria
     */

    @Override
    public List<Resource> findAllActiveResourcesByEmailAndRole(Email email, Role role, LocalDate date) {
        AccountID associatedAccount = AccountID.of(email);

        String activeRole = role.getRoleAsString();

        List<Resource> resourcesList = new ArrayList<>();
        List<ResourceJPA> activeResources = resourceJPARepository.findByAssociatedAccountAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndRole(associatedAccount, date, date, activeRole);
        for (ResourceJPA jpa : activeResources) {
            Resource resource = resourceAssembler.toDomain(jpa);
            resourcesList.add(resource);
        }
        return resourcesList;
    }

    /**
     * Find the resources involved in a project.
     *
     * @param projectID the ID of the project
     * @return list of resources in the project
     **/
    @Override
    public List<Resource> findAllByProjectID(ProjectID projectID) {

        List<ResourceJPA> addResourceJpa = resourceJPARepository.findByProjectID(projectID);
        List<Resource> addResource = new ArrayList<>();
        for (ResourceJPA resourceJPA : addResourceJpa) {
            Resource resource = resourceAssembler.toDomain(resourceJPA);
            addResource.add(resource);
        }
        return addResource;
    }

    /**
     * Find the resources referring to a certain account
     *
     * @param accountID the ID of the account
     * @return list of resources referring to a certain account
     **/
    @Override
    public List<Resource> findAllByAccountID(Email accountID) {

        List<ResourceJPA> addResourceJpa = resourceJPARepository.findByAssociatedAccount_Id(accountID);
        List<Resource> addResource = new ArrayList<>();
        for (ResourceJPA resourceJPA : addResourceJpa) {
            Resource resource = resourceAssembler.toDomain(resourceJPA);
            addResource.add(resource);
        }
        return addResource;

    }

    /**
     * Persist a Resource
     *
     * @param resource the resource to save
     * @return saved Resource
     */

    @Override
    public Resource save(Resource resource) {
        ResourceJPA resourceJPA = resourceAssembler.toData(resource);
        resourceJPA = resourceJPARepository.save(resourceJPA);
        return resourceAssembler.toDomain(resourceJPA);
    }

    /**
     * Finds a resource with a certain role and project id that is active in a certain date
     *
     * @param role      the role the resource should detain
     * @param projectID identifies the project to which resource must belong
     * @param date      date at which the resource must be active
     * @return Optional of resource meeting the criteria if found, Optional empty otherwise
     */
    @Override
    public Optional<Resource> findActiveResource
    (Role role, ProjectID projectID, LocalDate date) {
        Optional<Resource> opResource = Optional.empty();
        String activeRole = role.getRoleAsString();
        Optional<ResourceJPA> opResourceJPA = resourceJPARepository
                .findByRoleAndProjectIDAndAndStartDateLessThanEqualAndEndDateGreaterThanEqual
                        (activeRole, projectID, date, date);
        if (opResourceJPA.isPresent()) {
            ResourceJPA resourceJPA = opResourceJPA.get();
            Resource resource = resourceAssembler.toDomain(resourceJPA);
            opResource = Optional.of(resource);

        }
        return opResource;
    }

    /**
     * Deletes a resource with a certain role, project ID and whose date is contained in a certain period
     *
     * @param role      the role of the resource to eliminate
     * @param projectID identifies the project to which the resource belongs to
     * @param period    identifies the period in which the start date of the resource to eliminate must be contained.
     * @return true if resource existed and was deleted, false if it did not exist
     */
    @Override
    public boolean deleteResources(Role role, ProjectID projectID, Period period) {
        boolean removed = false;
        LocalDate startDate = period.getStartingDate();
        LocalDate endDate = period.getEndingDate();
        String activeRole = role.getRoleAsString();
        if (resourceJPARepository.existsByRoleAndProjectIDAndStartDateGreaterThanEqualAndStartDateLessThanEqual(activeRole, projectID, startDate, endDate)) {
            resourceJPARepository.deleteByRoleAndProjectIDAndStartDateGreaterThanEqualAndStartDateLessThanEqual(activeRole, projectID, startDate, endDate);
            removed = true;
        }


        return removed;

    }

    /**
     * Checks if a resource is active given a project.
     *
     * @param accountID account identifier
     * @param projectID project identifier
     * @param localDate date to be tested usually now()
     * @return true if resource active, false if otherwise
     */
    @Override
    public boolean existsByAssociatedAccountAndStartDateBeforeAndEndDateAfterAndProjectID(AccountID accountID, ProjectID projectID, LocalDate localDate) {
        return resourceJPARepository.existsByAssociatedAccountAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProjectID(accountID, localDate, localDate, projectID);
    }

    /**
     * Find the active resource, a user is in a specific project.
     *
     * @param accountID the ID of the account the resourche should refer
     * @param projectID identifies the project to which the resource should belong to
     * @param date      the date the Resource should be active
     * @return Optional of resource meeting the criteria if found, Optional empty otherwise
     */
    @Override
    public Optional<Resource> findActiveResourceByAccountAndProjectID
    (AccountID accountID, ProjectID projectID, LocalDate date) {
        Optional<Resource> opResource = Optional.empty();

        Optional<ResourceJPA> opResourceJPA = resourceJPARepository
                .findByAssociatedAccountAndProjectIDAndAndStartDateLessThanEqualAndEndDateGreaterThanEqual
                        (accountID, projectID, date, date);
        if (opResourceJPA.isPresent()) {
            ResourceJPA resourceJPA = opResourceJPA.get();
            Resource resource = resourceAssembler.toDomain(resourceJPA);
            opResource = Optional.of(resource);

        }
        return opResource;
    }

    @Override
    public List<Resource> findAllActiveResourcesByProjectID(ProjectID projectID, LocalDate date) {
        List<Resource> resources = new ArrayList<>();
        List<ResourceJPA> resourcesJPA = resourceJPARepository.findAllActiveResourcesByProjectIDAndAndStartDateLessThanEqualAndEndDateGreaterThanEqual(projectID, date, date);
        for (ResourceJPA rsc : resourcesJPA) {
            Resource resource = resourceAssembler.toDomain(rsc);
            resources.add(resource);

        }
        return resources;
    }


    /**
     * Finds all resources of a given account, at a given date,
     * in all projects that account is an active participant.
     *
     * @param email the identifier of the account
     * @param date  date at which the resource must be active
     * @return a list of Resources meeting the criteria
     */
    @Override
    public List<Resource> findAllActiveResourcesByEmail(Email email, LocalDate date) {
        AccountID associatedAccount = AccountID.of(email);
        List<Resource> resourcesList = new ArrayList<>();
        List<ResourceJPA> activeResources = resourceJPARepository.findByAssociatedAccountAndStartDateLessThanEqualAndEndDateGreaterThanEqual(associatedAccount, date, date);
        for (ResourceJPA jpa : activeResources) {
            Resource resource = resourceAssembler.toDomain(jpa);
            resourcesList.add(resource);
        }
        return resourcesList;
    }

    @Override
    public Optional<Resource> findResourceByID(ResourceID resourceID) {
        Optional<ResourceJPA> resourceJPAFound = resourceJPARepository.findById(resourceID);
        Optional<Resource> resourceFound = Optional.empty();
        if (resourceJPAFound.isPresent()) {
            resourceFound = Optional.of(resourceAssembler.toDomain(resourceJPAFound.get()));
        }
        return resourceFound;
    }

}
