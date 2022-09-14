package switchtwentyone.project.domain.aggregates.customer;

import lombok.NoArgsConstructor;
import switchtwentyone.project.domain.shared.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
@NoArgsConstructor
public class CustomerID implements ValueObject<CustomerID>, Serializable {
    private NIF nif;

    private CustomerID(NIF nif) {
        this.nif = nif;
    }

    public static CustomerID of(NIF nif) {
        return new CustomerID(nif);
    }

    public int getCustomerIDAsInt (){
        return this.nif.getNIFAsINT();
    }

    @Override
    public boolean sameValueAs(CustomerID other) {
        return this.nif.equals(other.nif) && other.nif != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerID that = (CustomerID) o;
        return that.sameValueAs(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif);
    }

}
