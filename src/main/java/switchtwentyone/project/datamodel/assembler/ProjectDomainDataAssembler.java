package switchtwentyone.project.datamodel.assembler;

import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.project.*;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.valueObjects.Text;
import switchtwentyone.project.datamodel.ProjectJPA;

import java.time.LocalDate;

@Service
public class ProjectDomainDataAssembler {

    public ProjectJPA toData(Project project) {
        ProjectID projID = project.getID();
        String name = project.getNameAsString();
        String description = project.getDescriptionAsString();
        LocalDate startDate = project.getStartDate();
        LocalDate endDate = project.getEndDate();
        int sprintDuration = project.getSprintDurationAsInt();
        String status = project.getStatusAsString();
        int budget = project.getBudget();
        CustomerID customer = project.getCustomerID();
        BusinesSectorID businessSector = project.getBusinessSectorID();
        ProjectTypologyID projTypoID = project.getProjectTypologyID();
        int plannedSprints = project.getPlannedSprintsAsInt();
        return new ProjectJPA(projID, name, description, startDate, endDate, sprintDuration, status, budget, businessSector, customer, projTypoID, plannedSprints);
    }


    public Project toDomain(ProjectJPA projJPA) {
        ProjectID projID = projJPA.getId();
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(projJPA.getName());
        Describable description = Text.write(projJPA.getDescription());
        LocalDate endDate = projJPA.getEndDate();
        Period period;
        if (endDate != null) {
            period = Period.between(projJPA.getStartDate(), projJPA.getEndDate());
        } else{
            period = Period.starting(projJPA.getStartDate());
        }
        PositiveNumber sprintDuration = PositiveNumber.of(projJPA.getSprintDuration());
        PositiveNumber budget = PositiveNumber.of(projJPA.getBudget());
        CustomerID customer = projJPA.getCustomer();
        BusinesSectorID busSector = projJPA.getBusinessSector();
        ProjectTypologyID projTypoID = projJPA.getProjectTypologyID();
        Project proj = new Project(projID, name, description, period, sprintDuration, budget, customer, busSector, projTypoID);
        proj.setStatus(PredefinedStatus.of(projJPA.getStatus()));
        int plannedSprints= projJPA.getPlannedSprints();
        if(plannedSprints>0){
        proj.setPlannedSprints(PositiveNumber.of(plannedSprints));}
        return proj;
    }
}
