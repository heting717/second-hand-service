package cn.itcast.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.factory.BeanFactory;
import cn.itcast.service.BuyService;
import cn.itcast.service.CollectService;
import cn.itcast.service.IndexService;
import cn.itcast.service.LoginService;
import cn.itcast.service.MainTypeManageService;
import cn.itcast.service.RegisterService;
import cn.itcast.service.TypeManageService;
import cn.itcast.service.UserBuyService;
import cn.itcast.service.UserMessageService;
import cn.itcast.service.UserSellService;
import cn.itcast.util.WebUtils;

public class BaseServlet extends HttpServlet {

	protected RegisterService registerServiceImpl = BeanFactory.getInstance("registerServiceImpl", RegisterService.class);
	protected LoginService loginServiceImpl = BeanFactory.getInstance("loginServiceImpl", LoginService.class);
	protected UserMessageService userServiceImpl = BeanFactory.getInstance("userMessageServiceImpl", UserMessageService.class);
	protected MainTypeManageService mainTypeManageServiceImpl = BeanFactory.getInstance("mainTypeManageServiceImpl", MainTypeManageService.class);
	protected TypeManageService typeManageServiceImpl = BeanFactory.getInstance("typeManageServiceImpl", TypeManageService.class);
	protected UserSellService userSellServiceImpl = BeanFactory.getInstance("userSellServiceImpl", UserSellService.class);
	protected UserBuyService userBuyServiceImpl = BeanFactory.getInstance("userBuyServiceImpl", UserBuyService.class);
	protected IndexService indexServiceImpl = BeanFactory.getInstance("indexServcieImpl", IndexService.class);
	protected BuyService buyServiceImpl = BeanFactory.getInstance("buyServiceImpl", BuyService.class);
	protected CollectService collectServcieImpl = BeanFactory.getInstance("collectServiceImpl", CollectService.class);
			
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("type/html;charset=utf-8");
		//���巵��ֵ
		Object returnValue = null;
		//��ȡ������
		String methodName = request.getParameter("method");
		try {
			//��ȡ��ǰ���е��࣬��ȡ��ǰ���е��ֽ���
			Class clazz = this.getClass();//getClass():���ش� Object ������ʱ�ࡣ
			//��ȡ�÷�����getDeclaredMethod:����һ�� Method ���󣬸ö���ӳ�� Class ��������ʾ�����ӿڵ�ָ��������������
			Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//���ø÷���,invoke:�Դ���ָ��������ָ����������ɴ� Method �����ʾ�ĵײ㷽����
			returnValue = method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = "/error/error.jsp";
		} 
		//��ת
		WebUtils.goTo(request, response, returnValue);
		
	}	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
