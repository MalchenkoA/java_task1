package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

public class ResetPasswordTests extends TestBase {



    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
        }



    @Test
    public void testResetPassword() throws IOException, MessagingException {

        app.loginHelper().loginByAdmin();
        app.resetPassword().openManageUsersPage();
        app.resetPassword().resetPassword();


    }





    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}