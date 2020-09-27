package pers.darren.designpattern.abstractfactory1;

import static pers.darren.designpattern.abstractfactory1.FactoryProducer.createFactory;
import static pers.darren.designpattern.abstractfactory1.FactoryType.ASUS;
import static pers.darren.designpattern.abstractfactory1.FactoryType.DELL;
import static pers.darren.designpattern.abstractfactory1.FactoryType.HUAWEI;
import static pers.darren.designpattern.abstractfactory1.FactoryType.LENOVO;

/**
 * 工厂模式示例
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 24, 2020 4:38:41 PM
 */
class AbstractFactoryPatternDemo {

    public static void main(final String[] args) {
        // 创建联想计算机工厂
        final ComputerFactory lenovoFactory = createFactory(LENOVO);
        // 生产联想笔记本电脑，并进行介绍
        lenovoFactory.createLaptop("英特尔 i7 6代", "英伟达 GTX 750", "2GB", "200GB").introduce();
        lenovoFactory.createLaptop("英特尔 i7 7代", "英伟达 GTX 850", "4GB", "500GB").introduce();
        lenovoFactory.createLaptop("英特尔 i7 8代", "英伟达 GTX 950", "8GB", "1TB").introduce();
        lenovoFactory.createLaptop("英特尔 i7 9代", "英伟达 GTX 1050", "16GB", "2TB").introduce();
        lenovoFactory.createLaptop("英特尔 i7 10代", "英伟达 GTX 1150", "32GB", "3TB").introduce();
        // 生产联想鼠标，并操作鼠标点击
        lenovoFactory.createMouse("有线", "黑色", "3个", "200").click();
        lenovoFactory.createMouse("无线", "红色", "6个", "500").click();
        lenovoFactory.createMouse("有线", "七彩", "8个", "700").click();
        System.out.println();
        // 创建华为计算机工厂
        final ComputerFactory huaweiFactory = createFactory(HUAWEI);
        // 生产华为笔记本电脑，并进行介绍
        huaweiFactory.createLaptop("英特尔 i5 6代", "英伟达 GTX 750", "2GB", "200GB").introduce();
        huaweiFactory.createLaptop("英特尔 i5 7代", "英伟达 GTX 850", "4GB", "500GB").introduce();
        huaweiFactory.createLaptop("英特尔 i5 8代", "英伟达 GTX 950", "8GB", "1TB").introduce();
        huaweiFactory.createLaptop("英特尔 i5 9代", "英伟达 GTX 1050", "16GB", "2TB").introduce();
        huaweiFactory.createLaptop("英特尔 i5 10代", "英伟达 GTX 1150", "32GB", "3TB").introduce();
        // 生产华为鼠标，并操作鼠标点击
        huaweiFactory.createMouse("有线", "紫色", "3个", "200").click();
        huaweiFactory.createMouse("有线", "黑色", "6个", "300").click();
        huaweiFactory.createMouse("无线", "褐色", "8个", "650").click();
        System.out.println();
        // 创建戴尔计算机工厂
        final ComputerFactory dellFactory = createFactory(DELL);
        // 生产戴尔笔记本电脑，并进行介绍
        dellFactory.createLaptop("英特尔 i3 6代", "英伟达 GTX 750", "2GB", "200GB").introduce();
        dellFactory.createLaptop("英特尔 i3 7代", "英伟达 GTX 850", "4GB", "500GB").introduce();
        dellFactory.createLaptop("英特尔 i3 8代", "英伟达 GTX 950", "8GB", "1TB").introduce();
        dellFactory.createLaptop("英特尔 i3 9代", "英伟达 GTX 1050", "16GB", "2TB").introduce();
        dellFactory.createLaptop("英特尔 i3 10代", "英伟达 GTX 1150", "32GB", "3TB").introduce();
        // 生产戴尔鼠标，并操作鼠标点击
        dellFactory.createMouse("有线", "七彩", "3个", "270").click();
        dellFactory.createMouse("无线", "红色", "4个", "300").click();
        dellFactory.createMouse("无线", "灰色", "6个", "550").click();
        System.out.println();
        // 创建戴尔计算机工厂
        final ComputerFactory asusFactory = createFactory(ASUS);
        // 生产华硕笔记本电脑，并进行介绍
        asusFactory.createLaptop("英特尔 i9 6代", "英伟达 GTX 750", "2GB", "200GB").introduce();
        asusFactory.createLaptop("英特尔 i9 7代", "英伟达 GTX 850", "4GB", "500GB").introduce();
        asusFactory.createLaptop("英特尔 i9 8代", "英伟达 GTX 950", "8GB", "1TB").introduce();
        asusFactory.createLaptop("英特尔 i9 9代", "英伟达 GTX 1050", "16GB", "2TB").introduce();
        asusFactory.createLaptop("英特尔 i9 10代", "英伟达 GTX 1150", "32GB", "3TB").introduce();
        // 生产华硕鼠标，并操作鼠标点击
        asusFactory.createMouse("无线", "白色", "4个", "270").click();
        asusFactory.createMouse("无线", "紫色", "5个", "300").click();
        asusFactory.createMouse("有线", "绿色", "6个", "550").click();
    }
}