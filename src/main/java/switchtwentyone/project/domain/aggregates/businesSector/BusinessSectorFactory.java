package switchtwentyone.project.domain.aggregates.businesSector;

import org.springframework.stereotype.Component;
import switchtwentyone.project.domain.shared.Nameable;

@Component
public class BusinessSectorFactory implements BusinesSectorCreatable {
    @Override
    public Business createBusinessSector(BusinesSectorID businesSectorID, Nameable name) {
        return new Business(businesSectorID, name);
    }
}
