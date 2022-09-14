
package switchtwentyone.project.datamodel;


import lombok.*;
import org.hibernate.Hibernate;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;
import switchtwentyone.project.domain.aggregates.project.ProjectID;
import switchtwentyone.project.domain.aggregates.projectTypology.ProjectTypologyID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="project")
public class ProjectJPA {
    @Id
    @Embedded
    @Getter
    ProjectID id;

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private int sprintDuration;
    private String status;
    private int budget;
    @Embedded
    private BusinesSectorID businessSector;
    @Embedded
    private CustomerID customer;
   @Embedded
    private ProjectTypologyID projectTypologyID;
    private int plannedSprints;





    public ProjectJPA(ProjectID id, String name, String description, LocalDate startDate, LocalDate endDate,
                        int sprintDuration, String status, int budget,
                      BusinesSectorID businessSector, CustomerID customer, ProjectTypologyID projTypoID, int plannedSprints) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sprintDuration = sprintDuration;
        this.status = status;
        this.budget = budget;
        this.businessSector = businessSector;
        this.customer = customer;
        this.projectTypologyID = projTypoID;
        this.plannedSprints = plannedSprints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProjectJPA that = (ProjectJPA) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
