package cn.itcast.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.dao.UserSellDao;
import cn.itcast.entity.SellMessage;
import cn.itcast.util.JdbcUtils;

public class UserSellDaoImpl implements UserSellDao{

	@Override
	public List<SellMessage> getAll() {
		String sql = "SELECT *FROM sellTable";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<SellMessage>(SellMessage.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public List<SellMessage> getDatasCount() {
		String sql = "SELECT *FROM sellTable WHERE sellCount>0";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<SellMessage>(SellMessage.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	

	@Override
	public List<SellMessage> getAll(int userId) {
		String sql = "SELECT st.id,st.type_id,t.typeName,st.sellName,st.brandName,st.buyTime,st.buyPrice,st.sellPrice,st.sellCount,st.goodsDescribe,st.upTime FROM sellTable st,typeTable t WHERE st.type_id=t.id AND st.userId=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<SellMessage>(SellMessage.class),userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override//添加，保存数据
	public void save(SellMessage sellMessage) {
		String sql = "INSERT INTO sellTable(userId,type_id,sellName,brandName,buyTime,buyPrice,sellPrice,sellCount,goodsDescribe,upTime,imagePath) VALUE(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			JdbcUtils.getQueryRunner().update(sql,sellMessage.getUserId(),sellMessage.getType_id(),sellMessage.getSellName(),sellMessage.getBrandName(),sellMessage.getBuyTime(),sellMessage.getBuyPrice(),sellMessage.getSellPrice(),sellMessage.getSellCount(),sellMessage.getGoodsDescribe(),sellMessage.getUpTime(),sellMessage.getImagePath());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	
	}
	@Override//根据id查找
	public SellMessage findById(int id) {
		String sql = "SELECT st.id,st.userId,u.mail,st.type_id,st.sellName,st.brandName,st.buyTime,st.buyPrice,st.sellPrice,st.sellCount,st.goodsDescribe,st.sellStatus,st.imagePath FROM sellTable st,userTable u WHERE st.userId=u.id AND st.id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<SellMessage>(SellMessage.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override//修改
	public void update(SellMessage sellMessage) {
		String sql = "UPDATE sellTable SET type_id=?,sellName=?,brandName=?,buyTime=?,buyPrice=?,sellPrice=?,sellCount=?,goodsDescribe=?,upTime=?,imagePath=? WHERE id=?";
		System.out.println("sellMessage sql"+sellMessage);
		try {
			JdbcUtils.getQueryRunner().update(sql,sellMessage.getType_id(),sellMessage.getSellName(),sellMessage.getBrandName(),sellMessage.getBuyTime(),sellMessage.getBuyPrice(),sellMessage.getSellPrice(),sellMessage.getSellCount(),sellMessage.getGoodsDescribe(),sellMessage.getUpTime(),sellMessage.getImagePath(),sellMessage.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override//删除
	public void delete(int id ) {
		String sql = "DELETE FROM sellTable WHERE id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public List<SellMessage> findByStatus(int userId, int status) {
		String sql = "SELECT st.id,st.type_id,t.typeName,st.sellName,st.brandName,st.buyTime,st.buyPrice,st.sellPrice,st.sellCount,st.goodsDescribe,st.upTime FROM sellTable st,typeTable t WHERE st.type_id=t.id AND st.userId=? AND st.sellStatus=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<SellMessage>(SellMessage.class),userId,status);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//根据类别id查询数据
	public List<SellMessage> findByTypeId(int typeId) {
		String sql = "SELECT *FROM sellTable WHERE type_id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<SellMessage>(SellMessage.class), typeId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public List<SellMessage> findBytTypeIdAndCount(int typeId) {
		String sql = "SELECT *FROM sellTable WHERE sellCount>0 AND type_id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<SellMessage>(SellMessage.class), typeId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	
	

}
