/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.phoenix_chat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Admin
 */
public class MessageTest {
public MessageTest() {}

    @Test
    public void testValidRecipientNumber() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight");
        assertTrue(message.checkReciepientCell("+27718693002"));
    }

    @Test
    public void testInvalidRecipientNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Message("08575975889", "Test");
        });
    }

    @Test
    public void testMessageUnder250Char() {
        String validNumber = "+27718693002";
        String shortMessage = "Hey, this is under 250 characters!";
        Message message = new Message(validNumber, shortMessage);
        assertEquals(shortMessage, message.getMessageContent());
    }

    @Test
    public void testMessageOver250Char() {
        String longMessage = "A".repeat(251);
        String validNumber = "+27123456789";
        assertThrows(IllegalArgumentException.class, () -> {
            new Message(validNumber, longMessage);
        });
    }

    @Test
    public void testMessageHashFormat() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight");
        String hash = message.createMessageHash();
        assertTrue(hash.matches("^\\d{2}:\\d+:[A-Z]+[A-Z]+$"));
    }

    @Test
    public void testMessageIDLength() {
        Message message = new Message("+27718693002", "Hey");
        assertTrue(message.checkMessageID());
    }

    @Test
    public void testSendMessageOptions() {
        Message msg = new Message("+27718693002", "Testing message");
        assertEquals("Message successfully sent.", msg.MessageOptions(1));
        assertEquals("Press 0 to delete message.", msg.MessageOptions(2));
        assertEquals("Message successfully stored.", msg.MessageOptions(3));
        assertEquals("Invalid option entered.", msg.MessageOptions(99));
    }

    @Test
    public void testMessageStatusUpdates() {
        Message message = new Message("+27718693002", "Test message");

        message.setMessageStatus("SENT");
        assertEquals("SENT", message.getMessageStatus());

        message.setMessageStatus("RECEIVED");
        assertEquals("RECEIVED", message.getMessageStatus());

        message.setMessageStatus("READ");
        assertEquals("READ", message.getMessageStatus());
    }

    @Test
    public void testInvalidMessageStatus() {
        Message message = new Message("+27718693002", "Invalid status test");
        assertThrows(IllegalArgumentException.class, () -> {
            message.setMessageStatus("DELIVERED"); // Not allowed
        });
    }

    @Test
    public void testDeleteMessage() {
        Message message = new Message("+27718693002", "Delete me");
        int sizeBefore = Message.getAllMessages().size();

        boolean deleted = Message.deleteMessage(message);
        int sizeAfter = Message.getAllMessages().size();

        assertTrue(deleted);
        assertEquals(sizeBefore - 1, sizeAfter);
    }

    @Test
    public void testBooleanFlags() {
        Message msg = new Message("+27718693002", "Flag test");
        msg.setSent(true);
        msg.setReceived(true);
        msg.setRead(true);

        assertTrue(msg.isSent());
        assertTrue(msg.isReceived());
        assertTrue(msg.isRead());
    }
   
}

