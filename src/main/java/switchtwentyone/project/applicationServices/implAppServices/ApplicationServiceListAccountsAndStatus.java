package switchtwentyone.project.applicationServices.implAppServices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import switchtwentyone.project.domain.aggregates.account.Account;
import switchtwentyone.project.dto.AccountAndStatusDTO;
import switchtwentyone.project.dto.mapper.AccountMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceListAccountsAndStatus {
    @Autowired
    private AccountRepository accountRepository;

    public List<AccountAndStatusDTO> getAllAccountsAndStatus() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountAndStatusDTO> accountsDTOS = new ArrayList<>();
        for (Account a : accounts) {
            AccountAndStatusDTO dto = AccountMapper.toAccountAndStatusDTO(a);
            accountsDTOS.add(dto);
        }
        return  accountsDTOS;
    }


}
