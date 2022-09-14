package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class ProjectDTO extends RepresentationModel<ProjectDTO> {
    public String code;
    public String name;
    public String description;




    @Override
    public String toString() {
        return "ProjectDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDTO that = (ProjectDTO) o;
        return Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash( code, name, description);
    }
}
