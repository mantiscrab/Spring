package pl.mantiscrab.calculator.exceptions;

public class WeightHeightMustBePositiveException extends RuntimeException {
    public WeightHeightMustBePositiveException(String message) {
        super(message);
    }
}
