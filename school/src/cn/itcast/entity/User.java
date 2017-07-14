package cn.itcast.entity;

import java.util.Date;

public class User {
	
	private int id;//主键
	private String phone;//电话号码
	private String mail;//邮箱
	private String userName;//用户名
	private String userPassword;//密码
	private String school;//学校
	private String inTime;//入学时间
	private String outTime;//离校时间
	private String imagePath;//图片路径
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", phone=" + phone + ", mail=" + mail
				+ ", userName=" + userName + ", userPassword=" + userPassword
				+ ", school=" + school + ", inTime=" + inTime + ", outTime="
				+ outTime + ", imagePath=" + imagePath + "]";
	}
	
	
	
}
