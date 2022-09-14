package switchtwentyone.project.applicationServices.irepositories.iwebrepositories;

import switchtwentyone.project.externaldto.ExternalProjectDTO;

import java.util.List;

public interface ProjectWebRepository {
    List<ExternalProjectDTO> getProjects();
}
