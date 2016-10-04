import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;

public class UserTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void delete_deletesUser_true() {
    User myUser = new User("Moe","password", "g@user.com", 22);
    myUser.save();
    int myUserId = myUser.getId();
    myUser.delete();
    assertEquals(null, User.find(myUserId));
  }
  @Test
  public void findsCoreectUser_User_true() {
    User myUser = new User("Moe","password", "g@user.com", 22);
    myUser.save();
    assertEquals(myUser, User.find(myUser.getId()));
    }
    @Test
    public void save_savesUser_true() {
      User myUser = new User("Moe","password", "g@user.com", 22);
      myUser.save();
      assertEquals(myUser, User.find(myUser.getId()));
    }

    @Test
    public void getBookings_ReturnsListOfAllUsersBookings(){
      User myUser = new User("Moe","password", "g@user.com", 22);
      myUser.save();
      Flight aFlight = new Flight("Today","Tomorrow",500,2,myUser.getId(),"PDX","SBA");
      aFlight.save();
      Hotel aHotel = new Hotel("Marriot","Santa Barbara","Today","Tomorrow",500,2,myUser.getId());
      aHotel.save();
      List<Object> bookings = myUser.getBookings();
      assertEquals(aFlight,myUser.getBookings().get(0));
    }

    @Test
    public void getTotalPrice_FindsFinalPrice(){
      User myUser = new User("Moe","password", "g@user.com", 22);
      myUser.save();
      Flight aFlight = new Flight("Today","Tomorrow",500,2,myUser.getId(),"PDX","SBA");
      aFlight.save();
      Hotel aHotel = new Hotel("Marriot","Santa Barbara","Today","Tomorrow",1000,2,myUser.getId());
      aHotel.save();
      assertEquals(1500,myUser.getTotalPrice());
    }

    // rejected from login, logged in successfully
    @Test
    public void login_UserLogsInSuccessfully_true(){
      User user1 = new User("Andrew Merrell","1234password","andrew@merrell.com",28);
      user1.save();
      User user2 = new User("Andrew Fisher","password1234","andrew@fisher.com",25);
      user2.save();
      User user3 = new User("Jackson Meyer","pas1swo2rd3","jackson@meyer.com",24);
      user3.save();
      assertTrue(User.login("andrew@fisher.com","password1234").equals(user2));
    }

    @Test(expected = RuntimeException.class)
    public void login_FailedLogin(){
      User aUser = new User("Andrew Merrell","1234password","andrew@merrell.com",28);
      aUser.save();
      User.login("andrew@fisher.com","1234password");
    }

  }
