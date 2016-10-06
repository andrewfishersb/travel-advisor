import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class HotelTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Hotel_instantiatesHotel(){
    Hotel aHotel = new Hotel("Marriot",500,1,5,1);
    assertTrue(aHotel instanceof Hotel);
  }

  @Test
  public void Hotel_savesHotelToDataBase_tru(){
    Hotel aHotel = new Hotel("Marriot",500,1,5,1);
    aHotel.save();
    assertTrue(Hotel.all().get(0).equals(aHotel));
  }

  @Test
  public void Hotel_returnAllHotels_true(){
    Hotel aHotel = new Hotel("Marriot",500,1,5,1);
    Hotel anotherHotel = new Hotel("Hilton",300,1,7,2);
    aHotel.save();
    anotherHotel.save();
    assertEquals(2, Hotel.all().size());
  }

  @Test
  public void Hotel_findHotelsFromDataBase_true(){
    Hotel aHotel = new Hotel("Marriot",500,1,5,1);
    aHotel.save();
    assertTrue(Hotel.find(aHotel.getUserId()).equals(aHotel));
  }

  @Test
  public void Hotel_DeletesFromDataBase_true(){
    Hotel aHotel = new Hotel("Marriot",500,1,5,1);
    aHotel.save();
    aHotel.delete();
    assertEquals(0, Hotel.all().size());
  }
}
