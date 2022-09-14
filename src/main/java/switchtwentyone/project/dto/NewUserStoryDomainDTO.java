package switchtwentyone.project.dto;

import switchtwentyone.project.domain.valueObjects.Text;

import java.util.Objects;

public class NewUserStoryDomainDTO {
    public Text statement;
    public Text detail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewUserStoryDomainDTO domainDTO = (NewUserStoryDomainDTO) o;
        return Objects.equals(statement, domainDTO.statement) && Objects.equals(detail, domainDTO.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statement, detail);
    }
}
