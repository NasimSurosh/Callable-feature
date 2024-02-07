package countDownTimer;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownTimerExercise {

  public static void main(String[] args) {
    
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter the initial countdown value (second): ");
    int initialCountdown = scan.nextInt();
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    
    AtomicInteger countdown = new AtomicInteger(initialCountdown);
    
    Runnable countdownTask = new Runnable() {

      @Override
      public void run() {
        int remainingTime = countdown.decrementAndGet();
        if(remainingTime > 0) {
          System.out.println("Countdown: " + remainingTime + " second");
        }else {
          System.out.println("Time's up!");
          executor.shutdown();
        }
        
      }
      
    };
    executor.scheduleAtFixedRate(countdownTask, 0, 1, TimeUnit.SECONDS);
    scan.close();
  }

}
