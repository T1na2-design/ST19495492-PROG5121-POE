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
        
      //PART 1
       LoginClass user = new LoginClass();

       
        String firstName = JOptionPane.showInputDialog("Enter your First Name:");
        String lastName = JOptionPane.showInputDialog("Enter your Last Name:");
        String username = JOptionPane.showInputDialog("Enter a Username (max 5 characters, must contain '_'):");
        String password = JOptionPane.showInputDialog("Enter a Password (min 8 chars, with uppercase, number, and special character):");
        String cellNumber = JOptionPane.showInputDialog("Enter Cell Number (e.g., +27831234567):");

        user.setFirstname(firstName);
        user.setLastName(lastName);

        String registerMessage = user.registerUser(username, password, cellNumber);
        JOptionPane.showMessageDialog(null, registerMessage);

        if (!registerMessage.equals("User registered successfully.")) {
            JOptionPane.showMessageDialog(null, "Registration failed. Exiting...");
            return;
        }

        JOptionPane.showMessageDialog(null, "=== User Login ===");

        String loginUsername = JOptionPane.showInputDialog("Enter your Username to login:");
        String loginPassword = JOptionPane.showInputDialog("Enter your Password to login:");

        String loginStatus = user.returnLoginStatus(loginUsername, loginPassword);
        JOptionPane.showMessageDialog(null, loginStatus);
        
    // PART2
try {
    // User inputs recipient's phone number
    String recipientPhoneNumber = JOptionPane.showInputDialog(null, "Enter recipient Number:");

    // User inputs message content
    String messageContent = JOptionPane.showInputDialog(null, "Enter your message (max 250 characters):");

    // Create message object
    Message message = new Message(recipientPhoneNumber, messageContent);

    // Print message details
    String messageDetails = message.getMessageDetails();
    JOptionPane.showMessageDialog(null, "Message Created:\n\n" + messageDetails);

    // Ask user to choose an option for the message
    String optionInput = JOptionPane.showInputDialog(null, "Select an option:\n1 - Send message\n2 - Delete message\n3 - Store message\nOther - Invalid Option");

    int selectedOption = Integer.parseInt(optionInput);
    String messageOptionResponse = message.getMessageOption(selectedOption);

    JOptionPane.showMessageDialog(null, messageOptionResponse);

    if (selectedOption == 3) {
        JSONObject storedMessage = message.toJson();
        JOptionPane.showMessageDialog(null, "Stored Message:\n" + storedMessage.toString(4)); // Pretty print JSON
    }

    JOptionPane.showMessageDialog(null, "Total Messages Sent: " + Message.getTotalMessagesCreated());
    
    //PART 3
    ArrayList<Message> allMessages = Message.getAllMessages();

            StringBuilder allMessageDisplay = new StringBuilder("All Messages Sent:\n\n");
            for (Message m : allMessages) {
                allMessageDisplay.append(m.getMessageDetails()).append("\n\n");
            }

            JOptionPane.showMessageDialog(null, allMessageDisplay.toString());
//---end of part 3 code---

} catch (IllegalArgumentException e) {
    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
}

    }
    }

    
    

