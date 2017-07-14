package cn.itcast.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.dao.CollectDao;
import cn.itcast.entity.CollectMessage;
import cn.itcast.util.JdbcUtils;

public class CollectDaoImpl implements CollectDao{

	@Override
	public void save(int userId, int sellId) {

		String sql = "INSERT INTO collectTable(userId,sellId) VALUES(?,?)";
		try {
			JdbcUtils.getQueryRunner().update(sql, userId,sellId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public List<CollectMessage> getAll(int userId) {
		String sql = "SELECT ct.id,ct.sellId,st.sellName,st.brandName,st.sellPrice,st.goodsDescribe,st.upTime FROM collectTable ct,sellTable st WHERE ct.sellId=st.id AND ct.userId=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<CollectMessage>(CollectMessage.class), userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public void delete(int id) {
		String sql = " DELETE FROM collectTable WHERE id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	

}
