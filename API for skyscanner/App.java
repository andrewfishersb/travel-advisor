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

          JSONArray array = (JSONArray) jsonObject.get("Currencies");

          JSONObject obj2 = (JSONObject)array.get(0);

           System.out.println(obj2.get("Code"));
           System.out.println();

           JSONArray  quoteArray = (JSONArray) 

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
