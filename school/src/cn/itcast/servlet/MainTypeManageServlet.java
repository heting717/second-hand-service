package cn.itcast.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.entity.MainType;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.MainTypeManageService;

public class MainTypeManageServlet extends BaseServlet {


	//��ʾ������б�ҳ��
	public Object showMainTypeList(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		List<MainType> mainTypeList=mainTypeManageServiceImpl.getAll();
		request.setAttribute("mainTypeList", mainTypeList);
		uri = request.getRequestDispatcher("/sys/mainTypeList.jsp");
		return uri;
	}
	//��������
	public Object addMainType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String mainTypeName =request.getParameter("mainTypeName");
		
		mainTypeManageServiceImpl.save(mainTypeName);
		uri = request.getRequestDispatcher("/MainTypeManageServlet?method=showMainTypeList");
		return uri;
	}
	//�����޸�ҳ��
	public Object toUpdateMainType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String mainTypeId = request.getParameter("mainTypeId");
		MainType mainType = mainTypeManageServiceImpl.findById(Integer.parseInt(mainTypeId));
		request.setAttribute("mainType", mainType);
		uri = request.getRequestDispatcher("/sys/mainType/updateMainType.jsp");
		return uri;
	}
	//�޸�
	public Object updateMainType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String mainTypeId = request.getParameter("mainTypeId");
		String mainTypeName = request.getParameter("mainTypeName");
		mainTypeManageServiceImpl.update(mainTypeName, Integer.parseInt(mainTypeId));
		uri = request.getRequestDispatcher("/MainTypeManageServlet?method=showMainTypeList");
		return uri;
	}
	//ɾ��
	public Object deleteMainType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String mainTypeId = request.getParameter("mainTypeId");
		mainTypeManageServiceImpl.delete(Integer.parseInt(mainTypeId));
		uri = request.getRequestDispatcher("/MainTypeManageServlet?method=showMainTypeList");
		return uri;
	}
	

}
