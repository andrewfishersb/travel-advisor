import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/travel_advisor_test", "postgres", "panthers");
  }

  @Override
    protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteUsersQuery = "DELETE FROM users *;";
      String deleteFlightsQuery = "DELETE FROM flights *;";
      String deleteHotelsQuery = "DELETE FROM hotels *;";
      String deleteCarsQuery = "DELETE FROM cars *;";
      con.createQuery(deleteUsersQuery).executeUpdate();
      con.createQuery(deleteFlightsQuery).executeUpdate();
      con.createQuery(deleteHotelsQuery).executeUpdate();
      con.createQuery(deleteCarsQuery).executeUpdate();
    }
  }

}
