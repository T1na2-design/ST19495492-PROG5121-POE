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

    // Tracks number of messages sent
    private static int messageSentCount = 0;
    private int numMessages;

    // Message properties
    private String messageID;
    private String recipient;
    private String messageContent;
    private String messageHash;

    // ✅ Getter for message content
    public String getMessageContent() {
        return messageContent;
    }

    // ✅ Constructor
    public Message(String recipient, String messageContent) {
       // System.out.println("Recipient received: [" + recipient + "]");

        if (!recipient.matches("\\+27\\d{9}")) {
            throw new IllegalArgumentException("Invalid recipient number. Must be 10 digits with international code(+27XXXXXX)");
        }

        if (messageContent.length() > 250) {
            throw new IllegalArgumentException("Message exceeds 250 characters.");
        }

        this.recipient = recipient.trim();
        this.messageContent = messageContent;
        this.messageID = generateMessageID();
        this.numMessages = ++messageSentCount;
        this.messageHash = createMessageHash();
    }

    // ✅ Generate a 9-digit message ID
    private String generateMessageID() {
        Random rand = new Random();
        int id = 100000000 + rand.nextInt(900000000);  // Ensures 9 digits
        return String.valueOf(id);
    }

    // ✅ Validate message ID
    public boolean checkMessageID() {
        return messageID.length() == 9;
    }

    // ✅ Validate recipient number (for testing)
    public boolean checkReciepientCell(String number) {
        return number.matches("\\+27\\d{9}");
    }

    // ✅ Create hash based on message content and ID
    public String createMessageHash() {
        String[] words = messageContent.trim().split(" ");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        String idPrefix = messageID.substring(0, 2);
        return idPrefix + ":" + numMessages + ":" + firstWord + lastWord;
    }

    // ✅ Provide options when sending a message
    public String MessageOptions(int option) {
        return switch (option) {
            case 1 -> "Message successfully sent.";
            case 2 -> "Press 0 to delete message.";
            case 3 -> "Message successfully stored.";
            default -> "Invalid option entered.";
        };
    }

    // ✅ Store message in a JSON object
    public JSONObject storeMessage() {
        JSONObject store = new JSONObject();
        store.put("messageID", messageID);
        store.put("messageHash", messageHash);
        store.put("recipient", recipient);  // 🔄 Removed space typo
        store.put("message", messageContent);
        return store;
    }

    // ✅ Print message info
    public String printMessages() {
        return "Message ID: " + messageID +
                "\nMessage Hash: " + messageHash +
                "\nRecipient: " + recipient +
                "\nMessage: " + messageContent;
    }

    // ✅ Return number of messages sent
    public static int returnTotalMessages() {
        return messageSentCount;
    }
}