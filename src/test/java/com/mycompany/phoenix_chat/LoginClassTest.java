/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.phoenix_chat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */
public class LoginClassTest {
    
    public LoginClassTest() {
    }
    

    @Test
    public void testCheckUserName_Valid() {
        LoginClass login = new LoginClass();
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserName_Invalid() {
        LoginClass login = new LoginClass();
        assertFalse(login.checkUserName("kyle!!!"));
    }

    @Test
    public void testCheckPasswordComplexity_Valid() {
        LoginClass login = new LoginClass();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() {
        LoginClass login = new LoginClass();
        assertFalse(login.checkPasswordComplexity("Password"));
    }

    @Test
    public void testCheckCellPhoneNumber_Valid() {
        LoginClass login = new LoginClass();
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid() {
        LoginClass login = new LoginClass();
        assertFalse(login.checkCellPhoneNumber("08366553"));
    }

    @Test
    public void testRegisterUser_AllValid() {
        LoginClass login = new LoginClass();
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("User registered successfully.", result);
    }

    @Test
    public void testLoginUser_Success() {
        LoginClass login = new LoginClass();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUser_Failure() {
        LoginClass login = new LoginClass();
        login.registerUser("kyle!!!", "password", "08966553");
        assertFalse(login.loginUser("user123", "password"));
    }

    @Test
    public void testReturnLoginStatus_Success() {
        LoginClass login = new LoginClass();
        login.setFirstname("Kyle");
        login.setLastName("Smith");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Welcome Kyle Smith, it is great to see you again.", login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testReturnLoginStatus_Failure() {
        LoginClass login = new LoginClass();
        login.setFirstname("Kyle");
        login.setLastName("Smith");
        login.registerUser("kyle!!!", "password", "08966553");
        assertEquals("Username or password incorrect, please try again.", login.returnLoginStatus("user", "pass"));
    }
   
    
}
