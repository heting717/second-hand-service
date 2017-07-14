package cn.itcast.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.dao.TypeManageDao;
import cn.itcast.entity.AllType;
import cn.itcast.entity.ShowType;
import cn.itcast.util.JdbcUtils;

public class TypeManageDaoImpl implements TypeManageDao{

	@Override//添加并保存数据
	public void save(int mainType_id,String typeName) {
		String sql = "INSERT INTO typeTable(mainType_id,typeName) VALUES(?,?)";
		try {
			JdbcUtils.getQueryRunner().update(sql,mainType_id,typeName);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//查询得到所有类别信息
	public List<ShowType> getAll() {
		String sql = "SELECT t.id,mt.mainTypeName,t.typeName FROM typeTable t,mainType mt WHERE t.mainType_id=mt.id";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<ShowType>(ShowType.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//根据id查询类别
	public AllType findById(int id) {
		String sql = "SELECT *FROM typeTable WHERE id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<AllType>(AllType.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//根据id删除
	public void delete(int id) {
		String sql = "DELETE FROM typeTable WHERE id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//修改
	public void update(int mainType_id,String typeName, int id) {
		String sql = "UPDATE typeTable SET mainType_id=?,typeName=? WHERE id = ?";
		try {
			JdbcUtils.getQueryRunner().update(sql, mainType_id,typeName,id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//根据主类别查询
	public List<AllType> findByMainType(int mainTypeId) {
		String sql = "SELECT *FROM typeTable WHERE mainType_id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<AllType>(AllType.class), mainTypeId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	

}
