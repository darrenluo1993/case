package pers.darren.bytecode.classreload;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

import static pers.darren.bytecode.classreload.Base.BASE_CLASS_NAME;

public class Attacher {

    public static void main(String[] args) {
        List<VirtualMachineDescriptor> vmdList = VirtualMachine.list();
        vmdList.forEach(vmd -> {
            String displayName = vmd.displayName();
            System.out.println("虚拟机名称：" + displayName);
            if (BASE_CLASS_NAME.equals(displayName)) {
                try {
                    VirtualMachine vm = VirtualMachine.attach(vmd.id());
                    vm.loadAgent("/home/darren/Workspaces/IDEA/case/target/agent-0.0.1-SNAPSHOT.jar");
                    vm.detach();
                } catch (AttachNotSupportedException | IOException | AgentLoadException |
                         AgentInitializationException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
