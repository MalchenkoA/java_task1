package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoContactPage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Nastya", "Mal", "Moscow", "123456789", "147852369", "987654321", "963258741", "test1@test.com"));
    }
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Nastya2", "Mal", "Moscow", "123456789", "147852369", "987654321", "963258741", "test1@test.com"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoContactPage();
  }
}
