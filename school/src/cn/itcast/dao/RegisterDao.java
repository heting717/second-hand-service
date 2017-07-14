package cn.itcast.dao;

import cn.itcast.entity.User;

public interface RegisterDao {
	//注册用户保存信息
	void save(User user);
	//判断用户电话号码判断用户是否已经存在
	boolean userExsits(String phone);
	
}
