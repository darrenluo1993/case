package pers.darren.designpattern.simplefactory;

/**
 * 图形类型枚举
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 3:29:28 PM
 */
enum ShapeType {
    /**
     * 圆
     */
    CIRCLE("pers.darren.designpattern.factory.Circle"),
    /**
     * 矩形
     */
    RECTANGLE("pers.darren.designpattern.factory.Rectangle"),
    /**
     * 正方形
     */
    SQUARE("pers.darren.designpattern.factory.Square");

    private String className;

    private ShapeType(final String className) {
        this.className = className;
    }

    String getClassName() {
        return this.className;
    }
}