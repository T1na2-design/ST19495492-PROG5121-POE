/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.phoenix_chat;

import org.json.JSONObject ;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */
public class MainUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
      
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
        
        //PART2
       try{
           //User inputs reciever's number
           String reciepint = JOptionPane.showInputDialog(null, "Enter recipient Number: ") ;
           
           //User inputs message
           String messageContent = JOptionPane.showInputDialog(null, "Enter your message (max 250 characters): ") ;
          
           //Creates message object
           Message message = new Message(reciepint, messageContent) ; 
           
           //Prints the message details
           String messageDetails = message.printMessages() ;
           JOptionPane.showInputDialog(null, "Message Created:\n\n " + messageDetails) ;
           
           //Asks user which option they want to choose for the message entered
           String Messageoptions = JOptionPane.showInputDialog(null, "Select an option:\n1 - "
                   + "Send message\n2 - Delete message\n3 - Store message\nOther - Invalid Option") ;
           
           int Options = Integer.parseInt(Messageoptions) ;
           String option = message.MessageOptions(Options) ;
           
           
           JOptionPane.showMessageDialog(null, option);
           
           if (Options == 3){
               JSONObject stored = message.storeMessage() ;
               
           }
           
           JOptionPane.showMessageDialog(null, "Total Message sent: " + Message.returnTotalMessages());
           
       } 
       catch (IllegalArgumentException e){
           JOptionPane.showMessageDialog(null, "Error:" + e.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
       }
       catch (Exception e){
            JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
       }   
    }
 }


    
    

