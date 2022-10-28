package pers.darren.bytecode.javassist.aop;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

class Generator {

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.getCtClass("pers.darren.bytecode.javassist.aop.Base");
        CtMethod ctMethod = ctClass.getDeclaredMethod("process");
        ctMethod.addParameter(pool.getCtClass("java.lang.String"));
        ctMethod.insertParameter(pool.getCtClass("java.lang.Integer"));
        ctMethod.addParameter(pool.getCtClass("java.lang.Long"));
        ctMethod.insertBefore("System.out.println(\"start\");");
        ctMethod.insertAfter("System.out.println($1);");
        ctMethod.insertAfter("System.out.println($2);");
        ctMethod.insertAfter("System.out.println($3);");
        ctMethod.insertAfter("System.out.println(\"end\");");
        ctClass.writeFile("target/classes/");
    }
}
