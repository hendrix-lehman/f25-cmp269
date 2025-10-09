// add package name here if needed
//
class App {

  public static void main(String[] args) throws InterruptedException {
    TaskOne taskOne = new TaskOne();
    taskOne.start();

    TaskTwo taskTwo = new TaskTwo();
    Thread taskTwoThread = new Thread(taskTwo);
    taskTwoThread.start();


    for (int i = 1; i <= 10; i++) {
      System.out.println("Main Thread - Count: " + i);
      try {
        Thread.sleep(300); // Sleep for 300 milliseconds
      } catch (InterruptedException e) {
        System.out.println("Main thread interrupted");
        Thread.currentThread().interrupt(); // Restore the interrupted status
      }
    }


    // synchronization demonstration
    Counter unsynchronizedCounter = new Counter();
    Runnable unsyncTask = () -> {
      for (int i = 0; i < 1000; i++) {
        unsynchronizedCounter.increment();
      }
    };

    Thread t1 = new Thread(unsyncTask);
    Thread t2 = new Thread(unsyncTask);

    t1.start();
    t2.start();

    // wait for both threads to finish
    t1.join();
    t2.join();

    System.out.println("Final count (unsynchronized): " + unsynchronizedCounter.getCount());

    // synchronized counter demonstration
    Counter synchronizedCounter = new Counter();
    Runnable syncTask = () -> {
      for (int i = 0; i < 1000; i++) {
        synchronizedCounter.synchronizedIncrement();
      }
    };

    Thread t3 = new Thread(syncTask);
    Thread t4 = new Thread(syncTask);

    t3.start();
    t4.start();

    // wait for both threads to finish
    t3.join();
    t4.join();

    System.out.println("Final count (synchronized): " + synchronizedCounter.getCount());
    
  }
}

