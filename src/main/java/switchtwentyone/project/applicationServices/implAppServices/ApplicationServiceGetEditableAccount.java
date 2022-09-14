package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.services.ServiceAccountLinks;
import switchtwentyone.project.dto.AccountShortDTO;
import switchtwentyone.project.dto.mapper.AccountMapper;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;

import java.util.Optional;


@Service
public class ApplicationServiceGetEditableAccount {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ServiceAccountLinks serviceAccountLinks;
//    @Autowired
//    ServiceAccountPermissions serviceAccountPermissions;

    public ApplicationServiceGetEditableAccount() {
        //this is an empty constructor
    }

    public Optional<AccountShortDTO> getAccountShortInfo(String email) {
        Email searchEmail = Email.of(email);
        Optional<AccountShortDTO> shortDtoOptional = Optional.empty();
        Optional<Account> account = accountRepository.findByEmail(searchEmail);

        if (account.isPresent()) {
            AccountShortDTO accountShortDTO = AccountMapper.toShortDTO(account.get());

            serviceAccountLinks.appendGlobalLinkCollection(account.get(), accountShortDTO);

//            if(serviceAccountPermissions.isDirector(account.get())){
//                serviceAccountLinks.appendDirectorLinkCollection(account.get(), accountShortDTO);
//            } else {
//                serviceAccountLinks.appendNotDirectorLinkCollection(account.get(), accountShortDTO);
//            }

            shortDtoOptional = Optional.of(accountShortDTO);
        }
        return shortDtoOptional;
    }



}
