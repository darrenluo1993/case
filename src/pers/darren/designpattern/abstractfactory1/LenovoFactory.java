package pers.darren.designpattern.abstractfactory1;

/**
 * 实际工厂-联想计算机工厂
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 24, 2020 4:28:01 PM
 */
class LenovoFactory extends ComputerFactory {

    @Override
    Laptop createLaptop(final String cpu, final String gpu, final String memory, final String disk) {
        return new LenovoLaptop(cpu, gpu, memory, disk);
    }

    @Override
    Mouse createMouse(final String wireless, final String color, final String keyNum, final String price) {
        return new LenovoMouse(wireless, color, keyNum, price);
    }
}