package cn.itcast.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.entity.User;
import cn.itcast.entity.UserBuy;

public class BuyServlet extends BaseServlet {

	public Object findUserBuy(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//获取user
		String userId = request.getParameter("userId");
		/*User user = userServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user", user);*/
		//得到已经购买的数据列表
		List<UserBuy> userBuyList = buyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("userBuyList", userBuyList);
		System.out.println("userBuyList"+userBuyList);
		uri  = request.getRequestDispatcher("/app/user/buyList.jsp");
		return uri;
		
	}

}
