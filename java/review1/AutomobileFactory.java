class AutomobileFactory {
  // Singleton instance
  private static AutomobileFactory instance;

  // Private constructor to prevent instantiation
  private AutomobileFactory() {}

  // Method to get the singleton instance
  // thread-safe
  public static synchronized AutomobileFactory getInstance() {
    if (instance == null) {
      instance = new AutomobileFactory();
    }
    return instance;
  }

  // Factory method to create automobiles
  // type can be "Sedan" or "SUV"
  public Automobile assemble(String type, String model, int year) {
    // This line is a code smell, but for simplicity we will keep it here
    // it forces the use of Ford as the only car manufacturer in this factory
    CarAssembler assembler = new Ford();
    return assembler.assembleCar(type, model, year);
  }

}

