package pers.darren.designpattern.abstractfactory1;

/**
 * 计算机工厂创造器
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 24, 2020 4:34:21 PM
 */
class FactoryProducer {
    /**
     * 创建计算机工厂
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 24, 2020 4:34:31 PM
     * @param factoryType 工厂类型
     * @return
     */
    static ComputerFactory createFactory(final FactoryType factoryType) {
        if (factoryType == null) {
            return null;
        }
        try {
            return (ComputerFactory) Class.forName(factoryType.getClassName()).newInstance();
        } catch (final InstantiationException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}