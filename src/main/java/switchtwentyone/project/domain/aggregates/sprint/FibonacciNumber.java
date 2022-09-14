package switchtwentyone.project.domain.aggregates.sprint;

import switchtwentyone.project.domain.shared.ValueObject;

import java.util.Objects;

public class FibonacciNumber implements ValueObject<FibonacciNumber> {

    private final int number;

    private FibonacciNumber(int number){
        this.number = number;
    }

    public static FibonacciNumber of(int number) {
        if (!verifyIfItsNumberFibonacci(number)) {
            throw new IllegalArgumentException("Amount value should be positive.");
        }
        return new FibonacciNumber(number);
    }
    /**
     * This method allows verifying if inserted number belongs to Fibonacci Sequence
     *
     * @param number inserted number to define effort estimate
     * @return if number belongs to Fibonacci Sequence
     */
    private static boolean verifyIfItsNumberFibonacci(int number) {
        boolean isFibonnacci = false;
        int num1 = 1;
        int num2 = 0;
        while ( num1 < 34) {
            num1 = num1 + num2;
            num2 = num1 - num2;

            if (number == num1) {
                isFibonnacci = true;
            }
        }
        return isFibonnacci;
    }

    public int getNumber(){return this.number;}

    @Override
    public boolean sameValueAs(FibonacciNumber other) {
        return other != null
                && this.number == other.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FibonacciNumber that = (FibonacciNumber) o;
        return this.sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
