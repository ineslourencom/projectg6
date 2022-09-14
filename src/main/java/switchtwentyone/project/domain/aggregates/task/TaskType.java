package switchtwentyone.project.domain.aggregates.task;

import switchtwentyone.project.domain.shared.ValueObject;

import java.util.Objects;

public class TaskType implements ValueObject<TaskType> {

    Type type;

    private TaskType (Type type){
        this.type = type;
    }

    public static TaskType meeting(){
        return new TaskType(Type.Meeting);
    }

    public static TaskType documentation(){
        return new TaskType(Type.Documentation);
    }

    public static TaskType design(){
        return new TaskType(Type.Design);
    }

    public static TaskType implementation(){
        return new TaskType(Type.Implementation);
    }

    public static TaskType testing(){
        return new TaskType(Type.Testing);
    }

    public static TaskType deployment(){
        return new TaskType(Type.Deployment);
    }

    public static TaskType dbConverter(String dbName){
        return new TaskType(Type.fromDbName(dbName));
    }

    public String getTaskTypeAsString(){
        return type.toString();
    }

    @Override
    public boolean sameValueAs(TaskType other) {
        return this.type.equals(other.type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskType taskType = (TaskType) o;
        return this.sameValueAs(taskType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
