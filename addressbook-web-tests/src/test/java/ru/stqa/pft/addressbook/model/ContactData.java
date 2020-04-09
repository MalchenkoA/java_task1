package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String homephone;
  private final String workphone;
  private final String mobilephone;
  private final String faxphone;
  private final String enail;

  public ContactData(String firstname, String lastname, String address, String homephone, String workphone, String mobilephone, String faxphone, String enail) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.homephone = homephone;
    this.workphone = workphone;
    this.mobilephone = mobilephone;
    this.faxphone = faxphone;
    this.enail = enail;
  }


  public ContactData(int id, String firstname, String lastname, String address, String homephone, String workphone, String mobilephone, String faxphone, String enail) {
    this.id = id;
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

  public int getId() {
    return id;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  public void setId(int id) {
    this.id = id;
  }

}
