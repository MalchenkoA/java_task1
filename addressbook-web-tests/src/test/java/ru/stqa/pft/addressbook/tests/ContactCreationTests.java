package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/stru.JPG");
    ContactData contact = new ContactData().withFirstname("Nastya").withLastname("Mal").withPhoto(photo).withAddress("Moscow").withHomephone("123456789").withWorkphone("147852369").withMobilephone("987654321").withFaxphone("963258741").withEmail("test1@test.com");
    app.contact().create(contact);
    app.goTo().contactPage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();


   assertThat(after, equalTo(
           before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
