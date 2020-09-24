package pers.darren.designpattern.simplefactory;

/**
 * 圆
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 2:53:22 PM
 */
class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}