package homework2.src.main.java.com.ylab.tasks.complexnumbers;

import java.util.Random;

public class ComplexNumber {

    private double realPartOfNumber;
    private double imaginaryPartOfNumber;

    public ComplexNumber(double realPartOfNumber) {
        this.realPartOfNumber = realPartOfNumber;
        Random random = new Random();
        this.imaginaryPartOfNumber = random.nextDouble(30);
    }

    public ComplexNumber(double realPartOfNumber, double imaginaryPartOfNumber) {
        this.realPartOfNumber = realPartOfNumber;
        this.imaginaryPartOfNumber = imaginaryPartOfNumber;
    }

    public ComplexNumber add(ComplexNumber number) {
        return new ComplexNumber(
            this.realPartOfNumber + number.getRealPartOfNumber(),
            this.imaginaryPartOfNumber + number.getImaginaryPartOfNumber()
        );
    }

    public ComplexNumber subtract(ComplexNumber number) {
        return new ComplexNumber(
            this.realPartOfNumber - number.getRealPartOfNumber(),
            this.imaginaryPartOfNumber - number.getImaginaryPartOfNumber()
        );
    }

    public ComplexNumber multiply(ComplexNumber number) {
        return new ComplexNumber(
                this.realPartOfNumber * number.getRealPartOfNumber() -
                        this.imaginaryPartOfNumber * number.getImaginaryPartOfNumber(),
                this.realPartOfNumber * number.getImaginaryPartOfNumber() +
                        this.imaginaryPartOfNumber * number.getRealPartOfNumber()
        );
    }

    public double abs() {
        double realPartPow = Math.pow(realPartOfNumber, 2);
        double imaginaryPartPow = Math.pow(imaginaryPartOfNumber, 2);

        return  Math.sqrt(realPartPow + imaginaryPartPow);
    }

    @Override
    public String toString() {
        return imaginaryPartOfNumber < 0 ?
                String.format("%s - %s * i",
                realPartOfNumber, Math.abs(imaginaryPartOfNumber))
                : String.format("%s + %s * i",
                realPartOfNumber, imaginaryPartOfNumber);
    }

    public double getRealPartOfNumber() {
        return realPartOfNumber;
    }

    public void setRealPartOfNumber(double realPartOfNumber) {
        this.realPartOfNumber = realPartOfNumber;
    }

    public double getImaginaryPartOfNumber() {
        return imaginaryPartOfNumber;
    }

    public void setImaginaryPartOfNumber(double imaginaryPartOfNumber) {
        this.imaginaryPartOfNumber = imaginaryPartOfNumber;
    }
}
