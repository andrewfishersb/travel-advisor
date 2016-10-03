import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Hotel extends Booking {
  private String location;
  private String name;


  public Hotel(String name ,String location, String startDate, String endDate, int price, int groupsize, int userId){
    this.startDate = startDate;
    this.endDate = endDate;
    this.price = price;
    this.groupSize = groupsize;
    this.userId = userId;
    this.location = location;
  }

  public String getLocation(){
    return location;
  }

  public
  //all save delete find

  public List<Hotel> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM hotels";
      return con.createQuery(sql).executeUpdate();
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO hotels (name,location,startdate,enddate,price,groupsize,userid) VALUES(:name,:location,:startdate,:enddate.:price,:groupsize,:userid)";
      this.id = (int) con.createQuery(sql)
        .addParameter("name",this.name)
        .addParameter("location",this.location)
        .addParameter("startdate",this.startDate)
        .addParameter("enddate",this.endDate)
        .addParameter("price",this.price)
        .addParameter("groupsize",this.groupSize)
        .addParameter()
        .addParameter()

    }
  }
}
