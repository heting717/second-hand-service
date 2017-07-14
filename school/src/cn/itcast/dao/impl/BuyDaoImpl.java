package cn.itcast.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.dao.BuyDao;
import cn.itcast.entity.UserBuy;
import cn.itcast.util.JdbcUtils;

public class BuyDaoImpl implements BuyDao{

	@Override
	public void saveBuy(int userId,int sellId,int buyCount,String buyRemark,Date buyTime) {
		String sql = "INSERT INTO userBuy(userId,sellId,buyCount,buyRemark,buyTime) VALUES(?,?,?,?,?);";
		try {
			JdbcUtils.getQueryRunner().update(sql,userId,sellId,buyCount,buyRemark,buyTime);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public List<UserBuy> getAll(int userId) {
		String sql = "SELECT ub.id,ub.userId,ub.sellId,ub.buyCount,ub.buyRemark,st.sellName,st.sellPrice,ub.buyTime FROM userBuy ub,sellTable st WHERE ub.sellId=st.id AND ub.userId=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<UserBuy>(UserBuy.class), userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
