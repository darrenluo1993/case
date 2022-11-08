package pers.darren.bytecode.asm.simple;

import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.ASM9;

/**
 * <pre>
 * <b>The methods of the ClassVisitor class must be called in the following order,
 * specified in the Javadoc of this class:</b>
 * visit
 * visitSource? visitOuterClass?
 * (visitAnnotation | visitAttribute)*
 * (visitInnerClass | visitField | visitMethod)*
 * visitEnd
 *
 * <b>This means that visit must be called first,
 * followed by at most one call to visitSource,
 * followed by at most one call to visitOuterClass,
 * followed by any number of calls in any order to visitAnnotation and visitAttribute,
 * followed by any number of calls in any order to visitInnerClass, visitField and visitMethod,
 * and terminated by a single call to visitEnd.</b>
 * </pre>
 *
 * @CreatedBy Darren Luo
 * @CreatedTime 11/3/22 10:49 AM
 */
class ClassPrinter extends ClassVisitor {

    protected ClassPrinter() {
        super(ASM9);
    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    public void visitSource(String source, String debug) {
    }

    public void visitOuterClass(String owner, String name, String desc) {
    }

    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        System.out.println("    ClassVisitor.visitAnnotation: " + desc + " " + visible);
        return new AnnotationPrinter("        ");
    }

    public void visitAttribute(Attribute attr) {
    }

    public void visitInnerClass(String name, String outerName, String innerName, int access) {
    }

    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println("    ClassVisitor.visitField: " + desc + " " + name);
        return new FieldPrinter();
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("    ClassVisitor.visitMethod: " + name + desc);
        return new MethodPrinter();
    }

    public void visitEnd() {
        System.out.println("}");
    }
}
