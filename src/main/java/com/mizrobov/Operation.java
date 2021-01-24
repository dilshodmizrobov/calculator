package com.mizrobov;


import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author dilsh
 */
public final class Operation {

    private Operation() {
    }

    public static int action(int a, int b ,String operator){
        switch (operator){
            case "+":
                return add(a, b);
            case "-":
                return sub(a, b);
            case "*":
                return multiply(a, b);
            case "/":
                return divide(a, b);
            default:
                return 0;
        }
    }

    private static int add(int a, int b) {
        return a + b;
    }

    private static int sub(int a, int b) {
        return b - a;
    }

    private static int multiply(int a, int b) {
        return a * b;
    }

    private static int divide(int a, int b) {
        return b / a;
    }

    public static boolean isValidExpression(String input){
        String pattern = "(^[0-9]{1,2}[-+*/][0-9]{1,2}$)|(^[IVX]{1,3}[-+*/][IVX]{1,3}$)";
        return Pattern.matches(pattern, input.replaceAll("\\s", ""));
    }

    public static String[] operands(String expression){
        String opers[] =expression.split("[-+*/]");
        String operator = String.valueOf(expression.charAt(opers[0].length()));
        return new String[]{opers[0], opers[1], operator};

    }

    public static boolean isRoman(String symbol){
        List<String> romanSympols=Arrays.stream(RomanNumeral.values())
                                        .map(romanNumeral -> romanNumeral.name())
                                        .collect(Collectors.toList());
        return romanSympols.contains(symbol);
    }

    public static boolean isRange(int opr1, int opr2) {
        if ((opr1 > 0 & opr1 <= 10) & (opr2 > 0 & opr2 <= 10)) {
            return true;
        }
        return false;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number >= 100)) {
            throw new IllegalArgumentException(number + " is not in range (0,100]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }
}
