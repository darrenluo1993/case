package pers.darren.designpattern.abstractfactory1;

/**
 * 实际产品-鼠标基类
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 25, 2020 11:47:50 AM
 */
abstract class BaseMouse implements Mouse {

    BaseMouse(final String wireless, final String color, final String keyNum, final String price) {
        this.wireless = wireless;
        this.color = color;
        this.keyNum = keyNum;
        this.price = price;
    }

    /**
     * 有线/无线
     */
    String wireless;
    /**
     * 颜色
     */
    String color;
    /**
     * 按键数量
     */
    String keyNum;
    /**
     * 价格
     */
    String price;
}