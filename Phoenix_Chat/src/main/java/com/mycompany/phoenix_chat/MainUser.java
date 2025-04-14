/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.phoenix_chat;

import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class MainUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Scanner scanner = new Scanner(System.in);

        System.out.println("=== User Registration ===");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter cell phone number (e.g., +27831234567): ");
        String cellNumber = scanner.nextLine();

        LoginClass user = new LoginClass();

        if (!user.checkUserName(username)) {
            System.out.println("Username incorrectly formatted. Must contain an underscore and be no more than 5 characters long.");
        }

        if (!user.checkPasswordComplexity(password)) {
            System.out.println("Password does not meet complexity requirements.");
        }

        if (!user.checkCellPhoneNumber(cellNumber)) {
            System.out.println("Cell phone number incorrectly formatted. Must start with +27 and be 12 characters long.");
        }

        if (user.checkUserName(username) && user.checkPasswordComplexity(password) && user.checkCellPhoneNumber(cellNumber)) {
            System.out.println(user.registerUser(username, password, cellNumber));
        } else {
            System.out.println("Registration failed due to incorrect input.");
            return;
        }

        System.out.println("\n=== User Login ===");

        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        user.loginUser(loginUsername, loginPassword);
        System.out.println(user.returnLoginStatus(username, password));
    }
    }

    
    

