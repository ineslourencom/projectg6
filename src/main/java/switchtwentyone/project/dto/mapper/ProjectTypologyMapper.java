package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypology;
import switchtwentyone.project.dto.ProjectTypologyDTO;

import java.util.ArrayList;
import java.util.List;

public class ProjectTypologyMapper {

    public static List<ProjectTypologyDTO> toDTO(List<ProjectTypology> listOfProjectTypologies) {
        List<ProjectTypologyDTO> listOfDTOs = new ArrayList<>();
        for (int i = 0; i < listOfProjectTypologies.size(); i++) {
            ProjectTypologyDTO projectTypologyDTO = new ProjectTypologyDTO();
            projectTypologyDTO.name = listOfProjectTypologies.get(i).getProjectTypologyIDAsString();
            projectTypologyDTO.description = listOfProjectTypologies.get(i).getDescriptionAsString();
            listOfDTOs.add(projectTypologyDTO);
        }
        return listOfDTOs;
    }

    public static ProjectTypologyDTO toSingleDTO(ProjectTypology projectTypology) {

        ProjectTypologyDTO projectTypologyDTO = new ProjectTypologyDTO();
        projectTypologyDTO.name = projectTypology.getProjectTypologyIDAsString();
        projectTypologyDTO.description = projectTypology.getDescriptionAsString();

        return projectTypologyDTO;
    }
}
