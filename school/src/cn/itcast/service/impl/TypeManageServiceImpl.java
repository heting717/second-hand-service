package cn.itcast.service.impl;

import java.util.List;

import cn.itcast.dao.TypeManageDao;
import cn.itcast.entity.AllType;
import cn.itcast.entity.ShowType;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.TypeManageService;

public class TypeManageServiceImpl implements TypeManageService{

	
	private TypeManageDao typeManageDaoImpl = BeanFactory.getInstance("typeManageDaoImpl", TypeManageDao.class);
	@Override
	public void save(int mainType_id, String typeName) {
		try {
			typeManageDaoImpl.save(mainType_id, typeName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<ShowType> getAll() {
		try {
			return typeManageDaoImpl.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public AllType findById(int id) {
		try {
			return typeManageDaoImpl.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void delete(int id) {
		try {
			typeManageDaoImpl.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void update(int mainType_id, String typeName, int id) {
		try {
			typeManageDaoImpl.update(mainType_id, typeName, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<AllType> findByMainType(int mainTypeId) {
		try {
			return typeManageDaoImpl.findByMainType(mainTypeId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}


}
