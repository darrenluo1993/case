package pers.darren.designpattern.factory;

import static pers.darren.designpattern.factory.FactoryProducer.createFactory;
import static pers.darren.designpattern.factory.FactoryType.DELL;
import static pers.darren.designpattern.factory.FactoryType.HUAWEI;
import static pers.darren.designpattern.factory.FactoryType.LENOVO;

/**
 * 工厂模式示例
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 24, 2020 4:38:41 PM
 */
class FactoryPatternDemo {

    public static void main(final String[] args) {
        // 创建联想计算机工厂，生产联想笔记本电脑，并进行介绍
        final ComputerFactory lenovoFactory = createFactory(LENOVO);
        lenovoFactory.createLaptop("英特尔 i7 6代", "英伟达 GTX 750", "2GB", "200GB").introduce();
        lenovoFactory.createLaptop("英特尔 i7 7代", "英伟达 GTX 850", "4GB", "500GB").introduce();
        lenovoFactory.createLaptop("英特尔 i7 8代", "英伟达 GTX 950", "8GB", "1TB").introduce();
        lenovoFactory.createLaptop("英特尔 i7 9代", "英伟达 GTX 1050", "16GB", "2TB").introduce();
        lenovoFactory.createLaptop("英特尔 i7 10代", "英伟达 GTX 1150", "32GB", "3TB").introduce();
        System.out.println();
        // 创建华为计算机工厂，生产华为笔记本电脑，并进行介绍
        final ComputerFactory huaweiFactory = createFactory(HUAWEI);
        huaweiFactory.createLaptop("英特尔 i5 6代", "英伟达 GTX 750", "2GB", "200GB").introduce();
        huaweiFactory.createLaptop("英特尔 i5 7代", "英伟达 GTX 850", "4GB", "500GB").introduce();
        huaweiFactory.createLaptop("英特尔 i5 8代", "英伟达 GTX 950", "8GB", "1TB").introduce();
        huaweiFactory.createLaptop("英特尔 i5 9代", "英伟达 GTX 1050", "16GB", "2TB").introduce();
        huaweiFactory.createLaptop("英特尔 i5 10代", "英伟达 GTX 1150", "32GB", "3TB").introduce();
        System.out.println();
        // 创建戴尔计算机工厂，生产戴尔笔记本电脑，并进行介绍
        final ComputerFactory dellFactory = createFactory(DELL);
        dellFactory.createLaptop("英特尔 i3 6代", "英伟达 GTX 750", "2GB", "200GB").introduce();
        dellFactory.createLaptop("英特尔 i3 7代", "英伟达 GTX 850", "4GB", "500GB").introduce();
        dellFactory.createLaptop("英特尔 i3 8代", "英伟达 GTX 950", "8GB", "1TB").introduce();
        dellFactory.createLaptop("英特尔 i3 9代", "英伟达 GTX 1050", "16GB", "2TB").introduce();
        dellFactory.createLaptop("英特尔 i3 10代", "英伟达 GTX 1150", "32GB", "3TB").introduce();
    }
}