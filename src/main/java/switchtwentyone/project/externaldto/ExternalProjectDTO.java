package switchtwentyone.project.externaldto;

import org.springframework.hateoas.RepresentationModel;

public class ExternalProjectDTO extends RepresentationModel<ExternalProjectDTO> {
    public String id;
    public String name;
    public String description;
    public String businessSector;
    public int sprintDuration;
    public int plannedSprints;
    public String startDate;
    public String endDate;
    public double budgetAmount;
    public String budgetCurrency;
    public String customerName;
    public String customerVat;
    public String typology;
    public String status;

}
