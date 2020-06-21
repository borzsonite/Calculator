
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alpha on 19.06.2020.
 */
public class Calculator {
    public static void main(String[] args) {


        while (true) {
            System.out.print("Введите выражение: ");
            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();
            String operator = null;
            int result = 0;

            input = input.replaceAll("\\s+", "");

            if (!(input.matches("([1-9]|10)([+\\-*/])([1-9]|10)") |
                    input.matches("(I{1,3}|IV|V|VI{1,3}|(IX)|X)([+\\-*/])(I{1,3}|IV|V|VI{1,3}|(IX)|X)"))) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Wrong input");
                }
            }

            Pattern regex = Pattern.compile("([+\\-*/])");
            Matcher matcher = regex.matcher(input);
            while (matcher.find()) {
                operator = matcher.group();
            }

            String val1 = input.split("[+-/\\*]")[0];
            String val2 = input.split("[+-/\\*]")[1];

            if (val1.matches("\\d+")) {
                System.out.println(calculate(val1, val2, operator));
            } else if (Integer.parseInt(romanToArabic(val2)) >= Integer.parseInt(romanToArabic(val1)) & operator.equals("-")) {
                System.out.println("");
            } else System.out.println(convertToRoman(calculate(romanToArabic(val1), romanToArabic(val2), operator)));
        }
    }

    public static int calculate(String val1, String val2, String operator) {

        int value1 = Integer.parseInt(val1);
        int value2 = Integer.parseInt(val2);

        switch (operator) {
            case "+": {
                return value1 + value2;
            }
            case "-": {
                return value1 - value2;
            }
            case "*": {
                return value1 * value2;
            }
            case "/": {
                return value1 / value2;
            }
            default:
                return 0;
        }
    }

    public static String romanToArabic(String val) {
        Map<String, String> digits = new HashMap<>();
        digits.put("1", "I");
        digits.put("2", "II");
        digits.put("3", "III");
        digits.put("4", "IV");
        digits.put("5", "V");
        digits.put("6", "VI");
        digits.put("7", "VII");
        digits.put("8", "VIII");
        digits.put("9", "IX");
        digits.put("10", "X");

        for (Map.Entry<String, String> elem : digits.entrySet()) {
            if (val.equals(elem.getValue())) val = elem.getKey();
        }
        return val;
    }

    public static String convertToRoman(int number) {

        Map<String, Integer> roman = new LinkedHashMap<>();

        roman.put("M", 1000);
        roman.put("CM", 900);
        roman.put("D", 500);
        roman.put("CD", 400);
        roman.put("C", 100);
        roman.put("XC", 90);
        roman.put("L", 50);
        roman.put("XL", 40);
        roman.put("X", 10);
        roman.put("IX", 9);
        roman.put("V", 5);
        roman.put("IV", 4);
        roman.put("I", 1);

        String result = "";

        for (Map.Entry<String, Integer> elem : roman.entrySet()) {
            int repeat = (int) Math.floor(number / elem.getValue());
            number -= repeat * elem.getValue();
            result += elem.getKey().repeat(repeat);
        }
        return result;
    }
}

