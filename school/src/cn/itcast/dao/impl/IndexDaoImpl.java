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

	@Override//查找到主类别
	public List<MainType> findMainType() {
		String sql = "SELECT *FROM mainType";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<MainType>(MainType.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//根据主类别查询到次类别
	public List<AllType> findType(int mainTypeId) {
		String sql = "SELECT *FROM typeTable WHERE mainType_id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<AllType>(AllType.class),mainTypeId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//根据类别查找出售数据,还未出售的数据
	public List<SellMessage> findByType(int typeId) {
		String sql = "SELECT *FROM sellTable WHERE type_id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<SellMessage>(SellMessage.class),typeId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	//计算总记录数,出售
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
	//根据类别查找出售数据，分页
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
		//分页条件
		sb.append(" LIMIT ?,?");
		//总记录数
		int totalCount = getTotalCount(pb);
		System.out.println("totalCount:"+totalCount);
		
		pb.setTotalCount(totalCount);
		
		System.out.println("当前页："+pb.getCurrentPage());
		System.out.println("总页数："+pb.getTotalPage());
		//判断，当前页<1，则设置为1，当前页>总页数，则设置为总页数
		if(pb.getCurrentPage()<1){
			pb.setCurrentPage(1);
		}else if(pb.getCurrentPage()>pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());
		}
		System.out.println("当前页："+pb.getCurrentPage());
		//起始行
		int index;
		if(totalCount==0){
			index = 0;
		}else{
			index = (pb.getCurrentPage()-1)*pb.getPageCount();
		}
		//行数
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

	//计算总记录数,想购,显示未解决的
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
			//分页条件
			sb.append(" LIMIT ?,?");
			//总记录数
			int totalCount = getTotalCountBuy(pb);
			System.out.println("totalCount:"+totalCount);
			
			pb.setTotalCount(totalCount);
			
			System.out.println("当前页："+pb.getCurrentPage());
			System.out.println("总页数："+pb.getTotalPage());
			//判断，当前页<1，则设置为1，当前页>总页数，则设置为总页数
			if(pb.getCurrentPage()<1){
				pb.setCurrentPage(1);
			}else if(pb.getCurrentPage()>pb.getTotalPage()){
				pb.setCurrentPage(pb.getTotalPage());
			}
			System.out.println("当前页："+pb.getCurrentPage());
			//起始行
			int index;
			if(totalCount==0){
				index = 0;
			}else{
				index = (pb.getCurrentPage()-1)*pb.getPageCount();
			}
			//行数
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
