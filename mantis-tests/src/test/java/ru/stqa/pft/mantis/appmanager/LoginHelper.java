package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import java.io.IOException;

public class LoginHelper extends HelperBase {
    public LoginHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public void reset(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));

    }

    public boolean isLoggedInAs(String username) throws IOException {
        return wd.getPageSource().contains
                (String.format("<a href=\"/mantisbt-2.24.0/account_page.php\">%s ( %s ) </a>", username));
    }

}
