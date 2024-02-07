package callablaAndFuture;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class Factorial implements Callable<Long> {

  int n;

  public Factorial(int n) {
    this.n = n;
  }

  @Override
  public Long call() throws Exception {
    if (n == 0) {
      return (long) 1;
    }
    long result = 1;
    for (int i = 1; i <= n; i++) {
      result *= i;
    }
    return result;
  }

}

public class Calculator {

  public static void main(String[] args) throws InterruptedException {

    Scanner scan = new Scanner(System.in);

    System.out.println("Enter a positive integer: ");
    int n = scan.nextInt();

    if (n <= 0) {
      System.err.println("Please enter a positive integer.");
    } else {
      ExecutorService executor = Executors.newSingleThreadExecutor();
      Callable<Long> factorialTask = new Factorial(n);
      Future<Long> future = executor.submit(factorialTask);

      System.out.println("Calculating the factorial of " + n + " in progress...");

      try {
        long result = future.get();
        System.out.println("Factorial of " + n + " is: " + result);
      } catch (Exception e) {
        System.out.println("An error occurred while calculating the factorial.");
      }

      executor.shutdown();
      executor.awaitTermination(4, TimeUnit.MINUTES);
    }
    scan.close();
  }

}
