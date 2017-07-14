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

	
	//���������
	public Object showMainType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//���������
		List<MainType> mainTypeList = indexServiceImpl.findMainType();
		request.setAttribute("mainTypeList", mainTypeList);
		
		uri = request.getRequestDispatcher("/app/index.jsp");
		return uri;
	}
	
	//��ʾ�����
	public Object showTypeAndData(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String mainTypeId = request.getParameter("mainTypeId");
		//���ش����
		List<AllType> typeList = typeManageServiceImpl.findByMainType(Integer.parseInt(mainTypeId));
		request.setAttribute("typeList", typeList);
		System.out.println("typeList"+typeList);
		uri = request.getRequestDispatcher("/IndexServlet?method=showMainType");
		return uri;
	}
	
	//��ʾ���г�������
	public Object showAllMessageList(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//��ȡ��ҳ����
		PageBean<SellMessage> pb = new PageBean<SellMessage>();
		//��ȡ��ǰҳ
		String curPage =  request.getParameter("currentPage");
		System.out.println("curPage:"+curPage);
		if(curPage==null||" ".equals(curPage.trim())){
			pb.setCurrentPage(1);
		}else{
			pb.setCurrentPage(Integer.parseInt(curPage));
		}
		System.out.println("��ǰҳ��"+pb.getCurrentPage());
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
		
	
		/*//������������
		List<SellMessage> sellMessageList = userSellServiceImpl.getDatasCount();
		System.out.println("sellMessageList"+sellMessageList);
		request.setAttribute("sellMessageList", sellMessageList);*/
		uri = request.getRequestDispatcher("/IndexServlet?method=showMainType");
		return uri;
	}
	
	//��ʾ����ĳ�������
	public Object showDatasByType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String typeId = request.getParameter("typeId");
		System.out.println("typeId:"+typeId);
		AllType type =  typeManageServiceImpl.findById(Integer.parseInt(typeId));
		int mainTypeId = type.getMainType_id();
		
		//���ش����
		List<AllType> typeList = typeManageServiceImpl.findByMainType(mainTypeId);
		request.setAttribute("typeList", typeList);

		List<SellMessage> sellMessageList = userSellServiceImpl.findBytTypeIdAndCount(Integer.parseInt(typeId));
		request.setAttribute("sellMessageList", sellMessageList);
		uri = request.getRequestDispatcher("/IndexServlet?method=showMainType");
		return uri;
	}
	//��ʾ���۵���ϸ��Ϣ
	public Object showSellMessage(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String sellMessageId = request.getParameter("sellMessageId");
		SellMessage sellMessage = userSellServiceImpl.findById(Integer.parseInt(sellMessageId));
		request.setAttribute("sellMessage", sellMessage);
		
		
		uri = request.getRequestDispatcher("/app/sellMessage.jsp");
		return uri;
		
	}
	//���������,��ת���빺ҳ��
	public Object buyShowMainType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//���������
		List<MainType> mainTypeList = indexServiceImpl.findMainType();
		request.setAttribute("mainTypeList", mainTypeList);
		
		uri = request.getRequestDispatcher("/app/buyIndex.jsp");
		return uri;
	}
	//��ʾ�������ת���빺�������෽��
	public Object buyShowTypeAndData(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String mainTypeId = request.getParameter("mainTypeId");
		//���ش����
		List<AllType> typeList = typeManageServiceImpl.findByMainType(Integer.parseInt(mainTypeId));
		request.setAttribute("typeList", typeList);
		System.out.println("typeList"+typeList);
		uri = request.getRequestDispatcher("/IndexServlet?method=buyShowMainType");
		return uri;
	}
	//��ʾ�����빺����
	public Object showAllBuyMessageList(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		
		//��ȡ��ҳ����
		PageBean<BuyMessage> pb = new PageBean<BuyMessage>();
		//��ȡ��ǰҳ
		String curPage =  request.getParameter("currentPage");
		System.out.println("curPage:"+curPage);
		if(curPage==null||" ".equals(curPage.trim())){
			pb.setCurrentPage(1);
		}else{
			pb.setCurrentPage(Integer.parseInt(curPage));
		}
		System.out.println("��ǰҳ��"+pb.getCurrentPage());
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


		
		/*//������������
		List<BuyMessage> buyMessageList = userBuyServiceImpl.getAll();
		System.out.println("buyMessageList"+buyMessageList);
		request.setAttribute("buyMessageList", buyMessageList);*/
		uri = request.getRequestDispatcher("/IndexServlet?method=buyShowMainType");
		return uri;
	}
	//��ʾ������빺����
	public Object showBuyDatasByType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String typeId = request.getParameter("typeId");
		System.out.println("typeId:"+typeId);
		AllType type =  typeManageServiceImpl.findById(Integer.parseInt(typeId));
		int mainTypeId = type.getMainType_id();
		
		//���ش����
		List<AllType> typeList = typeManageServiceImpl.findByMainType(mainTypeId);
		request.setAttribute("typeList", typeList);
		
		List<BuyMessage> buyMessageList = userBuyServiceImpl.findByTypeId(Integer.parseInt(typeId));
		request.setAttribute("buyMessageList", buyMessageList);
		uri = request.getRequestDispatcher("/IndexServlet?method=buyShowMainType");
		return uri;
	}
	
	//��ʾ�빺����ϸ��Ϣ
	public Object showBuyMessage(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String buyMessageId = request.getParameter("buyMessageId");
		BuyMessage buyMessage = userBuyServiceImpl.findById(Integer.parseInt(buyMessageId));
		request.setAttribute("buyMessage", buyMessage);
		
		
		uri = request.getRequestDispatcher("/app/buyMessage.jsp");
		return uri;
		
	}


}
