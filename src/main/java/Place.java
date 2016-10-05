public class Place{
  private int placeId;
  private String skyScannerCode;

  public Place(int placeId, String skyScannerCode){
    this.placeId = placeId;
    this.skyScannerCode = skyScannerCode;
  }

  public int getPlaceId(){
    return placeId;
  }

  public String getScannerCode(){
    return skyScannerCode;
  }
  
}
