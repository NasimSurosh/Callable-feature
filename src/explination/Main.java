package explination;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class Task implements Callable<String>{
  
  private String name;
  
  public Task(String name) {
    this.name = name;
  }

  @Override
  public String call() throws Exception {
    System.out.println(name +" executed on: " + LocalDateTime.now().toString());
    return name;
  }
  
}
public class Main {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    
    ExecutorService es = Executors.newFixedThreadPool(3);
    
    Future<String> future = es.submit(new Task("task1"));
    Future<String> future1 = es.submit(new Task("task2"));
    Future<String> future2 = es.submit(new Task("task3"));
    
    String thread1 = future.get();
    String thread2 = future1.get();
    String thread3 = future2.get();
    
    System.out.println("Thread completed: " + thread1);
    System.out.println("Thread completed: " + thread2);
    System.out.println("Thread completed: " + thread3);
    
    es.shutdown();
    es.awaitTermination(4, TimeUnit.MINUTES);

  }

}
