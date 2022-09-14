package switchtwentyone.project.dto;

import lombok.Getter;

import java.util.Objects;

public class NewProfileRequestInfoDTO {
    @Getter
    public String email;
    @Getter
    public String profileType;

    public NewProfileRequestInfoDTO() {
    }

    public NewProfileRequestInfoDTO(String email, String profileType) {
        this.email = email;
        this.profileType = profileType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewProfileRequestInfoDTO)) return false;
        NewProfileRequestInfoDTO prDTO = (NewProfileRequestInfoDTO) o;
        return email.equalsIgnoreCase(String.valueOf(prDTO.email))
                && profileType.equalsIgnoreCase(String.valueOf(prDTO.profileType));


    }

    @Override
    public int hashCode() {
        return Objects.hash(email, profileType);
    }
}
