package switchtwentyone.project.datamodel;


import lombok.*;
import org.hibernate.Hibernate;
import switchtwentyone.project.domain.aggregates.customer.CustomerID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerJPA {

    @EmbeddedId
    CustomerID NIF;
    private String name;

    public CustomerJPA(CustomerID nif, String name ){
        this.NIF = nif;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustomerJPA that = (CustomerJPA) o;
        return NIF != null && Objects.equals(NIF, that.NIF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NIF);
    }
}
