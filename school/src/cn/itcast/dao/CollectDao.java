package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.CollectMessage;

public interface CollectDao {
	
	//�����ղ�
	void save(int userId,int sellId);
	//�����û�id�������и��û����ղ�����
	List<CollectMessage> getAll(int userId);
	//ɾ��
	void delete(int id);
}
