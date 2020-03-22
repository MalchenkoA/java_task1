package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
    gotoContactPage();
    select();
    acceptNextAlert = true;
    deleteSelectedContacts();
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    gotoContactPage();
  }

}
