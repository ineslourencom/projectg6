package switchtwentyone.project.dto;

import java.util.Objects;

public class BusinessSectorDTO {
    public String code;
    public String description;

    //-TODO Testar!


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessSectorDTO that = (BusinessSectorDTO) o;
        return Objects.equals(code, that.code) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, description);
    }
}
