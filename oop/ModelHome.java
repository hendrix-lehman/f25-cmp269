abstract class ModelHome {
  // abstract class can have abstract methods (without body) and concrete methods (with body)
  abstract void showModelDetails(); // abstract method

  void displayModelInfo() { // concrete method
    System.out.println("This is a model home.");
  }

  // static methods
  public static void showWelcomeMessage() {
    System.out.println("Welcome to the House Management System!");
  }


}

