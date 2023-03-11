package homework2.src.main.java.com.ylab.tasks.complexnumbers;

public class ComplexNumberTest {

    public static void main(String[] args) {
        // create complex number with 1 param
        ComplexNumber firstNumber = new ComplexNumber(-2.22);

        // create complex number with 2 param
        ComplexNumber secondNumber = new ComplexNumber(1, 2);
        ComplexNumber thirdNumber = new ComplexNumber(-2, 1);
        ComplexNumber fourthNumber = new ComplexNumber(3.33, -4.44);

        // addition

        ComplexNumber additionResult = secondNumber.add(thirdNumber);
        System.out.println(String.format("Addition of two complex number '%s' & '%s' = %s",
                secondNumber, thirdNumber, additionResult));
        System.out.println();

        // subtraction

        ComplexNumber subtractionResult = secondNumber.subtract(thirdNumber);
        System.out.println(String.format("Subtraction of two complex number '%s' & '%s' = %s",
                secondNumber, thirdNumber, subtractionResult));
        System.out.println();

        // multiplying

        ComplexNumber multiplyingResult = secondNumber.multiply(thirdNumber);
        System.out.println(String.format("Multiplying of two complex number '%s' & '%s' = %s",
                secondNumber, thirdNumber, multiplyingResult));
        System.out.println();

        // getting module

        double firstNumberByModule = firstNumber.abs();
        System.out.println(String.format("Complex number '%s' by module = %s",
                firstNumber, firstNumberByModule));

        double thirdNumberByModule = thirdNumber.abs();
        System.out.println(String.format("Complex number '%s' by module = %s",
                thirdNumber, thirdNumberByModule));

        double fourthNumberByModule = fourthNumber.abs();
        System.out.println(String.format("Complex number '%s' by module = %s",
                fourthNumber, fourthNumberByModule));
    }

}
