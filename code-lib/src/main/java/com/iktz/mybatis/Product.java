package com.iktz.mybatis;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


public class Product implements Serializable{

/*	product_id bigint,
    descript varchar(300),
    fabric_id int comment '面料id',
	gender int comment '性别',
    area_code int, 
    add_time date,
    update_time date,
    add_user int	
*/	
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", descript=" + descript + ", fabricId=" + fabricId + ", gender="
				+ gender + ", areaCode=" + areaCode + ", updateTime=" + updateTime + ", addTime=" + addTime
				+ ", addUser=" + addUser + ", skus=" + skus + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -7344162463754920423L;
	private BigInteger productId;
	private String descript;
	private int fabricId;
	private int gender;
	private int areaCode;
	private Date updateTime;
	private Date addTime;
	private int addUser;
	private List<ProductSku> skus;

	public List<ProductSku> getSkus() {
		return skus;
	}
	public void setSkus(List<ProductSku> skus) {
		this.skus = skus;
	}
	public BigInteger getProductId() {
		return productId;
	}
	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public int getFabricId() {
		return fabricId;
	}
	public void setFabricId(int fabricId) {
		this.fabricId = fabricId;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getAddUser() {
		return addUser;
	}
	public void setAddUser(int addUser) {
		this.addUser = addUser;
	}
	
	
}
