package cn.itcast.servlet;



import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.entity.User;
import cn.itcast.exception.UserExistException;

public class RegisterServlet extends BaseServlet {


	//注册，保存数据
	public Object register(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		
		
		User user = new User();
		
		String userPhoto = request.getParameter("userphoto");
		String mail = request.getParameter("mail");
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		String college = request.getParameter("college");
		String inTime = request.getParameter("inTime");
		String outTime = request.getParameter("outTime");
		user.setPhone(userPhoto);
		System.out.println("userPhoto"+userPhoto);
		user.setMail(mail);
		user.setUserName(userName);
		System.out.println("userName"+userName);
		user.setUserPassword(passWord);
		System.out.println("passWord"+passWord);
		user.setSchool(college);
		System.out.println("college"+college);
		user.setInTime(inTime);
		System.out.println("inTime"+inTime);
		user.setOutTime(outTime);
		System.out.println("outTime"+outTime);
		
		try {
			registerServiceImpl.register(user);
		} catch (UserExistException e) {
			request.setAttribute("message", "用户已经存在！");
			uri = request.getRequestDispatcher("/app/register.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			uri = request.getRequestDispatcher("/error/error.jsp");
		}
		
		uri = request.getRequestDispatcher("/app/login.jsp");
		return uri;
	}

}
