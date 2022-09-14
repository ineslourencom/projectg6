package switchtwentyone.project.infrastructure.persistence.ijparepositories;

import org.springframework.data.repository.CrudRepository;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.datamodel.ProjectJPA;

import java.util.List;

public interface IProjectJPARepository extends CrudRepository<ProjectJPA, ProjectID> {

   List<ProjectJPA> findAll();
}
 