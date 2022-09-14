package switchtwentyone.project.domain.aggregates.businesSector;

import lombok.NoArgsConstructor;
import switchtwentyone.project.domain.shared.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
@NoArgsConstructor
public class BusinesSectorID implements ValueObject<BusinesSectorID>, Serializable {
    private CAE cae;

    private BusinesSectorID(CAE cae) {
        this.cae = cae;
    }

    public static BusinesSectorID of(CAE cae) {
        return new BusinesSectorID(cae);
    }

    public String getBusinessSectorIDAsSting (){
        return this.cae.getCAEAsString();
    }

    @Override
    public boolean sameValueAs(BusinesSectorID other) {
        return other != null && this.cae.equals( other.cae);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinesSectorID that = (BusinesSectorID) o;
        return this.sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cae);
    }
}
