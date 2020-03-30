package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().createContact(new ContactData("Nastya", "Mal", "Moscow", "123456789", "147852369", "987654321", "963258741", "test1@test.com"));
    app.getNavigationHelper().gotoContactPage();
    app.logout();
  }

}
