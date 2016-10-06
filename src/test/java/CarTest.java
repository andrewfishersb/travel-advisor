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
    Car aCar = new Car("Volvo",2,100,1);
    assertTrue(aCar instanceof Car);
  }

  @Test
  public void Car_savesCarToDataBase_true(){
    Car aCar = new Car("Volvo",2,100,1);
    aCar.save();
    assertTrue(Car.all().get(0).equals(aCar));
  }

  @Test
  public void Car_returnAllCars_true(){
    Car aCar = new Car("Volvo",2,100,1);
    Car anotherCar = new Car("Tesla",3,300,3);
    aCar.save();
    anotherCar.save();
    assertEquals(2, Car.all().size());
  }

  @Test
  public void Car_findCarsFromDataBase_true(){
    Car aCar = new Car("Volvo",2,100,1);
    aCar.save();
    assertTrue(Car.find(1).equals(aCar));
  }

  @Test
  public void Car_DeletesFromDataBase_true(){
    Car aCar = new Car("Volvo",2,100,1);
    aCar.save();
    aCar.delete();
    assertEquals(0, Car.all().size());
  }
}
