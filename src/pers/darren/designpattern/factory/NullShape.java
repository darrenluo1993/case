package pers.darren.designpattern.factory;

/**
 * 图形空对象
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 2:58:34 PM
 */
public class NullShape implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside NullShape::draw() method.");
    }
}