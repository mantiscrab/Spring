package pl.mantiscrab.calculator.bmi;

import java.math.BigDecimal;

public class BmiFullInfo {
    private final double weight;
    private final double height;
    private final BigDecimal bmi;
    private final String type;

    public BmiFullInfo(double weight, double height, BigDecimal bmi, String type) {
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public BigDecimal getBmi() {
        return bmi;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return bmi.toString();
    }


}
