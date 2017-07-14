package cn.itcast.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.dao.UserBuyDao;
import cn.itcast.entity.BuyMessage;
import cn.itcast.entity.Status;
import cn.itcast.util.JdbcUtils;

public class UserBuyDaoImpl implements UserBuyDao{

	@Override
	public void save(BuyMessage buyMessage) {
		String sql = "INSERT INTO buyTable(userId,type_id,brandName,priceRequest,requireDescribe,upTime) VALUES(?,?,?,?,?,?)";
		try {
			JdbcUtils.getQueryRunner().update(sql, buyMessage.getUserId(),buyMessage.getType_id(),buyMessage.getBrandName(),buyMessage.getPriceRequest(),buyMessage.getRequireDescribe(),buyMessage.getUpTime());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<BuyMessage> getAll(int userId) {
		String sql = "SELECT b.id,b.type_id,t.typeName,b.brandName,b.priceRequest,b.requireDescribe,b.upTime FROM buyTable b,typeTable t WHERE b.type_id=t.id AND b.userId=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<BuyMessage>(BuyMessage.class), userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public BuyMessage findById(int id) {
		String sql = "SELECT b.id,b.userId,u.mail,b.type_id,t.typeName,b.brandName,b.priceRequest,b.requireDescribe,b.upTime FROM buyTable b,typeTable t,userTable u WHERE b.type_id=t.id AND b.userId=u.id AND b.id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<BuyMessage>(BuyMessage.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void update(int type_id, String brandName, String priceRequest,
			String requireDescribe, Date upTime,int id) {
		String sql = "UPDATE buyTable SET type_id=?,brandName=?,priceRequest=?,requireDescribe=?,upTime=? WHERE id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql, type_id,brandName,priceRequest,requireDescribe,upTime,id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM buyTable WHERE id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<BuyMessage> findByStatus(int userId,int status) {
		String sql = "SELECT b.id,b.userId,b.type_id,t.typeName,b.brandName,b.priceRequest,b.requireDescribe,b.upTime FROM buyTable b,typeTable t WHERE b.type_id=t.id AND b.userId=? AND buyStatus=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<BuyMessage>(BuyMessage.class), userId,status);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<BuyMessage> getAll() {
		String sql = "SELECT *FROM buyTable";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<BuyMessage>(BuyMessage.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<BuyMessage> findByTypeId(int typeId) {
		String sql = "SELECT *FROM buyTable WHERE type_id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<BuyMessage>(BuyMessage.class), typeId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
