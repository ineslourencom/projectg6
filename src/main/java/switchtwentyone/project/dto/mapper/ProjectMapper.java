package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.businesSector.Business;
import switchtwentyone.project.domain.aggregates.customer.Customer;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.Nameable;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.dto.ProjectDTO;
import switchtwentyone.project.dto.ProjectInfoReturnedWhenCreatedDTO;
import switchtwentyone.project.dto.ProjectWithValueObjectsDTO;

import java.time.LocalDate;

public class ProjectMapper {
    private ProjectMapper() {
        //Not supposed to be instantiated
    }

    public static ProjectDTO toDTO(Project project) {

        ProjectDTO dto = new ProjectDTO();
        dto.code = Integer.toString(project.getIDAsInt());
        dto.name = project.getNameAsString();
        dto.description = project.getDescriptionAsString();
        return dto;
    }

    public static ProjectWithValueObjectsDTO toDTOWithValueObjects(Nameable name, Describable description,
                                                                   Period period,
                                                                   PositiveNumber sprintDuration,
                                                                   PositiveNumber budget,
                                                                   BusinesSectorID businessSector,
                                                                   CustomerID customer,
                                                                   ProjectTypologyID projectTypologyID) {
        ProjectWithValueObjectsDTO projectDTO = new ProjectWithValueObjectsDTO();
        projectDTO.name = name;
        projectDTO.description = description;
        projectDTO.period = period;
        projectDTO.sprintDuration = sprintDuration;
        projectDTO.budget = budget;
        projectDTO.businessSector = businessSector;
        projectDTO.customer = customer;
        projectDTO.projectTypologyID = projectTypologyID;

        return projectDTO;
    }

    public static ProjectInfoReturnedWhenCreatedDTO toDTOReturnedUpoCreation(Project project) {

        ProjectInfoReturnedWhenCreatedDTO dto = new ProjectInfoReturnedWhenCreatedDTO();

        dto.projectID = project.getIDAsInt();
        dto.name = project.getNameAsString();
        dto.description = project.getDescriptionAsString();
        dto.startDate = project.getStartDate();
        dto.endDate = project.getEndDate();
        dto.sprintDuration = project.getSprintDurationAsInt();
        dto.budget = project.getBudget();
        dto.CustomerID = project.getCustomer();
        dto.BusinessSectorID = project.getBusinessSector();
        dto.ProjectTypologyID = project.getProjectTypologyIDAsString();

        return dto;
    }

}
