package pers.darren.designpattern.factory;

/**
 * 实际工厂-华为计算机工厂
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 24, 2020 4:28:51 PM
 */
class HuaweiFactory extends ComputerFactory {

    @Override
    Laptop createLaptop(final String cpu, final String gpu, final String memory, final String disk) {
        return new HuaweiLaptop(cpu, gpu, memory, disk);
    }
}