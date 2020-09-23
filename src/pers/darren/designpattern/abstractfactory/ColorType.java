package pers.darren.designpattern.abstractfactory;

/**
 * 颜色类型枚举
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 4:54:19 PM
 */
enum ColorType {
    /**
     * 红色
     */
    RED("pers.darren.designpattern.abstractfactory.Red"),
    /**
     * 绿色
     */
    GREEN("pers.darren.designpattern.abstractfactory.Green"),
    /**
     * 蓝色
     */
    BLUE("pers.darren.designpattern.abstractfactory.Blue");

    private String className;

    private ColorType(final String className) {
        this.className = className;
    }

    String getClassName() {
        return this.className;
    }
}