package cn.itcast.dao;

import java.util.Date;
import java.util.List;

import cn.itcast.entity.SellMessage;

public interface UserSellDao {
	//得到所有数据
	List<SellMessage> getAll();
	//得到所有数据，当销售数量>0时
	List<SellMessage> getDatasCount();
	//根据用户id查询某人的所有出售情况
	List<SellMessage> getAll(int userId);
	
	//添加，保存数据
	void save(SellMessage sellMessage);
	//查询
	SellMessage findById(int id);
	//根据类别id查询数据
	List<SellMessage> findByTypeId(int typeId);
	//根据类别id查询数据，且出售数量>0
	List<SellMessage> findBytTypeIdAndCount(int typeId);
	/*//修改
	void update( int type_id,String sellName,String brandName,String buyTime,int buyPrice,int sellPrice,int sellCount,String goodsDescribe,Date upTime,String imagePath,int id);
	*/
	//修改
	void update(SellMessage sellMessage);
	//删除
	void delete(int id);
	//根据解决状态查找
	List<SellMessage> findByStatus(int userId,int status);
}
