package pers.darren.bytecode.classreload;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

import static pers.darren.bytecode.classreload.Base.BASE_CLASS;

public class CustomAgent {

    public static void agentmain(String args, Instrumentation instrumentation) {
        // 指定我们自己定义的Transformer，在其中利用Javassist做字节码替换
        instrumentation.addTransformer(new CustomTransformer(), true);
        // 获取所有已加载的类，若存在Base.class，则对其进行字节码增强
        Class<?>[] allLoadedClasses = instrumentation.getAllLoadedClasses();
        for (Class<?> loadedClass : allLoadedClasses) {
            if (loadedClass == BASE_CLASS) {
                try {
                    // 重定义类并载入新的字节码
                    instrumentation.retransformClasses(loadedClass);
                    System.out.println("Agent load done.");
                    break;
                } catch (UnmodifiableClassException e) {
                    e.printStackTrace();
                    System.out.println("Agent load failed!");
                }
            }
        }
    }
}
