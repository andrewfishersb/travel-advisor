public class Flights{
  private double price;
  private boolean direct;
  private int outBoundCarrierId;
  private int outBoundOriginId;
  private int outBoundDestinationId;
  private String outBoundDestinationDate;
  private String inBoundDestinationDate;
  private int inBoundCarrierId;
  private int inBoundOriginId;
  private int inBoundDestinationId;
  private String boundQuoteDateTime;

  public Flights(double price, boolean direct, String boundQuoteDateTime){
    this.price = price;
    this.direct = direct;
    this.boundQuoteDateTime = boundQuoteDateTime;
  }

  public String getBoundQuoteDateTime() {
    return boundQuoteDateTime;
  }

  public void setOutBoundDestinationInformation(int outBoundOriginId, int outBoundDestinationId, String outBoundDestinationDate){
    this.outBoundOriginId = outBoundOriginId;
    this.outBoundDestinationId = outBoundDestinationId;
    this.outBoundDestinationDate = outBoundDestinationDate;
  }

  public void setOutBoundCarrierId(int outBoundCarrierId){
      this.outBoundCarrierId = outBoundCarrierId;
  }

  public void setInBoundDestinationInformation(int inBoundOriginId, int inBoundDestinationId, String inBoundDestinationDate){
    this.inBoundOriginId = inBoundOriginId;
    this.inBoundDestinationId = inBoundDestinationId;
    this.inBoundDestinationDate = inBoundDestinationDate;
  }

  public void setInBoundCarrierId(int inBoundCarrierId){
      this.inBoundCarrierId = inBoundCarrierId;
  }

  public int getOutBoundCarrierId(){
    return outBoundCarrierId;
  }
  public int getOutBoundOriginId(){
    return outBoundOriginId;
  }

  public int getOutBoundDestinationId(){
    return outBoundDestinationId;
  }

  public String getOutDestinationDate(){
    return outBoundDestinationDate;
  }


  public int getInBoundCarrierId(){
    return inBoundCarrierId;
  }
  public int getInBoundOriginId(){
    return inBoundOriginId;
  }

  public int getInBoundDestinationId(){
    return inBoundDestinationId;
  }

  public String getInDestinationDate(){
    return inBoundDestinationDate;
  }

  public double getPrice() {
    return price;
  }

  public boolean getDirect() {
    return direct;
  }

  public String getOutBoundDate(String outbound){
    return outbound;
  }

  public String getInBoundDate(String inbound){
    return inbound;
  }

}
