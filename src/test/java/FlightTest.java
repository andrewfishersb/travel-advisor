import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class FlightTest{


//save all delete find

  public void Flight_instantiatesFlight(){
    Flight aFlight = new Flight("Today","Tomorrow",500,2,1,"PDX","SBA");
    assertTrue(aFlight instanceof Flight);
  }

  public void Flight_savesFlightToDataBase_tru(){
    Flight aFlight = new Flight("Today","Tomorrow",500,2,1,"PDX","SBA");
    aFlight.save();
    assertTrue(Flight.all().get(0).equals(aFlight));
  }

  public void Flight_returnAllFlights_true(){
    Flight aFlight = new Flight("Today","Tomorrow",500,2,1,"PDX","SBA");
    Flight anotherFlight = new Flight("Today","Tomorrow",55,2234,12,"PDX","SBA");
    aFlight.save();
    anotherFlight.save();
    assertEquals(2, Flight.all().size());
  }
  public void Flight_findFlightsFromDataBase_true(){
    Flight aFlight = new Flight("Today","Tomorrow",500,2,1,"PDX","SBA");
    aFlight.save();
    assertTrue(Flight.find(aFlight.getId()).equals(aFlight));
  }

  public void Flight_DeletesFromDataBase_true(){
    Flight aFlight = new Flight("Today","Tomorrow",500,2,1,"PDX","SBA");
    aFlight.save();
    aFlight.delete();
    assertEquals(0, Flight.all().size());
  }
}
