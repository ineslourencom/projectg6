package switchtwentyone.project.domain.services;

import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;


@Service
public class ServiceAccountPermissions {

    public boolean isDirector(Account userAccount){
        return userAccount.existsProfile(ProfileID.ofProfileType("Director"));
    }
}
