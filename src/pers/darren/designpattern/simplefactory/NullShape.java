package pers.darren.designpattern.simplefactory;

/**
 * 图形空对象
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 2:58:34 PM
 */
class NullShape implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside NullShape::draw() method.");
    }
}