package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.CollectMessage;

public interface CollectService {

	//�����ղ�
	void save(int userId,int sellId);
	//�����û�id�������и��û����ղ�����
	List<CollectMessage> getAll(int userId);
	//ɾ��
	void delete(int id);
}
