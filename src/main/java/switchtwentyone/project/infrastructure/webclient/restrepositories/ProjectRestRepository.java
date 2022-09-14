package switchtwentyone.project.infrastructure.webclient.restrepositories;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Repository;
import switchtwentyone.project.externaldto.ExternalProjectDTO;
import switchtwentyone.project.infrastructure.webclient.irestrepositories.IProjectRestRepository;

import java.net.URI;

@Repository
public class ProjectRestRepository implements IProjectRestRepository {

    @Override
    public CollectionModel<ExternalProjectDTO> getAllProjects() {
        try {
            Traverson traverson = new Traverson(new URI("http://vs864.dei.isep.ipp.pt:8080/switchproject-1.0-SNAPSHOT/local/"), MediaTypes.HAL_JSON);
            Traverson.TraversalBuilder tb = traverson.follow("projects");
            ParameterizedTypeReference<CollectionModel<ExternalProjectDTO>> typeRefDevices = new ParameterizedTypeReference<CollectionModel<ExternalProjectDTO>>() {
            };
            CollectionModel<ExternalProjectDTO> resUsers = tb.toObject(typeRefDevices);


            return resUsers;
        } catch (Exception e) {
            return null;
        }
    }
}
