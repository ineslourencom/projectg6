package switchtwentyone.project.datamodel.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.account.*;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.datamodel.AccountJPA;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountDomainDataAssembler {

    @Autowired
    AccountFactory accountFactory;

    public AccountJPA toData(Account account ) {
        List<String> profileIDs = new ArrayList<>();

        AccountID accountID = account.getAccountID();
        String email = account.getEmail().getEmailData();
        String name = account.getName().getValueString();
        String function = account.getFunction().getValueString();
        String photo = account.getPhoto().getPhotoString();
        String password = account.getPassword().getString();
        for(ProfileID p : account.getProfileIDs()){
            profileIDs.add(p.getProfileType().getValue());
        }
        boolean active = account.getActive();

        return new AccountJPA(accountID, email, name, function, photo, password, profileIDs, active);
    }

    public Account toDomain(AccountJPA accountJPA ) {
        List<ProfileID> profileIDs = new ArrayList<>();

        AccountID accountID = accountJPA.getAccountID();
        Email email = Email.of(accountJPA.getEmail());
        NoNumberNoSymbolString name = NoNumberNoSymbolString.of(accountJPA.getName());
        NoNumberNoSymbolString function = NoNumberNoSymbolString.of(accountJPA.getFunction());
        Photo photo = Photo.of(accountJPA.getPhoto());
        Password password = Password.of(accountJPA.getPassword(),1);
        for(String p : accountJPA.getProfileIDs()){
            profileIDs.add(ProfileID.ofProfileType(p));
        }
        boolean active = accountJPA.isActive();

        return accountFactory.createAccount(accountID, email, name, function, photo, password, profileIDs, active);
    }
}
