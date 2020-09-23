package pers.darren.designpattern.abstractfactory;

/**
 * 工厂类型枚举
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 5:39:25 PM
 */
enum FactoryType {
    /**
     * 图形工厂
     */
    SHAPE("pers.darren.designpattern.abstractfactory.ShapeFactory"),
    /**
     * 颜色工厂
     */
    COLOR("pers.darren.designpattern.abstractfactory.ColorFactory");

    private String className;

    private FactoryType(final String className) {
        this.className = className;
    }

    String getClassName() {
        return this.className;
    }
}