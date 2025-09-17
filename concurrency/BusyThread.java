// add package name here if needed
//
class BusyThread extends Thread {

  @Override
  public void run() {
    for (int i = 0; i < 1_000; i++) {
      // randomly sleep for 1 to 10 milliseconds
      try {
        Thread.sleep((int)(Math.random() * 10) + 1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      // Busy work
      double x = Math.sqrt(i);
      // System.out.println(Thread.currentThread().getName() + " : " + i + " sqrt: " + x);
      printResult(i, x);
    }
  
  }

  private synchronized void printResult(int i, double x) {
      System.out.println(Thread.currentThread().getName() + " : " + i + " sqrt: " + x);
    // System.out.println(Thread.currentThread().getName() + " : " + i + " sqrt: " + x);
    //
  }
}

