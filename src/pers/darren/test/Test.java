package pers.darren.test;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.currentThread;

public class Test {

    public static void main(final String[] args) {
        // new BigDecimal(10).add(null);

        System.out.println(new BigDecimal(20.00).toPlainString());
        System.out.println(new BigDecimal("20.00").stripTrailingZeros().toPlainString());

        System.out.println(4 % 3); // 1
        System.out.println(4 / 3); // 1

        System.out.println("threadPoolThrowException>>>");
        threadPoolThrowException();
    }

    public static void threadPoolThrowException() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        try {
            CountDownLatch latch = new CountDownLatch(5);
            for (int i = 0; i < 5; i++) {
                final int fi = i;
                executor.execute(() -> {
                    try {
                        System.out.println(fi + ">>>" + currentThread().getName());
                        if (fi == 2) {
                            System.out.println(fi + ">>>" + "Execute Failure");
                            System.out.println(fi / 0);
                        }
                        System.out.println(fi + ">>>" + "Execute Success");
                        latch.countDown();
                    } catch (Exception e) {
                        System.out.println(fi + ">>>" + "Inner Exception>>>" + e.getMessage());
                        latch.countDown();
                        throw e;
                    }
                });
            }
            executor.shutdown();
            latch.await();
        } catch (Exception e) {
            System.out.println("Outer Exception>>>" + e.getMessage());
        } finally {
            System.out.println("Finally");
        }
    }
}