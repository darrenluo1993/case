package pers.darren.bytecode.javassist.aop;

class Test {

    public static void main(String[] args) {
        // 修改字节码前执行以下语句查看效果
        new Base().process();
        // 修改字节码后执行以下语句查看效果，编译会报错，不用管它
        // new Base().process(123, "456", 789L);
    }
}
