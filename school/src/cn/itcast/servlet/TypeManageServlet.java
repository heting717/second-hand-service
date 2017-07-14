package cn.itcast.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.entity.AllType;
import cn.itcast.entity.MainType;
import cn.itcast.entity.ShowType;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.TypeManageService;

public class TypeManageServlet extends BaseServlet {

	
	
	//显示类别列表页面
	public Object typeList(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		List<ShowType> typeList = typeManageServiceImpl.getAll();
		request.setAttribute("typeList", typeList);
		uri = request.getRequestDispatcher("/sys/allTypeList.jsp");
		return uri;
	}
	//进入添加页面
	public Object addTypeList(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		
		List<MainType> mainTypeList = mainTypeManageServiceImpl.getAll();
		System.out.println("mainTypeList:"+mainTypeList);
		request.setAttribute("mainTypeList", mainTypeList);
		uri = request.getRequestDispatcher("/sys/allType/addType.jsp");
		return uri;
	}
	//添加
	public Object addType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String mainTypeId = request.getParameter("mainTypeId");
		
		System.out.println("mainTypeId:"+mainTypeId);
		String typeName = request.getParameter("typeName");
		System.out.println("typeName:"+typeName);
		
		typeManageServiceImpl.save(Integer.parseInt(mainTypeId), typeName);
		uri = request.getRequestDispatcher("/TypeManageServlet?method=typeList");
		return uri;
	}
	//进入修改页面
	public Object toUpdateType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//添加主类别
		MainType mainType = new MainType();
		List<MainType> mainTypeList = mainTypeManageServiceImpl.getAll();
		request.setAttribute("mainTypeList", mainTypeList);
		
		String typeId = request.getParameter("typeId");
		AllType type = typeManageServiceImpl.findById(Integer.parseInt(typeId));
		//对应主类别
		int mainTypeId = type.getMainType_id();
		MainType mainType1 = mainTypeManageServiceImpl.findById(mainTypeId);
		String mainTypeName = mainType1.getMainTypeName();
		request.setAttribute("mainTypeName", mainTypeName);
		//类别
		
		request.setAttribute("type", type);
		uri = request.getRequestDispatcher("/sys/allType/updateType.jsp");
		return uri;
	}
	//修改
	public Object updateType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String typeId = request.getParameter("typeId");
		String mainTypeId = request.getParameter("mainTypeId");
		String typeName = request.getParameter("typeName");
		typeManageServiceImpl.update(Integer.parseInt(mainTypeId), typeName, Integer.parseInt(typeId));
		uri = request.getRequestDispatcher("/TypeManageServlet?method=typeList");
		return uri;
	}
	//删除
	public Object deleteType(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String typeId = request.getParameter("typeId");
		typeManageServiceImpl.delete(Integer.parseInt(typeId));
		uri = request.getRequestDispatcher("/TypeManageServlet?method=typeList");
		return uri;
	}

}
