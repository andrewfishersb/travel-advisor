import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.net.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("user", request.session().attribute("user"));
      if (User.getLogInStatus()) {
        model.put("template", "templates/index.vtl");
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
        model.put("template", "templates/index.vtl");
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
      model.put("template","templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/users/:id", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("user", request.session().attribute("user"));
    //   model.put("template", "templates/form.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    // post("/itinerary/flights/results", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   String flightDepart = request.queryParams("depart");
    //   String flightArrive = request.queryParams("arrive");
    //   String flightDepartDate = request.queryParams("depart-date");
    //   String flightReturnDate = request.queryParams("return-date");
    //   int groupSize = Integer.parseInt(request.queryParams("group-size"));
    //   Flight newFlight = new Flight(flightDepartDate, flightReturnDate, 0, groupSize, 1, flightDepart, flightArrive);
    //   newFlight.save();
    //   response.redirect("itinerary/flights/" + flightDepart + "/" + flightArrive + "/" + flightDepartDate + "/" + flightReturnDate);
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    //creates the train
    post("/buy", (request, response) ->{
      Map<String,Object> model = new HashMap<String, Object>();
      String airline = request.queryParams("airline");
      String origin = request.queryParams("origin");
      String destination = request.queryParams("destination");
      String startDate = request.queryParams("start");
      String endDate = request.queryParams("end");
      double pricePerTicket =Double.parseDouble(request.queryParams("cost"));
      int groupSize = Integer.parseInt(request.queryParams("group-size"));
      int userId = Integer.parseInt(request.queryParams("userId"));
      Flight boughtFlight = new Flight(startDate, endDate, pricePerTicket,groupSize,userId, origin, destination,airline);
      boughtFlight.save();
      response.redirect("/");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/find",  (request, response) -> {
      Map <String, Object> model = new HashMap <String, Object>();
      String originPlace = request.queryParams("originPlace");
      String destinationPlace = request.queryParams("destinationPlace");
      String outboundPartialDate = request.queryParams("outboundPartialDate");
      String inboundPartialDate = request.queryParams("inboundPartialDate");
      int groupSize = Integer.parseInt(request.queryParams("group-size"));
      String url = "http://partners.api.skyscanner.net/apiservices/browsedates/v1.0/US/USD/en-us/" + originPlace + "/" + destinationPlace + "/" + outboundPartialDate + "/" + inboundPartialDate + "?apiKey=jo567814663897645898889958369326";

      String output = getData(url);
      model.put("originPlace", originPlace);
      model.put("destinationPlace", destinationPlace);
      model.put("startdate",outboundPartialDate);
      model.put("enddate",inboundPartialDate);
      model.put("group-size",groupSize);

      JSONParser parser = new JSONParser();

      try{
         Object obj = parser.parse(output);

          JSONObject jsonObject = (JSONObject) obj;

          JSONArray places = (JSONArray) jsonObject.get("Places");

          ArrayList<Place> placesList = new ArrayList<Place>();

          for (int i = 0; i<places.size(); i++) {
            JSONObject data = (JSONObject) places.get(i);
            Place newPlace = new Place(Integer.parseInt(data.get("PlaceId").toString()), data.get("SkyscannerCode").toString());
            placesList.add(newPlace);
          }

          JSONArray carriers = (JSONArray) jsonObject.get("Carriers");

          ArrayList<Carrier> carriersList = new ArrayList<Carrier>();

          for (int i=0; i<carriers.size() ; i++ ) {
            JSONObject carrierData = (JSONObject) carriers.get(i);
            Carrier newCarrier = new Carrier(Integer.parseInt(carrierData.get("CarrierId").toString()),carrierData.get("Name").toString());
            carriersList.add(newCarrier);
          }

          JSONArray quotes = (JSONArray) jsonObject.get("Quotes");

          ArrayList<Flights> flightsList = new ArrayList<Flights>();

          for (int i=0; i < quotes.size();i++) {
            JSONObject quotesData = (JSONObject) quotes.get(i);

            Flights newFlight = new Flights (Double.parseDouble(quotesData.get("MinPrice").toString()), Boolean.valueOf(quotesData.get("Direct").toString()), quotesData.get("QuoteDateTime").toString());

            JSONObject outBound = (JSONObject) quotesData.get("OutboundLeg");
            JSONObject inBound = (JSONObject) quotesData.get("InboundLeg");

            if (outBound != null) {
              newFlight.setOutBoundDestinationInformation(Integer.parseInt(outBound.get("OriginId").toString()), Integer.parseInt(outBound.get("DestinationId").toString()), outBound.get("DepartureDate").toString());
              JSONArray outBoundCarrierId = (JSONArray) outBound.get("CarrierIds");
              newFlight.setOutBoundCarrierId(Integer.parseInt(outBoundCarrierId.get(0).toString()));
            }

            if (inBound != null) {
              newFlight.setInBoundDestinationInformation(Integer.parseInt(inBound.get("OriginId").toString()), Integer.parseInt(inBound.get("DestinationId").toString()), inBound.get("DepartureDate").toString());
              JSONArray inBoundCarrierId = (JSONArray) inBound.get("CarrierIds");
              newFlight.setInBoundCarrierId(Integer.parseInt(inBoundCarrierId.get(0).toString()));
            }

            flightsList.add(newFlight);
          }

          model.put("flights", flightsList);
          model.put("places", placesList);
          model.put("carriers", carriersList);

      }catch(ParseException pe){

         System.out.println("position: " + pe.getPosition());
         System.out.println(pe);
      }

      model.put("template", "templates/find.vtl");
      model.put("user", request.session().attribute("user"));
      //model.put("title", "Adam Hair Salon");
      //model.put("header", header);
      //model.put("css", "");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }

  public static String getData(String urlLink) {
    String json = "";
    try {

      URL url = new URL(urlLink);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : "
            + conn.getResponseCode());
      }

      BufferedReader br = new BufferedReader(new InputStreamReader(
        (conn.getInputStream())));

      String output;

      while ((output = br.readLine()) != null) {
        json += output;
      }

      conn.disconnect();

      } catch (MalformedURLException e) {

      e.printStackTrace();

      } catch (IOException e) {

      e.printStackTrace();

      }
       return json;
  }
}

//  post("", (request, response) -> {
//        Map<String, Object> model = new HashMap<String, Object>();
//        model.put("template", "templates/endangered.vtl");
//        return new ModelAndView(model, layout);
//  }, new VelocityTemplateEngine());
