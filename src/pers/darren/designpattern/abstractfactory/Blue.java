package pers.darren.designpattern.abstractfactory;

/**
 * 蓝色
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 4:40:38 PM
 */
class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}