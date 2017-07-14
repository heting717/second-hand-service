package cn.itcast.service.impl;

import java.util.Date;
import java.util.List;

import cn.itcast.dao.BuyDao;
import cn.itcast.entity.UserBuy;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.BuyService;

public class BuyServiceImpl implements BuyService{

	
	private BuyDao buyDaoImpl = BeanFactory.getInstance("buyDaoImpl", BuyDao.class);
	
	@Override
	public void saveBuy(int userId, int sellId, int buyCount, String buyRemark,Date buyTime) {
		try {
			buyDaoImpl.saveBuy(userId, sellId, buyCount, buyRemark,buyTime);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public List<UserBuy> getAll(int userId) {
		try {
			return buyDaoImpl.getAll(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
