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

   private static int totalMessagesSent = 0;
    private int currentMessageCount;

    // Message properties
    private String msgID;
    private String receiverNumber;
    private String msgText;
    private String msgHash;

    // ✅ Getter for message content
    public String getMessageContent() {
        return msgText;
    }

    // ✅ Constructor
    public Message(String receiverNumber, String msgText) {

        if (!receiverNumber.matches("\\+27\\d{9}")) {
            throw new IllegalArgumentException("Invalid recipient number. Must be 10 digits with international code(+27XXXXXX)");
        }

        if (msgText.length() > 250) {
            throw new IllegalArgumentException("Message exceeds 250 characters.");
        }

        this.receiverNumber = receiverNumber.trim();
        this.msgText = msgText;
        this.msgID = generateMessageID();
        this.currentMessageCount = ++totalMessagesSent;
        this.msgHash = createMessageHash();
    }

    // ✅ Generate a 9-digit message ID
    private String generateMessageID() {
        Random rand = new Random();
        int id = 100000000 + rand.nextInt(900000000);  // Ensures 9 digits
        return String.valueOf(id);
    }

    // ✅ Validate message ID
    public boolean checkMessageID() {
        return msgID.length() == 9;
    }

    // ✅ Validate recipient number (for testing)
    public boolean checkRecipientCell(String number) {
        return number.matches("\\+27\\d{9}");
    }

    // ✅ Create hash based on message content and ID
    public String createMessageHash() {
        String[] words = msgText.trim().split(" ");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        String idPrefix = msgID.substring(0, 2);
        return idPrefix + ":" + currentMessageCount + ":" + firstWord + lastWord;
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
        store.put("messageID", msgID);
        store.put("messageHash", msgHash);
        store.put("recipient", receiverNumber);
        store.put("message", msgText);
        return store;
    }

    // ✅ Print message info
    public String printMessages() {
        return "Message ID: " + msgID +
                "\nMessage Hash: " + msgHash +
                "\nRecipient: " + receiverNumber +
                "\nMessage: " + msgText;
    }

    // ✅ Return number of messages sent
    public static int returnTotalMessages() {
        return totalMessagesSent;
    }
}
