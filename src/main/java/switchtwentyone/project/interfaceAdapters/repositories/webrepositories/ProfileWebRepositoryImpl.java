package switchtwentyone.project.interfaceAdapters.repositories.webrepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Repository;
import switchtwentyone.project.applicationServices.irepositories.iwebrepositories.ProfileWebRepository;
import switchtwentyone.project.externaldto.ExternalProfileDTO;
import switchtwentyone.project.infrastructure.webclient.irestrepositories.IProfileRestRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class ProfileWebRepositoryImpl implements ProfileWebRepository {
    @Autowired
    IProfileRestRepository repository;

    @Override
    public List<ExternalProfileDTO> getProfiles() {
        CollectionModel<ExternalProfileDTO> profilesCollectionModel= repository.getAllProfiles();
        Collection<ExternalProfileDTO> profilesCollection = profilesCollectionModel.getContent();
        return new ArrayList<>(profilesCollection);
    }

}
