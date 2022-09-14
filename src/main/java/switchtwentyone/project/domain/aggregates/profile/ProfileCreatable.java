package switchtwentyone.project.domain.aggregates.profile;

import org.springframework.stereotype.Component;
import switchtwentyone.project.domain.valueObjects.Text;

@Component
public interface ProfileCreatable {

    static Profile createProfile(final ProfileID profileID, Text description){
            return new Profile(profileID, description);
    }
}
