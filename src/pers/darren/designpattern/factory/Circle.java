package pers.darren.designpattern.factory;

/**
 * 圆
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 2:53:22 PM
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}