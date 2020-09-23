package pers.darren.designpattern.factory;

/**
 * 矩形
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 2:51:24 PM
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}