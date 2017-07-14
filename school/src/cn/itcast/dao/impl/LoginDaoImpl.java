package cn.itcast.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.dao.LoginDao;
import cn.itcast.entity.User;
import cn.itcast.util.JdbcUtils;

public class LoginDaoImpl implements LoginDao{

	@Override
	public User findByPhoneAndPwd(String phone, String passWord) {
		String sql = "SELECT *FROM userTable WHERE phone=? AND userPassword=?";
		
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<User>(User.class), phone,passWord);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
