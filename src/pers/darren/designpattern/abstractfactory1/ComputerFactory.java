package pers.darren.designpattern.abstractfactory1;

/**
 * 抽象工厂-计算机工厂
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 24, 2020 4:06:35 PM
 */
abstract class ComputerFactory {
    /**
     * 生产笔记本电脑
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 24, 2020 4:13:21 PM
     * @param cpu    CPU型号
     * @param gpu    GPU型号
     * @param memory 运行内存容量
     * @param disk   磁盘容量
     * @return
     */
    abstract Laptop createLaptop(String cpu, String gpu, String memory, String disk);

    /**
     * 生产鼠标
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 25, 2020 11:40:46 AM
     * @param wireless 有线/无线
     * @param color    颜色
     * @param keyNum   按键数量
     * @param price    价格
     * @return
     */
    abstract Mouse createMouse(String wireless, String color, String keyNum, String price);
}