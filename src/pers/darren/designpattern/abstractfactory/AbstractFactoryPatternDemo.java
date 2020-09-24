package pers.darren.designpattern.abstractfactory;

import static pers.darren.designpattern.abstractfactory.ColorType.BLUE;
import static pers.darren.designpattern.abstractfactory.ColorType.GREEN;
import static pers.darren.designpattern.abstractfactory.ColorType.RED;
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
        // 获取图形为 NullShape 的对象
        final Shape shape4 = shapeFactory.getShape(null);
        // 调用 NullShape 的 draw 方法
        shape4.draw();
        System.out.println();

        // 获取颜色工厂
        final AbstractFactory colorFactory = FactoryProducer.getFactory(COLOR);
        // 获取颜色为 Red 的对象
        final Color color1 = colorFactory.getColor(RED);
        // 调用 Red 的 fill 方法
        color1.fill();
        // 获取颜色为 Green 的对象
        final Color color2 = colorFactory.getColor(GREEN);
        // 调用 Green 的 fill 方法
        color2.fill();
        // 获取颜色为 Blue 的对象
        final Color color3 = colorFactory.getColor(BLUE);
        // 调用 Blue 的 fill 方法
        color3.fill();
        // 获取颜色为 NullColor 的对象
        final Color color4 = colorFactory.getColor(null);
        // 调用 NullColor 的 fill 方法
        color4.fill();
        System.out.println();

        // 获取空工厂对象
        final AbstractFactory nullFactory = FactoryProducer.getFactory(null);
        nullFactory.getShape(CIRCLE).draw();
        nullFactory.getShape(RECTANGLE).draw();
        nullFactory.getShape(SQUARE).draw();
        nullFactory.getColor(RED).fill();
        nullFactory.getColor(GREEN).fill();
        nullFactory.getColor(BLUE).fill();
    }
}