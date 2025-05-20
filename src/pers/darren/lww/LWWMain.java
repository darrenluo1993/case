package pers.darren.lww;

import static java.lang.System.currentTimeMillis;

public class LWWMain {

    public static void main(String[] args) throws Exception {
        // 时间戳不同，副本ID不同
        System.out.println("时间戳不同，副本ID不同：");
        LWWRegister<String> lwwRegister1 = new LWWRegister<>("1", currentTimeMillis(), "G001");
        System.out.println("        lwwRegister1>>>" + lwwRegister1);
        lwwRegister1.update("2", currentTimeMillis(), "F001");
        System.out.println("updated lwwRegister1>>>" + lwwRegister1);
        Thread.sleep(5);
        LWWRegister<String> lwwRegister2 = new LWWRegister<>("3", currentTimeMillis(), "E001");
        lwwRegister1.merge(lwwRegister2);
        System.out.println(" merged lwwRegister1>>>" + lwwRegister1);

        // 时间戳相同，副本ID不同
        System.out.println("时间戳相同，副本ID不同：");
        System.out.println("        lwwRegister1>>>" + lwwRegister1);
        long timestamp = lwwRegister1.getTimestamp();
        lwwRegister1.update("4", timestamp, "D001");
        System.out.println("updated lwwRegister1>>>" + lwwRegister1);
        LWWRegister<String> lwwRegister3 = new LWWRegister<>("5", timestamp, "C001");
        lwwRegister1.merge(lwwRegister3);
        System.out.println(" merged lwwRegister1>>>" + lwwRegister1);
    }
}
