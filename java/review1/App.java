// add package name here if needed
//
class App {

  public static void main(String[] args) {


    // Car Factory Simulation

    // car has doors, engine, windows, seats, wheels, windshield, rearview mirror, side mirrors, lights, trunk, hood
    //
    // This factory produces two different types of cars: Sedan and SUV
    //
    // Each car has different specifications for its components
    // Sedan: 4 doors, V4 engine, 4 windows, 5 seats, 4 wheels, 1 windshield, 1 rearview mirror, 2 side mirrors, 4 lights, small trunk, small hood
    //
    // SUV: 4 doors, V6 engine, 6 windows, 7 seats, 4 wheels, 1 windshield, 1 rearview mirror, 2 side mirrors, 6 lights, large trunk, large hood
    

    AutomobileFactory fordFactory = AutomobileFactory.getInstance();
    Automobile mySedan = fordFactory.assemble("Sedan", "Fusion", 2020);

    mySedan.honkHorn();

    System.out.println("My Sedan: " + mySedan);

    mySedan.startEngine();
    mySedan.stopEngine();
  }
}

