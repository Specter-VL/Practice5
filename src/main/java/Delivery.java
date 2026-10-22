public abstract class Delivery {

  private final String name;

  public Delivery(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public abstract long cost();
}
