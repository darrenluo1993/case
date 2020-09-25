package pers.darren.designpattern.abstractfactory1;

import static java.lang.String.format;

/**
 * 实际产品-戴尔鼠标
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 25, 2020 4:07:01 PM
 */
class DellMouse extends BaseMouse {

    DellMouse(final String wireless, final String color, final String keyNum, final String price) {
        super(wireless, color, keyNum, price);
    }

    @Override
    public void click() {
        System.out.println(format("你好，我是一个%s的%s戴尔鼠标，我有%s按键，我的价格是%s元，欢迎使用！", super.color, super.wireless, super.keyNum,
                super.price));
    }
}