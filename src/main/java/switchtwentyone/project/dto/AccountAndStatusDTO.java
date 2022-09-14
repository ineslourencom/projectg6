package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class AccountAndStatusDTO extends RepresentationModel <AccountAndStatusDTO> {
    public String email;
    public boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountAndStatusDTO that = (AccountAndStatusDTO) o;
        return active == that.active && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, active);
    }
}
