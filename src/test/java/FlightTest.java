import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class FlightTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Flight_instantiatesFlight(){
    Flight aFlight = new Flight("2016-10-06","2016-10-17",500,2,1,"PDX","SBA","Alaskan");
    assertTrue(aFlight instanceof Flight);
  }

  @Test
  public void Flight_savesFlightToDataBase_true(){
    Flight aFlight = new Flight("2016-10-06","2016-10-17",500,2,1,"PDX","SBA","Alaskan");
    aFlight.save();
    System.out.println("Flight: "+aFlight.getStartLocation());

    System.out.println("Size: "+Flight.all().get(0).getStartLocation());

    assertTrue(Flight.all().get(0).equals(aFlight));
  }

  @Test
  public void Flight_returnAllFlights_true(){
    Flight aFlight = new Flight("2016-10-06","2016-10-17",500,2,1,"PDX","SBA","Alaskan");
    Flight anotherFlight = new Flight("2016-10-16","2016-10-27",200,1,2,"PDX","SFO","United");
    aFlight.save();
    anotherFlight.save();
    assertEquals(2, Flight.all().size());
  }

  @Test
  public void Flight_findFlightsFromDataBase_true(){
    Flight aFlight = new Flight("2016-10-06","2016-10-17",500,2,1,"PDX","SBA","Alaskan");
    aFlight.save();
    assertTrue(Flight.find(aFlight.getUserId()).equals(aFlight));
  }

  @Test
  public void Flight_DeletesFromDataBase_true(){
    Flight aFlight = new Flight("2016-10-06","2016-10-17",500,2,1,"PDX","SBA","Alaskan");
    aFlight.save();
    aFlight.delete();
    assertEquals(0, Flight.all().size());
  }
}
