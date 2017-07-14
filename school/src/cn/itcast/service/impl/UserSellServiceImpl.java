package cn.itcast.service.impl;

import java.util.Date;
import java.util.List;

import cn.itcast.dao.UserSellDao;
import cn.itcast.entity.SellMessage;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.UserSellService;

public class UserSellServiceImpl implements UserSellService{
	
	
	private UserSellDao userSellDaoImpl = BeanFactory.getInstance("userSellDaoImpl", UserSellDao.class);
	
	
	@Override
	public List<SellMessage> getAll() {
		try {
			return userSellDaoImpl.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw  new RuntimeException();
		}
	}
	@Override
	public List<SellMessage> getDatasCount() {
		try {
			return userSellDaoImpl.getDatasCount();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public List<SellMessage> getAll(int userId) {
		try {
			return userSellDaoImpl.getAll(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public void save(SellMessage sellMessage) {
		try {
			userSellDaoImpl.save(sellMessage);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public SellMessage findById(int id) {
		try {
			return userSellDaoImpl.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


	@Override
	public void delete(int id) {
		try {
			userSellDaoImpl.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public List<SellMessage> findNoSell(int userId) {
		try {
			return userSellDaoImpl.findByStatus(userId, 0);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public List<SellMessage> findSell(int userId) {
		try {
			return userSellDaoImpl.findByStatus(userId, 1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public List<SellMessage> findByTypeId(int typeId) {
		try {
			return userSellDaoImpl.findByTypeId(typeId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public List<SellMessage> findBytTypeIdAndCount(int typeId) {
		try {
			return userSellDaoImpl.findBytTypeIdAndCount(typeId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public void update(SellMessage sellMessage) {
		try {
			userSellDaoImpl.update(sellMessage);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	

	

}
