package switchtwentyone.project.datamodel;

import lombok.*;
import org.hibernate.Hibernate;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.dto.ProfileDTO;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class ProfileJPA {

    @EmbeddedId
    private ProfileID profileIDData;
    private String descriptionData;

    public ProfileJPA(ProfileDTO profileDTO) {
        this.profileIDData = ProfileID.ofProfileType(profileDTO.profileID);
        this.descriptionData = profileDTO.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProfileJPA that = (ProfileJPA) o;
        return profileIDData != null && Objects.equals(profileIDData, that.profileIDData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileIDData);
    }
}