package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class DeleteContactFromGroupTest extends TestBase{

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
    public void testDeleteContactFromGroup() throws Exception {
        Contacts beforeContacts = app.db().contacts();
        Groups beforeGroups = app.db().groups();
        ContactData deleteContactFromGroup = beforeContacts.iterator().next();
        GroupData groupToRemove = beforeGroups.iterator().next();
        if (deleteContactFromGroup.getGroups().size() == 0) {
            app.goTo().contactPage();
            app.contact().addToGroup(deleteContactFromGroup);
            deleteContactFromGroup.inGroup(groupToRemove);
        }
        app.goTo().contactPage();
        app.contact().deleteFromGroup(deleteContactFromGroup);
        deleteContactFromGroup.fromGroup(groupToRemove);

        assertTrue(deleteContactFromGroup.getGroups().size() == 0);


        Contacts afterContacts = app.db().contacts();
        Groups afterGroups = app.db().groups();
        assertThat(afterContacts, equalTo(beforeContacts.withOut(deleteContactFromGroup).withAdded(deleteContactFromGroup)));
        assertThat(afterGroups, equalTo(beforeGroups.withOut(groupToRemove).withAdded(groupToRemove)));

    }

}