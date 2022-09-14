package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.dto.ProjectTypologyDTO;
import switchtwentyone.project.dto.mapper.ProjectTypologyMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectTypologyRepository;

import java.util.List;

@Service
public class ApplicationServiceGetAllProjectTypologies {

    @Autowired
    ProjectTypologyRepository projectTypologyRepository;

    public List<ProjectTypologyDTO> getProjectAllTypologies(){
        return ProjectTypologyMapper.toDTO(projectTypologyRepository.getAllProjectTypologies());
    }
}
