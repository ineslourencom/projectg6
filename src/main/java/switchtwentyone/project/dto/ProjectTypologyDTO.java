package switchtwentyone.project.dto;

import java.util.Objects;

public class ProjectTypologyDTO {

    public String name;
    public String description;


    //TODO - TESTAR!!!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTypologyDTO that = (ProjectTypologyDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
