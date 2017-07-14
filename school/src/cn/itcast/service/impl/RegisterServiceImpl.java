package cn.itcast.service.impl;

import cn.itcast.dao.RegisterDao;
import cn.itcast.entity.User;
import cn.itcast.exception.UserExistException;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.RegisterService;

public class RegisterServiceImpl implements RegisterService{
	
	
	private RegisterDao registerDaoImpl = BeanFactory.getInstance("registerDaoImpl", RegisterDao.class);
	@Override
	public void register(User user) throws UserExistException{
		try {
			boolean flag = registerDaoImpl.userExsits(user.getPhone());
			if(flag){
				throw new UserExistException("该用户已经存在，请重新输入！");
			}
			registerDaoImpl.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
