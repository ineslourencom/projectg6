package switchtwentyone.project.datamodel;

import lombok.*;
import org.hibernate.Hibernate;
import switchtwentyone.project.domain.aggregates.userStory.UserStoryID;
import switchtwentyone.project.dto.USDTO;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="USER_STORIES")
public class UserStoryJPA {
    @EmbeddedId
    private UserStoryID usID;
    //We used the serialized class UserStoryID to identify the JPA. It is unique in the whole system.
    private int storyNumber;
    private String statement;
    private String detail;
    private boolean decomposed;
    private int priority;
    private int projectID;
    //because projectID is in a another aggregate, a foreign key constraint is not used

    private double usIDParent;
    //In an ideal scenario, the UserStoryJPA would have a one to one relationship (foreign key) with another UserStoryJPA
    //which is its parent. However, it was decided that it is better to isolate the UserStory from its parent and so
    //we are not using a foreign key.

    public UserStoryJPA(USDTO dto) {
        this.usID = UserStoryID.ofDouble(dto.usID);
        this.storyNumber = dto.storyNumber;
        this.statement = dto.statement;
        this.detail = dto.detail;
        this.decomposed = dto.isDecomposed;
        this.priority = dto.priority;
        this.projectID = dto.projID;
        this.usIDParent = dto.parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserStoryJPA that = (UserStoryJPA) o;
        return usID != null && Objects.equals(usID, that.usID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usID);
    }
}
