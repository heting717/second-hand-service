package cn.itcast.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

public class IndexServlet extends BaseServlet {

	
	//加载主类别
	public Object showMainType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//加载主类别
		List<MainType> mainTypeList = indexServiceImpl.findMainType();
		request.setAttribute("mainTypeList", mainTypeList);
		
		uri = request.getRequestDispatcher("/app/index.jsp");
		return uri;
	}
	
	//显示次类别
	public Object showTypeAndData(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String mainTypeId = request.getParameter("mainTypeId");
		//加载次类别
		List<AllType> typeList = typeManageServiceImpl.findByMainType(Integer.parseInt(mainTypeId));
		request.setAttribute("typeList", typeList);
		System.out.println("typeList"+typeList);
		uri = request.getRequestDispatcher("/IndexServlet?method=showMainType");
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
		
	
		/*//加载所有数据
		List<SellMessage> sellMessageList = userSellServiceImpl.getDatasCount();
		System.out.println("sellMessageList"+sellMessageList);
		request.setAttribute("sellMessageList", sellMessageList);*/
		uri = request.getRequestDispatcher("/IndexServlet?method=showMainType");
		return uri;
	}
	
	//显示此类的出售数据
	public Object showDatasByType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String typeId = request.getParameter("typeId");
		System.out.println("typeId:"+typeId);
		AllType type =  typeManageServiceImpl.findById(Integer.parseInt(typeId));
		int mainTypeId = type.getMainType_id();
		
		//加载次类别
		List<AllType> typeList = typeManageServiceImpl.findByMainType(mainTypeId);
		request.setAttribute("typeList", typeList);

		List<SellMessage> sellMessageList = userSellServiceImpl.findBytTypeIdAndCount(Integer.parseInt(typeId));
		request.setAttribute("sellMessageList", sellMessageList);
		uri = request.getRequestDispatcher("/IndexServlet?method=showMainType");
		return uri;
	}
	//显示出售的详细信息
	public Object showSellMessage(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String sellMessageId = request.getParameter("sellMessageId");
		SellMessage sellMessage = userSellServiceImpl.findById(Integer.parseInt(sellMessageId));
		request.setAttribute("sellMessage", sellMessage);
		
		
		uri = request.getRequestDispatcher("/app/sellMessage.jsp");
		return uri;
		
	}
	//加载主类别,跳转到想购页面
	public Object buyShowMainType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//加载主类别
		List<MainType> mainTypeList = indexServiceImpl.findMainType();
		request.setAttribute("mainTypeList", mainTypeList);
		
		uri = request.getRequestDispatcher("/app/buyIndex.jsp");
		return uri;
	}
	//显示次类别，跳转到想购加载主类方法
	public Object buyShowTypeAndData(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String mainTypeId = request.getParameter("mainTypeId");
		//加载次类别
		List<AllType> typeList = typeManageServiceImpl.findByMainType(Integer.parseInt(mainTypeId));
		request.setAttribute("typeList", typeList);
		System.out.println("typeList"+typeList);
		uri = request.getRequestDispatcher("/IndexServlet?method=buyShowMainType");
		return uri;
	}
	//显示所有想购数据
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


		
		/*//加载所有数据
		List<BuyMessage> buyMessageList = userBuyServiceImpl.getAll();
		System.out.println("buyMessageList"+buyMessageList);
		request.setAttribute("buyMessageList", buyMessageList);*/
		uri = request.getRequestDispatcher("/IndexServlet?method=buyShowMainType");
		return uri;
	}
	//显示此类的想购数据
	public Object showBuyDatasByType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String typeId = request.getParameter("typeId");
		System.out.println("typeId:"+typeId);
		AllType type =  typeManageServiceImpl.findById(Integer.parseInt(typeId));
		int mainTypeId = type.getMainType_id();
		
		//加载次类别
		List<AllType> typeList = typeManageServiceImpl.findByMainType(mainTypeId);
		request.setAttribute("typeList", typeList);
		
		List<BuyMessage> buyMessageList = userBuyServiceImpl.findByTypeId(Integer.parseInt(typeId));
		request.setAttribute("buyMessageList", buyMessageList);
		uri = request.getRequestDispatcher("/IndexServlet?method=buyShowMainType");
		return uri;
	}
	
	//显示想购的详细信息
	public Object showBuyMessage(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String buyMessageId = request.getParameter("buyMessageId");
		BuyMessage buyMessage = userBuyServiceImpl.findById(Integer.parseInt(buyMessageId));
		request.setAttribute("buyMessage", buyMessage);
		
		
		uri = request.getRequestDispatcher("/app/buyMessage.jsp");
		return uri;
		
	}


}
