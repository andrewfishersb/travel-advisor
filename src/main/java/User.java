import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class User {
  public String name;
  public String email;
  public int age;
  public int id;

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
public static List<User>all(){
  String sql = "SELECT * FROM users";
  try(Connection con = DB.sql2o.open()) {
   return con.createQuery(sql).executeAndFetch(User.class);
 }
}
public int getId() {
   return id;
  }
 }
