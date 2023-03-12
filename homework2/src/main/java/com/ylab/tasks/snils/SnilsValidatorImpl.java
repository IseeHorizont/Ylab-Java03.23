package homework2.src.main.java.com.ylab.tasks.snils;

public class SnilsValidatorImpl implements SnilsValidator{

    @Override
    public boolean validate(String snils) {
        int snilsLengthShouldBe = 11;

        if (snils.contains(" ")) {
            snils = snils.replaceAll(" ", "");
        } else if (snils.contains("-")) {
            snils = snils.replaceAll("-", "");
        }

        if (snils.length() != snilsLengthShouldBe) {
            System.out.println("Error: Snils length not equals 11");
            return false;
        }
        for (int i = 0; i < snils.length(); i++) {
            if (!Character.isDigit(snils.charAt(i))) {
                System.out.println("Error: Snils must contains only digits");
                return false;
            }
        }

        // calculate control sum
        int controlSum = getControlSum(snils.substring(0, snils.length() - 2));

        // calculate control number from control sum
        int controlNumber = getControlNumber(controlSum);

        // comparing controlNumber with last two digits of snils
        int twoYoungestDigits = Integer.parseInt(snils.substring(snils.length() - 2));
        if (controlNumber == twoYoungestDigits) {
            return true;
        } else {
            System.out.println("Error: Wrong control number");
            return false;
        }
    }

    private int getControlSum(String first9Digit) {
        int sum = 0;
        for (int i = 0; i < first9Digit.length(); i++) {
            sum += Character.digit(first9Digit.charAt(i), 10) * (first9Digit.length() - i);
        }
        return sum;
    }

    private int getControlNumber(int controlSum) {
        int controlNumber = 0;
        if (controlSum < 100) {
            controlNumber = controlSum;
        } else if (controlSum > 101) {
            controlNumber = controlSum % 101;
            if (controlNumber == 100) {
                controlNumber = 0;
            }
        }
        return controlNumber;
    }

}
