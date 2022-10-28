package pers.darren.bytecode.classreload;

import java.lang.management.ManagementFactory;

public class Base {

    public static final Class<Base> BASE_CLASS = Base.class;
    public static final String BASE_CLASS_NAME = BASE_CLASS.getName();

    public static void main(String[] args) {
        // String name = ManagementFactory.getRuntimeMXBean().getName();
        // String pid = name.split("@")[0];
        long pid = ManagementFactory.getRuntimeMXBean().getPid();
        // 打印当前pid
        System.out.println("pid:" + pid);
        while (true) {
            try {
                Thread.sleep(5000L);
            } catch (Exception e) {
                break;
            }
            process();
        }
    }

    public static void process() {
        System.out.println("process");
    }
}
