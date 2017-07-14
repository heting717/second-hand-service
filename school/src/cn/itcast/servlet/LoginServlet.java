package cn.itcast.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.entity.User;

public class LoginServlet extends BaseServlet{
	
	public Object login(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		User user = loginServiceImpl.findByPhoneAndPwd(phone, password);
		
		request.setAttribute("user",user);
		uri = request.getRequestDispatcher("/UserIndexServlet?method=showAllMessageList");
		return uri;
	}

}
