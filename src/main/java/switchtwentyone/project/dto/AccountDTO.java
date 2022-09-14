package switchtwentyone.project.dto;

import java.util.Objects;

public class AccountDTO {

    public String accountID;
    public String email;
    public String name;
    public String function;
    public String photo;
    public String profileID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO that = (AccountDTO) o;
        return Objects.equals(accountID, that.accountID) && Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(function, that.function) && Objects.equals(photo, that.photo) && Objects.equals(profileID, that.profileID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID, email, name, function, photo, profileID);
    }

}

