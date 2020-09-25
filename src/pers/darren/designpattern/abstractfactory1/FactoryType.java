package pers.darren.designpattern.abstractfactory1;

/**
 * 工厂类型枚举
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 5:39:25 PM
 */
enum FactoryType {
    /**
     * 联想计算机工厂
     */
    LENOVO("pers.darren.designpattern.abstractfactory1.LenovoFactory"),
    /**
     * 华为计算机工厂
     */
    HUAWEI("pers.darren.designpattern.abstractfactory1.HuaweiFactory"),
    /**
     * 戴尔计算机工厂
     */
    DELL("pers.darren.designpattern.abstractfactory1.DellFactory");

    private String className;

    private FactoryType(final String className) {
        this.className = className;
    }

    String getClassName() {
        return this.className;
    }
}