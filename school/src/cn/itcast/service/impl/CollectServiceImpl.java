package cn.itcast.service.impl;

import java.util.List;

import cn.itcast.dao.CollectDao;
import cn.itcast.entity.CollectMessage;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.CollectService;

public class CollectServiceImpl implements CollectService{

	private CollectDao collectDaoImpl = BeanFactory.getInstance("collectDaoImpl", CollectDao.class);
	@Override
	public void save(int userId, int sellId) {
		try {
			collectDaoImpl.save(userId, sellId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<CollectMessage> getAll(int userId) {
		try {
			return collectDaoImpl.getAll(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void delete(int id) {
		try {
			collectDaoImpl.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
