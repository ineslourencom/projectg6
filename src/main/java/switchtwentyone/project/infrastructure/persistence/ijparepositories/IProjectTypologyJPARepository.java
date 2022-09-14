package switchtwentyone.project.infrastructure.persistence.ijparepositories;
import org.springframework.data.repository.CrudRepository;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.datamodel.ProjectTypologyJPA;

public interface IProjectTypologyJPARepository extends CrudRepository<ProjectTypologyJPA, ProjectTypologyID> {

}
