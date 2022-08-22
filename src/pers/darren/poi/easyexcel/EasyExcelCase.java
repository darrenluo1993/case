package pers.darren.poi.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EasyExcelCase {

    private static final List<User> USER_LIST = Lists.newArrayList();
    private static final String FILE_PATH = "/home/darren/Temporary/easyexcel/用户列表.xlsx";

    static {
        USER_LIST.add(new User("username1", "Full Name 1", "Female", 18, "0731-11111111", new BigDecimal(20000), "address 1", new Date()));
        USER_LIST.add(new User("username2", "Full Name 2", "Male", 19, "0731-11111112", new BigDecimal(20001), "address 2", new Date()));
        USER_LIST.add(new User("username3", "Full Name 3", "Female", 20, "0731-11111113", new BigDecimal(20002), "address 3", new Date()));
        USER_LIST.add(new User("username4", "Full Name 4", "Female", 21, "0731-11111114", new BigDecimal(20003), "address 4", new Date()));
        USER_LIST.add(new User("username5", "Full Name 5", "Male", 22, "0731-11111115", new BigDecimal(20004), "address 5", new Date()));
        USER_LIST.add(new User("username6", "Full Name 6", "Male", 23, "0731-11111116", new BigDecimal(20005), "address 6", new Date()));
        USER_LIST.add(new User("username7", "Full Name 7", "Female", 24, "0731-11111117", new BigDecimal(20006), "address 7", new Date()));
        USER_LIST.add(new User("username8", "Full Name 8", "Male", 25, "0731-11111118", new BigDecimal(20007), "address 8", new Date()));
        USER_LIST.add(new User("username9", "Full Name 9", "Male", 26, "0731-11111119", new BigDecimal(20008), "address 9", new Date()));
        USER_LIST.add(new User("username10", "Full Name 10", "Female", 27, "0731-11111120", new BigDecimal(20009), "address 10", new Date()));
        USER_LIST.add(new User("username11", "Full Name 11", "Male", 28, "0731-11111121", new BigDecimal(20010), "address 11", new Date()));
        USER_LIST.add(new User("username12", "Full Name 12", "Female", 29, "0731-11111122", new BigDecimal(20011), "address 12", new Date()));
        USER_LIST.add(new User("username13", "Full Name 13", "Male", 30, "0731-11111123", new BigDecimal(20012), "address 13", new Date()));
        USER_LIST.add(new User("username14", "Full Name 14", "Female", 31, "0731-11111124", new BigDecimal(20013), "address 14", new Date()));
        USER_LIST.add(new User("username15", "Full Name 15", "Male", 32, "0731-11111125", new BigDecimal(20014), "address 15", new Date()));
        USER_LIST.add(new User("username16", "Full Name 16", "Male", 33, "0731-11111126", new BigDecimal(20015), "address 16", new Date()));
        USER_LIST.add(new User("username17", "Full Name 17", "Female", 34, "0731-11111127", new BigDecimal(20016), "address 17", new Date()));
        USER_LIST.add(new User("username18", "Full Name 18", "Female", 35, "0731-11111128", new BigDecimal(20017), "address 18", new Date()));
        USER_LIST.add(new User("username19", "Full Name 19", "Male", 36, "0731-11111129", new BigDecimal(20018), "address 19", new Date()));
        USER_LIST.add(new User("username20", "Full Name 20", "Female", 37, "0731-11111130", new BigDecimal(20019), "address 20", new Date()));
    }

    public static void main(String[] args) {
        simpleWriteExcel();
        simpleReadExcel();
    }

    private static void simpleWriteExcel() {
        EasyExcel.write(FILE_PATH, User.class).sheet("用户列表").doWrite(USER_LIST);
    }

    private static void simpleReadExcel() {
        // 自定义ReadListener
        EasyExcel.read(FILE_PATH, User.class, new ReadListener<User>() {
            // invoke方法每解析到一条数据就会执行一次
            @Override
            public void invoke(User user, AnalysisContext analysisContext) {
                System.out.println("解析到一条数据：" + user);
            }

            // 所有数据解析完成会执行一次
            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("所有数据解析完成！");
            }
        }).sheet().doRead();
        // 使用自定义的PageReadListener，不指定pageSize
        System.out.println("==================使用自定义的PageReadListener，不指定pageSize==================");
        EasyExcel.read(FILE_PATH, User.class, new PageReadListener<User>(pageList -> {
            System.out.println("分页列表大小：" + pageList.size());
            System.out.println("分页列表数据：" + pageList);
            System.out.println("分页列表数据逐条打印：");
            pageList.forEach(System.out::println);
        })).sheet().doRead();
        // 使用自定义的PageReadListener，指定pageSize为10
        System.out.println("=================使用自定义的PageReadListener，指定pageSize为10=================");
        EasyExcel.read(FILE_PATH, User.class, new PageReadListener<User>(10, pageList -> {
            System.out.println("分页列表大小：" + pageList.size());
            System.out.println("分页列表数据：" + pageList);
            System.out.println("分页列表数据逐条打印：");
            pageList.forEach(System.out::println);
        })).sheet().doRead();
        // pers.darren.poi.easyexcel.PageReadListener构造方法参数为null检查
        try {
            EasyExcel.read(FILE_PATH, User.class, new PageReadListener<User>(null, pageList -> {
            })).sheet().doRead();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        // pers.darren.poi.easyexcel.PageReadListener构造方法参数为null检查
        try {
            EasyExcel.read(FILE_PATH, User.class, new PageReadListener<User>(10, null)).sheet().doRead();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
