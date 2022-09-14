package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.account.Photo;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.services.ServiceAccountLinks;
import switchtwentyone.project.domain.services.ServiceAccountPermissions;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.AccountShortDTO;
import switchtwentyone.project.dto.AccountShortDomainDTO;
import switchtwentyone.project.dto.mapper.AccountMapper;
import switchtwentyone.project.datamodel.assembler.AccountDomainDataAssembler;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;

import java.util.Optional;

@Service
public class ApplicationServiceUpdateAccount {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountDomainDataAssembler accountDomainDataAssembler;
    @Autowired
    ApplicationServiceGetEditableAccount applicationServiceGetEditableAccount;
    @Autowired
    ServiceAccountLinks serviceAccountLinks;
    @Autowired
    ServiceAccountPermissions serviceAccountPermissions;

    public ApplicationServiceUpdateAccount() {
        //this is an empty constructor
    }

    /**
     * This method updates the editable account attributes.
     *
     * @param domainDTO accounts email, function and photo DTO
     * @return a short account DTO if successful or empty if otherwise.
     */
    public Optional<AccountShortDTO> updateAccount(AccountShortDomainDTO domainDTO) {

        Email searchEmail = Email.of(domainDTO.email);
        NoNumberNoSymbolString newFunction = NoNumberNoSymbolString.of(domainDTO.jobTitle);
        Photo newPhoto = Photo.of(domainDTO.photo);

        Optional<AccountShortDTO> shortDtoOptional = Optional.empty();
        Optional<Account> storedAccount = Optional.empty();

        Optional<Account> account = accountRepository.findByEmail(searchEmail);

        if (account.isPresent()) {
            account.get().setFunction(newFunction);
            account.get().setPhoto(newPhoto);
            storedAccount = accountRepository.update(account);
        }
        if (storedAccount.isPresent()) {
            shortDtoOptional = Optional.of(AccountMapper.toShortDTO(storedAccount.get()));

            serviceAccountLinks.appendGlobalLinkCollection(storedAccount.get(), shortDtoOptional.get());

//            if(serviceAccountPermissions.isDirector(storedAccount.get())){
//                serviceAccountLinks.appendDirectorLinkCollection(account.get(), shortDtoOptional.get());
//            } else {
//                serviceAccountLinks.appendNotDirectorLinkCollection(account.get(), shortDtoOptional.get());
//            }
        }
        return shortDtoOptional;
    }

    /**
     * This method update the account profile.
     *
     * @param account
     * @param profileID
     * @return optional account or empty if unsuccessful.
     */
    public Optional<Account> updateAccount(Optional<Account> account, ProfileID profileID) {
        if(account.isPresent()){
            account.get().addProfile(profileID);
        }
        return accountRepository.update(account);
    }
}

