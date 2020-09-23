package pers.darren.designpattern.abstractfactory;

/**
 * 抽象工厂
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 4:42:01 PM
 */
abstract class AbstractFactory {
    /**
     * 生产颜色对象
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 23, 2020 4:51:41 PM
     * @param colorType 颜色类型
     * @return
     */
    abstract Color getColor(ColorType colorType);

    /**
     * 生产图形对象
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 23, 2020 4:52:51 PM
     * @param shapeType 图形类型
     * @return
     */
    abstract Shape getShape(ShapeType shapeType);
}