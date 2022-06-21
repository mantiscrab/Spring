package pl.mantiscrab.calculator.bmi;

import java.math.BigDecimal;
import java.math.MathContext;

public enum BmiType {
    UNDERWEIGHT_SEVERE_THINNESS(BigDecimal.valueOf(0.0), BigDecimal.valueOf(16.0)),
    UNDERWEIGHT_MODERATE_THINNESS(BigDecimal.valueOf(16.0), BigDecimal.valueOf(16.9)),
    UNDERWEIGHT_MILD_THINNESS(BigDecimal.valueOf(17.0), BigDecimal.valueOf(18.4)),
    NORMAL(BigDecimal.valueOf(18.5), BigDecimal.valueOf(24.9)),
    OVERWEIGHT(BigDecimal.valueOf(25.0), BigDecimal.valueOf(29.9)),
    OBESE_CLASS_I(BigDecimal.valueOf(30.0), BigDecimal.valueOf(34.9)),
    OBESE_CLASS_II(BigDecimal.valueOf(35.0), BigDecimal.valueOf(39.9)),
    OBESE_CLASS_III(BigDecimal.valueOf(40.0), BigDecimal.valueOf(10000000.0));

    private final BigDecimal lowerLimit;
    private final BigDecimal upperLimit;

    BmiType(BigDecimal lowerLimit, BigDecimal upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    private boolean inRange(BigDecimal bmi) {
        return bmi.compareTo(lowerLimit)>=0 && bmi.compareTo(upperLimit)<=0;
    }

    public static BmiType getBmiType(double bmi) {
        BigDecimal bmiBigDecimal = BigDecimal.valueOf(bmi).round(new MathContext(2));
        if (BmiType.UNDERWEIGHT_SEVERE_THINNESS.inRange(bmiBigDecimal))
            return BmiType.UNDERWEIGHT_SEVERE_THINNESS;
        else if (BmiType.UNDERWEIGHT_MODERATE_THINNESS.inRange(bmiBigDecimal))
            return BmiType.UNDERWEIGHT_MODERATE_THINNESS;
        else if (BmiType.UNDERWEIGHT_MILD_THINNESS.inRange(bmiBigDecimal))
            return BmiType.UNDERWEIGHT_MILD_THINNESS;
        else if (BmiType.NORMAL.inRange(bmiBigDecimal))
            return BmiType.NORMAL;
        else if (BmiType.OVERWEIGHT.inRange(bmiBigDecimal))
            return BmiType.OVERWEIGHT;
        else if (BmiType.OBESE_CLASS_I.inRange(bmiBigDecimal))
            return BmiType.OBESE_CLASS_I;
        else if (BmiType.OBESE_CLASS_II.inRange(bmiBigDecimal))
            return BmiType.OBESE_CLASS_II;
        else
            return BmiType.OBESE_CLASS_III;
    }

}
