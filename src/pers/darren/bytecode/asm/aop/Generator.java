package pers.darren.bytecode.asm.aop;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;

import static org.objectweb.asm.ClassReader.SKIP_DEBUG;
import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;

class Generator {

    public static void main(String[] args) throws Exception {
        // 创建字节码写入器
        ClassWriter classWriter = new ClassWriter(COMPUTE_MAXS);
        // 创建类访问器
        ClassVisitor classVisitor = new CustomClassVisitor(classWriter);

        // 创建字节码读取器，并读取已存在的字节码
        ClassReader classReader = new ClassReader("pers/darren/bytecode/asm/aop/Base");
        // 读取字节码并修改
        classReader.accept(classVisitor, SKIP_DEBUG);

        byte[] data = classWriter.toByteArray();
        // 将修改后的字节码输出成文件
        FileOutputStream fos = new FileOutputStream("target/classes/pers/darren/bytecode/asm/aop/Base.class");
        fos.write(data);
        fos.close();
        System.out.println("now generator cc success!!!!!");
    }
}
