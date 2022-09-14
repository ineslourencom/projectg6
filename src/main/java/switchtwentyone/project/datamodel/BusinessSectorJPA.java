package switchtwentyone.project.datamodel;


import lombok.*;
import org.hibernate.Hibernate;
import switchtwentyone.project.domain.aggregates.businesSector.BusinesSectorID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "Business_Sector")
public class BusinessSectorJPA {

    @EmbeddedId
    BusinesSectorID businesSectorID;
    String name;

    public BusinessSectorJPA(BusinesSectorID businesSectorID, String name){
        this.businesSectorID = businesSectorID;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BusinessSectorJPA that = (BusinessSectorJPA) o;
        return businesSectorID != null && Objects.equals(businesSectorID, that.businesSectorID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businesSectorID);
    }
}
