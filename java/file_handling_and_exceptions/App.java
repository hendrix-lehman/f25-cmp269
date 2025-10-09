// add package name here if needed
//
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;

class App {

  public static void main(String[] args) {

    FileHandler fileHandler = new FileHandler();

    try {
      File file = fileHandler.createFile("example.txt");

      System.out.println("File path: " + file.getAbsolutePath());
      System.out.println("Is executable: " + file.canExecute());
      System.out.println("Is writable: " + file.canWrite());
      System.out.println("Is readable: " + file.canRead());

      fileHandler.writeToFile(file, "Hello, World!");
      fileHandler.writeToFile(file, "\nAppended line.", true);

      String fileContent = fileHandler.readFromFile(file);
      System.out.println("File content:\n" + fileContent);
      
      fileHandler.deleteFile(file);

    } catch (FileHandlerException e) {
      System.out.println("Error: " + e.getMessage());
    }

  }
}

