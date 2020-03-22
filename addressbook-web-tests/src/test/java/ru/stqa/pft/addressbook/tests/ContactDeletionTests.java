package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().selectContact();
    app.acceptNextAlert = true;
    app.getContactHelper().deleteSelectedContacts();
    assertTrue(app.closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    app.getNavigationHelper().gotoContactPage();
  }

}
