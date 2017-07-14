package cn.itcast.service;

import java.util.Date;
import java.util.List;

import cn.itcast.entity.BuyMessage;
import cn.itcast.entity.Status;

public interface UserBuyService {
	//��ӱ�������
	void save(BuyMessage buyMessage);
	//��ȡ��������
	List<BuyMessage> getAll(int userId);
	
	//��ȡ��������
	List<BuyMessage> getAll();
	
	//�������id��������
	List<BuyMessage> findByTypeId(int typeId);
	
	//����id��������
	BuyMessage findById(int id);
	//�޸�
	void update(int type_id,String brandName,String priceRequest,String requireDescribe,Date upTime,int id);
	//ɾ��
	void delete(int id);
	//���ݽ��״̬�����Ѿ������
	List<BuyMessage> findNoSolve(int userId);
	//���ݽ��״̬����û�н����
	List<BuyMessage> findSolve(int userId);
	
}
