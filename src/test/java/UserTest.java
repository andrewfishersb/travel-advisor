import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class UserTest{


@Test
public void delete_deletesUser_true() {
  User myUser = new User("Mow the lawn", "g@user.com", 22);
  myUser.save();
  int myUserId = myUser.getId();
  myUser.delete();
  assertEquals(null, User.find(myUserId));
}
@Test
public void findsCoreectUser_User_true() {
  User myUser = new User("Mow the lawn", "g@user.com", 22);
  myUser.save();
  assertEquals(myUser, User.find(myUser.getId()));
  }
  @Test
  public void save_savesUser_true() {
    User myUser = new User("Mow the lawn", "g@user.com", 22);
    myUser.save();
    assertEquals(myUser, User.find(myUser.getId()));
  }

}
