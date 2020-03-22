package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String homephone;
  private final String workphone;
  private final String mobilephone;
  private final String faxphone;
  private final String enail;

  public ContactData(String firstname, String lastname, String address, String homephone, String workphone, String mobilephone, String faxphone, String enail) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.homephone = homephone;
    this.workphone = workphone;
    this.mobilephone = mobilephone;
    this.faxphone = faxphone;
    this.enail = enail;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getWorkphone() {
    return workphone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getFaxphone() {
    return faxphone;
  }

  public String getEnail() {
    return enail;
  }
}
