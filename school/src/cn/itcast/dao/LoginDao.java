package cn.itcast.dao;

import cn.itcast.entity.User;

public interface LoginDao {
	//���ݵ绰������������
	User findByPhoneAndPwd(String phone,String passWord);
}
