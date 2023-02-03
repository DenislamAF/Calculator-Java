package Solution;
import java.util.Scanner;

public class Main {
    public static final String operations = "+-*/";

    public static void main(String[] args) throws Exception {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("end")) { break; }
            try {
                System.out.println(calc(input));
            } catch (Exception e) {
                System.out.println("Перехвачено исключение. Работа завершена");
                break;
            }
        }
    }
    public static String calc(String input) throws Exception {
        input = input.replace(" ", "");
        int index = indexOfOperation(input);
        int firstValue = 0, secondValue = 0, result = 0;

        firstValue = findArabicValue(input.substring(0, index));
        secondValue = findArabicValue(input.substring(index + 1));
        result = makeResult(firstValue, secondValue, input.charAt(index));

        if (firstValue != -1 && secondValue != -1) {
            return String.valueOf(result);
        }

        firstValue = findRomanValue(input.substring(0, index));
        secondValue = findRomanValue(input.substring(index + 1, input.length()));
        result = makeResult(firstValue, secondValue, input.charAt(index));

        if (firstValue != -1 && secondValue != -1 && result > 0) {
            return convertingArabicToRoman(result);
        }
        throw new Exception();
    }

    private static int indexOfOperation(String input) throws Exception { // находит индекс символа арифметической
        for (int i = 0; i < input.length(); i++) {                       // операции в полученной строке
            if (operations.indexOf(input.charAt(i)) != -1) {
                return i;
            }
        }
        throw new Exception();
    }

    private static int findArabicValue(String str) {    // поиск допустимых значений в переданной строке среди арабских чисел
        String[] arabicValues = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        for (int i = 0; i < arabicValues.length; i++) {
            if (str.equals(arabicValues[i])) {
                return i + 1;
            }
        }
        return -1;
    }

    private static int findRomanValue(String str) {    // поиск допустимых значений в переданной строке среди римских чисел
        RomanValues[] romanValues = RomanValues.values();
        for (int i = 0; i < romanValues.length; i++) {
            if (str.equals(romanValues[i].toString())) {
                return i + 1;
            }
        }
        return -1;
    }

    private static int makeResult(int a, int b, char operation) throws Exception {
        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                throw new Exception();
        }
    }

    private static String convertingArabicToRoman(int a) {
        if (a == 0) { return ""; }
        RomanNumerals[] romanNumerals = RomanNumerals.values();
        int i = 0;
        while (a - romanNumerals[i].toInt() >= 0) {
            i++;
        }
        return romanNumerals[i - 1].toString() + convertingArabicToRoman(a - romanNumerals[i - 1].toInt());
    }
}
