import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class HotelTest{


  public void Hotel_instantiatesHotel(){
    Hotel aHotel = new Hotel("Marriot","Santa Barbara","Today","Tomorrow",500,2,1);
    assertTrue(aHotel instanceof Hotel);
  }

  public void Hotel_savesHotelToDataBase_tru(){
    Hotel aHotel = new Hotel("Marriot","Santa Barbara","Today","Tomorrow",500,2,1);
    aHotel.save();
    assertTrue(Hotel.all().get(0).equals(aHotel));
  }

  public void Hotel_returnAllHotels_true(){
    Hotel aHotel = new Hotel("Marriot","Santa Barbara","Today","Tomorrow",500,2,1);
    Hotel anotherHotel = new Hotel("Hilton","Portland","Today","Tomorrow",300,4,2);
    aHotel.save();
    anotherHotel.save();
    assertEquals(2, Hotel.all().size());
  }
  public void Hotel_findHotelsFromDataBase_true(){
    Hotel aHotel = new Hotel("Marriot","Santa Barbara","Today","Tomorrow",500,2,1);
    aHotel.save();
    assertTrue(Hotel.find(aHotel.getId()).equals(aHotel));
  }

  public void Hotel_DeletesFromDataBase_true(){
    Hotel aHotel = new Hotel("Marriot","Santa Barbara","Today","Tomorrow",500,2,1);
    aHotel.save();
    aHotel.delete();
    assertEquals(0, Hotel.all().size());
  }
}
