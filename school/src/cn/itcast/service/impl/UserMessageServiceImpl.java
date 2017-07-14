package cn.itcast.service.impl;

import cn.itcast.dao.UserMessageDao;
import cn.itcast.entity.User;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.UserMessageService;

public class UserMessageServiceImpl implements UserMessageService{
	
	private UserMessageDao userMessageDaoImpl = BeanFactory.getInstance("userMessageDaoImpl", UserMessageDao.class);
	@Override//查看个人信息
	public User findById(int id) {
		try {
			return userMessageDaoImpl.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//修改个人信息
	public void update(User user) {
		try {
			userMessageDaoImpl.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//修改密码
	public void updatePwd(String pwd,int id) {
		try {
			userMessageDaoImpl.updatePwd(pwd,id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	

}
