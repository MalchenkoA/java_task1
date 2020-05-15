package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test41"));
        }
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Nastya")
                    .withLastname("Mal").withAddress("Moscow")
                    .withHomephone("123456789").withWorkphone("147852369")
                    .withMobilephone("987654321").withFaxphone("963258741")
                    .withEmail("test1@test.com"));
        }

    }

    @Test
    public void testContactAddedToGroup() throws Exception {

        ContactData contactToTest = selectAvailableContact();
        GroupData groupToTest = selectAvailableGroup(contactToTest);

        Groups groupsForContactBefore = app.db().contactById(contactToTest.getId()).getGroups();

        app.goTo().contactPage();
        app.contact().addContactToGroup(contactToTest, groupToTest);

        Groups groupsForContactAfter = app.db().contactById(contactToTest.getId()).getGroups();

        assertThat(groupsForContactAfter.size(), equalTo(groupsForContactBefore.size() + 1));
        assertThat(groupsForContactAfter, equalTo(groupsForContactBefore.withAdded(app.db().groupByName(groupToTest.getName()))));
    }

    public ContactData selectAvailableContact() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() < groups.size()) {
                return contact;
            }
        }
        app.goTo().contactPage();
        app.contact().create(new ContactData().withFirstname("Nastya").withLastname("Mal99").withAddress("Moscow").withHomephone("123456789").withEmail("test1@test.com"));
        Contacts newContact = app.db().contacts();
        return app.db().contactById(newContact.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    }

    private GroupData selectAvailableGroup(ContactData contact) {
        Groups groups = app.db().groups();
        Groups contactGroups = contact.getGroups();
        for (GroupData group : contactGroups) {
            groups.remove(group);
        }
        return groups.iterator().next();
    }
}