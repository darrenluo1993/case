package pers.darren.designpattern.abstractfactory;

/**
 * 绿色
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 4:39:48 PM
 */
class Green implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}