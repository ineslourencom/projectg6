package switchtwentyone.project.interfaceAdapters.repositories.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentyone.project.applicationServices.irepositories.irepositories.AccountRepository;
import switchtwentyone.project.infrastructure.persistence.ijparepositories.IAccountJPARepository;
import switchtwentyone.project.domain.aggregates.account.*;
import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.dto.AccountDTO;
import switchtwentyone.project.dto.mapper.AccountMapper;
import switchtwentyone.project.datamodel.AccountJPA;
import switchtwentyone.project.datamodel.assembler.AccountDomainDataAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    IAccountJPARepository IAccountJPARepository;
    @Autowired
    AccountDomainDataAssembler accountDomainDataAssembler;
    @Autowired
    AccountFactory accountFactory;

    /**
     * Method to update account in repository.
     * It must find the account to update, remove it and add the new version to
     * the repo.
     *
     * @param accountID id vo
     * @param email     email vo
     * @param name      name vo
     * @param function  function vo
     * @param photo     photo vo
     * @param password  password vo
     * @param profileID profileID vo
     * @return the created account if valid, empty if otherwise
     */
    @Override
    public Optional<AccountDTO> createAndSaveAccount(final AccountID accountID,
                                                     final Email email,
                                                     final NoNumberNoSymbolString name,
                                                     final NoNumberNoSymbolString function,
                                                     final Photo photo,
                                                     final Password password,
                                                     final ProfileID profileID) {

        Optional<AccountDTO> accountDTO = Optional.empty();
        if (!IAccountJPARepository.findById(accountID).isPresent()) {
            Account newAccount = accountFactory.createAccount(accountID, email, name, function, photo, password, profileID);
            IAccountJPARepository.save(accountDomainDataAssembler.toData(newAccount));
            accountDTO = Optional.of(AccountMapper.toDTO(newAccount));
        }
        return accountDTO;
    }

    /**
     * Method to update account in repository.
     * It must find the account to update, remove it and add the new version to
     * the repo.
     *
     * @param accountOpt new updated account
     * @return the updated account if found, empty if otherwise
     */
    @Override
    public Optional<Account> update(Optional<Account> accountOpt) {
        if (accountOpt.isPresent()) {
            AccountJPA accountJPAData = accountDomainDataAssembler.toData(accountOpt.get());
            IAccountJPARepository.save(accountJPAData);
        }
        return accountOpt;
    }

    /**
     * Method to retrieve an account in repository from a given email.
     *
     * @param email email
     * @return the stored account match if found, empty if otherwise
     */
    @Override
    public Optional<Account> findByEmail(Email email) {
        Optional<Account> accountOpt = Optional.empty();
        Optional<AccountJPA> accountJPA = IAccountJPARepository.findByEmail(email.getEmailData());
        if (accountJPA.isPresent()) {
            accountOpt = Optional.of(accountDomainDataAssembler.toDomain(accountJPA.get()));
        }
        return accountOpt;
    }

    /**
     * Method to retrieve an account in repository from a given account ID.
     *
     * @param accountID account ID
     * @return the stored account match if found, empty if otherwise
     */
    @Override
    public Optional<Account> findByAccountID(AccountID accountID) {
        Optional<Account> accountOpt = Optional.empty();
        Optional<AccountJPA> accountJPA = IAccountJPARepository.findById(accountID);
        if (accountJPA.isPresent()) {
            accountOpt = Optional.of(accountDomainDataAssembler.toDomain(accountJPA.get()));
        }
        return accountOpt;
    }

    @Override
    public boolean existsByAccountID(AccountID id){
        return IAccountJPARepository.existsById(id);
    }



    /**Returns all existing accounts
     *
     * @return all existing accounts
     */
    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        Iterable<AccountJPA> accountsJPA = IAccountJPARepository.findAll();
        for (AccountJPA a : accountsJPA) {
            Account account = accountDomainDataAssembler.toDomain(a);
            accounts.add(account);
        }
        return accounts;
    }

}
