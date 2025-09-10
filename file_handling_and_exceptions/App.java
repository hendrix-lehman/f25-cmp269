// add package name here if needed
//
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

class App {

  public static void main(String[] args) {

    // To write to the file, we can use FileWriter, BufferedWriter, Scanner, etc.
    File file = new File("example.txt");
    FileWriter writer = null;
    try {
      System.out.println("File exists: " + file.exists());
      file.createNewFile(); // Create the file if it doesn't exist
      System.out.println("File path: " + file.getAbsolutePath());
      System.out.println("Is executable: " + file.canExecute());
      System.out.println("Is writable: " + file.canWrite());
      System.out.println("Is readable: " + file.canRead());
      writer = new FileWriter(file);
      writer.write("Hello, World!");
      writer.flush();


      // read file content
      FileReader reader = new FileReader(file);

    } catch (IOException e) {
      System.out.println("An error occurred: " + e.getMessage());
    } finally {
      try {
        if (writer != null) {
          writer.close();
        }
      } catch (IOException e) {
        System.out.println("Failed to close the writer: " + e.getMessage());
      }
    }

    
  }
}

