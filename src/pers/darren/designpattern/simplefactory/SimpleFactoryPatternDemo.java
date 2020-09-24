package pers.darren.designpattern.simplefactory;

import static pers.darren.designpattern.simplefactory.ShapeType.CIRCLE;
import static pers.darren.designpattern.simplefactory.ShapeType.RECTANGLE;
import static pers.darren.designpattern.simplefactory.ShapeType.SQUARE;

/**
 * 简单工厂模式示例
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 3:00:42 PM
 */
class SimpleFactoryPatternDemo {

    public static void main(final String[] args) {
        final ShapeFactory shapeFactory = new ShapeFactory();

        // 获取 Circle 的对象，并调用它的 draw 方法
        final Shape shape1 = shapeFactory.getShape(CIRCLE);
        // 调用 Circle 的 draw 方法
        shape1.draw();

        // 获取 Rectangle 的对象，并调用它的 draw 方法
        final Shape shape2 = shapeFactory.getShape(RECTANGLE);
        // 调用 Rectangle 的 draw 方法
        shape2.draw();

        // 获取 Square 的对象，并调用它的 draw 方法
        final Shape shape3 = shapeFactory.getShape(SQUARE);
        // 调用 Square 的 draw 方法
        shape3.draw();

        // 获取 NullShape 的对象，并调用它的 draw 方法
        final Shape shape4 = shapeFactory.getShape(null);
        // 调用 NullShape 的 draw 方法
        shape4.draw();
    }
}