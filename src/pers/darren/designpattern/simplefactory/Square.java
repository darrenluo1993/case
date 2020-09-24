package pers.darren.designpattern.simplefactory;

/**
 * 正方形
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 2:52:36 PM
 */
class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}