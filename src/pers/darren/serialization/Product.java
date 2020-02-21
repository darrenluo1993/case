package pers.darren.serialization;

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

	private static final long serialVersionUID = 7241915439741270646L;

	public static final String defaultUser = "Darren Luo";

	private String name;

	private String category;

	private String supplier;

	private int quantity;

	private BigDecimal price;

	private boolean featured;

	private String description;

	private transient String createdBy;

	private transient Date createdTime;

	private transient String modifiedBy;

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
		return "Product [name=" + name + ", category=" + category + ", supplier=" + supplier + ", quantity=" + quantity
				+ ", price=" + price + ", featured=" + featured + ", description=" + description + ", createdBy="
				+ createdBy + ", createdTime=" + createdTime + ", modifiedBy=" + modifiedBy + ", modifiedTime="
				+ modifiedTime + "]";
	}
}