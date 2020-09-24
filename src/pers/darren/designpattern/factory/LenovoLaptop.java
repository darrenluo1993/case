package pers.darren.designpattern.factory;

import static java.lang.String.format;

/**
 * 实际产品-联想笔记本电脑
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 24, 2020 4:16:34 PM
 */
class LenovoLaptop extends BaseLaptop {

    LenovoLaptop(final String cpu, final String gpu, final String memory, final String disk) {
        super(cpu, gpu, memory, disk);
    }

    @Override
    public void introduce() {
        System.out.println(format("你好，我是一台联想笔记本电脑，我的配置为CPU=%s、GPU=%s、运行内存容量=%s、磁盘容量=%s，欢迎使用！", this.cpu, this.gpu,
                this.memory, this.disk));
    }
}