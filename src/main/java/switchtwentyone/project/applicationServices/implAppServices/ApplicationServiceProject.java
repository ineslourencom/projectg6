package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.dto.ProjectInfoDTO;
import switchtwentyone.project.dto.ProjectInfoDomainDTO;
import switchtwentyone.project.dto.mapper.ProjectInfoToDTOMapper;
import switchtwentyone.project.dto.mapper.ProjectInfoToDomainMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;

import java.util.Optional;

@Service
public class ApplicationServiceProject {
    @Autowired
    ProjectRepository projectRepository;

    public Optional<ProjectInfoDTO> getProjectDetails(int id) {
        ProjectID pid = new ProjectID(id);

        Optional<Project> opProject = projectRepository.findById(pid);
        ProjectInfoDTO dto;

        if (opProject.isPresent()) {

            Project project = opProject.get();
            dto = ProjectInfoToDTOMapper.toDTO(project);
            return Optional.of(dto);

        } else {
            return Optional.empty();
        }

    }
    public Optional <ProjectInfoDTO> editProject(int id, ProjectInfoDTO dto){
        ProjectID pid = new ProjectID(id);
        Optional<Project> opProject = projectRepository.findById(pid);

        if (opProject.isPresent()) {
            ProjectInfoDomainDTO details = ProjectInfoToDomainMapper.toDomain(dto);
            Project project = opProject.get();
            project.edit(details);
            Project editedProject = projectRepository.save(project);
            return Optional.of(ProjectInfoToDTOMapper.toDTO(editedProject));

        } else {
            return Optional.empty();
        }


    }


}
