package pers.darren.designpattern.abstractfactory1;

/**
 * 实际产品-键盘基类
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 27, 2020 11:50:37 AM
 */
abstract class BaseKeyboard implements Keyboard {

    BaseKeyboard(final String wireless, final String color, final String mechanical, final String keypad) {
        this.wireless = wireless;
        this.color = color;
        this.mechanical = mechanical;
        this.keypad = keypad;
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
     * 机械/普通
     */
    String mechanical;
    /**
     * 有无小键盘
     */
    String keypad;
}