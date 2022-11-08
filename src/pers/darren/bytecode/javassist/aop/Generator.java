package pers.darren.bytecode.javassist.aop;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

import static javassist.CtField.Initializer.constant;
import static javassist.Modifier.*;

class Generator {

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.getCtClass("pers.darren.bytecode.javassist.aop.Base");
        CtField name = new CtField(pool.getCtClass("java.lang.String"), "name", ctClass);
        name.setModifiers(PUBLIC + STATIC + FINAL);
        ctClass.addField(name, constant("Darren Luo"));
        CtField gender = new CtField(pool.getCtClass("java.lang.String"), "gender", ctClass);
        gender.setModifiers(PUBLIC + STATIC + FINAL);
        ctClass.addField(gender, constant("Male"));
        // CtField age = new CtField(pool.getCtClass("java.lang.Integer"), "age", ctClass);
        // age.setModifiers(PUBLIC);
        // age.setModifiers(STATIC);
        // ctClass.addField(age, constant(18));
        CtMethod ctMethod = ctClass.getDeclaredMethod("process");
        ctMethod.addParameter(pool.getCtClass("java.lang.String"));
        ctMethod.addParameter(pool.getCtClass("java.lang.Long"));
        ctMethod.insertParameter(pool.getCtClass("java.lang.Integer"));
        ctMethod.insertBefore("System.out.println(\"start\");");
        ctMethod.insertAfter("System.out.println($1);");
        ctMethod.insertAfter("System.out.println($2);");
        ctMethod.insertAfter("System.out.println($3);");
        ctMethod.insertAfter("System.out.println(name);");
        ctMethod.insertAfter("System.out.println(gender);");
        // ctMethod.insertAfter("System.out.println(age);");
        ctMethod.insertAfter("System.out.println(\"end\");");
        ctClass.writeFile("target/classes/");
    }
}
