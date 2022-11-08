package pers.darren.bytecode.asm.simple;

import org.objectweb.asm.ClassReader;

class Test {

    public static void main(String[] args) throws Exception {
        ClassPrinter printer = new ClassPrinter();
        ClassReader reader = new ClassReader(Base.class.getName());
        reader.accept(printer, 0);
    }
}
