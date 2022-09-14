package switchtwentyone.project.dto;

import java.util.Objects;

public class AccountShortDomainDTO {

    public String email;
    public String name;
    public String jobTitle;
    public String photo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountShortDomainDTO that = (AccountShortDomainDTO) o;
        return Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(jobTitle, that.jobTitle) && Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, jobTitle, photo);
    }
}

