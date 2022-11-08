package pers.darren.bytecode.asm.simple;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;

import static org.objectweb.asm.Opcodes.ASM9;

class FieldPrinter extends FieldVisitor {

    protected FieldPrinter() {
        super(ASM9);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        System.out.println("        FieldVisitor.visitAnnotation: " + descriptor + " " + visible);
        return new AnnotationPrinter("            ");
    }

    @Override
    public void visitEnd() {
        System.out.println("        FieldVisitor.visitEnd: 字段访问结束");
    }
}
