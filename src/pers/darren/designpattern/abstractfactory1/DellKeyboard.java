package pers.darren.designpattern.abstractfactory1;

import static java.lang.String.format;

/**
 * 实际产品-戴尔键盘
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 27, 2020 11:54:40 AM
 */
class DellKeyboard extends BaseKeyboard {

    DellKeyboard(final String wireless, final String color, final String mechanical, final String keypad) {
        super(wireless, color, mechanical, keypad);
    }

    @Override
    public void press() {
        System.out.println(
                format("你好，我是一个%s的%s戴尔%s键盘，我%s小键盘，欢迎使用！", super.color, super.wireless, super.mechanical, super.keypad));
    }
}