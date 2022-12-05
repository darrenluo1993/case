package pers.darren.bytecode.asm.generating;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Type.*;

public class GenerateClass {

    public static void main(String[] args) throws Exception {
        ClassWriter user = new ClassWriter(0);
        user.visit(V18, ACC_PUBLIC | ACC_FINAL | ACC_SUPER, "pers/darren/bytecode/asm/generating/User", null, "java/lang/Object", null);
        user.visitField(ACC_PRIVATE, "userName", getDescriptor(String.class), null, null).visitEnd();
        user.visitField(ACC_PRIVATE, "fullName", getDescriptor(String.class), null, null).visitEnd();
        user.visitField(ACC_PRIVATE, "gender", getDescriptor(String.class), null, null).visitEnd();
        user.visitField(ACC_PRIVATE, "age", getDescriptor(Integer.class), null, null).visitEnd();
        user.visitField(ACC_PRIVATE, "phone", getDescriptor(String.class), null, null).visitEnd();
        user.visitField(ACC_PRIVATE, "salary", getDescriptor(BigDecimal.class), null, null).visitEnd();
        user.visitField(ACC_PRIVATE, "address", getDescriptor(String.class), null, null).visitEnd();
        user.visitField(ACC_PRIVATE, "createdTime", getDescriptor(Date.class), null, null).visitEnd();
        {
            MethodVisitor init = user.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            init.visitCode();
            Label label0 = new Label();
            init.visitLabel(label0);
            init.visitVarInsn(ALOAD, 0);
            init.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", getMethodDescriptor(VOID_TYPE), false);
            init.visitInsn(RETURN);
            Label label1 = new Label();
            init.visitLabel(label1);
            init.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            init.visitMaxs(1, 1);
            init.visitEnd();
        }
        {
            MethodVisitor getUserName = user.visitMethod(ACC_PUBLIC, "getUserName", getMethodDescriptor(getType(String.class)), null, null);
            getUserName.visitCode();
            Label label0 = new Label();
            getUserName.visitLabel(label0);
            getUserName.visitVarInsn(ALOAD, 0);
            getUserName.visitFieldInsn(GETFIELD, "pers/darren/bytecode/asm/generating/User", "userName", getDescriptor(String.class));
            getUserName.visitInsn(ARETURN);
            Label label1 = new Label();
            getUserName.visitLabel(label1);
            getUserName.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            getUserName.visitMaxs(1, 1);
            getUserName.visitEnd();
        }
        {
            MethodVisitor setUserName = user.visitMethod(ACC_PUBLIC, "setUserName", getMethodDescriptor(VOID_TYPE, getType(String.class)), null, null);
            setUserName.visitCode();
            Label label0 = new Label();
            setUserName.visitLabel(label0);
            setUserName.visitVarInsn(ALOAD, 0);
            setUserName.visitVarInsn(ALOAD, 1);
            setUserName.visitFieldInsn(PUTFIELD, "pers/darren/bytecode/asm/generating/User", "userName", getDescriptor(String.class));
            setUserName.visitInsn(RETURN);
            Label label1 = new Label();
            setUserName.visitLabel(label1);
            setUserName.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            setUserName.visitLocalVariable("userName", getDescriptor(String.class), null, label0, label1, 1);
            setUserName.visitMaxs(2, 2);
            setUserName.visitEnd();
        }
        {
            MethodVisitor getFullName = user.visitMethod(ACC_PUBLIC, "getFullName", getMethodDescriptor(getType(String.class)), null, null);
            getFullName.visitCode();
            Label label0 = new Label();
            getFullName.visitLabel(label0);
            getFullName.visitVarInsn(ALOAD, 0);
            getFullName.visitFieldInsn(GETFIELD, "pers/darren/bytecode/asm/generating/User", "fullName", getDescriptor(String.class));
            getFullName.visitInsn(ARETURN);
            Label label1 = new Label();
            getFullName.visitLabel(label1);
            getFullName.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            getFullName.visitMaxs(1, 1);
            getFullName.visitEnd();
        }
        {
            MethodVisitor setFullName = user.visitMethod(ACC_PUBLIC, "setFullName", getMethodDescriptor(VOID_TYPE, getType(String.class)), null, null);
            setFullName.visitCode();
            Label label0 = new Label();
            setFullName.visitLabel(label0);
            setFullName.visitVarInsn(ALOAD, 0);
            setFullName.visitVarInsn(ALOAD, 1);
            setFullName.visitFieldInsn(PUTFIELD, "pers/darren/bytecode/asm/generating/User", "fullName", getDescriptor(String.class));
            setFullName.visitInsn(RETURN);
            Label label1 = new Label();
            setFullName.visitLabel(label1);
            setFullName.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            setFullName.visitLocalVariable("fullName", getDescriptor(String.class), null, label0, label1, 1);
            setFullName.visitMaxs(2, 2);
            setFullName.visitEnd();
        }
        {
            MethodVisitor getGender = user.visitMethod(ACC_PUBLIC, "getGender", getMethodDescriptor(getType(String.class)), null, null);
            getGender.visitCode();
            Label label0 = new Label();
            getGender.visitLabel(label0);
            getGender.visitVarInsn(ALOAD, 0);
            getGender.visitFieldInsn(GETFIELD, "pers/darren/bytecode/asm/generating/User", "gender", getDescriptor(String.class));
            getGender.visitInsn(ARETURN);
            Label label1 = new Label();
            getGender.visitLabel(label1);
            getGender.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            getGender.visitMaxs(1, 1);
            getGender.visitEnd();
        }
        {
            MethodVisitor setGender = user.visitMethod(ACC_PUBLIC, "setGender", getMethodDescriptor(VOID_TYPE, getType(String.class)), null, null);
            setGender.visitCode();
            Label label0 = new Label();
            setGender.visitLabel(label0);
            setGender.visitVarInsn(ALOAD, 0);
            setGender.visitVarInsn(ALOAD, 1);
            setGender.visitFieldInsn(PUTFIELD, "pers/darren/bytecode/asm/generating/User", "gender", getDescriptor(String.class));
            setGender.visitInsn(RETURN);
            Label label1 = new Label();
            setGender.visitLabel(label1);
            setGender.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            setGender.visitLocalVariable("gender", getDescriptor(String.class), null, label0, label1, 1);
            setGender.visitMaxs(2, 2);
            setGender.visitEnd();
        }
        {
            MethodVisitor getAge = user.visitMethod(ACC_PUBLIC, "getAge", getMethodDescriptor(getType(Integer.class)), null, null);
            getAge.visitCode();
            Label label0 = new Label();
            getAge.visitLabel(label0);
            getAge.visitVarInsn(ALOAD, 0);
            getAge.visitFieldInsn(GETFIELD, "pers/darren/bytecode/asm/generating/User", "age", getDescriptor(Integer.class));
            getAge.visitInsn(ARETURN);
            Label label1 = new Label();
            getAge.visitLabel(label1);
            getAge.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            getAge.visitMaxs(1, 1);
            getAge.visitEnd();
        }
        {
            MethodVisitor setAge = user.visitMethod(ACC_PUBLIC, "setAge", getMethodDescriptor(VOID_TYPE, getType(Integer.class)), null, null);
            setAge.visitCode();
            Label label0 = new Label();
            setAge.visitLabel(label0);
            setAge.visitVarInsn(ALOAD, 0);
            setAge.visitVarInsn(ALOAD, 1);
            setAge.visitFieldInsn(PUTFIELD, "pers/darren/bytecode/asm/generating/User", "age", getDescriptor(Integer.class));
            setAge.visitInsn(RETURN);
            Label label1 = new Label();
            setAge.visitLabel(label1);
            setAge.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            setAge.visitLocalVariable("age", getDescriptor(Integer.class), null, label0, label1, 1);
            setAge.visitMaxs(2, 2);
            setAge.visitEnd();
        }
        {
            MethodVisitor getPhone = user.visitMethod(ACC_PUBLIC, "getPhone", getMethodDescriptor(getType(String.class)), null, null);
            getPhone.visitCode();
            Label label0 = new Label();
            getPhone.visitLabel(label0);
            getPhone.visitVarInsn(ALOAD, 0);
            getPhone.visitFieldInsn(GETFIELD, "pers/darren/bytecode/asm/generating/User", "phone", getDescriptor(String.class));
            getPhone.visitInsn(ARETURN);
            Label label1 = new Label();
            getPhone.visitLabel(label1);
            getPhone.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            getPhone.visitMaxs(1, 1);
            getPhone.visitEnd();
        }
        {
            MethodVisitor setPhone = user.visitMethod(ACC_PUBLIC, "setPhone", getMethodDescriptor(VOID_TYPE, getType(String.class)), null, null);
            setPhone.visitCode();
            Label label0 = new Label();
            setPhone.visitLabel(label0);
            setPhone.visitVarInsn(ALOAD, 0);
            setPhone.visitVarInsn(ALOAD, 1);
            setPhone.visitFieldInsn(PUTFIELD, "pers/darren/bytecode/asm/generating/User", "phone", getDescriptor(String.class));
            setPhone.visitInsn(RETURN);
            Label label1 = new Label();
            setPhone.visitLabel(label1);
            setPhone.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            setPhone.visitLocalVariable("phone", getDescriptor(String.class), null, label0, label1, 1);
            setPhone.visitMaxs(2, 2);
            setPhone.visitEnd();
        }
        {
            MethodVisitor getSalary = user.visitMethod(ACC_PUBLIC, "getSalary", getMethodDescriptor(getType(BigDecimal.class)), null, null);
            getSalary.visitCode();
            Label label0 = new Label();
            getSalary.visitLabel(label0);
            getSalary.visitVarInsn(ALOAD, 0);
            getSalary.visitFieldInsn(GETFIELD, "pers/darren/bytecode/asm/generating/User", "salary", getDescriptor(BigDecimal.class));
            getSalary.visitInsn(ARETURN);
            Label label1 = new Label();
            getSalary.visitLabel(label1);
            getSalary.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            getSalary.visitMaxs(1, 1);
            getSalary.visitEnd();
        }
        {
            MethodVisitor setSalary = user.visitMethod(ACC_PUBLIC, "setSalary", getMethodDescriptor(VOID_TYPE, getType(BigDecimal.class)), null, null);
            setSalary.visitCode();
            Label label0 = new Label();
            setSalary.visitLabel(label0);
            setSalary.visitVarInsn(ALOAD, 0);
            setSalary.visitVarInsn(ALOAD, 1);
            setSalary.visitFieldInsn(PUTFIELD, "pers/darren/bytecode/asm/generating/User", "salary", getDescriptor(BigDecimal.class));
            setSalary.visitInsn(RETURN);
            Label label1 = new Label();
            setSalary.visitLabel(label1);
            setSalary.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            setSalary.visitLocalVariable("salary", getDescriptor(BigDecimal.class), null, label0, label1, 1);
            setSalary.visitMaxs(2, 2);
            setSalary.visitEnd();
        }
        {
            MethodVisitor getAddress = user.visitMethod(ACC_PUBLIC, "getAddress", getMethodDescriptor(getType(String.class)), null, null);
            getAddress.visitCode();
            Label label0 = new Label();
            getAddress.visitLabel(label0);
            getAddress.visitVarInsn(ALOAD, 0);
            getAddress.visitFieldInsn(GETFIELD, "pers/darren/bytecode/asm/generating/User", "address", getDescriptor(String.class));
            getAddress.visitInsn(ARETURN);
            Label label1 = new Label();
            getAddress.visitLabel(label1);
            getAddress.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            getAddress.visitMaxs(1, 1);
            getAddress.visitEnd();
        }
        {
            MethodVisitor setAddress = user.visitMethod(ACC_PUBLIC, "setAddress", getMethodDescriptor(VOID_TYPE, getType(String.class)), null, null);
            setAddress.visitCode();
            Label label0 = new Label();
            setAddress.visitLabel(label0);
            setAddress.visitVarInsn(ALOAD, 0);
            setAddress.visitVarInsn(ALOAD, 1);
            setAddress.visitFieldInsn(PUTFIELD, "pers/darren/bytecode/asm/generating/User", "address", getDescriptor(String.class));
            setAddress.visitInsn(RETURN);
            Label label1 = new Label();
            setAddress.visitLabel(label1);
            setAddress.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            setAddress.visitLocalVariable("address", getDescriptor(String.class), null, label0, label1, 1);
            setAddress.visitMaxs(2, 2);
            setAddress.visitEnd();
        }
        {
            MethodVisitor getCreatedTime = user.visitMethod(ACC_PUBLIC, "getCreatedTime", getMethodDescriptor(getType(Date.class)), null, null);
            getCreatedTime.visitCode();
            Label label0 = new Label();
            getCreatedTime.visitLabel(label0);
            getCreatedTime.visitVarInsn(ALOAD, 0);
            getCreatedTime.visitFieldInsn(GETFIELD, "pers/darren/bytecode/asm/generating/User", "createdTime", getDescriptor(Date.class));
            getCreatedTime.visitInsn(ARETURN);
            Label label1 = new Label();
            getCreatedTime.visitLabel(label1);
            getCreatedTime.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            getCreatedTime.visitMaxs(1, 1);
            getCreatedTime.visitEnd();
        }
        {
            MethodVisitor setCreatedTime = user.visitMethod(ACC_PUBLIC, "setCreatedTime", getMethodDescriptor(VOID_TYPE, getType(Date.class)), null, null);
            setCreatedTime.visitCode();
            Label label0 = new Label();
            setCreatedTime.visitLabel(label0);
            setCreatedTime.visitVarInsn(ALOAD, 0);
            setCreatedTime.visitVarInsn(ALOAD, 1);
            setCreatedTime.visitFieldInsn(PUTFIELD, "pers/darren/bytecode/asm/generating/User", "createdTime", getDescriptor(Date.class));
            setCreatedTime.visitInsn(RETURN);
            Label label1 = new Label();
            setCreatedTime.visitLabel(label1);
            setCreatedTime.visitLocalVariable("this", "Lpers/darren/bytecode/asm/generating/User;", null, label0, label1, 0);
            setCreatedTime.visitLocalVariable("createdTime", getDescriptor(Date.class), null, label0, label1, 1);
            setCreatedTime.visitMaxs(2, 2);
            setCreatedTime.visitEnd();
        }
        user.visitEnd();
        // 将字节码输出为class文件
        byte[] bytes = user.toByteArray();
        FileOutputStream fos = new FileOutputStream("/home/darren/Workspaces/IDEA/case/target/classes/pers/darren/bytecode/asm/generating/User.class");
        fos.write(bytes);
        fos.close();
        // 加载生成的User类并进行实例化，调用类方法
        CustomClassLoader loader = new CustomClassLoader();
        Class<?> clazz = loader.defineClass("pers.darren.bytecode.asm.generating.User", bytes);
        // 实例化User类
        Object instance = clazz.getDeclaredConstructor().newInstance();
        // 调用setUserName方法
        Method setUserName = clazz.getDeclaredMethod("setUserName", String.class);
        setUserName.invoke(instance, "Darren Luo");
        // 调用getUserName方法
        Method getUserName = clazz.getDeclaredMethod("getUserName");
        System.out.println("UserName:" + getUserName.invoke(instance));
        // 调用setAge方法
        Method setAge = clazz.getDeclaredMethod("setAge", Integer.class);
        setAge.invoke(instance, 18);
        // 调用getAge方法
        Method getAge = clazz.getDeclaredMethod("getAge");
        System.out.println("Age:" + getAge.invoke(instance));
        // 调用setSalary方法
        Method setSalary = clazz.getDeclaredMethod("setSalary", BigDecimal.class);
        setSalary.invoke(instance, new BigDecimal(12000));
        // 调用getSalary方法
        Method getSalary = clazz.getDeclaredMethod("getSalary");
        System.out.println("Salary:" + getSalary.invoke(instance));
        // 调用setCreatedTime方法
        Method setCreatedTime = clazz.getDeclaredMethod("setCreatedTime", Date.class);
        setCreatedTime.invoke(instance, new Date());
        // 调用getCreatedTime方法
        Method getCreatedTime = clazz.getDeclaredMethod("getCreatedTime");
        System.out.println("CreatedTime:" + getCreatedTime.invoke(instance));
    }
}
