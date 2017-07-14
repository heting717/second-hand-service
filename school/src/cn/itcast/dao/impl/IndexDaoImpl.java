package cn.itcast.dao.impl;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.dao.IndexDao;
import cn.itcast.entity.AllType;
import cn.itcast.entity.BuyMessage;
import cn.itcast.entity.MainType;
import cn.itcast.entity.SellMessage;
import cn.itcast.util.Condition;
import cn.itcast.util.JdbcUtils;
import cn.itcast.util.PageBean;

public class IndexDaoImpl implements IndexDao{

	@Override//���ҵ������
	public List<MainType> findMainType() {
		String sql = "SELECT *FROM mainType";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<MainType>(MainType.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//����������ѯ�������
	public List<AllType> findType(int mainTypeId) {
		String sql = "SELECT *FROM typeTable WHERE mainType_id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<AllType>(AllType.class),mainTypeId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//���������ҳ�������,��δ���۵�����
	public List<SellMessage> findByType(int typeId) {
		String sql = "SELECT *FROM sellTable WHERE type_id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<SellMessage>(SellMessage.class),typeId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	//�����ܼ�¼��,����
	@Override
	public int getTotalCount(PageBean<SellMessage> pb) {
		
		Condition condition = pb.getCondition();
		int typeId = condition.getTypeId();
		System.out.println("typeid:"+typeId);
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT");
		sb.append(" COUNT(*)");
		sb.append(" FROM");
		sb.append(" sellTable");
		sb.append(" WHERE");
		sb.append(" sellCount>0");
		List<Object> list = new ArrayList<Object>(); 
		if(typeId>0){
			sb.append(" AND type_id=?");
			list.add(typeId);
		}
		
		try {
			Long num = JdbcUtils.getQueryRunner().query(sb.toString(),new ScalarHandler<Long>(),list.toArray());
			return num.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	//���������ҳ������ݣ���ҳ
	@Override
	public void getAll(PageBean<SellMessage> pb) {
		Condition condition = pb.getCondition();
		int typeId = condition.getTypeId();
		System.out.println("typeid:"+typeId);
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT");
		sb.append(" *FROM");
		sb.append(" sellTable");
		sb.append(" WHERE");
		sb.append(" sellCount>0");
		List<Object> list = new ArrayList<Object>(); 
		if(typeId>0){
			sb.append(" AND type_id=?");
			list.add(typeId);
		}
		//��ҳ����
		sb.append(" LIMIT ?,?");
		//�ܼ�¼��
		int totalCount = getTotalCount(pb);
		System.out.println("totalCount:"+totalCount);
		
		pb.setTotalCount(totalCount);
		
		System.out.println("��ǰҳ��"+pb.getCurrentPage());
		System.out.println("��ҳ����"+pb.getTotalPage());
		//�жϣ���ǰҳ<1��������Ϊ1����ǰҳ>��ҳ����������Ϊ��ҳ��
		if(pb.getCurrentPage()<1){
			pb.setCurrentPage(1);
		}else if(pb.getCurrentPage()>pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());
		}
		System.out.println("��ǰҳ��"+pb.getCurrentPage());
		//��ʼ��
		int index;
		if(totalCount==0){
			index = 0;
		}else{
			index = (pb.getCurrentPage()-1)*pb.getPageCount();
		}
		//����
		int num = pb.getPageCount();		
		System.out.println("index:"+index+"num:"+num);
		list.add(index);
		list.add(num);		
		try {
			List<SellMessage> pageData = JdbcUtils.getQueryRunner().query(sb.toString(), new BeanListHandler<SellMessage>(SellMessage.class), list.toArray());
			System.out.println("pagedata"+pageData);
			pb.setPageData(pageData);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	//�����ܼ�¼��,�빺,��ʾδ�����
		@Override
		public int getTotalCountBuy(PageBean<BuyMessage> pb) {
			
			Condition condition = pb.getCondition();
			int typeId = condition.getTypeId();
			System.out.println("typeid:"+typeId);
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT");
			sb.append(" COUNT(*)");
			sb.append(" FROM");
			sb.append(" buyTable");
			sb.append(" WHERE");
			sb.append(" buyStatus=0");
			List<Object> list = new ArrayList<Object>(); 
			if(typeId>0){
				sb.append(" AND type_id=?");
				list.add(typeId);
			}
			
			try {
				Long num = JdbcUtils.getQueryRunner().query(sb.toString(),new ScalarHandler<Long>(),list.toArray());
				return num.intValue();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}

		@Override
		public void getAllBuy(PageBean<BuyMessage> pb) {
			Condition condition = pb.getCondition();
			int typeId = condition.getTypeId();
			System.out.println("typeid:"+typeId);
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT");
			sb.append(" *FROM");
			sb.append(" buyTable");
			sb.append(" WHERE");
			sb.append(" buyStatus=0");
			List<Object> list = new ArrayList<Object>(); 
			if(typeId>0){
				sb.append(" AND type_id=?");
				list.add(typeId);
			}
			//��ҳ����
			sb.append(" LIMIT ?,?");
			//�ܼ�¼��
			int totalCount = getTotalCountBuy(pb);
			System.out.println("totalCount:"+totalCount);
			
			pb.setTotalCount(totalCount);
			
			System.out.println("��ǰҳ��"+pb.getCurrentPage());
			System.out.println("��ҳ����"+pb.getTotalPage());
			//�жϣ���ǰҳ<1��������Ϊ1����ǰҳ>��ҳ����������Ϊ��ҳ��
			if(pb.getCurrentPage()<1){
				pb.setCurrentPage(1);
			}else if(pb.getCurrentPage()>pb.getTotalPage()){
				pb.setCurrentPage(pb.getTotalPage());
			}
			System.out.println("��ǰҳ��"+pb.getCurrentPage());
			//��ʼ��
			int index;
			if(totalCount==0){
				index = 0;
			}else{
				index = (pb.getCurrentPage()-1)*pb.getPageCount();
			}
			//����
			int num = pb.getPageCount();		
			System.out.println("index:"+index+"num:"+num);
			list.add(index);
			list.add(num);		
			try {
				List<BuyMessage> pageData = JdbcUtils.getQueryRunner().query(sb.toString(), new BeanListHandler<BuyMessage>(BuyMessage.class), list.toArray());
				System.out.println("pagedata"+pageData);
				pb.setPageData(pageData);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
	

}
