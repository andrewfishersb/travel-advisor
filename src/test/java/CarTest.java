import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class CarTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Car_instantiatesCar(){
    Car aCar = new Car("Today","Tomorrow",500,2,1);
    assertTrue(aCar instanceof Car);
  }

  @Test
  public void Car_savesCarToDataBase_true(){
    Car aCar = new Car("Today","Tomorrow",500,2,1);
    aCar.save();
    assertTrue(Car.all().get(0).equals(aCar));
  }

  @Test
  public void Car_returnAllCars_true(){
    Car aCar = new Car("Today","Tomorrow",500,2,1);
    Car anotherCar = new Car("Today","Tomorrow",55,2234,12);
    aCar.save();
    anotherCar.save();
    assertEquals(2, Car.all().size());
  }

  @Test
  public void Car_findCarsFromDataBase_true(){
    Car aCar = new Car("Today","Tomorrow",500,2,1);
    aCar.save();
    assertTrue(Car.find(aCar.getId()).equals(aCar));
  }

  @Test
  public void Car_DeletesFromDataBase_true(){
    Car aCar = new Car("Today","Tomorrow",500,2,1);
    aCar.save();
    aCar.delete();
    assertEquals(0, Car.all().size());
  }
}
