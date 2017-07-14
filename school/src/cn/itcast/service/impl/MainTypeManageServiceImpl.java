package cn.itcast.service.impl;

import java.util.List;

import cn.itcast.dao.MainTypeManageDao;
import cn.itcast.entity.MainType;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.MainTypeManageService;

public class MainTypeManageServiceImpl implements MainTypeManageService{
	private MainTypeManageDao mainTypeManageDaoImpl =BeanFactory.getInstance("mainTypeManageDaoImpl", MainTypeManageDao.class);
	@Override
	public void save(String mainTypeName) {
		try {
			mainTypeManageDaoImpl.save(mainTypeName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<MainType> getAll() {
		try {
			return mainTypeManageDaoImpl.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public MainType findById(int id) {
		try {
			return mainTypeManageDaoImpl.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void delete(int id) {
		try {
			mainTypeManageDaoImpl.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void update(String mainTypeName, int id) {
		try {
			mainTypeManageDaoImpl.update(mainTypeName, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
