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
        
     //PART 1 - Register/Login 
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
        
    // PART2 - Sending messages and storing
try {
    String recipientPhoneNumber = JOptionPane.showInputDialog(null, "Enter recipient Number:");
    String messageContent = JOptionPane.showInputDialog(null, "Enter your message (max 250 characters):");

    Message message = new Message(recipientPhoneNumber, messageContent);

    String messageDetails = message.getMessageDetails();
    JOptionPane.showMessageDialog(null, "Message Created:\n\n" + messageDetails);

    String optionInput = JOptionPane.showInputDialog(null,
        "Select an option:\n1 - Send message\n2 - Delete message\n3 - Store message\nOther - Invalid Option");

    int selectedOption = Integer.parseInt(optionInput);
    String messageOptionResponse = message.getMessageOption(selectedOption);

    JOptionPane.showMessageDialog(null, messageOptionResponse);

           switch (selectedOption) {
               case 1:
                   // Simulate sending the message
                   message.setMessageStatus("SENT");
                   JOptionPane.showMessageDialog(null, "Message status updated to SENT.");
                   // For demonstration, simulate message is received and read
                   message.setMessageStatus("RECEIVED");
                   message.setMessageStatus("READ");
                   JOptionPane.showMessageDialog(null, "Message status updated to RECEIVED and then READ.");
                   // Show current statuses
                   String statusMessage = "Message Status:\n" +
                           "Sent: " + message.isSent() + "\n" +
                           "Received: " + message.isReceived() + "\n" +
                           "Read: " + message.isRead();
                   JOptionPane.showMessageDialog(null, statusMessage);
                   break;
               case 2:
                   int confirm = JOptionPane.showConfirmDialog(null,
                           "Are you sure you want to delete this message?",
                           "Confirm Delete", JOptionPane.YES_NO_OPTION);
                   if (confirm == JOptionPane.YES_OPTION) {
                       boolean deleted = Message.deleteMessage(message);
                       if (deleted) {
                           JOptionPane.showMessageDialog(null, "Message deleted successfully.");
                       } else {
                           JOptionPane.showMessageDialog(null, "Message could not be found or already deleted.");
                       }
                   } else {
                       JOptionPane.showMessageDialog(null, "Message deletion canceled.");
                   }              break;
               case 3:
                   JSONObject storedMessage = message.toJson();
                   JOptionPane.showMessageDialog(null, "Stored Message:\n" + storedMessage.toString(4)); // Pretty print JSON
                   break;
               default:
                   break;
           }

    JOptionPane.showMessageDialog(null, "Total Messages Sent: " + Message.getTotalMessagesCreated());

    // PART 3 — Display all messages
    ArrayList<Message> allMessages = Message.getAllMessages();

    StringBuilder allMessageDisplay = new StringBuilder("All Messages Sent:\n\n");
    for (Message m : allMessages) {
        allMessageDisplay.append(m.getMessageDetails()).append("\n\n");
    }

    JOptionPane.showMessageDialog(null, allMessageDisplay.toString());
 //---end of part 3 code ---
 
} catch (IllegalArgumentException e) {
    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
}

    }
    }

    
    

