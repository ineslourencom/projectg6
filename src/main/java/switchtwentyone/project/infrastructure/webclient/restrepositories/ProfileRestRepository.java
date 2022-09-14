package switchtwentyone.project.infrastructure.webclient.restrepositories;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Repository;
import switchtwentyone.project.externaldto.ExternalProfileDTO;
import switchtwentyone.project.infrastructure.webclient.irestrepositories.IProfileRestRepository;

import java.net.URI;

@Repository
public class ProfileRestRepository implements IProfileRestRepository {


    @Override
    public CollectionModel<ExternalProfileDTO> getAllProfiles() {
        try {
            Traverson traverson = new Traverson(new URI("http://vs864.dei.isep.ipp.pt:8080/switchproject-1.0-SNAPSHOT/local/"), MediaTypes.HAL_JSON);
            Traverson.TraversalBuilder tb = traverson.follow("profiles");
            ParameterizedTypeReference<CollectionModel<ExternalProfileDTO>> typeRefDevices = new ParameterizedTypeReference<CollectionModel<ExternalProfileDTO>>() {
            };
            CollectionModel<ExternalProfileDTO> resUsers = tb.toObject(typeRefDevices);


            return resUsers;
        } catch (Exception e) {
            return null;
        }

    }
}
