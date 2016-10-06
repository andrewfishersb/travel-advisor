import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Car extends Booking{
  private int id;
  private String name;
  private int rentalDays;


  public Car(String name, int rentalDays, int price, int userId){
    this.name = name;
    this.rentalDays = rentalDays;
    this.price = price;
    this.userId = userId;
  }

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public int getRentalDays(){
    return rentalDays;
  }

  public static List<Car> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM cars";
      return con.createQuery(sql).executeAndFetch(Car.class);
    }
  }

  public static Car find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM cars WHERE userid=:id";
      return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Car.class);
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO cars (name, rentaldays, price, userid) VALUES (:name, :rentaldays, :price, :userid)";
      this.id = (int) con.createQuery(sql,true)
        .addParameter("name",this.name)
        .addParameter("rentaldays",this.rentalDays)
        .addParameter("price",this.price)
        .addParameter("userid",this.userId).executeUpdate().getKey();
    }
  }

}
