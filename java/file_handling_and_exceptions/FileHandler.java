// add package name here if needed
//
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;

class FileHandler {

  public File createFile(String fileName) throws FileHandlerException {
    File file = new File(fileName);
    try {
      if (file.createNewFile()) {
        System.out.println("File created: " + file.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      throw new FileHandlerException("An error occurred: " + e.getMessage());
    }
    return file;
  }

  public void writeToFile(File file, String content) throws FileHandlerException {
    FileWriter writer = null;
    try {
      writer = new FileWriter(file);
      writer.write(content);
      writer.flush();
    } catch (IOException e) {
      throw new FileHandlerException("An error occurred: " + e.getMessage());
    } finally {
      try {
        if (writer != null) {
          writer.close();
        }
      } catch (IOException e) {
        throw new FileHandlerException("Failed to close the writer: " + e.getMessage());
      }
    }
  }

  public void writeToFile(File file, String content, boolean append) throws FileHandlerException {
    FileWriter writer = null;
    try {
      writer = new FileWriter(file, append);
      writer.write(content);
      writer.flush();
    } catch (IOException e) {
      throw new FileHandlerException("An error occurred: " + e.getMessage());
    } finally {
      try {
        if (writer != null) {
          writer.close();
        }
      } catch (IOException e) {
        throw new FileHandlerException("Failed to close the writer: " + e.getMessage());
      }
    }
  }

  public String readFromFile(File file) throws FileHandlerException {
    StringBuilder content = new StringBuilder();
    try (FileReader reader = new FileReader(file)) {
      int ch;
      while ((ch = reader.read()) != -1) {
        content.append((char) ch);
      }
    } catch (IOException e) {
      throw new FileHandlerException("Failed to read the file: " + e.getMessage());
    }
    return content.toString();
  }

  public void deleteFile(File file) throws FileHandlerException {
    if (file.delete()) {
      System.out.println("Deleted the file: " + file.getName());
      return;
    } 

    throw new FileHandlerException("Failed to delete the file: " + file.getName());
  }

}

