package switchtwentyone.project.domain.aggregates.sprint;

public enum Category {

    To_do("To do"),
    In_progress("In progress"),
    Code_review("Code review"),
    Done("Done"),
    Rejected("Rejected");

    private String dbName;

    Category(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return this.dbName;
    }

    public static Category fromDbName(String dbName) {
        switch (dbName) {

            case "To do":
                return Category.To_do;

            case "In progress":
                return Category.In_progress;

            case "Code review":
                return Category.Code_review;

            case "Done":
                return Category.Done;

            case "Rejected":
                return Category.Rejected;

            default:
                throw new IllegalArgumentException("dbName [" + dbName
                        + "] not supported.");
        }
    }
}
