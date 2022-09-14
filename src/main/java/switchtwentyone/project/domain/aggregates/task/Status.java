package switchtwentyone.project.domain.aggregates.task;

public enum Status {

    Planned("Planned"),
    Running("Running"),
    Finished("Finished"),
    Blocked("Blocked");

    private String dbName;

    Status(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return this.dbName;
    }

    public static Status fromDbName(String dbName) {
        switch (dbName) {
            case "Planned":
                return Status.Planned;

            case "Running":
                return Status.Running;

            case "Finished":
                return Status.Finished;

            case "Blocked":
                return Status.Blocked;

            default:
                throw new IllegalArgumentException("dbName [" + dbName
                        + "] not supported.");
        }
    }


}
