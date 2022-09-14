package switchtwentyone.project.domain.aggregates.account;

import org.springframework.stereotype.Component;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import java.util.List;

@Component
public class AccountFactory implements AccountCreatable {

    @Override
    public Account createAccount(final AccountID accountID,
                                 final Email email,
                                 final NoNumberNoSymbolString name,
                                 NoNumberNoSymbolString function,
                                 Photo photo,
                                 Password password,
                                 ProfileID profileID) {
        return new Account(accountID, email, name, function, photo, password, profileID);
    }

    @Override
    public Account createAccount(final AccountID accountID,
                                 final Email email,
                                 final NoNumberNoSymbolString name,
                                 NoNumberNoSymbolString function,
                                 Photo photo,
                                 Password password,
                                 List<ProfileID> profileID,
                                 boolean active) {
        return new Account(accountID, email, name, function, photo, password, profileID, active);
    }

}
