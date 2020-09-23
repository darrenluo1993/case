package pers.darren.designpattern.abstractfactory;

import static pers.darren.designpattern.abstractfactory.FactoryType.COLOR;
import static pers.darren.designpattern.abstractfactory.FactoryType.SHAPE;
import static pers.darren.designpattern.abstractfactory.ShapeType.CIRCLE;
import static pers.darren.designpattern.abstractfactory.ShapeType.RECTANGLE;
import static pers.darren.designpattern.abstractfactory.ShapeType.SQUARE;

/**
 * 抽象工厂模式示例
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 5:55:35 PM
 */
class AbstractFactoryPatternDemo {

    public static void main(final String[] args) {
        // 获取图形工厂
        final AbstractFactory shapeFactory = FactoryProducer.getFactory(SHAPE);
        // 获取图形为 Circle 的对象
        final Shape shape1 = shapeFactory.getShape(CIRCLE);
        // 调用 Circle 的 draw 方法
        shape1.draw();
        // 获取图形为 Rectangle 的对象
        final Shape shape2 = shapeFactory.getShape(RECTANGLE);
        // 调用 Rectangle 的 draw 方法
        shape2.draw();
        // 获取图形为 Square 的对象
        final Shape shape3 = shapeFactory.getShape(SQUARE);
        // 调用 Square 的 draw 方法
        shape3.draw();

        // 获取颜色工厂
        final AbstractFactory colorFactory = FactoryProducer.getFactory(COLOR);
        // 获取颜色为 Red 的对象
        final Color color1 = colorFactory.getColor(ColorType.RED);
        // 调用 Red 的 fill 方法
        color1.fill();
        // 获取颜色为 Green 的对象
        final Color color2 = colorFactory.getColor(ColorType.GREEN);
        // 调用 Green 的 fill 方法
        color2.fill();
        // 获取颜色为 Blue 的对象
        final Color color3 = colorFactory.getColor(ColorType.BLUE);
        // 调用 Blue 的 fill 方法
        color3.fill();
    }
}