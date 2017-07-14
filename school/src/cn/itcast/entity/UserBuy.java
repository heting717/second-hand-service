package cn.itcast.entity;

import java.util.Date;

public class UserBuy {
	
	
	private int id;
	private int userId;//用户id，外键
	private int sellId;//出售信息id,外键
	private int buyCount;//购买数量
	private String buyRemark;//备注颜色大小型号
	
	private String sellName;//物品名称
	private int sellPrice;//出售即购买价格
	
	private Date buyTime;//购买时间
	
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
	public int getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
	public String getBuyRemark() {
		return buyRemark;
	}
	public void setBuyRemark(String buyRemark) {
		this.buyRemark = buyRemark;
	}
	public String getSellName() {
		return sellName;
	}
	public void setSellName(String sellName) {
		this.sellName = sellName;
	}
	public int getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public Date getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}
	@Override
	public String toString() {
		return "UserBuy [id=" + id + ", userId=" + userId + ", sellId="
				+ sellId + ", buyCount=" + buyCount + ", buyRemark="
				+ buyRemark + ", sellName=" + sellName + ", sellPrice="
				+ sellPrice + ", buyTime=" + buyTime + "]";
	}
	
	
}
