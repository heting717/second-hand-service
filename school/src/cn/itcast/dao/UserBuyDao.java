package cn.itcast.dao;

import java.util.Date;
import java.util.List;

import cn.itcast.entity.BuyMessage;
import cn.itcast.entity.Status;

public interface UserBuyDao {
	//��ӱ�������
	void save(BuyMessage buyMessage);
	//��ȡ��������
	List<BuyMessage> getAll(int userId);
	
	//��ȡ��������
	List<BuyMessage> getAll();
	
	//����id��������
	BuyMessage findById(int id);
	//�������id��������
	List<BuyMessage> findByTypeId(int typeId);
	
	//�޸�
	void update(int type_id,String brandName,String priceRequest,String requireDescribe,Date upTime,int id);
	//ɾ��
	void delete(int id);
	//���ݽ��״̬����
	List<BuyMessage> findByStatus(int userId,int status);
	
	
	
}
