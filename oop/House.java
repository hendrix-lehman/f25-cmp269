class House extends ModelHome {
  // properties, fields or attributes
  String color;
  int rooms;
  boolean hasGarage;

  // default constructor
  public House() {
    this.color = "blue";
    this.rooms = 3;
    this.hasGarage = false;
  }

  // overloaded constructor
  public House(String color, int rooms, boolean hasGarage) {
    this.color = color;
    this.rooms = rooms;
    this.hasGarage = hasGarage;
  }

  // getter methods
  public String getColor() {
    return color;
  }

  public int getRooms() {
    return rooms;
  }

  public boolean hasGarage() {
    return hasGarage;
  }

  // setter methods
  public void setColor(String color) {
    this.color = color;
  }

  public void setRooms(int rooms) {
    this.rooms = rooms;
  }

  public void setHasGarage(boolean hasGarage) {
    this.hasGarage = hasGarage;
  }

  // special methods
  public void paintHouse(String newColor) {
    this.color = newColor;
    System.out.println("The house has been painted " + newColor);
  }

  public void displayInfo() {
    System.out.println("House color: " + color);
    System.out.println("Number of rooms: " + rooms);
    System.out.println("Has garage: " + hasGarage);
  }

  // implement abstract method from ModelHome
  @Override
  void showModelDetails() {
    System.out.println("This is a model home with " + rooms + " rooms and color " + color);
  }

}
