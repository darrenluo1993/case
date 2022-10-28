package pers.darren.bytecode.classreload;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import static pers.darren.bytecode.classreload.Base.BASE_CLASS_NAME;

public class CustomTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replace("/", ".");
        System.out.println("Transforming " + className);
        // 若className为pers.darren.bytecode.classreload.Base，则进行字节码增强
        if (BASE_CLASS_NAME.equals(className)) {
            try {
                ClassPool pool = ClassPool.getDefault();
                pool.appendClassPath(new LoaderClassPath(loader));
                // pool.appendClassPath(new ClassClassPath(classBeingRedefined));
                CtClass ctClass = pool.getCtClass(className);
                CtMethod ctMethod = ctClass.getDeclaredMethod("process");
                ctMethod.insertBefore("System.out.println(\"start\");");
                ctMethod.insertAfter("System.out.println(\"end\");");
                return ctClass.toBytecode();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
