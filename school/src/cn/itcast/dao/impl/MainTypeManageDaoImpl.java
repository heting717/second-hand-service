package cn.itcast.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.dao.MainTypeManageDao;
import cn.itcast.entity.MainType;
import cn.itcast.util.JdbcUtils;

public class MainTypeManageDaoImpl implements MainTypeManageDao{

	@Override//添加并保存数据
	public void save(String mainTypeName) {
		String sql = "INSERT INTO mainType(mainTypeName) VALUES(?)";
		try {
			JdbcUtils.getQueryRunner().update(sql, mainTypeName);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//查询得到所有主类别信息
	public List<MainType> getAll() {
		String sql = "SELECT *FROM mainType";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<MainType>(MainType.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//根据id查询主类别
	public MainType findById(int id) {
		String sql = "SELECT *FROM mainType WHERE id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<MainType>(MainType.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//根据id删除
	public void delete(int id) {
		String sql = "DELETE FROM mainType WHERE id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//修改
	public void update(String mainTypeName,int id) {
		String sql = "UPDATE mainType SET mainTypeName=? WHERE id = ?";
		try {
			JdbcUtils.getQueryRunner().update(sql,mainTypeName,id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
