package cn.itcast.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.entity.CollectMessage;

public class UserCollectServlet extends BaseServlet {

	//查看收藏数据
	public Object showCollectMessage(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		
		String userId = request.getParameter("userId");
		
		List<CollectMessage> collectMessageList = collectServcieImpl.getAll(Integer.parseInt(userId));
		System.out.println("collectMessageList:"+collectMessageList);
		request.setAttribute("collectMessageList", collectMessageList);
		uri = request.getRequestDispatcher("/app/user/CollectList.jsp");
		return uri;
	}
	public Object delete(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String collectId = request.getParameter("collectId");
		
		collectServcieImpl.delete(Integer.parseInt(collectId));
		uri = request.getRequestDispatcher("/UserCollectServlet?method=showCollectMessage");
		return uri;
	}
	
}
