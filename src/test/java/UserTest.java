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
      User myUser = new User("Moe", "g@user.com", 22);
      myUser.save();
      Flight aFlight = new Flight("Today","Tomorrow",500,2,myUser.getId(),"PDX","SBA");
      aFlight.save();
      Hotel aHotel = new Hotel("Marriot","Santa Barbara","Today","Tomorrow",1000,2,myUser.getId());
      aHotel.save();
      assertEquals(1500,myUser.getTotalPrice());
    }

  }
