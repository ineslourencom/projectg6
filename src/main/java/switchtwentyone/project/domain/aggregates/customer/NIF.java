package switchtwentyone.project.domain.aggregates.customer;

import lombok.NoArgsConstructor;
import switchtwentyone.project.domain.shared.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
@NoArgsConstructor
public class NIF implements ValueObject<NIF>, Serializable {

    private int NIF;

    private NIF (final int NIF){
        this.NIF = NIF;
    }

    public static NIF of(int NIF) {

        String number = String.valueOf(NIF);

        final int max=9;
        //check if is numeric and has 9 numbers
        if (!number.matches("[0-9]+") || number.length()!=max) {
            throw new IllegalArgumentException("Invalid NIF");
        }
        int checkSum=0;
        //calculate checkSum
        for (int i=0; i<max-1; i++){
            checkSum+=(number.charAt(i))*(max-i);
        }
        int checkDigit=11-(checkSum % 11);
        //if checkDigit is higher than 9 set it to zero
        if (checkDigit>9) checkDigit=0;
        //compare checkDigit with the last number of NIF

        if (checkDigit!=number.charAt(max-1)-'0'){
            throw new IllegalArgumentException("Invalid NIF");
        }

        return new NIF(NIF);
    }

    int getNIFAsINT(){
        return this.NIF;
    }

    @Override
    public boolean sameValueAs(NIF other) {
        return other != null && this.NIF == other.NIF;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NIF nif = (NIF) o;
        return this.sameValueAs(nif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NIF);
    }

    @Override
    public String toString(){
        return String.valueOf(this.NIF);
    }

}