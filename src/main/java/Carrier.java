public class Carrier{
  private int carrierId;
  private String airline;

  public Carrier(int carrierId, String airline){
    this.carrierId = carrierId;
    this.airline = airline;
  }

  public int getCarrierId(){
    return carrierId;
  }

  public String getAirline(){
    return airline;
  }


}
