package cn.itcast.service.impl;

import cn.itcast.dao.LoginDao;
import cn.itcast.entity.User;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.LoginService;

public class LoginServiceImpl implements LoginService{
	LoginDao loginDaoImpl = BeanFactory.getInstance("loginDaoImpl", LoginDao.class);
	@Override
	public User findByPhoneAndPwd(String phone, String passWord) {
		try {
			return loginDaoImpl.findByPhoneAndPwd(phone, passWord);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
