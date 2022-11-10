package pers.darren.bytecode.asm.generating;

import org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;

import static org.objectweb.asm.Opcodes.*;

public class GenerateInterface {

    public static void main(String[] args) throws Exception {
        ClassWriter writer = new ClassWriter(0);
        // Java版本为18，公共抽象接口，接口名为Comparable，包名为pers.darren.bytecode.asm.generating，父类名为java.lang.Object，继承的接口为pers.darren.bytecode.asm.generating.Measurable
        writer.visit(V18, ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE, "pers/darren/bytecode/asm/generating/Comparable", null, "java/lang/Object", new String[]{"pers/darren/bytecode/asm/generating/Measurable"});
        // 公共静态常量，常量名为LESS，类型为基本类型int，常量值为-1
        writer.visitField(ACC_PUBLIC | ACC_FINAL | ACC_STATIC, "LESS", "I", null, -1).visitEnd();
        // 公共静态常量，常量名为EQUAL，类型为基本类型int，常量值为0
        writer.visitField(ACC_PUBLIC | ACC_FINAL | ACC_STATIC, "EQUAL", "I", null, 0).visitEnd();
        // 公共静态常量，常量名为GREATER，类型为基本类型int，常量值为1
        writer.visitField(ACC_PUBLIC | ACC_FINAL | ACC_STATIC, "GREATER", "I", null, 1).visitEnd();
        // 公共抽象方法，方法名compareTo，方法参数类型为java.lang.Object，返回类型为基本类型int
        writer.visitMethod(ACC_PUBLIC | ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
        writer.visitEnd();
        // 将字节码输出为class文件
        byte[] bytes = writer.toByteArray();
        FileOutputStream fos = new FileOutputStream("/home/darren/Workspaces/IDEA/case/target/classes/pers/darren/bytecode/asm/generating/Comparable.class");
        fos.write(bytes);
        fos.close();
        // 加载生成的Comparable接口
        CustomClassLoader loader = new CustomClassLoader();
        Class<?> clazz = loader.defineClass("pers.darren.bytecode.asm.generating.Comparable", bytes);
        System.out.println(clazz.toGenericString());
    }
}
