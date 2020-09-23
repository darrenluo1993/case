package pers.darren.designpattern.abstractfactory;

/**
 * 颜色空对象
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 5:14:40 PM
 */
class NullColor implements Color {

    @Override
    public void fill() {
        System.out.println("Inside NullColor::fill() method.");
    }
}