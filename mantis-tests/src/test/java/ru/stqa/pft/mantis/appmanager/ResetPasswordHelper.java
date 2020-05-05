package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ResetPasswordHelper extends HelperBase {

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void openManageUsersPage() {
        click(By.xpath("//i[@class='menu-icon fa fa-gears']"));
        click(By.xpath("//a[contains(@href, '/mantisbt-2.24.0/manage_user_page.php')]"));
    }

    public void selectUser(String username) {
        click(By.linkText(username));
    }

    public void resetPassword() {
        click(By.cssSelector("form#manage-user-reset-form.pull-left"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
