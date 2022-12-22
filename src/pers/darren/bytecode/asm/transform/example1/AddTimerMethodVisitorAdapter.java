package pers.darren.bytecode.asm.transform.example1;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.PrintStream;

import static org.objectweb.asm.Type.*;

public class AddTimerMethodVisitorAdapter extends MethodVisitor implements Opcodes {

    private String owner;

    protected AddTimerMethodVisitorAdapter(String owner, MethodVisitor methodVisitor) {
        super(ASM9, methodVisitor);
        this.owner = owner;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        super.visitFieldInsn(GETSTATIC, this.owner, "timer", getDescriptor(long.class));
        super.visitMethodInsn(INVOKESTATIC, getInternalName(System.class), "currentTimeMillis", getMethodDescriptor(LONG_TYPE), false);
        super.visitInsn(LSUB);
        super.visitFieldInsn(PUTSTATIC, this.owner, "timer", getDescriptor(long.class));
    }

    @Override
    public void visitInsn(int opcode) {
        if (opcode >= IRETURN && opcode <= RETURN || opcode == ATHROW) {
            super.visitFieldInsn(GETSTATIC, this.owner, "timer", getDescriptor(long.class));
            super.visitMethodInsn(INVOKESTATIC, getInternalName(System.class), "currentTimeMillis", getMethodDescriptor(LONG_TYPE), false);
            super.visitInsn(LADD);
            super.visitFieldInsn(PUTSTATIC, this.owner, "timer", getDescriptor(long.class));
            super.visitFieldInsn(GETSTATIC, getInternalName(System.class), "out", getDescriptor(PrintStream.class));
            super.visitLdcInsn("方法执行耗时：%sms%n");
            super.visitInsn(ICONST_1);
            super.visitTypeInsn(ANEWARRAY, getInternalName(Object.class));
            super.visitInsn(DUP);
            super.visitInsn(ICONST_0);
            super.visitFieldInsn(GETSTATIC, this.owner, "timer", getDescriptor(long.class));
            super.visitMethodInsn(INVOKESTATIC, getInternalName(Long.class), "valueOf", getMethodDescriptor(getType(Long.class), LONG_TYPE), false);
            super.visitInsn(AASTORE);
            super.visitMethodInsn(INVOKEVIRTUAL, getInternalName(PrintStream.class), "printf", getMethodDescriptor(getType(PrintStream.class), getType(String.class), getType(Object[].class)), false);
            super.visitInsn(POP);
        }
        super.visitInsn(opcode);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack + 5, maxLocals);
    }
}
