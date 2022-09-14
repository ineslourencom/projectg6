package switchtwentyone.project.domain.aggregates.account;

import org.springframework.stereotype.Component;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import java.util.List;

@Component
public interface AccountCreatable {

    Account createAccount(final AccountID accountID,
                          final Email email,
                          final NoNumberNoSymbolString name,
                          final NoNumberNoSymbolString function,
                          final Photo photo,
                          final Password password,
                          final ProfileID profileID);

    Account createAccount(final AccountID accountID,
                          final Email email,
                          final NoNumberNoSymbolString name,
                          final NoNumberNoSymbolString function,
                          final Photo photo,
                          final Password password,
                          final List<ProfileID> profileID,
                          final boolean active);
}
