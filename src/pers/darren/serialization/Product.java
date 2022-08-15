package pers.darren.serialization;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品信息
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Aug 7, 2019 5:38:02 PM
 */
public class Product implements Serializable {
    /**
     * <pre>
     * 为什么要显式指定serialVersionUID的值?
     * 1、如果不显式指定serialVersionUID, JVM在序列化时会根据属性自动生成一个serialVersionUID, 然后与属性一起序列化, 再进行持久化或网络传输. 在反序列化时, JVM会再根据属性自动生成一个新版serialVersionUID, 然后将这个新版serialVersionUID与序列化时生成的旧版serialVersionUID进行比较, 如果相同则反序列化成功, 否则报错.
     * 2、如果显式指定了serialVersionUID, JVM在序列化和反序列化时仍然都会生成一个serialVersionUID, 但值为我们显式指定的值, 这样在反序列化时新旧版本的serialVersionUID就一致了.
     * 3、在实际开发中, 不显式指定serialVersionUID的情况会导致什么问题? 如果我们的类写完后不再修改, 那当然不会有问题, 但这在实际开发中是不可能的, 我们的类会不断迭代, 一旦类被修改了, 那旧对象反序列化就会报错. 所以在实际开发中, 我们都会显式指定一个serialVersionUID, 值是多少无所谓, 只要不变就行.
     * </pre>
     */
    @Serial
    private static final long serialVersionUID = 7241915439741270646L;
    /**
     * <pre>
     * static修饰的字段，不会被序列化
     *
     * 1、static属性为什么不会被序列化?
     * 因为序列化是针对对象而言的, 而static属性优先于对象存在, 随着类的加载而加载, 所以不会被序列化.
     * 2、serialVersionUID也被static修饰, 为什么serialVersionUID会被序列化?
     * 其实serialVersionUID属性并没有被序列化, JVM在序列化对象时会自动生成一个serialVersionUID, 然后将我们显式指定的serialVersionUID属性值赋给自动生成的serialVersionUID.
     * </pre>
     */
    public static final String defaultUser = "Darren Luo";

    private String name;

    private String category;

    private String supplier;

    private int quantity;

    private BigDecimal price;

    private boolean featured;

    private String description;
    /**
     * 创建人
     * transient修饰的字段，不会被序列化
     */
    private transient String createdBy;
    /**
     * 创建时间
     * transient修饰的字段，不会被序列化
     */
    private transient Date createdTime;
    /**
     * 修改人
     * transient修饰的字段，不会被序列化
     */
    private transient String modifiedBy;
    /**
     * 修改时间
     * transient修饰的字段，不会被序列化
     */
    private transient Date modifiedTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", category=" + category + ", supplier=" + supplier + ", quantity=" + quantity + ", price=" + price + ", featured=" + featured + ", description=" + description + ", createdBy=" + createdBy + ", createdTime=" + createdTime + ", modifiedBy=" + modifiedBy + ", modifiedTime=" + modifiedTime + "]";
    }
}