package cn.itcast.entity;

import java.util.Date;

public class SellMessage {
	
	private int id;//主键
	private int userId;//用户id，外键
	private String mail;//邮箱
	private int type_id;//类别id，外键
	private String typeName;//类别名称
	private String sellName;//物品名称
	private String brandName;//品牌
	private String buyTime;//购买时间
	private String buyTimeYear;
	private String buyTimeMouth;
	private int buyPrice;//购买价格
	private int sellPrice;//出售价格
	private int sellCount;//出售数量
	private String goodsDescribe;//物品描述
	private Date upTime;//上传时间
	private int sellStatus;//出售状态
	private String imagePath;//图片路径
	
	
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
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	
	public String getBuyTimeYear() {
		return buyTimeYear;
	}
	public void setBuyTimeYear(String buyTimeYear) {
		this.buyTimeYear = buyTimeYear;
	}
	public String getBuyTimeMouth() {
		return buyTimeMouth;
	}
	public void setBuyTimeMouth(String buyTimeMouth) {
		this.buyTimeMouth = buyTimeMouth;
	}
	public int getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
	public int getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public int getSellCount() {
		return sellCount;
	}
	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
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
	
	public int getSellStatus() {
		return sellStatus;
	}
	public void setSellStatus(int sellStatus) {
		this.sellStatus = sellStatus;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return "SellMessage [id=" + id + ", userId=" + userId + ", mail="
				+ mail + ", type_id=" + type_id + ", typeName=" + typeName
				+ ", sellName=" + sellName + ", brandName=" + brandName
				+ ", buyTime=" + buyTime + ", buyTimeYear=" + buyTimeYear
				+ ", buyTimeMouth=" + buyTimeMouth + ", buyPrice=" + buyPrice
				+ ", sellPrice=" + sellPrice + ", sellCount=" + sellCount
				+ ", goodsDescribe=" + goodsDescribe + ", upTime=" + upTime
				+ ", sellStatus=" + sellStatus + ", imagePath=" + imagePath
				+ "]";
	}
	
	
	
	
}
