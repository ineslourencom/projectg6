package switchtwentyone.project.domain.aggregates.resource;

import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;
import switchtwentyone.project.domain.shared.ValueObject;

import java.util.Objects;

public class Role implements ValueObject<Role> {

    private final Erole role;
    private final NoNumberNoSymbolString description;


    public Role(Erole role, NoNumberNoSymbolString description) {

        this.role = role;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return sameValueAs(role1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, description);
    }

    @Override
    public boolean sameValueAs(Role other) {
        return other!= null && this.role == other.role;
    }

    public String getRoleAsString() {
        return this.role.toString();
    }

    public String getDescriptionAsString() {
        return this.description.getValueString();
    }
}
