package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.IssueStatus;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;


public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config/config_inc.php.bak", "config/config_inc.php");
        app.stop();
    }

    public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        Issue issue = app.soap().getIssueById(issueId);
        if ((issue.getStatus().equals("resolved")) || (issue.getStatus().equals("closed")) &
                (issue.getResolution().equals("fixed"))) {
            return false;
        }  return true;

    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }


    public boolean isIssueOpenRest(int issueId) throws IOException {
        Set<IssueStatus> issueStatus = app.rest().getIssueForBugifyById(issueId);
        for (IssueStatus status: issueStatus) {
            if (status.getState_name().equals("resolved") || (status.getState_name().equals("closed"))) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void skipIfNotFixedRest(int issueId) throws IOException {
        if (isIssueOpenRest(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}