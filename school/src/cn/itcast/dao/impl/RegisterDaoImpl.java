package cn.itcast.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.dao.RegisterDao;
import cn.itcast.entity.User;
import cn.itcast.util.JdbcUtils;

public class RegisterDaoImpl implements RegisterDao{

	@Override//ע���û�������Ϣ
	public void save(User user) {
		String sql = " INSERT INTO userTable(phone,mail,userName,userPassword,school,intime,outTime) VALUES(?,?,?,?,?,?,?)";
		try {
			JdbcUtils.getQueryRunner().update(sql,user.getPhone(),user.getMail(),user.getUserName(),user.getUserPassword(),user.getSchool(),user.getInTime(),user.getOutTime());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override//�ж��û��绰�����ж��û��Ƿ��Ѿ�����
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
