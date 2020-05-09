package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ResetPasswordHelper extends HelperBase {

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
    }


    public void resetPassword() throws MessagingException, IOException {

        List<UserData> users = new ArrayList<UserData>();
        users = getUserList();

        UserData firstContact = users.iterator().next();
        String firstName = firstContact.getUsername();
        click(By.linkText(firstName));

        click(By.cssSelector("form#manage-user-reset-form.pull-left"));

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, firstContact.getEmail());

        String newPassword = "RESETpassword";
        app.loginHelper().reset(confirmationLink, newPassword);
        assertTrue (app.newSession().login(firstName, newPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email){
        MailMessage mailMessage = mailMessages.stream().filter((m)->m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    private List<UserData> getUserList() {
        List<UserData> users = new ArrayList<UserData>();

        List<WebElement> elements = wd.findElements(By.cssSelector("table.table-striped > tbody >tr"));
        for (WebElement element : elements){
            List<WebElement>  cells = element.findElements(By.cssSelector("td"));
            String username = cells.get(0).getText();
            String email = cells.get(2).getText();
            if (!(username.equals("administrator"))) {
                UserData user = new UserData().setUsername(username).setEmail(email);
                users.add(user);
            }

        }
        return users;
    }


    public void openManageUsersPage() {
        click(By.xpath("//i[@class='menu-icon fa fa-gears']"));
        click(By.xpath("//a[contains(@href, '/mantisbt-2.24.0/manage_user_page.php')]"));
    }

}
