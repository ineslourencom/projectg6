package switchtwentyone.project.datamodel;

import lombok.*;
import org.hibernate.Hibernate;
import switchtwentyone.project.domain.aggregates.account.AccountID;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class AccountJPA {
    @EmbeddedId
    private AccountID accountID;
    private String email;
    private String name;
    private String function;
    private String photo;
    private String password;
    @ElementCollection
    private List<String> profileIDs = new ArrayList<>();
    private boolean active;


    public AccountJPA(AccountID accountID, String email, String name,
                      String function, String photo, String password,
                      List<String> profileIDs, boolean active) {
        this.accountID = accountID;
        this.email = email;
        this.name = name;
        this.function = function;
        this.photo = photo;
        this.password = password;
        this.profileIDs = new ArrayList<>(profileIDs);
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AccountJPA that = (AccountJPA) o;
        return accountID != null && Objects.equals(accountID, that.accountID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID,email,name,function, photo, password,profileIDs,active );
    }
}