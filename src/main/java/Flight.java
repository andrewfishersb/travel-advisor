import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Flight extends Booking{
  private int id;
  private String startLocation;
  private String endLocation;
  private String dateReturn;

  public Flight(String startDate, String endDate, int price, int groupsize,int userId,String startLocation,String endLocation){
    this.startDate = startDate;
    this.endDate = endDate;
    this.price = price;
    this.groupSize = groupsize;
    this.userId = userId;
    this.startLocation = startLocation;
    this.endLocation = endLocation;
  }

  public int getId(){
    return id;
  }

  public String getStartLocation(){
    return startLocation;
  }

  public String getEndLocation(){
    return startLocation;
  }
//all save find delete update?
  public static List<Flight> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM flights";
      return con.createQuery(sql).executeAndFetch(Flight.class);
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO flights (startDate, endDate,startLocation,endLocation, price, groupsize,userid) VALUES (:startDate, :endDate, :startLocation, :endLocation,:price,:groupsize,:userid)";
      this.id = (int) con.createQuery(sql,true)
        .addParameter("startDate",this.startDate)
        .addParameter("endDate",this.endDate)
        .addParameter("price",this.price)
        .addParameter("groupsize",this.groupSize)
        .addParameter("userId",this.userId)
        .addParameter("startlocation",this.startLocation)
        .addParameter("endLocation",this.endLocation).executeUpdate().getKey();
    }
  }

}
