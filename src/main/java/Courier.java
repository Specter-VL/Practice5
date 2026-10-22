public class Courier extends Delivery implements Trackable, Billable {

  private final String trackingCode;
  private final long price;

  public Courier(String name, String trackingCode, long price) {
    super(name);
    this.trackingCode = trackingCode;
    this.price = price;
  }

  @Override
  public long cost() {
    return price;
  }

  @Override
  public String getTrackingCode() {
    return trackingCode;
  }

  @Override
  public long payment() {
    return cost();
  }
}