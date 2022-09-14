package switchtwentyone.project.applicationServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IAccountJPARepository;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.domain.aggregates.account.Email;
import switchtwentyone.project.dto.AccountDTO;
import switchtwentyone.project.dto.mapper.AccountMapper;
import switchtwentyone.project.datamodel.assembler.AccountDomainDataAssembler;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;

import java.util.Optional;

@Service
public class ApplicationServiceGetAccount {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    IAccountJPARepository accountJPARepo;

    @Autowired
    AccountDomainDataAssembler accountDomainDataAssembler;

    public Optional<AccountDTO> getAccountInfo(String email) {
        Email newEmail = Email.of(email);
        Optional<AccountDTO> dtoOptional = Optional.empty();

        Optional<Account> account = accountRepository.findByEmail(newEmail);

        if (account.isPresent()) {
            dtoOptional = Optional.of(AccountMapper.toDTO(account.get()));
        }
        return dtoOptional;
    }

}


