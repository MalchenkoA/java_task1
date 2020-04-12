package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().contactPage();
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstname("Nastya").withLastname("Mal").withAddress("Moscow").withHomephone("123456789").withWorkphone("147852369").withMobilephone("987654321").withFaxphone("963258741").withEmail("test1@test.com"));
    }
  }

  @Test

  public void testContactPhones(){
    app.goTo().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);

    assertThat(contact.getHomephone(), equalTo(cleaned(contactInfoFormEditForm.getHomephone())));
    assertThat(contact.getMobilephone(), equalTo(cleaned(contactInfoFormEditForm.getMobilephone())));
    assertThat(contact.getWorkphone(), equalTo(cleaned(contactInfoFormEditForm.getWorkphone())));
  }

  public String cleaned(String phone){
    return  phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
