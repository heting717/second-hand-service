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
	//������д�빺��Ϣ,������
	public Object toBuyMessage(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//����useId
		String userId = request.getParameter("userId");
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user", user);
		//��������б�
		List<ShowType> typeList = typeManageServiceImpl.getAll();
		request.setAttribute("typeList", typeList);
		uri = request.getRequestDispatcher("/app/user/userWantBuy.jsp");
		return uri;
	}
	public Object save(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//��ȡuserId
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
		
		//�빺�б�
		List<BuyMessage> buyList = userBuyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("buyList", buyList);
		//�����б�
		List<SellMessage> sellList = userSellServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("sellList", sellList);
		//δ����
		List<SellMessage> sellNoSellList = userSellServiceImpl.findNoSell(Integer.parseInt(userId));
		request.setAttribute("sellNoSellList", sellNoSellList);
		//�Ѿ�����
		List<SellMessage> sellSellList = userSellServiceImpl.findSell(Integer.parseInt(userId));
		request.setAttribute("sellSellList", sellSellList);
		//δ�����
		List<BuyMessage> buyNoSolveList = userBuyServiceImpl.findNoSolve(Integer.parseInt(userId));
		System.out.println("buyNoSolveList:"+buyNoSolveList);
		request.setAttribute("buyNoSolveList", buyNoSolveList);
		//�Ѿ����
		List<BuyMessage> buySolveList = userBuyServiceImpl.findSolve(Integer.parseInt(userId));
		System.out.println("buySolveList:"+buySolveList);
		request.setAttribute("buySolveList", buySolveList);
		uri = request.getRequestDispatcher("/app/user/sellSituation.jsp");
		return uri;
	}
	
	//�����޸�ҳ��
	public Object toUpdateBuy(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//��ȡuserId
		String userId = request.getParameter("userId");
		System.out.println("userid"+userId);
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user", user);
		System.out.println("user--"+user);
		
		
		String buyId = request.getParameter("buyId");
		BuyMessage buyMessage = userBuyServiceImpl.findById(Integer.parseInt(buyId));
		System.out.println("buyMessage"+buyMessage);
		request.setAttribute("buyMessage", buyMessage);
		
		//��������б�
		List<ShowType> typeList = typeManageServiceImpl.getAll();
		request.setAttribute("typeList", typeList);
		
		uri = request.getRequestDispatcher("/app/user/userBuyUpdate.jsp");
		return uri;
	}
	//�޸�
	public Object updateBuy(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//��ȡuserId
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
		//�빺�б�
		List<BuyMessage> buyList = userBuyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("buyList", buyList);
		//�����б�
		List<SellMessage> sellList = userSellServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("sellList", sellList);
		
		
		//δ����
		List<SellMessage> sellNoSellList = userSellServiceImpl.findNoSell(Integer.parseInt(userId));
		request.setAttribute("sellNoSellList", sellNoSellList);
		//�Ѿ�����
		List<SellMessage> sellSellList = userSellServiceImpl.findSell(Integer.parseInt(userId));
		request.setAttribute("sellSellList", sellSellList);
		//δ�����
		List<BuyMessage> buyNoSolveList = userBuyServiceImpl.findNoSolve(Integer.parseInt(userId));
		System.out.println("buyNoSolveList:"+buyNoSolveList);
		request.setAttribute("buyNoSolveList", buyNoSolveList);
		//�Ѿ����
		List<BuyMessage> buySolveList = userBuyServiceImpl.findSolve(Integer.parseInt(userId));
		System.out.println("buySolveList:"+buySolveList);
		request.setAttribute("buySolveList", buySolveList);
		uri = request.getRequestDispatcher("/app/user/sellSituation.jsp");
		return uri;
	}
	//ɾ��
	public Object delete(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String buyId = request.getParameter("buyId");
		System.out.println("buyId:"+buyId);
		userBuyServiceImpl.delete(Integer.parseInt(buyId));
		
		//��ȡuserId
		String userId = request.getParameter("userId");
		System.out.println("userId:"+userId);
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user", user);
		//�빺�б�
		List<BuyMessage> buyList = userBuyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("buyList", buyList);
		//�����б�
		List<SellMessage> sellList = userSellServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("sellList", sellList);
		//δ����
		List<SellMessage> sellNoSellList = userSellServiceImpl.findNoSell(Integer.parseInt(userId));
		request.setAttribute("sellNoSellList", sellNoSellList);
		//�Ѿ�����
		List<SellMessage> sellSellList = userSellServiceImpl.findSell(Integer.parseInt(userId));
		request.setAttribute("sellSellList", sellSellList);
		//δ�����
		List<BuyMessage> buyNoSolveList = userBuyServiceImpl.findNoSolve(Integer.parseInt(userId));
		System.out.println("buyNoSolveList:"+buyNoSolveList);
		request.setAttribute("buyNoSolveList", buyNoSolveList);
		//�Ѿ����
		List<BuyMessage> buySolveList = userBuyServiceImpl.findSolve(Integer.parseInt(userId));
		System.out.println("buySolveList:"+buySolveList);
		request.setAttribute("buySolveList", buySolveList);
		uri = request.getRequestDispatcher("/app/user/sellSituation.jsp");
		return uri;
	}
}
