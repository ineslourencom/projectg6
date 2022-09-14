package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IAccountJPARepository;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.dto.AccountDTO;
import switchtwentyone.project.dto.mapper.AccountMapper;
import switchtwentyone.project.datamodel.assembler.AccountDomainDataAssembler;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;

import java.util.Optional;

@Service
public class ApplicationServiceUpdateProfile {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    IAccountJPARepository accountJPARepo;

    @Autowired
    AccountDomainDataAssembler accountDomainDataAssembler;

    @Autowired
    ApplicationServiceGetAccount applicationServiceGetAccount;

    @Autowired
    ApplicationServiceUpdateAccount applicationServiceUpdateAccount;


    public Optional<AccountDTO> updateProfile(Email email, ProfileID pf) {


        Optional<AccountDTO> accountDTO = Optional.empty();
        Optional<Account> account = accountRepository.findByEmail(email);

        if (account.isPresent()) {
            Optional<Account> accountOptional = applicationServiceUpdateAccount.updateAccount(account, pf);
            AccountDTO accountDTO1 = AccountMapper.toDTO(accountOptional.get());
            accountDTO = Optional.of(accountDTO1);
        }

        return accountDTO;
    }


}
