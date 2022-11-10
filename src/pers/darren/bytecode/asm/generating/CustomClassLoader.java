package pers.darren.bytecode.asm.generating;

public class CustomClassLoader extends ClassLoader {

    public Class<?> defineClass(String name, byte[] bytes) {
        return super.defineClass(name, bytes, 0, bytes.length);
    }
}
