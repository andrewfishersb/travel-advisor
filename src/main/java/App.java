import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;
import java.util.ArrayList;


public class App{
  public static void main(String[] args) {
  staticFileLocation("/public");
  String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("user", request.session().attribute("user"));
      if (User.getLogInStatus()) {
        model.put("template", "templates/form.vtl");
      } else {
        model.put("template", "templates/user-login.vtl");
      }
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/logged-in", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String userEmail = request.queryParams("user-email");
      String userPassword = request.queryParams("user-password");
      User loggedInUser = User.login(userEmail,userPassword);
      if (User.getLogInStatus()) {

        request.session().attribute("user", loggedInUser);
        model.put("template", "templates/form.vtl");
      } else {
        model.put("template", "templates/user-login.vtl");//error message or something
      }
      response.redirect("/");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/create-account", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String userName = request.queryParams("user-name");
      String userEmail = request.queryParams("user-email");
      String userPassword = request.queryParams("user-password");
      int userAge = Integer.parseInt(request.queryParams("user-age"));
      User newUser = new User(userName, userPassword, userEmail, userAge);
      newUser.save();
      // request.session().attribute("user", newUser);
      model.put("user",newUser);
      model.put("template","templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/users/:id", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("user", request.session().attribute("user"));
    //   model.put("template", "templates/form.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    post("/itinerary/flights/results", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String flightDepart = request.queryParams("depart");
      String flightArrive = request.queryParams("arrive");
      String flightDepartDate = request.queryParams("depart-date");
      String flightReturnDate = request.queryParams("return-date");
      int groupSize = Integer.parseInt(request.queryParams("group-size"));
      Flight newFlight = new Flight(flightDepartDate, flightReturnDate, 0, groupSize, 1, flightDepart, flightArrive);
      newFlight.save();
      response.redirect("itinerary/flights/" + flightDepart + "/" + flightArrive + "/" + flightDepartDate + "/" + flightReturnDate);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/itinerary/flights/itinerary/flights/LAX/JFK/2016-10-04/2016-10-05", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/flight-results.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}




//  post("", (request, response) -> {
//        Map<String, Object> model = new HashMap<String, Object>();
//        model.put("template", "templates/endangered.vtl");
//        return new ModelAndView(model, layout);
//  }, new VelocityTemplateEngine());
