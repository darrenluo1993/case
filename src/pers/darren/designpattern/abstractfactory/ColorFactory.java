package pers.darren.designpattern.abstractfactory;

/**
 * 颜色工厂
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 5:28:46 PM
 */
class ColorFactory extends AbstractFactoryAdapter {

    @Override
    Color getColor(final ColorType colorType) {
        if (colorType == null) {
            return new NullColor();
        }
        try {
            return (Color) Class.forName(colorType.getClassName()).newInstance();
        } catch (final InstantiationException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new NullColor();
    }
}