package samCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class SumCalculator implements Callable<Integer>{
  final int start;
  final int end;
  public SumCalculator(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public Integer call() throws Exception {
    int sum = 0;
    for ( int i = start; i <= end; i++) {
      sum += i;
      
    }
    return sum;
  }
  
}

public class SumMain {

  public static void main(String[] args) throws InterruptedException {
    
    ExecutorService executor = Executors.newFixedThreadPool(3);
    
    List<SumCalculator> calculator = new ArrayList<>();
    calculator.add(new SumCalculator(2, 100));
    calculator.add(new SumCalculator(101, 200));
    calculator.add(new SumCalculator(201, 300));
    
    List<Future<Integer>> futures = new ArrayList<>();
    for(SumCalculator calculators : calculator) {
      futures.add(executor.submit(calculators));
    }
    for ( int i = 0; i < calculator.size(); i++) {
      SumCalculator calculat = calculator.get(i);
        int result ;
      try {
        result = futures.get(i).get();
        System.out.println("Sum of integer is range [" + calculat.start + " , " + calculat.end + "]: " + result);
      }catch(Exception e) {
        e.printStackTrace();
      }
    }
    executor.shutdown();
    executor.awaitTermination(4, TimeUnit.HOURS);
    
    

  }

}
