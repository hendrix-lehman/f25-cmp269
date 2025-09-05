abstract class Automobile {

  // Attributes
  // Common attributes for all automobiles
  protected String make;
  protected String model;
  protected int year;

  protected int doors;
  protected String engine;
  protected int windows;
  protected int seats;
  protected int wheels;
  protected int lights;
  protected String trunkSize;
  protected String hoodSize;

  // Constants
  protected final static int WINDSHIELD = 1;
  protected final static int REARVIEW_MIRROR = 1;
  protected final static int SIDE_MIRRORS = 2;

  // Constructor
  // Common constructor for all automobiles
  // public Automobile(String make, String model, int year) {
  //   this.make = make;
  //   this.model = model;
  //   this.year = year;

  //   // default values
  //   this.doors = 4; 
  //   this.engine = "V4";
  //   this.windows = 4;
  //   this.seats = 5;
  //   this.wheels = 4;
  //   this.lights = 4;
  //   this.trunkSize = "medium";
  //   this.hoodSize = "medium";
  // }

  // Overloaded constructor
  // Allows setting all attributes
  public Automobile(String make, String model, int year, int doors, String engine, int windows, int seats, int wheels, int lights, String trunkSize, String hoodSize) {
    this.make = make;
    this.model = model;
    this.year = year;
    this.doors = doors;
    this.engine = engine;
    this.windows = windows;
    this.seats = seats;
    this.wheels = wheels;
    this.lights = lights;
    this.trunkSize = trunkSize;
    this.hoodSize = hoodSize;
  }

  // Getters and setters for all attributes
  public String getMake() {
    return make;
  }

  public String getModel() {
    return model;
  }

  public int getYear() {
    return year;
  }

  public int getDoors() {
    return doors;
  }

  public String getEngine() {
    return engine;
  }

  public void setEngine(String engine) {
    this.engine = engine;
  }

  public int getWindows() {
    return windows;
  }

  public int getSeats() {
    return seats;
  }

  public void setSeats(int seats) {
    this.seats = seats;
  }

  public int getWheels() {
    return wheels;
  }

  public int getLights() {
    return lights;
  }

  public void setLights(int lights) {
    this.lights = lights;
  }

  public String getTrunkSize() {
    return trunkSize;
  }

  public String getHoodSize() {
    return hoodSize;
  }

  // Common methods
  public void startEngine() {
    System.out.println("The " + make + " " + model + " is starting.");
  }

  public void stopEngine() {
    System.out.println("The " + make + " " + model + " is stopping.");
  }

  public abstract void honkHorn();

}

