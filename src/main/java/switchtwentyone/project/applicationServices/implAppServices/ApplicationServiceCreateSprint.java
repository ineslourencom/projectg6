package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.project.Project;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.sprint.Sprint;
import switchtwentyone.project.domain.aggregates.sprint.SprintID;
import switchtwentyone.project.domain.services.ServiceCreateSprint;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.dto.SprintDTO;
import switchtwentyone.project.dto.SprintTableDTO;
import switchtwentyone.project.dto.mapper.SprintMapper;
import switchtwentyone.project.dto.mapper.SprintTableMapper;
import switchtwentyone.project.interfaceAdapters.controllers.implControllers.SprintController;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProjectRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.SprintRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ApplicationServiceCreateSprint {
    @Autowired
    SprintRepository sprintRepo;

    @Autowired
    ProjectRepository projRepo;

    @Autowired
    ServiceCreateSprint servcCreateSprint;


    public SprintDTO createSprint(LocalDate startDate, ProjectID projID) {
        Project proj = projRepo.findById(projID).get();
        List<Sprint> listSprint = sprintRepo.findAllSprintsByProjectID(projID);
        PositiveNumber sprintNumber = servcCreateSprint.generateSprintNumber(listSprint);
        SprintID sptID = servcCreateSprint.generateSprintID(sprintNumber, projID);

        if (servcCreateSprint.startDateAfterLastSprint(listSprint, startDate)) {
            Sprint newSprint = proj.createSprint(sptID, startDate, sprintNumber);
            sprintRepo.saveSprint(newSprint);
            SprintDTO newSprintDTO = SprintMapper.toDTO(newSprint);
            return newSprintDTO;
        } else {
            throw new IllegalArgumentException("Start date must be after the end of the last sprint of the project");
        }
    }

    public Optional<Sprint> findSprintByID(SprintID sprintID) {
        return sprintRepo.findByID(sprintID);
    }

    public List<SprintTableDTO> getAllSprintsByProjectID(int id) {
        ProjectID projectID = new ProjectID(id);
        List<Sprint> sprint = sprintRepo.findAllSprintsByProjectID(projectID);
        List<SprintTableDTO> sprintDTOS = new ArrayList<>();
        for (Sprint spt : sprint) {
            SprintTableDTO dto = SprintTableMapper.tableMapperToDTO(spt);
            Link link = linkTo(methodOn(SprintController.class).getSprintById(spt.getSprintIDDouble())).withSelfRel();
            dto.add(link);
            sprintDTOS.add(dto);
        }
        return sprintDTOS;
    }

    public Optional<SprintDTO> setNewStartDate(LocalDate startDate, ProjectID projID, int id) {
        Project proj = projRepo.findById(projID).get();
        int projDuration = proj.getSprintDurationAsInt();
        proj.containsPeriod(Period.between(startDate, startDate.plusDays(projDuration)));
        SprintID sprintID = new SprintID(projID, PositiveNumber.of(id));
        Optional<Sprint> sprintopt = sprintRepo.findByID(sprintID);
        Optional<SprintDTO> sprintDTO = Optional.empty();
        List<Sprint> sprintsToBeSafe = new ArrayList<>();
        if (sprintopt.isPresent()) {
            Sprint sprint = sprintopt.get();
            List<Sprint> listSprint = sprintRepo.findAllSprintsByProjectID(projID);
            sprintsToBeSafe = servcCreateSprint.setStartDateInsubsequentSprints(listSprint, sprint, startDate);
            for (Sprint sprt :
                    sprintsToBeSafe) {
                sprintRepo.saveSprint(sprt);
            }
            sprint.setNewStartDate(startDate);
            sprintRepo.saveSprint(sprint);
            sprintDTO = Optional.of(SprintMapper.toDTO(sprint));
        }
        return sprintDTO;
    }
}
