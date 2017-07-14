package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.AllType;
import cn.itcast.entity.ShowType;

public interface TypeManageService {
	//��Ӳ���������
	void save(int mainType_id,String typeName);
	//��ѯ�õ����������Ϣ
	List<ShowType> getAll();
	//����id��ѯ�����
	AllType findById(int id);
	//����������ѯ
	List<AllType> findByMainType(int mainTypeId);
	
	
		
	//����idɾ��
	void delete(int id);
	//�޸�
	void update(int mainType_id,String typeName,int id);
}
