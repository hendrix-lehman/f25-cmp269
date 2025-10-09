// add package name here if needed
//
class TaskTwo implements Runnable {

  @Override
  public void run() {
    for (int i = 1; i <= 10; i++) {
      System.out.println("TaskTwo - Count: " + i);
      try {
        Thread.sleep(400); // Sleep for 400 milliseconds
      } catch (InterruptedException e) {
        System.out.println("TaskTwo interrupted");
        Thread.currentThread().interrupt(); // Restore the interrupted status
      }
    }
  }
}

