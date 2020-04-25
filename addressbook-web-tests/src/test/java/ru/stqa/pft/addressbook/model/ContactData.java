package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  private String firstname;
  @Expose
  @Column(name = "lastname")
  private String lastname;
  @Expose
  @Column (name = "address")
  @Type(type = "text")
  private String address;
  @Expose
  @Column (name = "home")
  @Type(type = "text")
  private String homephone;
  @Expose
  @Column (name = "work")
  @Type(type = "text")
  private String workphone;
  @Expose
  @Column (name = "mobile")
  @Type(type = "text")
  private String mobilephone;
  @Transient
  private String faxphone;
  @Expose
  @Column (name = "email")
  @Type(type = "text")
  private String email;
  @Column (name = "email2")
  @Type(type = "text")
  private String email2;
  @Column (name = "photo")
  @Type(type = "text")
  private String photo;
  @Column (name = "email3")
  @Type(type = "text")
  private String email3;
  @Transient
  private String AllPhones;
  @Transient
  private String AllEmail;

  public File getPhoto() {
    return new File(photo);
  }



  public String getEmail2() {
    return email2;
  }


  public String getEmail3() {
    return email3;
  }




  public String getAllEmail() {
    return AllEmail;
  }


  public String getAllPhones() {
    return AllPhones;
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

  public String getEmail() {
    return email;
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

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomephone(String homephone) {
    this.homephone = homephone;
    return this;
  }

  public ContactData withWorkphone(String workphone) {
    this.workphone = workphone;
    return this;
  }

  public ContactData withMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public ContactData withFaxphone(String faxphone) {
    this.faxphone = faxphone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    AllPhones = allPhones;
    return this;
  }
  public ContactData withAllEmail(String allEmail) {
    AllEmail = allEmail;
    return this;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }
  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }
}
