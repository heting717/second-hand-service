package cn.itcast.service;

import java.util.Date;
import java.util.List;

import cn.itcast.entity.UserBuy;

public interface BuyService {

	//���򣬱�����Ϣ
	void saveBuy(int userId,int sellId,int buyCount,String buyRemark,Date buyTime);
	//��ѯ��������
	List<UserBuy> getAll(int userId);
}
