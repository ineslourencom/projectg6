package switchtwentyone.project.infrastructure.webclient.irestrepositories;

import org.springframework.hateoas.CollectionModel;
import switchtwentyone.project.externaldto.ExternalProfileDTO;

public interface IProfileRestRepository {
    CollectionModel<ExternalProfileDTO> getAllProfiles() ;
}
