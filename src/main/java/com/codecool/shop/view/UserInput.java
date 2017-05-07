package com.codecool.shop.view;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInput {

    public static Integer getUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Try again");
            scanner.next();
        }
        Integer option = scanner.nextInt();
        return option;
    }

    public static Boolean yesNoInput() {
        String[] goodAnswers = {"Y", "y", "N", "n"};
        Scanner scanner = new Scanner(System.in);
        String option = "";
//        System.out.println(option);
//        System.out.println(Arrays.asList(goodAnswers).contains(option));

        while (scanner.hasNextLine()) {
            option = scanner.nextLine();

            if (Arrays.asList(goodAnswers).contains(option)) {
                System.out.println("Breaking");
                break;
            }

            System.out.println("Invalid input. Try again");
//            scanner.next();
        }

        System.out.println(option);
        return true;
    }


}
