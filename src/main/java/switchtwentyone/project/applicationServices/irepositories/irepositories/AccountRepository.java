package switchtwentyone.project.applicationServices.irepositories.irepositories;

import switchtwentyone.project.domain.aggregates.account.*;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.AccountDTO;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Optional<AccountDTO> createAndSaveAccount(AccountID accountID,
                                              Email email,
                                              NoNumberNoSymbolString name,
                                              NoNumberNoSymbolString function,
                                              Photo photo,
                                              Password password,
                                              ProfileID profileID);

    Optional<Account> update(Optional<Account> accountOpt);

    Optional<Account> findByEmail(Email email);

    Optional<Account> findByAccountID(AccountID accountID);

    boolean existsByAccountID(AccountID id);

    List<Account> findAll();

}
