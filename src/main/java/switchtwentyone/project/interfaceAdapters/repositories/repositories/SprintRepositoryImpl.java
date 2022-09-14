package switchtwentyone.project.interfaceAdapters.repositories.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.ISprintJPARepository;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.datamodel.SprintJPA;
import switchtwentyone.project.datamodel.assembler.SprintDomainDataAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class SprintRepositoryImpl implements SprintRepository {

    @Autowired
    ISprintJPARepository sprintJPARepository;
    @Autowired
    SprintDomainDataAssembler sprintDomainDataAssembler;

    @Override
    public boolean saveSprint(Sprint sprint) {
        Optional<SprintJPA> result = Optional.of(sprintJPARepository.save(sprintDomainDataAssembler.toData(sprint)));
        return result.isPresent();
    }

    @Override
    public Optional<Sprint> findByID(SprintID sprintID) {
        Optional<SprintJPA> jpa = sprintJPARepository.findBySprintID(sprintID);
        if(jpa.isPresent()){
            return Optional.of(sprintDomainDataAssembler.toDomain(jpa.get()));
        }
        else{
            return Optional.empty();
        }
    }

    /**
     * This method finds all Sprints in the repository that have a project ID passed as parameter.
     */
    @Override
    public List<Sprint> findAllSprintsByProjectID(ProjectID projID) {
        List<SprintJPA> newListSprintJPA = sprintJPARepository.findAllByProjectID(projID.getProjectID());
        List<Sprint> newListSprint = new ArrayList<>();

        for (SprintJPA jpa : newListSprintJPA){
            Sprint sprint = sprintDomainDataAssembler.toDomain(jpa);
            newListSprint.add(sprint);
        }
        return newListSprint;
    }

    @Override
    public Optional<Sprint> findRunningSprintByProjectID(ProjectID projectID){
        Optional<Sprint> sprintFound = Optional.empty();
        List<SprintJPA> newListSprintJPA = sprintJPARepository.findAllByProjectID(projectID.getProjectID());
        List<Sprint> newListSprint = new ArrayList<>();

        for (SprintJPA jpa : newListSprintJPA){
            Sprint sprint = sprintDomainDataAssembler.toDomain(jpa);
            newListSprint.add(sprint);
        }


        for (Sprint sprint : newListSprint) {
            if (sprint.isRunning()) {
                sprintFound = Optional.of(sprint);
            }
        }
        return sprintFound;
    }

    @Override
    public boolean existsByID(SprintID sprintID) {
        return sprintJPARepository.existsById(sprintID);
    }


    }
// TO REMOVE ONCE VALIDATED NON USAGE
//    public List<Sprint> findAllBySprintIDAndProjectID(SprintID sprintID, ProjectID projectID) {
//        List<SprintJPA> newListSprintJPA = sprintJPARepository.findAllBySprintIDAndProjectID(sprintID, projectID.getProjectID());
//        List<Sprint> newListSprint = new ArrayList<>();
//
//        for (SprintJPA jpa : newListSprintJPA){
//            Sprint sprint = sprintDomainDataAssembler.toDomain(jpa);
//            newListSprint.add(sprint);
//        }
//        return newListSprint;
//    }







