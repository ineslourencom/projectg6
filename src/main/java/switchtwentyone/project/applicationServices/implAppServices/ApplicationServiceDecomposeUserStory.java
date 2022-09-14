package switchtwentyone.project.applicationServices.implAppServices;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.userStory.UserStory;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.domain.services.ServiceDecomposeUS;
import switchtwentyone.project.dto.ChildUserStoryDTO;
import switchtwentyone.project.dto.NewUserStoryDomainDTO;
import switchtwentyone.project.dto.USDTO;
import switchtwentyone.project.dto.mapper.NewUserStoryDomainMapper;
import switchtwentyone.project.dto.mapper.UserStoryMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.UserStoryRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceDecomposeUserStory {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserStoryRepository userStoryRepository;
    @Autowired
    ServiceDecomposeUS decomposeUSService;


    public List<USDTO> decomposeUserStory(double userStoryID,
                                          List<ChildUserStoryDTO> newUserStories, int projectID) {

        List<NewUserStoryDomainDTO> newUserStoriesDomain = convertToDomainDTOS(newUserStories);

        UserStoryID parentID = UserStoryID.ofDouble(userStoryID);
        UserStory parent = findUserStory(parentID);
        ProjectID projID = new ProjectID(projectID);
        findProject(projID);
        List<UserStory> listUS = userStoryRepository.findAllUSByProjectID(projID);
        List<UserStory> children = decomposeUSService.generateChildren(listUS, newUserStoriesDomain, projID, parent);
        userStoryRepository.saveAll(children);
        parent.markAsDecomposed();
        userStoryRepository.save(parent);

        return convertParentAndChildrenToDTO(parent, children);
    }

    @NotNull
    private Project findProject(ProjectID projectID) {
        Optional<Project> opProject = projectRepository.findById(projectID);
        if (!opProject.isPresent()) {
            throw new EntityNotFoundException("Project not found");
        }
        Project project = opProject.get();
        return project;
    }

    @NotNull
    private UserStory findUserStory(UserStoryID usID) {
        Optional<UserStory> opParent = userStoryRepository.findByID(usID);
        if (!opParent.isPresent()) {
            throw new EntityNotFoundException("User Story not found");
        }
        UserStory parent = opParent.get();
        return parent;
    }

    @NotNull
    private List<USDTO> convertParentAndChildrenToDTO(UserStory parent, List<UserStory> children) {
        List<USDTO> modifiedUS = new ArrayList<>();
        USDTO parentDTO = UserStoryMapper.toDTO(parent);
        modifiedUS.add(parentDTO);

        for (UserStory us : children) {
            USDTO dto = UserStoryMapper.toDTO(us);
            modifiedUS.add(dto);
        }
        return modifiedUS;
    }

    @NotNull
    private List<NewUserStoryDomainDTO> convertToDomainDTOS(List<ChildUserStoryDTO> newUserStories) {
        List<NewUserStoryDomainDTO> newUserStoriesDomain = new ArrayList<>();

        for (ChildUserStoryDTO dto : newUserStories) {
            NewUserStoryDomainDTO domainDTO = NewUserStoryDomainMapper.toDomain(dto);
            newUserStoriesDomain.add(domainDTO);
        }
        return newUserStoriesDomain;
    }

}
