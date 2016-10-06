import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Hotel extends Booking {
  private String name;
  private int duration;
  private int roomsBooked;

  public Hotel(String name , int price, int roomsBooked, int duration, int userId){
    this.name = name;
    this.price = price;
    this.roomsBooked = roomsBooked;
    this.userId = userId;
    this.duration = duration;
  }
  
  public String getName(){
    return name;
  }

  public int getRoomsBooked(){
    return roomsBooked;
  }

  public int getDuration(){
    return duration;
  }

  public static List<Hotel> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM hotels";
      return con.createQuery(sql).executeAndFetch(Hotel.class);
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO hotels (name,roomsbooked,duration,price,userid) VALUES(:name,:roomsbooked,:duration,:price,:userid)";
      this.id = (int) con.createQuery(sql,true)
        .addParameter("name",this.name)
        .addParameter("roomsbooked",this.roomsBooked)
        .addParameter("duration",this.duration)
        .addParameter("price",this.price)
        .addParameter("userid",this.userId).executeUpdate().getKey();
    }

  }

      public static Hotel find(int id){
        try(Connection con = DB.sql2o.open()){
          String sql = "SELECT * FROM hotels WHERE id = :id";
          return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Hotel.class);
        }
      }

      public void delete(){
        try(Connection con = DB.sql2o.open()){
          String sql = "DELETE FROM hotels WHERE id=:id";
          con.createQuery(sql).addParameter("id",id).executeUpdate();
        }
      }
}
