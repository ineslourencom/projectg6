package switchtwentyone.project.interfaceAdapters.repositories.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.UserStoryJPARepository;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.datamodel.UserStoryJPA;
import switchtwentyone.project.datamodel.assembler.UserStoryDomainDataAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class UserStoryRepositoryImpl implements UserStoryRepository {

    @Autowired
    UserStoryJPARepository usJPARepo;
    @Autowired
    UserStoryDomainDataAssembler usDomaintoData;


    @Override
    public boolean saveUS(UserStory us) {
        Optional<UserStoryJPA> result = Optional.of(usJPARepo.save(usDomaintoData.toData(us)));
        return result.isPresent();
    }

    /**
     * This method finds all US in the repository that have a project ID passed as parameter.
     */
    @Override
    public List<UserStory> findAllUSByProjectID(ProjectID projID) {
        List<UserStoryJPA> newListUSJPA = usJPARepo.findByProjectID(projID.getProjectID());
        List<UserStory> newListUS = new ArrayList<>();

        for (UserStoryJPA jpa : newListUSJPA){
            UserStory us = usDomaintoData.toDomain(jpa);
            newListUS.add(us);
        }
        return newListUS;
    }

    @Override
    public Optional<UserStory> findByID(UserStoryID userStoryID) {
        Optional<UserStoryJPA> jpa = usJPARepo.findById(userStoryID);
        return jpa.map(userStoryJPA -> usDomaintoData.toDomain(userStoryJPA));
    }

    /**
     * As mentioned in https://www.baeldung.com/spring-data-crud-repository-save
     * the save method can be used to update a persisted entity in the DB.
     */
    @Override
    public UserStory save(UserStory userStory) {
       UserStoryJPA jpa = usDomaintoData.toData(userStory);
       UserStoryJPA jpaSaved = usJPARepo.save(jpa);
       return usDomaintoData.toDomain(jpaSaved);
    }

    @Override
    public boolean saveAll(List<UserStory> userStories) {
        List<UserStoryJPA> listJPAs = new ArrayList<>();
        for(UserStory us : userStories){
            listJPAs.add(usDomaintoData.toData(us));
        }
        List<UserStoryJPA> listJPAsSaved = (List<UserStoryJPA>) usJPARepo.saveAll(listJPAs);
        return listJPAsSaved.size() == userStories.size();
    }

    /**
     * List all non-decomposed User Stories from a given Project ordered
     * by priority.
     *
     * @param projectID project identifier
     * @return true if present, false if otherwise
     */
    @Override
    public List<UserStory> findAllByProjectIDNotDecomposed(ProjectID projectID) {
        List<UserStoryJPA> newListUSJPA = usJPARepo.findAllByProjectIDAndDecomposedFalseOrderByPriority(projectID.getProjectID());
        List<UserStory> newListUS = new ArrayList<>();
        for (UserStoryJPA usJPA : newListUSJPA){
            UserStory us = usDomaintoData.toDomain(usJPA);
            newListUS.add(us);
        }
        return newListUS;
    }

    @Override
    public List<UserStory> findAllByProjectIDOrderByPriority(ProjectID projectID) {
        List<UserStoryJPA> newListUSJPA = usJPARepo.findAllByProjectIDOrderByPriority(projectID.getProjectID());
        List<UserStory> newListUS = new ArrayList<>();
        for (UserStoryJPA usJPA : newListUSJPA){
            UserStory us = usDomaintoData.toDomain(usJPA);
            newListUS.add(us);
        }
        return newListUS;

    }
}
