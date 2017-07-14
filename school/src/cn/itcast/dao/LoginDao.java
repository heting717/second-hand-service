package cn.itcast.dao;

import cn.itcast.entity.User;

public interface LoginDao {
	//根据电话号码和密码查找
	User findByPhoneAndPwd(String phone,String passWord);
}
