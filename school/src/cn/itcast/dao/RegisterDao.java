package cn.itcast.dao;

import cn.itcast.entity.User;

public interface RegisterDao {
	//ע���û�������Ϣ
	void save(User user);
	//�ж��û��绰�����ж��û��Ƿ��Ѿ�����
	boolean userExsits(String phone);
	
}
