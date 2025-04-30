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
    private String firstname ;
    private String lastName ;
    
    //getters

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Check if username is valid (contains underscore and max 5 chars)
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Password complexity check
    public boolean checkPasswordComplexity(String password) {
        Pattern upperCase = Pattern.compile("[A-Z]");
        Pattern lowerCase = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern specialChar = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");

        return password.length() >= 8 &&
               upperCase.matcher(password).find() &&
               lowerCase.matcher(password).find() &&
               digit.matcher(password).find() &&
               specialChar.matcher(password).find();
    }

    // Phone number must start with + and be 12 digits (e.g., +27831234567)
    public boolean checkCellPhoneNumber(String cellNumber) {
        return cellNumber.matches("^\\+27\\d{9}$");
    }

    // Registration method
    public String registerUser(String username, String password, String cellNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted. It must contain an underscore and be no more than five characters long.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted. It must contain at least 8 characters, including a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell number is incorrectly formatted. It must start with +27 and be 12 digits long.";
        }

        // Store the validated details
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
        return "User registered successfully.";
    }

    // Login check
    public boolean loginUser(String username, String password) {
        return this.username != null &&
               this.password != null &&
               this.username.equals(username) &&
               this.password.equals(password);
    }

    // Login status message
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstname + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    }
   
