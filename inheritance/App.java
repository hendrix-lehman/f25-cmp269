// add package name here if needed
//
class App {

  // App entry point
  public static void main(String[] args) {

    House myHouse = new House(); // creates an instance of House using default constructor. 

    // the variable myHouse is an object of type House
    //
    myHouse.displayInfo(); // calls the displayInfo method on the myHouse object

    House anotherHouse = new House("red", 4, true); // creates another instance of House using overloaded constructor

    anotherHouse.displayModelInfo(); // calls the displayModelInfo method on the anotherHouse object

    // control flow using if-else
    if (anotherHouse.hasGarage()) {
      System.out.println("This house has a garage.");
    } else {
      System.out.println("This house does not have a garage.");
    }
    anotherHouse.paintHouse("green"); // calls the paintHouse method on the anotherHouse object
    anotherHouse.displayInfo(); // calls the displayInfo method on the anotherHouse object

    House.showWelcomeMessage(); // calls the static method showWelcomeMessage on the House class
    //
    // note: static methods belong to the class itself, not to any specific instance of the class
    // House.paintHouse("yellow"); // calls the static method paintHouse on the House class

    // abstract class cannot be instantiated
    // ModelHome model = new ModelHome(); 
    
    
  }
}

