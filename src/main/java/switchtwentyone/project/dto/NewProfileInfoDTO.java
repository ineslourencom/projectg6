package switchtwentyone.project.dto;

import java.util.Objects;

public class NewProfileInfoDTO {

    private String profileType;
    private String description;

    public NewProfileInfoDTO(String profileType, String description) {
        this.profileType = profileType;
        this.description = description;
    }

    public String getProfileType() {
        return profileType;
    }

    public String getDescription() {return description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewProfileInfoDTO that = (NewProfileInfoDTO) o;
        return profileType.equals(that.profileType)
                && description.equalsIgnoreCase(that.description);
    }


    @Override
    public int hashCode() {
        return Objects.hash(profileType, description);
    }
}
