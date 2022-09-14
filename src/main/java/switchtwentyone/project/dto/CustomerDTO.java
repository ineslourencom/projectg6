package switchtwentyone.project.dto;


import java.util.Objects;

public class CustomerDTO {
    public int NIF;
   public String name;

   //TODO - Testar!!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return NIF == that.NIF && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NIF, name);
    }
}
