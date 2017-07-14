package cn.itcast.entity;

import java.util.Date;

public class CollectMessage {
	private int id;//主键
	private int userId;//用户id，外键
	private int sellId;//出售表id，外键
	private String sellName;//物品名称
	private String brandName;//品牌
	private int sellPrice;//出售价格
	private String goodsDescribe;//物品描述
	private Date upTime;//上传时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSellId() {
		return sellId;
	}
	public void setSellId(int sellId) {
		this.sellId = sellId;
	}
	public String getSellName() {
		return sellName;
	}
	public void setSellName(String sellName) {
		this.sellName = sellName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public int getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getGoodsDescribe() {
		return goodsDescribe;
	}
	public void setGoodsDescribe(String goodsDescribe) {
		this.goodsDescribe = goodsDescribe;
	}
	public Date getUpTime() {
		return upTime;
	}
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}
	@Override
	public String toString() {
		return "CollectMessage [id=" + id + ", userId=" + userId + ", sellId="
				+ sellId + ", sellName=" + sellName + ", brandName="
				+ brandName + ", sellPrice=" + sellPrice + ", goodsDescribe="
				+ goodsDescribe + ", upTime=" + upTime + "]";
	}
	
}
