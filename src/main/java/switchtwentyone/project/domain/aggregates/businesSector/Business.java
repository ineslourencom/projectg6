package switchtwentyone.project.domain.aggregates.businesSector;

import switchtwentyone.project.domain.shared.Entity;
import switchtwentyone.project.domain.shared.Nameable;

import java.util.Objects;

/**
 * This class represents a business.
 */
public class Business implements Entity<Business> {


    /**
     * The name of the sector.
     */
    private Nameable description;
    /**
     * The code that identifies the sector,
     * according to Classificação das Actividades Económicas
     * Portuguesa por Ramos de Actividade.
     */
    private BusinesSectorID businesSectorID;

    protected Business(BusinesSectorID businesSectorID, Nameable desc) {
        this.businesSectorID = businesSectorID;
        this.description = desc;

    }

    public BusinesSectorID getID (){
        return this.businesSectorID;
    }

    public String getIDAsString() {
        return this.businesSectorID.getBusinessSectorIDAsSting();
    }

    public String getDescriptionAsString(){
        return this.description.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null
                || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return this.sameIdentityAs(business);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businesSectorID, description);
    }


    @Override
    public boolean sameIdentityAs(Business other) {
        return other != null
                && this.businesSectorID.equals(other.businesSectorID);
    }
}
