package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.MainType;

public interface MainTypeManageService {
	//添加并保存数据
	void save(String mainTypeName);
	//查询得到所有类别信息
	List<MainType> getAll();
	//根据id查询主类别
	MainType findById(int id);
	//根据id删除
	void delete(int id);
	//修改
	void update(String mainTypeName,int id);
}
