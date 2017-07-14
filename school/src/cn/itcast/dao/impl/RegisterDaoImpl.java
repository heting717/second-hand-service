package cn.itcast.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.dao.RegisterDao;
import cn.itcast.entity.User;
import cn.itcast.util.JdbcUtils;

public class RegisterDaoImpl implements RegisterDao{

	@Override//注册用户保存信息
	public void save(User user) {
		String sql = " INSERT INTO userTable(phone,mail,userName,userPassword,school,intime,outTime) VALUES(?,?,?,?,?,?,?)";
		try {
			JdbcUtils.getQueryRunner().update(sql,user.getPhone(),user.getMail(),user.getUserName(),user.getUserPassword(),user.getSchool(),user.getInTime(),user.getOutTime());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//判断用户电话号码判断用户是否已经存在
	public boolean userExsits(String phone) {
		String sql = " SELECT *FROM userTable WHERE phone=?";
		try {
			User user = JdbcUtils.getQueryRunner().query(sql, new BeanHandler<User>(User.class),phone);
			if(user!=null){
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

}
