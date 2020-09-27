package pers.darren.designpattern.abstractfactory1;

import static java.lang.String.format;

/**
 * 实际产品-华硕笔记本电脑
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 27, 2020 9:24:55 AM
 */
class AsusLaptop extends BaseLaptop {

    AsusLaptop(final String cpu, final String gpu, final String memory, final String disk) {
        super(cpu, gpu, memory, disk);
    }

    @Override
    public void introduce() {
        System.out.println(format("你好，我是一台华硕笔记本电脑，我的配置为CPU=%s、GPU=%s、运行内存容量=%s、磁盘容量=%s，欢迎使用！", super.cpu, super.gpu,
                super.memory, super.disk));
    }
}