/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.phoenix_chat;


import java.util.Random ;
import org.json.JSONObject ;

/**
 *
 * @author Admin
 */
public class Message {

    // Tracks total number of messages created
    private static int totalMessagesCreated = 0;
    private int messageSequenceNumber;

    // List to store all message objects
    private static ArrayList<Message> allMessages = new ArrayList<>();

    // Message properties
    private String messageId;
    private String recipientPhoneNumber;
    private String content;
    private String contentHash;
    private String messageStatus = "SENT"; // Default status is SENT

    // Constructor
    public Message(String recipientPhoneNumber, String content) {

        if (!recipientPhoneNumber.matches("\\+27\\d{9}")) {
            throw new IllegalArgumentException("Invalid recipient number. Must be 10 digits with international code (+27XXXXXXXXX).");
        }

        if (content.length() > 250) {
            throw new IllegalArgumentException("Message exceeds 250 characters.");
        }

        this.recipientPhoneNumber = recipientPhoneNumber.trim();
        this.content = content;
        this.messageId = generateMessageId();
        this.messageSequenceNumber = ++totalMessagesCreated;
        this.contentHash = generateContentHash();
        allMessages.add(this);
    }

    // Generate a 9-digit message ID
    private String generateMessageId() {
        Random random = new Random();
        int randomId = 100000000 + random.nextInt(900000000);  // Ensures 9 digits
        return String.valueOf(randomId);
    }

    // Validate message ID
    public boolean isValidMessageId() {
        return messageId.length() == 9;
    }

    // Validate recipient number (for testing)
    public boolean isValidRecipientNumber(String number) {
        return number.matches("\\+27\\d{9}");
    }

    // Create hash based on message content and ID
    public String generateContentHash() {
        String[] words = content.trim().split(" ");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        String idPrefix = messageId.substring(0, 2);
        return idPrefix + ":" + messageSequenceNumber + ":" + firstWord + lastWord;
    }

    // Provide options when sending a message
    public String getMessageOption(int option) {
        return switch (option) {
            case 1 -> "Message successfully sent.";
            case 2 -> "Press 0 to delete message.";
            case 3 -> "Message successfully stored.";
            default -> "Invalid option entered.";
        };
    }

    // Store message in a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("messageId", messageId);
        json.put("messageHash", contentHash);
        json.put("recipient", recipientPhoneNumber);
        json.put("message", content);
        json.put("status", messageStatus);
        return json;
    }

    // Print message info
    public String getMessageDetails() {
        return "Message ID: " + messageId +
                "\nMessage Hash: " + contentHash +
                "\nRecipient: " + recipientPhoneNumber +
                "\nMessage: " + content +
                "\nStatus: " + messageStatus;
    }

    public static ArrayList<Message> getAllMessages() {
        return allMessages;
    }

    // Return number of messages sent
    public static int getTotalMessagesCreated() {
        return totalMessagesCreated;
    }

    // Getters
    public String getContent() {
        return content;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    // Set message status with validation
    public void setMessageStatus(String status) {
        if (status.equals("SENT") || status.equals("RECEIVED") || status.equals("READ")) {
            this.messageStatus = status;
        } else {
            throw new IllegalArgumentException("Invalid status: must be SENT, RECEIVED, or READ.");
        }
    }

    // Boolean getters for easy status checks
    public boolean isSent() {
        return "SENT".equals(messageStatus);
    }

    public boolean isReceived() {
        return "RECEIVED".equals(messageStatus);
    }

    public boolean isRead() {
        return "READ".equals(messageStatus);
    }
    
    //Deletes messages
    public static boolean deleteMessage(Message message) {
    boolean removed = allMessages.remove(message);
    if (removed) {
        totalMessagesCreated--;  // Optional: decrement total messages count
    }
    return removed;
}
    }
}
