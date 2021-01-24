package com.mizrobov;
import java.util.Scanner;

/**
 * @author dilsh
 */
public class Start {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Вводите выражение");
        while (true){

            String exp = scanner.nextLine();
            if (!Operation.isValidExpression(exp)) {
                System.out.println("Ввод не явлюется правелным выражением");
                System.out.println("Завершить y/n");
                if (scanner.nextLine().equals("y")){
                    break;
                } else {
                    continue;
                }
            } else {
                String[] operands = Operation.operands(exp);
                String firstOper = operands[0];
                String secondOper = operands[1];
                String operator = operands[2];
                if (Operation.isRoman(firstOper) && Operation.isRoman(secondOper)) {
                    int opr1 = Operation.romanToArabic(firstOper);
                    int opr2 = Operation.romanToArabic(secondOper);
                    if (Operation.isRange(opr1,opr2)){
                        int result = Operation.action(opr1, opr2, operator);
                        try {
                            System.out.println(Operation.arabicToRoman(result));
                        }catch (IllegalArgumentException e){
                            System.out.println(e.getMessage());
                            System.out.println("Завершить y/n");
                            if (scanner.nextLine().equals("y")){
                                break;
                            } else {
                                continue;
                            }
                        }
                    } else{
                        System.out.println("Ввод не в диапозоне (0;10]");
                        System.out.println("Завершить y/n");
                        if (scanner.nextLine().equals("y")){
                            break;
                        } else {
                            continue;
                        }
                    }
                } else {
                    int opr1 = Integer.parseInt(firstOper);
                    int opr2 =Integer.parseInt(secondOper);
                    if (Operation.isRange(opr1,opr2)){
                        int result = Operation.action(opr1, opr2, operator);
                        System.out.println(result);
                    } else {
                        System.out.println("Ввод не в диапозоне (0;10]");
                        System.out.println("Завершить y/n");
                        if (scanner.nextLine().equals("y")){
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
    }
}
