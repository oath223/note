package com.iktz.mybatis;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class ProductSku implements Serializable{
	private static final long serialVersionUID = 4977099325828469982L;
	
	private BigInteger skuId;
	private BigInteger productId;
	private String skuTitle;//显示标题
	private String skuDesc;//描述
	private int color;
	private  int size;
	private String descript;
	private Date addTime;
	private Date  updateTime;
	private int   addUser;
	

	public String getSkuTitle() {
		return skuTitle;
	}
	public void setSkuTitle(String skuTitle) {
		this.skuTitle = skuTitle;
	}
	public String getSkuDesc() {
		return skuDesc;
	}
	public void setSkuDesc(String skuDesc) {
		this.skuDesc = skuDesc;
	}
	
	public BigInteger getSkuId() {
		return skuId;
	}
	public void setSkuId(BigInteger skuId) {
		this.skuId = skuId;
	}
	public BigInteger getProductId() {
		return productId;
	}
	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getAddUser() {
		return addUser;
	}
	public void setAddUser(int addUser) {
		this.addUser = addUser;
	}
}
