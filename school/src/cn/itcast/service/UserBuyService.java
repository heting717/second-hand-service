package cn.itcast.service;

import java.util.Date;
import java.util.List;

import cn.itcast.entity.BuyMessage;
import cn.itcast.entity.Status;

public interface UserBuyService {
	//添加保存数据
	void save(BuyMessage buyMessage);
	//获取所有数据
	List<BuyMessage> getAll(int userId);
	
	//获取所有数据
	List<BuyMessage> getAll();
	
	//根据类别id查找数据
	List<BuyMessage> findByTypeId(int typeId);
	
	//根据id查找数据
	BuyMessage findById(int id);
	//修改
	void update(int type_id,String brandName,String priceRequest,String requireDescribe,Date upTime,int id);
	//删除
	void delete(int id);
	//根据解决状态查找已经解决的
	List<BuyMessage> findNoSolve(int userId);
	//根据解决状态查找没有解决的
	List<BuyMessage> findSolve(int userId);
	
}
