package cn.itcast.service.impl;

import java.util.List;

import cn.itcast.dao.IndexDao;
import cn.itcast.entity.AllType;
import cn.itcast.entity.BuyMessage;
import cn.itcast.entity.MainType;
import cn.itcast.entity.SellMessage;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IndexService;
import cn.itcast.util.PageBean;

public class IndexServcieImpl implements IndexService{

	
	private IndexDao indexDaoImpl = BeanFactory.getInstance("indexDaoImpl", IndexDao.class);
	@Override
	public List<MainType> findMainType() {
		try {
			return indexDaoImpl.findMainType();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<AllType> findType(int mainTypeId) {
		try {
			return indexDaoImpl.findType(mainTypeId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<SellMessage> findByType(int typeId) {
		try {
			return indexDaoImpl.findByType(typeId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void getAll(PageBean<SellMessage> pb) {
		
		try {
			indexDaoImpl.getAll(pb);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void getAllBuy(PageBean<BuyMessage> pb) {
		try {
			indexDaoImpl.getAllBuy(pb);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
