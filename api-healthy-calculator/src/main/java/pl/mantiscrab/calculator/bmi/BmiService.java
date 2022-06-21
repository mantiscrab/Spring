package pl.mantiscrab.calculator.bmi;

import org.springframework.stereotype.Service;
import pl.mantiscrab.calculator.exceptions.WeightHeightMustBePositiveException;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
public class BmiService {
    BmiFullInfo getBmi(double weightInKg, double heightInCm) {
        if (weightInKg <= 0 || heightInCm <= 0)
            throw new WeightHeightMustBePositiveException("invalid data, weight and height parameters must be positive numbers");

        double heightInM = heightInCm / 100;
        double bmi = weightInKg / (heightInM * heightInM);
        BigDecimal bmiBigDecimal = BigDecimal.valueOf(bmi).round(new MathContext(2));
        BmiType bmiType = BmiType.getBmiType(bmi);

        return new BmiFullInfo(weightInKg, heightInCm, bmiBigDecimal, bmiType.name());
    }
}
