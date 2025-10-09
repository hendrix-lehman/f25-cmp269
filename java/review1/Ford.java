// add package name here if needed
//
class Ford implements CarAssembler {
  // Ford is a car manufacturer that produces different models of cars

  // We are going to use our Automobile class to create different models 
  // SUVs and Sedans of Ford cars
  //

  public static final String MAKE = "Ford";

  public static Automobile createSedan(String model, int year) {
    return new Sedan(MAKE, model, year);
  }

  public static Automobile createSuv(String model, int year) {
    return new Suv(MAKE, model, year);
  }

  @Override
  public Automobile assembleCar(String type, String model, int year) {
    if (type.equalsIgnoreCase("Sedan")) {
      return createSedan(model, year);
    } else if (type.equalsIgnoreCase("SUV")) {
      return createSuv(model, year);
    } else {
      throw new IllegalArgumentException("Unknown car type: " + type);
    }
  }
}

