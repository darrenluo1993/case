package pers.darren.bytecode.asm.aop;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class CustomMethodVisitor extends MethodVisitor implements Opcodes {

    public CustomMethodVisitor(MethodVisitor mv) {
        super(ASM9, mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        // 在方法体最前面插入打印"start"的字节码
        super.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        super.visitLdcInsn("start");
        super.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    @Override
    public void visitInsn(int opcode) {
        if (opcode >= IRETURN && opcode <= RETURN || opcode == ATHROW) {
            // 在方法体最后面插入打印"end"的字节码
            super.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            super.visitLdcInsn("end");
            super.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
        super.visitInsn(opcode);
    }
}
