package cn.itcast.service;

import cn.itcast.entity.User;

public interface LoginService {
	User findByPhoneAndPwd(String phone,String passWord);
}
