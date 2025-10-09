// a class is best described as a blueprint for creating objects
class App {

  public void testComputer(Computer computer) {
    computer.assemble();
    computer.start();
    computer.shutdown();
  }

  // public, private, protected are access modifiers
  public static void main(String[] args) {

    // two types of data in Java
    // 1. primitive data types (int, char, float, double, boolean)
    // 2. reference data types (arrays, classes, interfaces)
    //
    // loops
    // for, while, do-while, for-each
    //
    // arrays
    // int[] numbers = new int[5];
    // numbers[0] = 1;
    // int[] numbers2 = {1, 2, 3, 4, 5};
    // System.out.println(numbers2[0]);
    
    // objects are instances of classes
    // the variable dell is a reference to an object of type Dell
    // in memory, the variable dell holds the address of the object
    // 
    // this is a good example of polymorphism
    Dell dell = new Dell("XPS 13");
    Microsoft microsoft = new Microsoft("Surface Pro");
    Microsoft.developerDeveloperDeveloper();
    System.out.println("Microsoft OS: " + Microsoft.OS);

    App app = new App();
    app.testComputer(dell);
    app.testComputer(microsoft);

  }
}

