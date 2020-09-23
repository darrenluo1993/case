package pers.darren.designpattern.abstractfactory;

/**
 * 工厂创造器/生成器
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 5:51:27 PM
 */
class FactoryProducer {
    /**
     * 生产工厂对象
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 23, 2020 5:51:54 PM
     * @param factoryType 工厂类型
     * @return
     */
    static AbstractFactory getFactory(final FactoryType factoryType) {
        if (factoryType == null) {
            return new NullFactory();
        }
        try {
            return (AbstractFactory) Class.forName(factoryType.getClassName()).newInstance();
        } catch (final InstantiationException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new NullFactory();
    }
}