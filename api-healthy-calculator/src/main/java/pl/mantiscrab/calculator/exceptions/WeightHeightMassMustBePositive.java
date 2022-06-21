package pl.mantiscrab.calculator.exceptions;

public class WeightHeightMassMustBePositive extends RuntimeException {

    public WeightHeightMassMustBePositive(String message) {
        super(message);
    }
}
