package pl.mantiscrab.calculator.bmr;

import org.springframework.stereotype.Service;
import pl.mantiscrab.calculator.exceptions.UnknownGenderException;
import pl.mantiscrab.calculator.exceptions.WeightHeightMassMustBePositive;

@Service
public class BmrService {

    public static final double MASS_MULTIPLIER = 10.0;
    public static final double HEIGHT_MULTIPLIER = 6.25;
    public static final double AGE_MULTIPLIER = 5.0;

    public BmrFullInfo getBmr(String gender, double weight, double height, int age) {
        if (weight <= 0 || height <= 0 || age <= 0)
            throw new WeightHeightMassMustBePositive("invalid data, weight, height and age parameters must be positive numbers");
        int s = switch (gender) {
            case "man" -> 5;
            case "woman" -> -161;
            default -> throw new UnknownGenderException();
        };

        int bmr = (int) (MASS_MULTIPLIER * weight + HEIGHT_MULTIPLIER * height - AGE_MULTIPLIER * age) + s;

        return new BmrFullInfo(gender, weight, height, age, bmr);
    }
}
