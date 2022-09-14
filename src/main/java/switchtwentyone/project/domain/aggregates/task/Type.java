package switchtwentyone.project.domain.aggregates.task;

public enum Type {
    Meeting ("Meeting"),
    Documentation("Documentation"),
    Design("Design"),
    Implementation("Implementation"),
    Testing("Testing"),
    Deployment("Deployment");

    private String dbName;

    Type(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return this.dbName;
    }

    public static Type fromDbName(String dbName) {
        switch (dbName) {
            case "Meeting":
                return Type.Meeting;

            case "Documentation":
                return Type.Documentation;

            case "Design":
                return Type.Design;

            case "Implementation":
                return Type.Implementation;

            case "Testing":
                return Type.Testing;

            case "Deployment":
                return Type.Deployment;
            default:
                throw new IllegalArgumentException("dbName [" + dbName
                        + "] not supported.");
        }
    }

}
