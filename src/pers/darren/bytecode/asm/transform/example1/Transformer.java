package pers.darren.bytecode.asm.transform.example1;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;

import static org.objectweb.asm.ClassReader.SKIP_DEBUG;

public class Transformer {

    public static void main(String[] args) throws Exception {
        ClassWriter classWriter = new ClassWriter(0);
        AddTimerClassVisitorAdapter classVisitor = new AddTimerClassVisitorAdapter(classWriter);

        ClassReader classReader = new ClassReader("pers.darren.bytecode.asm.transform.example1.C");
        classReader.accept(classVisitor, SKIP_DEBUG);

        byte[] data = classWriter.toByteArray();
        FileOutputStream fos = new FileOutputStream("target/classes/pers/darren/bytecode/asm/transform/example1/C.class");
        fos.write(data);
        fos.close();
        System.out.println("transform success!");
    }
}
