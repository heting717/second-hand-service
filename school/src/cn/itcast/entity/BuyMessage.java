package cn.itcast.entity;

import java.util.Date;

public class BuyMessage {
	
	private int id;//����
	private int userId;//�û�id
	private String mail;//����
	private int type_id;//���id
	
	private String typeName;//�������
	
	private String brandName;//��Ʒ����
	private String priceRequest;//�۸�Ҫ��
	private String requireDescribe;//Ҫ��
	private Date upTime;
	private int buyStatus;//״̬
	
	
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
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getPriceRequest() {
		return priceRequest;
	}
	public void setPriceRequest(String priceRequest) {
		this.priceRequest = priceRequest;
	}
	public String getRequireDescribe() {
		return requireDescribe;
	}
	public void setRequireDescribe(String requireDescribe) {
		this.requireDescribe = requireDescribe;
	}
	public Date getUpTime() {
		return upTime;
	}
	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}
	
	public int getBuyStatus() {
		return buyStatus;
	}
	public void setBuyStatus(int buyStatus) {
		this.buyStatus = buyStatus;
	}
	@Override
	public String toString() {
		return "BuyMessage [id=" + id + ", userId=" + userId + ", mail=" + mail
				+ ", type_id=" + type_id + ", typeName=" + typeName
				+ ", brandName=" + brandName + ", priceRequest=" + priceRequest
				+ ", requireDescribe=" + requireDescribe + ", upTime=" + upTime
				+ ", buyStatus=" + buyStatus + "]";
	}
	
	
	
}
