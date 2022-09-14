package switchtwentyone.project.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class ProfileDTO extends RepresentationModel <ProfileDTO> {

    public String profileID;
    public String description;

    public ProfileDTO(String profileID ,String description) {
        this.profileID = profileID;
        this.description=description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProfileDTO that = (ProfileDTO) o;
        return profileID.equals(that.profileID) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), profileID, description);
    }

    public String getProfileID() {
        return profileID;
    }
    public String getDescription() {
        return description;
    }
}
