package cn.itcast.dao;

import cn.itcast.entity.User;

public interface UserMessageDao {
	//查看个人信息
	User findById(int id);
	//修改个人信息
	void update(User user);
	//修改密码
	void updatePwd(String pwd,int id);
	
}
