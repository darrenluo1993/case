package pers.darren.bytecode.asm.aop;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class CustomClassVisitor extends ClassVisitor implements Opcodes {

    public CustomClassVisitor(ClassVisitor cv) {
        super(ASM9, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        // Base类中有两个方法：无参构造以及process方法，这里不增强构造方法
        if (!"<init>".equals(name) && mv != null) {
            mv = new CustomMethodVisitor(mv);
        }
        return mv;
    }
}
