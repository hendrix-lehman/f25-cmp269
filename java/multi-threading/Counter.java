// add package name here if needed
//
class Counter {
  private int count = 0;

  // unsynchronized increment method
  public void increment() {
    count++;
  }

  // synchronized increment method
  public synchronized void synchronizedIncrement() {
    count++;
  }

  public int getCount() {
    return count;
  }
}

