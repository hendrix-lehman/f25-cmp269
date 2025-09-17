// add package name here if needed
//
class App {

  public static void main(String[] args) {

    // Multithreading is the concurrent execution of two or more threads.
    // It allows a program to perform multiple tasks simultaneously, 
    // improving performance and responsiveness.

    // Life Cycle of a Thread:
    // 1. New: A thread is created but not yet started.
    // 2. Runnable: A thread is ready to run and waiting for CPU time.
    // 3. Running: A thread is currently executing.
    // 4. Blocked/Waiting: A thread is waiting for a resource or event.
    // 5. Terminated: A thread has completed its execution.
    //
    // Thread thread = new Thread(new Greeter());
    // thread.start();

    Thread busyThread = new BusyThread();
    Thread busyThread2 = new BusyThread();

    busyThread.start();
    busyThread2.start();
    
  }
}

