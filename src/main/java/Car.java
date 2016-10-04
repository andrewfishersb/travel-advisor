import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Car extends Booking{

  public Car(String startDate, String endDate, int price, int groupSize, int userId){
    this.startDate = startDate;
    this.endDate = endDate;
    this.price = price;
    this.groupSize = groupSize;
    this.userId = userId;
  }

  public int getId(){
    return id;
  }
//all save find delete update?
  public static List<Car> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM cars";
      return con.createQuery(sql).executeAndFetch(Car.class);
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO cars (startDate, endDate, price, groupsize, userid) VALUES (:startDate, :endDate, :price, :groupsize, :userid)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("startDate", this.startDate)
        .addParameter("endDate", this.endDate)
        .addParameter("price", this.price)
        .addParameter("groupsize", this.groupSize)
        .addParameter("userid", this.userId).executeUpdate().getKey();
    }
  }

  public static Car find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM cars WHERE id=:id";
      return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Car.class);
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM cars WHERE id = :id";
      con.createQuery(sql).addParameter("id",id).executeUpdate();
    }
  }

  @Override
  public boolean equals(Object otherCar) {
   if (!(otherCar instanceof Car)) {
     return false;
   } else {
     Car newCar = (Car) otherCar;
     return this.getId() == newCar.getId();
    }
  }

}
