package com.codecool.shop.view;

import java.util.Scanner;

/**
 * Created by kamil on 04.05.17.
 */
public class UserInput {

    public static Integer getUserInput(){
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()){
            System.out.println("Wrong input try again");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
