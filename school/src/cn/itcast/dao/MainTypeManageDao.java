package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.MainType;

public interface MainTypeManageDao {
	//��Ӳ���������
	void save(String mainTypeName);
	//��ѯ�õ����������Ϣ
	List<MainType> getAll();
	//����id��ѯ�����
	MainType findById(int id);
	//����idɾ��
	void delete(int id);
	//�޸�
	void update(String mainTypeName,int id);
	
	
	
}
