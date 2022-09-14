package switchtwentyone.project.infrastructure.persistence.ijparepositories;

import org.springframework.data.repository.CrudRepository;
import switchtwentyone.project.domain.aggregates.account.AccountID;
import switchtwentyone.project.datamodel.AccountJPA;

import java.util.List;
import java.util.Optional;

public interface IAccountJPARepository extends CrudRepository<AccountJPA, AccountID> {

    Optional<AccountJPA> findByEmail(String email);

    Optional<AccountJPA> findByAccountID(AccountID accountID);

    List<AccountJPA> findAll();


}
