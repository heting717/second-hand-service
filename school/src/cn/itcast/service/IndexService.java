package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.AllType;
import cn.itcast.entity.BuyMessage;
import cn.itcast.entity.MainType;
import cn.itcast.entity.SellMessage;
import cn.itcast.util.PageBean;

public interface IndexService {
	//查找到主类别
	List<MainType> findMainType();
	//根据主类别查询到次类别
	List<AllType> findType(int mainTypeId);
	//根据类别查找出售数据,还未出售的数据
	List<SellMessage> findByType(int typeId);
	//根据类别查找出售数据，分页
	void getAll(PageBean<SellMessage> pb);
	//根据类别查找想购数据，分页
	void getAllBuy(PageBean<BuyMessage> pb);
}
