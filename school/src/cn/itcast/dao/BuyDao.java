package cn.itcast.dao;

import java.util.Date;
import java.util.List;

import cn.itcast.entity.UserBuy;

public interface BuyDao {
	//���򣬱�����Ϣ
	void saveBuy(int userId,int sellId,int buyCount,String buyRemark,Date buyTime);
	//��ѯ��������
	List<UserBuy> getAll(int userId);
}
