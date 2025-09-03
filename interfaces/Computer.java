abstract class Computer implements Disk, Keyboard, Memory, Screen {

  // properties common to all computers
  String brand;
  String model;

  // constructor
  public Computer(String brand, String model) {
    this.brand = brand;
    this.model = model;
  }

  /// getters
  public String getBrand() {
    return brand;
  }

  public String getModel() {
    return model;
  }

  // abstract method to be implemented by subclasses
  public abstract void assemble();

  // common methods
  public void start() {
    System.out.println("Starting the computer...");
  }

  public void shutdown() {
    System.out.println("Shutting down the computer...");
  }

  // we need to implement all methods from all interfaces
  @Override
  public void readData() {
    System.out.println("Reading data from disk...");
  }

  @Override
  public void writeData() {
    System.out.println("Writing data to disk...");
  }

  @Override
  public void type() {
    System.out.println("Typing on keyboard...");
  }

  @Override
  public void connect() {
    System.out.println("Connecting keyboard...");
  }

  @Override
  public void storeData() {
    System.out.println("Storing data in memory...");
  }

  @Override
  public void retrieveData() {
    System.out.println("Retrieving data from memory...");
  }

  @Override
  public void display() {
    System.out.println("Displaying on screen...");
  }

  @Override
  public void adjustBrightness() {
    System.out.println("Adjusting screen brightness...");
  }
}
