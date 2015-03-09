package com.twu.biblioteca.integration;

import com.twu.biblioteca.TestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by jeremynagel on 9/03/15.
 */
public class LoginTest {

    private TestHelper testHelper;

    @Before
    public void beforeTest() {
        testHelper = new TestHelper();
    }

    @After
    public void afterTest() {
    }

    @Test
    public void librarian_requires_login_at_start(){
        testHelper.fakeKeyboardInput("Borrow");
        testHelper.fakeKeyboardInput("Borrow");
        testHelper.exit();
        testHelper.startAppWithSampleParameters();
        int indexOfReturnMessage = testHelper.getIndexInOutput("I regret to inform you that your login details are incorrect.\n" +
                "Please try again. Remember to write it like this <card number> <password>\n");
        assertNotEquals(-1, indexOfReturnMessage);
    }

    @Test
    public void I_can_login_successfully() {
        testHelper.fakeKeyboardInput("Login 123-4567 reallySecurePassword");
        testHelper.startAppWithSampleParameters();
        int indexOfReturnMessage = testHelper.getIndexInOutput("Very well then. \n" +
                "Please follow me into the lobby where I will introduce you to my colleague.\n" +
                "I must warn you - he is quite rude.");

        assertNotEquals(-1, indexOfReturnMessage);
    }


    @Test
    public void i_can_view_my_details(){
        testHelper.loginSuccessfully();
        testHelper.fakeKeyboardInput("MyProfile");
        testHelper.startAppWithSampleParameters();
        int indexOfReturnMessage = testHelper.getIndexInOutput("Name: Dr Spock\nEmail: spock@dr.com\nPhone: 1800000000\n");

        assertNotEquals(-1, indexOfReturnMessage);
    }
}
