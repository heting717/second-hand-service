package cn.itcast.service.impl;

import java.util.Date;
import java.util.List;

import cn.itcast.dao.UserBuyDao;
import cn.itcast.entity.BuyMessage;
import cn.itcast.entity.Status;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.UserBuyService;

public class UserBuyServiceImpl implements UserBuyService{

	
	private UserBuyDao userBuyDaoImpl = BeanFactory.getInstance("userBuyDaoImpl", UserBuyDao.class);
	@Override
	public void save(BuyMessage buyMessage) {
		try {
			userBuyDaoImpl.save(buyMessage);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<BuyMessage> getAll(int userId) {
		try {
			return userBuyDaoImpl.getAll(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public BuyMessage findById(int id) {
		try {
			return userBuyDaoImpl.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void update(int type_id, String brandName, String priceRequest,
			String requireDescribe, Date upTime, int id) {
		try {
			userBuyDaoImpl.update(type_id, brandName, priceRequest, requireDescribe, upTime, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void delete(int id) {
		try {
			userBuyDaoImpl.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<BuyMessage> findNoSolve(int userId) {
		try {
			return userBuyDaoImpl.findByStatus(userId, 0);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<BuyMessage> findSolve(int userId) {
		try {
			return userBuyDaoImpl.findByStatus(userId,1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<BuyMessage> getAll() {
		
		try {
			return userBuyDaoImpl.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<BuyMessage> findByTypeId(int typeId) {
		try {
			return userBuyDaoImpl.findByTypeId(typeId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	

}
