package pers.darren.designpattern.abstractfactory;

/**
 * 图形工厂
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 4:58:18 PM
 */
class ShapeFactory extends AbstractFactoryAdapter {

    @Override
    public Shape getShape(final ShapeType shapeType) {
        if (shapeType == null) {
            return new NullShape();
        }
        try {
            return (Shape) Class.forName(shapeType.getClassName()).newInstance();
        } catch (final InstantiationException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new NullShape();
    }
}