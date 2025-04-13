/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phoenix_chat;

import java.util.regex.Matcher;  //Chatgpt, 2025
import java.util.regex.Pattern;

/**
 *
 * @author RC_Student_lab
 */
public class LoginClass {
 
    private String username;
    private String password;
    private String cellNumber;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        Pattern upperCase = Pattern.compile("[A-Z]"); //Chatgpt,2025
        Pattern lowerCase = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern specialChar = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");

        Matcher hasUpper = upperCase.matcher(password);
        Matcher hasLower = lowerCase.matcher(password);
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = specialChar.matcher(password);

        return password.length() >= 8 &&
               hasUpper.find() &&
               hasLower.find() &&
               hasDigit.find() &&
               hasSpecial.find();
    }

    public boolean checkCellPhoneNumber(String cellNumber) {
        Pattern pattern = Pattern.compile("^\\+\\d{11}$");
        Matcher matcher = pattern.matcher(cellNumber);
        return matcher.matches();
    }

    public String registerUser(String username, String password, String cellNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        }

        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
        return "User registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        return this.username != null &&
               this.password != null &&
               this.username.equals(username) &&
               this.password.equals(password);
    }

    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + username + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
   }
