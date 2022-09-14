package switchtwentyone.project.domain.aggregates.resource;

public enum Erole {
    PRODUCT_OWNER("Product Owner"),
    PROJECT_MANAGER("Project Manager"),
    SCRUM_MASTER("Scrum Master"),
    DEVELOPER("Developer");
    private String description;
    public String getDescription(){return description;}

    private Erole(String description) {
        this.description = description;
    }
}
