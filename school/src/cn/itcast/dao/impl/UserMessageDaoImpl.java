package cn.itcast.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.dao.UserMessageDao;
import cn.itcast.entity.User;
import cn.itcast.util.JdbcUtils;

public class UserMessageDaoImpl implements UserMessageDao{

	@Override//�鿴������Ϣ
	public User findById(int id) {
		String sql = "SELECT *FROM userTable WHERE id = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<User>(User.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//�޸ĸ�����Ϣ
	public void update(User user) {
		String sql = "UPDATE userTable SET phone=?,mail=?,userName=?,school=?,intime=?,outTime=?,imagePath=? WHERE id = ?";
		try {
			JdbcUtils.getQueryRunner().update(sql, user.getPhone(),user.getMail(),user.getUserName(),user.getSchool(),user.getInTime(),user.getOutTime(),user.getImagePath(),user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	
	}

	@Override//�޸�����
	public void updatePwd(String pwd,int id) {
		String sql = "UPDATE userTable SET userPassword=? WHERE id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql,pwd,id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	
	

}
