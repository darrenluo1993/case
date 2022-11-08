package pers.darren.bytecode.asm.parsing;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM9;

class MethodPrinter extends MethodVisitor {

    protected MethodPrinter() {
        super(ASM9);
    }

    @Override
    public void visitParameter(String name, int access) {
        System.out.println("        MethodVisitor.visitParameter: " + name + " " + access);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        System.out.println("        MethodVisitor.visitAnnotation: " + descriptor + " " + visible);
        return new AnnotationPrinter("            ");
    }

    @Override
    public void visitEnd() {
        System.out.println("        MethodVisitor.visitEnd: 方法访问结束");
    }
}
