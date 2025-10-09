// add package name here if needed
//
class TaskOne extends Thread {

  @Override
  public void run() {
    for (int i = 1; i <= 10; i++) {
      System.out.println("TaskOne - Count: " + i);
      try {
        Thread.sleep(500); // Sleep for 500 milliseconds
      } catch (InterruptedException e) {
        System.out.println("TaskOne interrupted");
        Thread.currentThread().interrupt(); // Restore the interrupted status
      }
    }
  }

}

