package switchtwentyone.project.infrastructure.webclient.irestrepositories;

import org.springframework.hateoas.CollectionModel;
import switchtwentyone.project.externaldto.ExternalProjectDTO;

public interface IProjectRestRepository {
    CollectionModel<ExternalProjectDTO> getAllProjects();
}
