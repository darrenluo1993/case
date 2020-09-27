package pers.darren.designpattern.abstractfactory1;

/**
 * 实际工厂-华硕计算机工厂
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 27, 2020 9:23:40 AM
 */
class AsusFactory extends ComputerFactory {

    @Override
    Laptop createLaptop(final String cpu, final String gpu, final String memory, final String disk) {
        return new AsusLaptop(cpu, gpu, memory, disk);
    }

    @Override
    Mouse createMouse(final String wireless, final String color, final String keyNum, final String price) {
        return new AsusMouse(wireless, color, keyNum, price);
    }
}