package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.BrowserType.FIREFOX;
import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {

  protected final ApplicationManager app = new ApplicationManager(FIREFOX);
  public boolean acceptNextAlert = true;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void returnToContactPage() {
    click(By.linkText("home"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("work"), contactData.getWorkphone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("fax"), contactData.getFaxphone());
    type(By.name("email"), contactData.getEmail());

    if (creation){
      if (contactData.getGroups().size() > 0){
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups()
                .iterator().next().getName());
      }
    }else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    }



  public void initContactCreation() {
    click(By.linkText("add new"));
  }


  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void initContactModification(int id) {
    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void closingTheDialogBox() {
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  public String closeAlertAndGetItsText() {
    try {
      Alert alert = wd.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
  public ContactData infoFormEditForm(ContactData contact){
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withAddress(address).withHomephone(home).withMobilephone(mobile).withWorkphone(work).withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;

  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToContactPage();
  }

  public void delete(ContactData contact) throws InterruptedException {
    selectContactById(contact.getId());
    acceptNextAlert = true;
    deleteSelectedContacts();
    closingTheDialogBox();
    contactCache = null;
    returnToContactPage();
    TimeUnit.SECONDS.sleep(15);
  }


  public void deleteContactFromGroup(ContactData contact, GroupData group) {
    wd.findElement(By.name("group")).click();
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    selectContactById(contact.getId());
    click(By.name("remove"));
    click(By.linkText("home"));
  }

  public void addContactToGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    selectGroupInList(group.getName());
    wd.findElement(By.name("add")).click();
  }
  public void selectGroupInList(String groupName) {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupName);
  }


  public void deleteContact(ContactData contact) {
    findGroup();
    ChooseGroup();
    selectContactById(contact.getId());
    deleteSelectedContactsFromGroup();
    returnToContactPage();
    ChooseAllGroups();
  }


  private void findGroup() {
    wd.findElement(By.name("group")).click();
  }
  private void ChooseGroup() {
    wd.findElement(By.xpath("//option[3]")).click();
  }

  public void deleteSelectedContactsFromGroup() {
    wd.findElement(By.name("remove")).click();
  }


  private void ChooseAllGroups() {
    wd.findElement(By.xpath("//option[2]")).click();
  }

  public void addContact(ContactData contact) {
    selectContactById(contact.getId());
    addSelectedContactsToGroup();
    returnToContactPage();
  }

  private void addSelectedContactsToGroup() {
    wd.findElement(By.name("add")).click();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }


  public Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String address = cells.get(3).getText();
      String allEmail = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAddress(address).withAllPhones(allPhones).withAllEmail(allEmail));
    }
    return new Contacts(contactCache);
  }

}

