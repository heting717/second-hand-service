package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.MainType;

public interface MainTypeManageService {
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
