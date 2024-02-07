package atomaticInteger;

import java.util.concurrent.atomic.AtomicInteger;

class IncrementTask implements Runnable{
  
  private AtomicInteger counter;
  
  public IncrementTask(AtomicInteger counter) {
    this.counter = counter;
    
  }

  @Override
  public void run() {
   for(int i = 0; i < 10000; i++) {
     counter.incrementAndGet();
   }
    
  }
  
}
class DecrementTask implements Runnable{

  private AtomicInteger counter;
  
  public DecrementTask(AtomicInteger counter) {
    this.counter = counter;
    
  }
  public void run() {
     for(int i = 0; i < 10000; i++) {
       counter.decrementAndGet();
     }
  }
  
}

public class AtomaticIntegerExercise {

  public static void main(String[] args) {
    
  //create an AtomicInteger named counter and initialize it with a value of 0.
  // This will serve as our shared counter

    AtomicInteger aI = new AtomicInteger(0);
    
  // Create multiple threads to increment and decrement the counter
    Thread incrementThread = new Thread(new IncrementTask(aI));
    
    Thread decrementThread = new Thread(new DecrementTask(aI));
    
    incrementThread.start();
    decrementThread.start();
    
    try {
      incrementThread.join();
      decrementThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    // get the final value of the counter
    int finalValue = aI.get();
    System.out.println("Final counter value: " + finalValue);
    
  }

}
