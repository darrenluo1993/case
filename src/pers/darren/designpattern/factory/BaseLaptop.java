package pers.darren.designpattern.factory;

/**
 * 实际产品-笔记本电脑基类
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 24, 2020 4:50:27 PM
 */
abstract class BaseLaptop implements Laptop {

    BaseLaptop(final String cpu, final String gpu, final String memory, final String disk) {
        this.cpu = cpu;
        this.gpu = gpu;
        this.memory = memory;
        this.disk = disk;
    }

    /**
     * CPU型号
     */
    String cpu;
    /**
     * GPU型号
     */
    String gpu;
    /**
     * 运行内存容量
     */
    String memory;
    /**
     * 磁盘容量
     */
    String disk;
}