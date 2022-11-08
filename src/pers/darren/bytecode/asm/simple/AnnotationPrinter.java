package pers.darren.bytecode.asm.simple;

import org.objectweb.asm.AnnotationVisitor;

import static org.objectweb.asm.Opcodes.ASM9;

class AnnotationPrinter extends AnnotationVisitor {

    private final String spaces;

    protected AnnotationPrinter(String spaces) {
        super(ASM9);
        this.spaces = spaces;
    }

    @Override
    public void visit(String name, Object value) {
        System.out.println(this.spaces + "AnnotationVisitor.visit: " + name + "=" + value);
    }

    @Override
    public void visitEnum(String name, String descriptor, String value) {
        System.out.println(this.spaces + "AnnotationVisitor.visitEnum: " + name + "=" + descriptor.replace(";", "") + "." + value);
    }

    @Override
    public AnnotationVisitor visitArray(String name) {
        System.out.println(this.spaces + "AnnotationVisitor.visitArray: " + name);
        return null;
    }

    @Override
    public void visitEnd() {
        System.out.println(this.spaces + "AnnotationVisitor.visitEnd: 注解访问结束");
    }
}
