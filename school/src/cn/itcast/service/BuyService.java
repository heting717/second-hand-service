package cn.itcast.service;

import java.util.Date;
import java.util.List;

import cn.itcast.entity.UserBuy;

public interface BuyService {

	//购买，保存信息
	void saveBuy(int userId,int sellId,int buyCount,String buyRemark,Date buyTime);
	//查询所有数据
	List<UserBuy> getAll(int userId);
}
