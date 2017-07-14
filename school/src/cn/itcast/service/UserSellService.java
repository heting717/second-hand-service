package cn.itcast.service;

import java.util.Date;
import java.util.List;

import cn.itcast.entity.SellMessage;

public interface UserSellService {
	//�õ���������
	List<SellMessage> getAll();
	//�õ��������ݣ�����������>0ʱ
	List<SellMessage> getDatasCount();
	
	//�����û�id��ѯĳ�˵����г������
	List<SellMessage> getAll(int userId);
	//��ӣ���������
	void save(SellMessage sellMessage);
	//��ѯ
	SellMessage findById(int id);
	//�������id��ѯ����
	List<SellMessage> findByTypeId(int typeId);
	//�������id��ѯ���ݣ��ҳ�������>0
	List<SellMessage> findBytTypeIdAndCount(int typeId);
	
	/*//�޸�
	void update( int type_id,String sellName,String brandName,String buyTime,int buyPrice,int sellPrice,int sellCount,String goodsDescribe,Date upTime,int id);
	*/
	//�޸�
	void update(SellMessage sellMessage);
	//ɾ��
	void delete(int id);
	//���ݳ���״̬����δ���۵�
	List<SellMessage> findNoSell(int userId);
	//���ݳ���״̬�����Ѿ����۵�
	List<SellMessage> findSell(int userId);
}
