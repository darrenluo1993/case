package pers.darren.designpattern.abstractfactory;

/**
 * 红色
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 4:39:07 PM
 */
class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}