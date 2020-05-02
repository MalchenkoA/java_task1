package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static java.util.Objects.isNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test41"));
        }

        app.goTo().contactPage();
        if (app.db().contacts().size() == 0) {
            app.goTo().contactPage();
            app.contact().create(new ContactData().withFirstname("Nastya")
                    .withLastname("Mal").withAddress("Moscow")
                    .withHomephone("123456789").withWorkphone("147852369")
                    .withMobilephone("987654321").withFaxphone("963258741")
                    .withEmail("test1@test.com"));
        }

    }

    @Test
    public void testContactAddedToGroup() throws Exception {
        //Contacts beforeContacts = app.db().contacts();
        //Groups beforeGroups = app.db().groups();
        ContactData addContactToGroup = null;
        GroupData groupToAdd = null;

        while (isNull(addContactToGroup)) {
            for (ContactData contact : app.db().contacts()) {
                for (GroupData group : app.db().groups()) {

                    for (ContactData contactInGroup : group.getContacts()) {
                        if (contactInGroup == contact) {
                            break;
                        }
                    }

                    groupToAdd = group;
                }
                addContactToGroup = contact;
            }

        //ContactData addContactToGroup = beforeContacts.iterator().next();
        //GroupData groupToAdd = beforeGroups.iterator().next();
            if (isNull(groupToAdd)) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test41"));
            }

            if (isNull(addContactToGroup)) {
                app.goTo().contactPage();
                app.contact().create(new ContactData().withFirstname("Nastya")
                        .withLastname("Mal").withAddress("Moscow")
                        .withHomephone("123456789").withWorkphone("147852369")
                        .withMobilephone("987654321").withFaxphone("963258741")
                        .withEmail("test1@test.com"));
            }
        }

        app.contact().addToGroup(addContactToGroup);
        addContactToGroup.inGroup(groupToAdd);

        Assert.assertTrue(addContactToGroup.getGroups().size() > 0);


        ContactData addNewContactToGroup = null;
        while (isNull(addNewContactToGroup)) {
           for (ContactData contact : app.db().contacts()) {
               if (contact.getGroups().size() == 0) {
                   addNewContactToGroup = contact;
                    break;
                }
            }
            if (isNull(addNewContactToGroup)) {
                app.goTo().contactPage();
                app.contact().create(new ContactData().withFirstname("Nastya")
                        .withLastname("Mal").withAddress("Moscow")
                        .withHomephone("123456789").withWorkphone("147852369")
                        .withMobilephone("987654321").withFaxphone("963258741")
                        .withEmail("test1@test.com"));
            }
            if (isNull(groupToAdd)) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test41"));
            }
        }
        Groups beforeGroups = app.db().groups();
        GroupData groupNewToAdd = beforeGroups.iterator().next(); // вернет группу, в которую будем добавлять (любую)
        app.contact().addToGroup(addNewContactToGroup);
        addNewContactToGroup.inGroup(groupNewToAdd);
        Assert.assertTrue(addNewContactToGroup.getGroups().size() > 0);



        //Contacts after = app.db().contacts();
        //Assert.assertEquals(after.size(),beforeContacts.size());

        //assertThat(after, equalTo(beforeContacts.withOut(addContactToGroup).withAdded(addContactToGroup)));


    }

}