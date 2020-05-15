package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class DeleteContactFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test41"));
        }
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
    public void testDeleteContactFromGroup() throws Exception {

        GroupData groupToTest = chooseGroupWithContact();
        Contacts contactsInGroupBefore = app.db().contactsInGroup(groupToTest.getName());

        ContactData contactToDelete = contactsInGroupBefore.iterator().next();
        Groups groupsForContactBefore = app.db().contactById(contactToDelete.getId()).getGroups();

        app.goTo().contactPage();
        app.contact().deleteContactFromGroup(contactToDelete, groupToTest);

        Contacts groupsForContactAfter = app.db().contactsInGroup(groupToTest.getName());

        assertThat(groupsForContactAfter.size(), equalTo(groupsForContactBefore.size() - 1));
        assertThat(groupsForContactAfter, equalTo(groupsForContactBefore.withOut(app.db().groupByName(groupToTest.getName()))));
    }

    private GroupData chooseGroupWithContact() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        for (GroupData group : groups) {
            if (app.db().contactsInGroup(group.getName()).size() > 0) {
                return group;
            }
        }
        GroupData groupWithContact = groups.iterator().next();
          app.contact().addContactToGroup(contacts.iterator().next(), groupWithContact);
          app.goTo().contactPage();
          return groupWithContact;
    }
}

