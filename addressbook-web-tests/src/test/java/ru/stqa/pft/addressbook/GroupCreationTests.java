package ru.stqa.pft.addressbook;


import org.testng.annotations.*;


public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test41", "test42", "test43"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
