package pers.darren.designpattern.factory;

/**
 * 图形工厂
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Sep 23, 2020 2:54:33 PM
 */
public class ShapeFactory {
    /**
     * 生产图形对象
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Sep 23, 2020 3:49:33 PM
     * @param shapeType 图形类型
     * @return
     */
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