import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Arrays;
import java.util.*;
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
    String header = "templates/header.vtl";

    // get("/", (request, response) -> {
    //   Map <String, Object> model = new HashMap <String, Object>();
    //   model.put("template", "templates/index.vtl");
    //   model.put("title", "Adam Hair Salon");
    //   model.put("header", header);
    //   model.put("css", "");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

   	get("/find",  (request, response) -> {
      Map <String, Object> model = new HashMap <String, Object>();
      String market = request.queryParams("market");
      String currency = request.queryParams("currency");
      String locale = request.queryParams("locale");
      String originPlace = request.queryParams("originPlace");
      String destinationPlace = request.queryParams("destinationPlace");
      String outboundPartialDate = request.queryParams("outboundPartialDate");
      String inboundPartialDate = request.queryParams("inboundPartialDate");
      String url = "http://partners.api.skyscanner.net/apiservices/browsedates/v1.0/" + market + "/" + currency + "/" +locale + "/" + originPlace + "/" + destinationPlace + "/" + outboundPartialDate + "/" + inboundPartialDate + "?apiKey=jo567814663897645898889958369326";
      String output = getData(url);

      JSONParser parser = new JSONParser();

      try{
         Object obj = parser.parse(output);

          JSONObject jsonObject = (JSONObject) obj;

          JSONArray places = (JSONArray) jsonObject.get("Places");

          ArrayList<Place> placesList = new ArrayList<Place>();

          for (int i = 0; i<places.length(); i++) {
            JSONObject data = (JSONObject) places.get(i);
            Place newPlace = new Place(data.get("PlaceId"), data.get("SkyscannerCode"));
            placesList.add(newPlace);
          }

          JSONArray carriers = (JSONArray) jsonObject.get("Carriers");

          ArrayList<Carrier> carriersList = new ArrayList<Carrier>();

          for (int i=0; i<carriers.length() ; i++ ) {
            JSONObject carrierData = (JSONObject) carriers.get(i);
            Carrier newCarrier = new Carrier(carrierData.get("CarrierId"),carrierData.get("Name"));
            carriersList.add(newCarrier);
          }

          JSONArray quotes = (JSONArray) jsonObject.get("Quotes");

          ArrayList<Flights> flightsList = new ArrayList<Flights>();

          for (int i=0; i<quotes.length();i++) {
            JSONObject quotesData = (JSONObject) quotes.get(i);

            Flights newFlight = new Flights (quotesData.get("MinPrice"), quotesData.get("Direct"));

            JSONArray outBound = (JSONArray) jsonObject.get("OutboundLeg");
            JSONObject outBoundData = (JSONObject) outBound.get(0);

            newFlight.setOutBoundDestinationInformation(outBoundData.get("OriginId"), outBoundData.get("DestinationId"), outBoundData.get("DepartureDate"));

            JSONArray outBoundCarrierId = (JSONArray) jsonObject.get("CarrierIds");
            JSONObject outBoundDataCarrierId = (JSONObject) outBoundCarrierId.get(0);
            newFlight.setOutBoundCarrierId(outBoundDataCarrierId);

            JSONArray inBound = (JSONArray) jsonObject.get("InboundLeg");
            JSONObject inBoundData = (JSONObject) inBound.get(0);

            newFlight.setInBoundDestinationInformation(inBoundData.get("OriginId"), inBoundData.get("DestinationId"), inBoundData.get("DepartureDate"));

            JSONArray inBoundCarrierId = (JSONArray) jsonObject.get("CarrierIds");
            JSONObject inBoundDataCarrierId = (JSONObject) inBoundCarrierId.get(0);
            newFlight.setInBoundCarrierId(inBoundDataCarrierId);

            flightsList.add(newFlight);
          }

          // JSONObject obj2 = (JSONObject)array.get(0);
          //
          //  System.out.println(obj2.get("Code"));
          //  System.out.println();
          //
          //  JSONArray  quoteArray = (JSONArray)

      }catch(ParseException pe){

         System.out.println("position: " + pe.getPosition());
         System.out.println(pe);
      }

      model.put("template", "templates/index.vtl");
      model.put("title", "Adam Hair Salon");
      model.put("header", header);
      model.put("css", "");
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
