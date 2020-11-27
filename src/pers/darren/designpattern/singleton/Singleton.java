package pers.darren.designpattern.singleton;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

import java.util.concurrent.CountDownLatch;

/**
 * 单例模式实现方式
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Nov 25, 2020 11:28:07 AM
 */
public class Singleton {

    private Singleton() {
    }

    public void printInfo() {
        System.out.println("This is a Singleton!!!");
    }

    private static Singleton singleton1;
    private static volatile Singleton singleton2;
    private static final Singleton SINGLETON = new Singleton();

    /**
     * <pre>
     * 1、懒汉式，线程不安全
     * 是否 Lazy 初始化：是
     * 是否多线程安全：否
     * 实现难度：易
     * 描述：这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
     * 这种方式 lazy loading 很明显，不要求线程安全，在多线程不能正常工作。
     * </pre>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Nov 25, 2020 11:28:39 AM
     * @return
     */
    public static Singleton getInstance1() {
        if (singleton1 == null) {
            singleton1 = new Singleton();
        }
        return singleton1;
    }

    /**
     * <pre>
     * 2、懒汉式，线程安全
     * 是否 Lazy 初始化：是
     * 是否多线程安全：是
     * 实现难度：易
     * 描述：这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步。
     * 优点：第一次调用才初始化，避免内存浪费。
     * 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
     * getInstance() 的性能对应用程序不是很关键（该方法使用不太频繁）。
     * </pre>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Nov 25, 2020 11:31:32 AM
     * @return
     */
    public static synchronized Singleton getInstance2() {
        if (singleton1 == null) {
            singleton1 = new Singleton();
        }
        return singleton1;
    }

    /**
     * <pre>
     * 3、饿汉式
     * 是否 Lazy 初始化：否
     * 是否多线程安全：是
     * 实现难度：易
     * 描述：这种方式比较常用，但容易产生垃圾对象。
     * 优点：没有加锁，执行效率会提高。
     * 缺点：类加载时就初始化，浪费内存。
     * 它基于 classloader 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化，虽然导致类装载的原因有很多种，在单例模式中大多数都是调用 getInstance 方法， 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化 instance 显然没有达到 lazy loading 的效果。
     * </pre>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Nov 25, 2020 11:37:16 AM
     * @return
     */
    public static Singleton getInstance3() {
        return SINGLETON;
    }

    /**
     * <pre>
     * 4、双检锁/双重校验锁（DCL，即 double-checked locking）
     * JDK 版本：JDK1.5 起
     * 是否 Lazy 初始化：是
     * 是否多线程安全：是
     * 实现难度：较复杂
     * 描述：这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
     * getInstance() 的性能对应用程序很关键。
     * </pre>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Nov 25, 2020 12:37:04 PM
     * @return
     */
    public static final Singleton getInstance4() {
        if (singleton2 == null) {
            synchronized (Singleton.class) {
                if (singleton2 == null) {
                    singleton2 = new Singleton();
                }
            }
        }
        return singleton2;
    }

    private static final class SingletonHolder {
        private static final Singleton SINGLETON = new Singleton();
    }

    /**
     * <pre>
     * 5、登记式/静态内部类
     * 是否 Lazy 初始化：是
     * 是否多线程安全：是
     * 实现难度：一般
     * 描述：这种方式能达到双检锁方式一样的功效，但实现更简单。对静态域使用延迟初始化，应使用这种方式而不是双检锁方式。这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用。
     * 这种方式同样利用了 classloader 机制来保证初始化 instance 时只有一个线程，它跟第 3 种方式不同的是：第 3 种方式只要 Singleton 类被装载
     * 了，那么 instance 就会被实例化（没有达到 lazy loading 效果），而这种方式是 Singleton 类被装载了，instance 不一定被初始化。
     * 因为 SingletonHolder 类没有被主动使用，只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，从而实例化 instance。
     * 想象一下，如果实例化 instance 很消耗资源，所以想让它延迟加载，另外一方面，又不希望在 Singleton 类加载时就实例化，因为不能确保 Singleton 类还可能在其他的地方被主动使用从而被加载，
     * 那么这个时候实例化 instance 显然是不合适的。这个时候，这种方式相比第 3 种方式就显得很合理。
     * </pre>
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Nov 27, 2020 4:11:38 PM
     */
    public static final Singleton getInstance5() {
        return SingletonHolder.SINGLETON;
    }

    public static void main(final String[] args) throws Exception {
        final int threadNum = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread((Runnable) () -> {
                try {
                    countDownLatch.await();
                    final long timestamp = currentTimeMillis();
                    final int hashCode = getInstance1().hashCode();
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