package pers.darren.designpattern.singleton;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

import java.util.concurrent.CountDownLatch;

/**
 * 单例模式实现方式-枚举
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Nov 30, 2020 3:47:25 PM
 */
public enum SingletonEnum {

    INSTANCE;

    private SingletonEnum() {
        try {
            // 模拟对象实例化过程需要1秒钟
            Thread.sleep(1000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        final int threadNum = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread((Runnable) () -> {
                try {
                    countDownLatch.await();
                    final long timestamp = currentTimeMillis();
                    final int hashCode = INSTANCE.hashCode();
                    final String threadName = currentThread().getName();
                    System.out.println(threadName + "启动时间>>>" + timestamp);
                    System.out.println(threadName + "获取到的单例Hash值>>>" + hashCode);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread" + i).start();
            countDownLatch.countDown();
        }
    }
}