package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.AllType;
import cn.itcast.entity.ShowType;

public interface TypeManageService {
	//添加并保存数据
	void save(int mainType_id,String typeName);
	//查询得到所有类别信息
	List<ShowType> getAll();
	//根据id查询主类别
	AllType findById(int id);
	//根据主类别查询
	List<AllType> findByMainType(int mainTypeId);
	
	
		
	//根据id删除
	void delete(int id);
	//修改
	void update(int mainType_id,String typeName,int id);
}
