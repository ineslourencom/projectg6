package switchtwentyone.project.dto.mapper;

import switchtwentyone.project.domain.aggregates.account.*;
import switchtwentyone.project.dto.AccountAndStatusDTO;
import switchtwentyone.project.dto.AccountDTO;
import switchtwentyone.project.dto.AccountShortDTO;
import switchtwentyone.project.dto.AccountShortDomainDTO;


public class AccountMapper {
    private AccountMapper() {
    }

    public static AccountDTO toDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.accountID = account.getAccountID().getID().getEmailData();
        dto.email = account.getEmail().getEmailData();
        dto.name = account.getName().getValue();
        dto.function = account.getFunction().getValue();
        dto.photo = account.getPhoto().getPhotoString();
        dto.profileID = account.getActiveProfileID().getProfileType().getValue();
        return dto;
    }

    public static AccountShortDTO toShortDTO(Account account) {
        AccountShortDTO dto = new AccountShortDTO();
        dto.email = account.getEmail().getEmailData();
        dto.name = account.getName().getValue();
        dto.jobTitle = account.getFunction().getValue();
        dto.photo = account.getPhoto().getPhotoString();
        return dto;
    }

    public static AccountShortDomainDTO toShortDomainDTO(Account account) {
        AccountShortDomainDTO dto = new AccountShortDomainDTO();
        dto.email = account.getEmail().getEmailData();
        dto.name = account.getName().getValue();
        dto.jobTitle = account.getFunction().getValue();
        dto.photo = account.getPhoto().getPhotoString();
        return dto;
    }

    public static AccountAndStatusDTO toAccountAndStatusDTO(Account account) {
        AccountAndStatusDTO dto = new AccountAndStatusDTO();
        dto.email = account.getEmail().getEmailData();
        dto.active = account.getActive();
        return dto;
    }
}



