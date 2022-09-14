package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.account.Password;
import switchtwentyone.project.domain.aggregates.account.Photo;
import switchtwentyone.project.domain.aggregates.profile.Profile;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.AccountDTO;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.ProfileRepository;

import java.util.Optional;

@Service
public class ApplicationServiceCreateAccount {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProfileRepository profileRepository;

    public ApplicationServiceCreateAccount() {
        //empty constructor
    }

    public Optional<AccountDTO> createNewAccount(Email email,
                                                 NoNumberNoSymbolString name,
                                                 NoNumberNoSymbolString function,
                                                 Photo photo,
                                                 Password password) {

        ProfileID profileID;
        Optional<Profile> profileOpt = profileRepository.findProfileById(ProfileID.ofProfileType("Visitor"));
        if(profileOpt.isPresent()){
            profileID = profileOpt.get().getProfileID();
        } else {
            throw new IllegalArgumentException ("No default 'Visitor' profile exists");
        }

        AccountID accountID = AccountID.of(email);

        return accountRepository.createAndSaveAccount(accountID, email, name, function, photo, password, profileID);
    }
}
