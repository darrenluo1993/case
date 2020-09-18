package pers.darren.string.append;

/**
 * 字符串拼接测试
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 18, 2020 11:33:09 AM
 */
public final class StringAppendTest {

    public static final int CYCLE_NUM_BIGGER = 1000_0000;
    public static final int CYCLE_NUM_LOWER = 1_0000;
    public static final String str1 = "张三";
    public static final String str2 = "李四";
    public static final String str3 = "王五";
    public static final String str4 = "赵六";

    /**
     * 循环内 String 拼接字符串，一次循环后销毁
     */
    @SuppressWarnings("unused")
    public static void useString() {
        for (int i = 0; i < CYCLE_NUM_BIGGER; i++) {
            final String str = str1 + i + str2 + i + str3 + i + str4;
        }
    }

    /**
     * 循环内 使用 StringBuilder 拼接字符串，一次循环后销毁
     */
    @SuppressWarnings("unused")
    public static void useStringBuilder() {
        for (int i = 0; i < CYCLE_NUM_BIGGER; i++) {
            final StringBuilder sb = new StringBuilder();
            final String str = sb.append(str1).append(i).append(str2).append(i).append(str3).append(i).append(str4).toString();
        }
    }

    /**
     * 多次循环拼接成一个字符串 用 String
     */
    @SuppressWarnings("unused")
    public static void useStringSpliceOneStr() {
        String str = "";
        for (int i = 0; i < CYCLE_NUM_LOWER; i++) {
            str += str1 + str2 + str3 + str4 + i;
        }
    }

    /**
     * 多次循环拼接成一个字符串 用 StringBuilder
     */
    public static void useStringBuilderSpliceOneStr() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CYCLE_NUM_LOWER; i++) {
            sb.append(str1).append(str2).append(str3).append(str4).append(i);
        }
    }

    public static int executeSometime(final int kind, final int num) throws InterruptedException {
        Thread.sleep(2000);
        int sum = 0;
        for (int i = 0; i < 5 + num; i++) {
            final long begin = System.currentTimeMillis();

            switch (kind) {
                case 1:
                    useString();
                    break;
                case 2:
                    useStringBuilder();
                    break;
                case 3:
                    useStringSpliceOneStr();
                    break;
                case 4:
                    useStringBuilderSpliceOneStr();
                    break;
                default:
                    return 0;
            }

            final long end = System.currentTimeMillis();

            if (i >= 5) {
                sum += end - begin;
            }
        }
        return sum / num;
    }

    public static void main(final String[] args) throws InterruptedException {
        int time = 0;
        final int num = 5;

        time = executeSometime(1, num);
        System.out.println("String拼接 " + CYCLE_NUM_BIGGER + " 次，" + num + "次平均时间：" + time + " ms");

        time = executeSometime(2, num);
        System.out.println("StringBuilder拼接 " + CYCLE_NUM_BIGGER + " 次，" + num + "次平均时间：" + time + " ms");

        time = executeSometime(3, num);
        System.out.println("String拼接单个字符串 " + CYCLE_NUM_LOWER + " 次，" + num + "次平均时间：" + time + " ms");

        time = executeSometime(4, num);
        System.out.println("StringBuilder拼接单个字符串 " + CYCLE_NUM_LOWER + " 次，" + num + "次平均时间：" + time + " ms");
    }
}