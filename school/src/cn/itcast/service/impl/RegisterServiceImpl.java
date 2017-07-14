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
				throw new UserExistException("���û��Ѿ����ڣ����������룡");
			}
			registerDaoImpl.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
