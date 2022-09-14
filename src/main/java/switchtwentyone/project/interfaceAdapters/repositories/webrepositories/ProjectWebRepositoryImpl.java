package switchtwentyone.project.interfaceAdapters.repositories.webrepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Repository;
import switchtwentyone.project.applicationServices.irepositories.iwebrepositories.ProjectWebRepository;
import switchtwentyone.project.externaldto.ExternalProjectDTO;
import switchtwentyone.project.infrastructure.webclient.irestrepositories.IProjectRestRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class ProjectWebRepositoryImpl implements ProjectWebRepository {
    @Autowired
    IProjectRestRepository repository;

    @Override
    public List<ExternalProjectDTO> getProjects() {

        CollectionModel<ExternalProjectDTO> projectsCollectionModel= repository.getAllProjects();
        Collection<ExternalProjectDTO> projectsCollection = projectsCollectionModel.getContent();
        return new ArrayList<>(projectsCollection);
    }


}
