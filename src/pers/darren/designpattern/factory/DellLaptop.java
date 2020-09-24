package pers.darren.designpattern.factory;

import static java.lang.String.format;

/**
 * 实际产品-戴尔笔记本电脑
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 24, 2020 4:22:55 PM
 */
class DellLaptop extends BaseLaptop {

    DellLaptop(final String cpu, final String gpu, final String memory, final String disk) {
        super(cpu, gpu, memory, disk);
    }

    @Override
    public void introduce() {
        System.out.println(format("你好，我是一台戴尔笔记本电脑，我的配置为CPU=%s、GPU=%s、运行内存容量=%s、磁盘容量=%s，欢迎使用！", this.cpu, this.gpu,
                this.memory, this.disk));
    }
}