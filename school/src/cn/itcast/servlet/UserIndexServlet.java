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
import cn.itcast.entity.MainType;
import cn.itcast.entity.SellMessage;
import cn.itcast.entity.User;
import cn.itcast.util.Condition;
import cn.itcast.util.PageBean;

public class UserIndexServlet extends BaseServlet {

	//加载主类别
		public Object showMainType(HttpServletRequest request,HttpServletResponse response){
			Object uri = null;
			//加载主类别
			List<MainType> mainTypeList = indexServiceImpl.findMainType();
			request.setAttribute("mainTypeList", mainTypeList);
			
			uri = request.getRequestDispatcher("/app/user/userIndex.jsp");
			return uri;
		}
		
		//显示次类别
		public Object showTypeAndData(HttpServletRequest request,HttpServletResponse response){
			Object uri = null;
			String userId = request.getParameter("userId");
			User user = userServiceImpl.findById(Integer.parseInt(userId));
			request.setAttribute("user", user);
			
			
			String mainTypeId = request.getParameter("mainTypeId");
			//加载次类别
			List<AllType> typeList = typeManageServiceImpl.findByMainType(Integer.parseInt(mainTypeId));
			request.setAttribute("typeList", typeList);
			System.out.println("typeList"+typeList);
			uri = request.getRequestDispatcher("/UserIndexServlet?method=showMainType");
			return uri;
		}
		
		//显示所有出售数据
		public Object showAllMessageList(HttpServletRequest request,HttpServletResponse response){
			Object uri = null;
			
			//获取分页对象
			PageBean<SellMessage> pb = new PageBean<SellMessage>();
			//获取当前页
			String curPage =  request.getParameter("currentPage");
			System.out.println("curPage:"+curPage);
			if(curPage==null||" ".equals(curPage.trim())){
				pb.setCurrentPage(1);
			}else{
				pb.setCurrentPage(Integer.parseInt(curPage));
			}
			System.out.println("当前页："+pb.getCurrentPage());
			Condition condition = new Condition();
			String typeId = request.getParameter("typeId");
			System.out.println("typeId:"+typeId);
			if(typeId!=null){
				condition.setTypeId(Integer.parseInt(typeId));
			}
			pb.setCondition(condition);
			
			
			System.out.println("pageData:"+pb.getPageData());
			indexServiceImpl.getAll(pb);
			System.out.println("pageData:"+pb.getPageData());
			request.setAttribute("pb", pb);
			System.out.println("pb"+pb);
			
			String userId = request.getParameter("userId");
			if(userId!=null){
				User user = userServiceImpl.findById(Integer.parseInt(userId));
				request.setAttribute("user", user);
			}
			/*//加载所有数据
			List<SellMessage> sellMessageList = userSellServiceImpl.getDatasCount();
			System.out.println("sellMessageList"+sellMessageList);
			request.setAttribute("sellMessageList", sellMessageList);*/
			uri = request.getRequestDispatcher("/UserIndexServlet?method=showMainType");
			return uri;
		}
		//返回主页
		public Object toIndex(HttpServletRequest request,HttpServletResponse response){
			Object uri = null;
			String userId = request.getParameter("userId");
			User user = userServiceImpl.findById(Integer.parseInt(userId));
			request.setAttribute("user", user);
			uri = request.getRequestDispatcher("/UserIndexServlet?method=showAllMessageList");
			return uri;
		}
		//显示此类的出售数据
		public Object showDatasByType(HttpServletRequest request,HttpServletResponse response){
			Object uri = null;
			String userId = request.getParameter("userId");
			User user = userServiceImpl.findById(Integer.parseInt(userId));
			request.setAttribute("user", user);
			
			
			
			String typeId = request.getParameter("typeId");
			System.out.println("typeId:"+typeId);
			AllType type =  typeManageServiceImpl.findById(Integer.parseInt(typeId));
			int mainTypeId = type.getMainType_id();
			
			//加载次类别
			List<AllType> typeList = typeManageServiceImpl.findByMainType(mainTypeId);
			request.setAttribute("typeList", typeList);

			List<SellMessage> sellMessageList = userSellServiceImpl.findBytTypeIdAndCount(Integer.parseInt(typeId));
			request.setAttribute("sellMessageList", sellMessageList);
			uri = request.getRequestDispatcher("/UserIndexServlet?method=showMainType");
			return uri;
		}
		
		//显示出售的详细信息
		public Object showSellMessage(HttpServletRequest request,HttpServletResponse response){
			Object uri = null;
			String sellMessageId = request.getParameter("sellMessageId");
			SellMessage sellMessage = userSellServiceImpl.findById(Integer.parseInt(sellMessageId));
			request.setAttribute("sellMessage", sellMessage);
			
			//获取用户id
			String userId = request.getParameter("userId");
			User user = userServiceImpl.findById(Integer.parseInt(userId));
			request.setAttribute("user", user);
			
			
			uri = request.getRequestDispatcher("/app/user/userSellMessage.jsp");
			return uri;
			
		}
		//购买
		public Object buy(HttpServletRequest request,HttpServletResponse response){
			Object uri = null;
			//获取数量
			String sellMessageId = request.getParameter("sellMessageId");
			SellMessage sellMessage = userSellServiceImpl.findById(Integer.parseInt(sellMessageId));
			request.setAttribute("sellMessage", sellMessage);
		
			//获取用户id
			String userId = request.getParameter("userId");
			User user = userServiceImpl.findById(Integer.parseInt(userId));
			request.setAttribute("user", user);
			
			
			
			uri = request.getRequestDispatcher("/app/buySubmit.jsp");
			return uri;
		}
		//保存购买
		public Object saveBuy(HttpServletRequest request,HttpServletResponse response){
			Object uri = null;
			
			String userId = request.getParameter("userId");
			User user = userServiceImpl.findById(Integer.parseInt(userId));
			request.setAttribute("user", user);
			
			//保存购买数据
		
			String sellMessageId = request.getParameter("sellMessageId");
			String buyCount = request.getParameter("buyCount");
			String buyRemark = request.getParameter("buyRemark");
			Date buyTime = new Date();
			System.out.println("购买时间："+buyTime);
			buyServiceImpl.saveBuy(Integer.parseInt(userId), Integer.parseInt(sellMessageId), Integer.parseInt(buyCount),buyRemark,buyTime);
			
			//出售物品数据相应减去购买数量
			SellMessage sellMessage = userSellServiceImpl.findById(Integer.parseInt(sellMessageId));
			System.out.println("sellMessage:"+sellMessage);
			int count = sellMessage.getSellCount()-Integer.parseInt(buyCount);
			
			
			
			
			sellMessage.setSellCount(count);
			System.out.println("sellMessage:"+sellMessage);
		
			userSellServiceImpl.update(sellMessage);
			request.setAttribute("sellMessage", sellMessage);
			
			//跳转到出售信息页面
			uri = request.getRequestDispatcher("/UserIndexServlet?method=showAllMessageList");
			return uri;
		}
		//添加收藏
		public Object addCollect(HttpServletRequest request,HttpServletResponse response){
			Object uri = null;
			String userId = request.getParameter("userId");
			String sellId = request.getParameter("sellMessageId");
			
			collectServcieImpl.save(Integer.parseInt(userId), Integer.parseInt(sellId));
			
			
			User user = userServiceImpl.findById(Integer.parseInt(userId));
			request.setAttribute("user", user);
			SellMessage sellMessage = userSellServiceImpl.findById(Integer.parseInt(sellId));
			System.out.println("sellMessage:"+sellMessage);
			uri = request.getRequestDispatcher("/UserIndexServlet?method=showAllMessageList");
			return uri;
		}
		//加载主类别，想购页面
		public Object buyShowMainType(HttpServletRequest request,HttpServletResponse response){
			Object uri = null;
			//加载主类别
			List<MainType> mainTypeList = indexServiceImpl.findMainType();
			request.setAttribute("mainTypeList", mainTypeList);
			
			uri = request.getRequestDispatcher("/app/user/userBuyIndex.jsp");
			return uri;
		}
		//显示次类别，想购页面
		public Object buyShowTypeAndData(HttpServletRequest request,HttpServletResponse response){
			Object uri = null;
			String userId = request.getParameter("userId");
			User user = userServiceImpl.findById(Integer.parseInt(userId));
			request.setAttribute("user", user);
			
			
			String mainTypeId = request.getParameter("mainTypeId");
			//加载次类别
			List<AllType> typeList = typeManageServiceImpl.findByMainType(Integer.parseInt(mainTypeId));
			request.setAttribute("typeList", typeList);
			System.out.println("typeList"+typeList);
			uri = request.getRequestDispatcher("/UserIndexServlet?method=buyShowMainType");
			return uri;
		}
		//显示所有想购数据，想购页面
		public Object showAllBuyMessageList(HttpServletRequest request,HttpServletResponse response){
			Object uri = null;
			//获取分页对象
			PageBean<BuyMessage> pb = new PageBean<BuyMessage>();
			//获取当前页
			String curPage =  request.getParameter("currentPage");
			System.out.println("curPage:"+curPage);
			if(curPage==null||" ".equals(curPage.trim())){
				pb.setCurrentPage(1);
			}else{
				pb.setCurrentPage(Integer.parseInt(curPage));
			}
			System.out.println("当前页："+pb.getCurrentPage());
			Condition condition = new Condition();
			String typeId = request.getParameter("typeId");
			System.out.println("typeId:"+typeId);
			if(typeId!=null){
				condition.setTypeId(Integer.parseInt(typeId));
			}
			pb.setCondition(condition);
			
			
			System.out.println("pageData:"+pb.getPageData());
			indexServiceImpl.getAllBuy(pb);;//
			System.out.println("pageData:"+pb.getPageData());
			request.setAttribute("pb", pb);
			System.out.println("pb"+pb);


			String userId = request.getParameter("userId");
			if(userId!=null){
				User user = userServiceImpl.findById(Integer.parseInt(userId));
				request.setAttribute("user", user);
			}
			
			/*//加载所有数据
			List<BuyMessage> buyMessageList = userBuyServiceImpl.getAll();
			System.out.println("buyMessageList"+buyMessageList);
			request.setAttribute("buyMessageList", buyMessageList);*/
			uri = request.getRequestDispatcher("/UserIndexServlet?method=buyShowMainType");
			return uri;
		}
		//显示此类的出售数据，想购页面
		public Object showBuyDatasByType(HttpServletRequest request,HttpServletResponse response){
			Object uri = null;
			String userId = request.getParameter("userId");
			User user = userServiceImpl.findById(Integer.parseInt(userId));
			request.setAttribute("user", user);
			
			
			String typeId = request.getParameter("typeId");
			System.out.println("typeId:"+typeId);
			AllType type =  typeManageServiceImpl.findById(Integer.parseInt(typeId));
			int mainTypeId = type.getMainType_id();
			
			//加载次类别
			List<AllType> typeList = typeManageServiceImpl.findByMainType(mainTypeId);
			request.setAttribute("typeList", typeList);
			
			List<BuyMessage> buyMessageList = userBuyServiceImpl.findByTypeId(Integer.parseInt(typeId));
			request.setAttribute("buyMessageList", buyMessageList);
			uri = request.getRequestDispatcher("/UserIndexServlet?method=buyShowMainType");
			return uri;
		}
		//显示想购的详细信息
		public Object showUserBuyMessage(HttpServletRequest request,HttpServletResponse response){
			Object uri = null;
			String buyMessageId = request.getParameter("buyMessageId");
			BuyMessage buyMessage = userBuyServiceImpl.findById(Integer.parseInt(buyMessageId));
			request.setAttribute("buyMessage", buyMessage);
			
			
			uri = request.getRequestDispatcher("/app/user/userBuyMessage.jsp");
			return uri;
			
		}
				
		
}
