package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.CollectMessage;

public interface CollectService {

	//保存收藏
	void save(int userId,int sellId);
	//根据用户id查找所有该用户的收藏数据
	List<CollectMessage> getAll(int userId);
	//删除
	void delete(int id);
}
