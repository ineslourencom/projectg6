package switchtwentyone.project.domain.aggregates.project;

import org.springframework.stereotype.Component;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.valueObjects.Period;
import switchtwentyone.project.domain.valueObjects.PositiveNumber;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;
import switchtwentyone.project.domain.shared.Describable;
import switchtwentyone.project.domain.shared.Nameable;

@Component
public class ProjectFactory implements ProjectCreatable{

    private static ProjectFactory instance;

    private ProjectFactory(){
        //Empty constructor after Singleton pattern.
    }

    public static ProjectFactory getInstance() {
        if (instance == null) {
            instance = new ProjectFactory();
        }
        return instance;
    }


    @Override
    public Project createProject(ProjectID projectID, Nameable name, Describable description, Period period,
                                 PositiveNumber sprintDuration, PositiveNumber budget, CustomerID customer,
                                 BusinesSectorID businessSector, ProjectTypologyID projectTypologyID) {
        return new Project(projectID, name, description, period, sprintDuration, budget, customer, businessSector,
                projectTypologyID);
    }
}
