package cn.itcast.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.entity.AllType;
import cn.itcast.entity.BuyMessage;
import cn.itcast.entity.SellMessage;
import cn.itcast.entity.ShowType;
import cn.itcast.entity.User;
import cn.itcast.service.impl.UserMessageServiceImpl;

public class UserBuyServlet extends BaseServlet {
	//进入填写想购信息,添加类别
	public Object toBuyMessage(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//传递useId
		String userId = request.getParameter("userId");
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user", user);
		//加载类别列表
		List<ShowType> typeList = typeManageServiceImpl.getAll();
		request.setAttribute("typeList", typeList);
		uri = request.getRequestDispatcher("/app/user/userWantBuy.jsp");
		return uri;
	}
	public Object save(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//获取userId
		String userId = request.getParameter("userId");
		System.out.println("userid"+userId);
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user", user);
		System.out.println("user--"+user);
		//
		BuyMessage buyMessage = new BuyMessage();
		String typeId = request.getParameter("typeId");
		
		
		String brandName = request.getParameter("brandName");
		String priceRequest = request.getParameter("priceRequest");
		String describe = request.getParameter("describe");
		Date dateTime = new Date();
		
		buyMessage.setUserId(Integer.parseInt(userId));
		buyMessage.setType_id(Integer.parseInt(typeId));
		buyMessage.setBrandName(brandName);
		buyMessage.setPriceRequest(priceRequest);
		buyMessage.setRequireDescribe(describe);
		buyMessage.setUpTime(dateTime);
		System.out.println(buyMessage);
		userBuyServiceImpl.save(buyMessage);
		
		//想购列表
		List<BuyMessage> buyList = userBuyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("buyList", buyList);
		//出售列表
		List<SellMessage> sellList = userSellServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("sellList", sellList);
		//未出售
		List<SellMessage> sellNoSellList = userSellServiceImpl.findNoSell(Integer.parseInt(userId));
		request.setAttribute("sellNoSellList", sellNoSellList);
		//已经出售
		List<SellMessage> sellSellList = userSellServiceImpl.findSell(Integer.parseInt(userId));
		request.setAttribute("sellSellList", sellSellList);
		//未解决：
		List<BuyMessage> buyNoSolveList = userBuyServiceImpl.findNoSolve(Integer.parseInt(userId));
		System.out.println("buyNoSolveList:"+buyNoSolveList);
		request.setAttribute("buyNoSolveList", buyNoSolveList);
		//已经解决
		List<BuyMessage> buySolveList = userBuyServiceImpl.findSolve(Integer.parseInt(userId));
		System.out.println("buySolveList:"+buySolveList);
		request.setAttribute("buySolveList", buySolveList);
		uri = request.getRequestDispatcher("/app/user/sellSituation.jsp");
		return uri;
	}
	
	//进入修改页面
	public Object toUpdateBuy(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//获取userId
		String userId = request.getParameter("userId");
		System.out.println("userid"+userId);
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user", user);
		System.out.println("user--"+user);
		
		
		String buyId = request.getParameter("buyId");
		BuyMessage buyMessage = userBuyServiceImpl.findById(Integer.parseInt(buyId));
		System.out.println("buyMessage"+buyMessage);
		request.setAttribute("buyMessage", buyMessage);
		
		//加载类别列表
		List<ShowType> typeList = typeManageServiceImpl.getAll();
		request.setAttribute("typeList", typeList);
		
		uri = request.getRequestDispatcher("/app/user/userBuyUpdate.jsp");
		return uri;
	}
	//修改
	public Object updateBuy(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//获取userId
		String userId = request.getParameter("userId");
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user", user);
		
	

		String typeId = request.getParameter("typeId");
		System.out.println("typeId:"+typeId);
		String brandName = request.getParameter("brandName");
		System.out.println("brandName:"+brandName);
		String priceRequest = request.getParameter("priceRequest");
		System.out.println("priceRequest:"+priceRequest);
		String describe = request.getParameter("describe");
		System.out.println("describe:"+describe);
		Date dateTime = new Date();
		System.out.println("dateTime:"+dateTime);
		String buyId = request.getParameter("buyId");
		System.out.println("buyId:"+buyId);
		
		userBuyServiceImpl.update(Integer.parseInt(typeId), brandName, priceRequest,describe, dateTime, Integer.parseInt(buyId));
		
		System.out.println("genxinl");
		//想购列表
		List<BuyMessage> buyList = userBuyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("buyList", buyList);
		//出售列表
		List<SellMessage> sellList = userSellServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("sellList", sellList);
		
		
		//未出售
		List<SellMessage> sellNoSellList = userSellServiceImpl.findNoSell(Integer.parseInt(userId));
		request.setAttribute("sellNoSellList", sellNoSellList);
		//已经出售
		List<SellMessage> sellSellList = userSellServiceImpl.findSell(Integer.parseInt(userId));
		request.setAttribute("sellSellList", sellSellList);
		//未解决：
		List<BuyMessage> buyNoSolveList = userBuyServiceImpl.findNoSolve(Integer.parseInt(userId));
		System.out.println("buyNoSolveList:"+buyNoSolveList);
		request.setAttribute("buyNoSolveList", buyNoSolveList);
		//已经解决
		List<BuyMessage> buySolveList = userBuyServiceImpl.findSolve(Integer.parseInt(userId));
		System.out.println("buySolveList:"+buySolveList);
		request.setAttribute("buySolveList", buySolveList);
		uri = request.getRequestDispatcher("/app/user/sellSituation.jsp");
		return uri;
	}
	//删除
	public Object delete(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String buyId = request.getParameter("buyId");
		System.out.println("buyId:"+buyId);
		userBuyServiceImpl.delete(Integer.parseInt(buyId));
		
		//获取userId
		String userId = request.getParameter("userId");
		System.out.println("userId:"+userId);
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user", user);
		//想购列表
		List<BuyMessage> buyList = userBuyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("buyList", buyList);
		//出售列表
		List<SellMessage> sellList = userSellServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("sellList", sellList);
		//未出售
		List<SellMessage> sellNoSellList = userSellServiceImpl.findNoSell(Integer.parseInt(userId));
		request.setAttribute("sellNoSellList", sellNoSellList);
		//已经出售
		List<SellMessage> sellSellList = userSellServiceImpl.findSell(Integer.parseInt(userId));
		request.setAttribute("sellSellList", sellSellList);
		//未解决：
		List<BuyMessage> buyNoSolveList = userBuyServiceImpl.findNoSolve(Integer.parseInt(userId));
		System.out.println("buyNoSolveList:"+buyNoSolveList);
		request.setAttribute("buyNoSolveList", buyNoSolveList);
		//已经解决
		List<BuyMessage> buySolveList = userBuyServiceImpl.findSolve(Integer.parseInt(userId));
		System.out.println("buySolveList:"+buySolveList);
		request.setAttribute("buySolveList", buySolveList);
		uri = request.getRequestDispatcher("/app/user/sellSituation.jsp");
		return uri;
	}
}
