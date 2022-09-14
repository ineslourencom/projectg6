package switchtwentyone.project.domain.aggregates.businesSector;

import switchtwentyone.project.domain.shared.Nameable;

public interface BusinesSectorCreatable {
    Business createBusinessSector(BusinesSectorID businesSectorID, Nameable name);
}
