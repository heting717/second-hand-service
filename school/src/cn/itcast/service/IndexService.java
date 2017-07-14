package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.AllType;
import cn.itcast.entity.BuyMessage;
import cn.itcast.entity.MainType;
import cn.itcast.entity.SellMessage;
import cn.itcast.util.PageBean;

public interface IndexService {
	//���ҵ������
	List<MainType> findMainType();
	//����������ѯ�������
	List<AllType> findType(int mainTypeId);
	//���������ҳ�������,��δ���۵�����
	List<SellMessage> findByType(int typeId);
	//���������ҳ������ݣ���ҳ
	void getAll(PageBean<SellMessage> pb);
	//�����������빺���ݣ���ҳ
	void getAllBuy(PageBean<BuyMessage> pb);
}
