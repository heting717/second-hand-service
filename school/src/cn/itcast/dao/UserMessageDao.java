package cn.itcast.dao;

import cn.itcast.entity.User;

public interface UserMessageDao {
	//�鿴������Ϣ
	User findById(int id);
	//�޸ĸ�����Ϣ
	void update(User user);
	//�޸�����
	void updatePwd(String pwd,int id);
	
}
