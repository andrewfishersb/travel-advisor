import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class User {
  private String name;
  private String email;
  private int age;
  private int id;

  public User(String name, String email, int age){
    this.name = name;
    this.email = email;
    this.age = age;
    this.id = id;
  }

  public String getName(){
    return name;
  }

  public String getEmail(){
    return email;
  }

  public int getAge(){
    return age;
  }

//possibly need to check if null value and add test to confirm total cost.
  public int getTotalPrice(){
    try(Connection con = DB.sql2o.open()){
      String flightSQL = "SELECT price FROM flights WHERE userid=:userid";
      String hotelSQL = "SELECT price FROM hotels WHERE userid=:userid";
      String carSQL = "SELECT price FROM cars WHERE userid=:userid";
      Integer flightCost = con.createQuery(flightSQL).addParameter("userid",this.id).executeAndFetchFirst(Integer.class);
      Integer hotelCost = con.createQuery(flightSQL).addParameter("userid",this.id).executeAndFetchFirst(Integer.class);
      Integer carCost = con.createQuery(flightSQL).addParameter("userid",this.id).executeAndFetchFirst(Integer.class);
      return flightCost + hotelCost + carCost;
    }
  }


  public List<Object> getBookings(){
    try(Connection con = DB.sql2o.open()){
      List<Object> allBookings = new ArrayList<Object>();
      String flightSQL = "SELECT * FROM flights WHERE userid=:userid";
      String hotelSQL = "SELECT * FROM hotels WHERE userid=:userid";
      // String carSQL = "SELECT * FROM cars WHERE userid=:id";
      Flight theFlight = con.createQuery(flightSQL).addParameter("userid",this.id).executeAndFetchFirst(Flight.class);
      Hotel theHotel = con.createQuery(hotelSQL).addParameter("userid",this.id).executeAndFetchFirst(Hotel.class);
      // Car theCar = con.createQuery(carSQL).addParameter("id",this.id).executeAndFetch(Car.class);
      allBookings.add(theFlight);
      allBookings.add(theHotel);
      // allBookings.add(theCar);
      return allBookings;
    }
  }

  public static List<User> all() {
    String sql = "SELECT * FROM users";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(User.class);
    }
  }

  public int getId() {
     return id;
  }

  @Override
  public boolean equals(Object otherUser) {
   if (!(otherUser instanceof User)) {
     return false;
   } else {
     User newUser = (User) otherUser;
     return this.getName().equals(newUser.getName()) &&
            this.getEmail().equals(newUser.getEmail()) &&
            this.getAge() == newUser.getAge();
    }
  }

   public void save() {
     try(Connection con = DB.sql2o.open()) {
       String sql = "INSERT INTO Users (name, email, age) VALUES (:name, :email, :age)";
       this.id = (int) con.createQuery(sql, true)
         .addParameter("name", this.name)
         .addParameter("email", this.email)
         .addParameter("age", this.age)
         .executeUpdate()
         .getKey();
     }
   }

   public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM Users WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public static User find(int id){
    try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM Users WHERE id = :id";
    return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(User.class);
    }
  }
}
