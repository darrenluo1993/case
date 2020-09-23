package pers.darren.designpattern.abstractfactory;

/**
 * 抽象工厂适配器
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 5:08:42 PM
 */
class AbstractFactoryAdapter extends AbstractFactory {

    @Override
    Color getColor(final ColorType colorType) {
        return new NullColor();
    }

    @Override
    Shape getShape(final ShapeType shapeType) {
        return new NullShape();
    }
}