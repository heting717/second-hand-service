package cn.itcast.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.entity.AllType;
import cn.itcast.entity.BuyMessage;
import cn.itcast.entity.MainType;
import cn.itcast.entity.SellMessage;
import cn.itcast.entity.ShowType;
import cn.itcast.entity.User;

public class UserSellServlet extends BaseServlet {

	//���������Ϣ�����������ʹ������
	public Object toSellMessage(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		
		//��ȡ�û�
		String userId = request.getParameter("userId");
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user",user);
		//��ȡ�������
		List<ShowType> typeList = typeManageServiceImpl.getAll();
		request.setAttribute("typeList", typeList);
		uri = request.getRequestDispatcher("/app/user/userSell.jsp");
		return uri;
	}
	//���뽻�������Ϣҳ��
	public Object toSellSituation(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//��ȡ�û�id
		String userId = request.getParameter("userId");
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		System.out.println("user:"+user);
		request.setAttribute("user",user);
		//�����б�
		List<SellMessage> sellList = userSellServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("sellList", sellList);
		//�빺�б�
		List<BuyMessage> buyList = userBuyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("buyList", buyList);
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
	//�����Ϣ����
	public Object addSellMessage(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		SellMessage sellMessage = new SellMessage();
		Date dateTime = new Date();//��ȡ��ǰʱ��
		sellMessage.setUpTime(dateTime);
		String userId = request.getParameter("userId");
		sellMessage.setUserId(Integer.parseInt(userId));
		
		
		
		
		//ͼƬ�ϴ�
		try {
			// 1. ������������
			FileItemFactory factory = new DiskFileItemFactory();
			// 2. �ļ��ϴ����Ĺ�����
			ServletFileUpload upload = new ServletFileUpload(factory);
			// ���ô�С���Ʋ���
			upload.setFileSizeMax(10*1024*1024);	// �����ļ���С����
			upload.setSizeMax(50*1024*1024);		// ���ļ���С����
			upload.setHeaderEncoding("UTF-8");		// �������ļ����봦��

			// �жϱ����ͣ��Ƿ�Ϊ�ϴ���
			if (upload.isMultipartContent(request)) {
				// 3. ����������ת��Ϊlist����
				List<FileItem> list = upload.parseRequest(request);
				String buyTimeMouth = null;
				String buyTimeYear = null;
				// ����
				for (FileItem item : list){
					// �жϣ���ͨ�ı�����
					if (item.isFormField()){
						
						// ��ȡ����
						String name = item.getFieldName();
						System.out.println("name:"+name);
						// ��ȡֵ
						String value = item.getString("utf-8");//Ҫ���ñ���utf-8�����������
						System.out.println(value);
						
						if("typeId".equals(name)){
							sellMessage.setType_id(Integer.parseInt(value));
						}else if("sellName".equals(name)){
							sellMessage.setSellName(value);
						}else if("brandName".equals(name)){
							sellMessage.setBrandName(value);
						}else if("buyTimeYear".equals(name)){
							buyTimeYear = value;
						}else if("buyTimeMouth".equals(name)){
							buyTimeMouth = value;
						}else if("buyPrice".equals(name)){
							sellMessage.setBuyPrice(Integer.parseInt(value));
						}else if("sellPrice".equals(name)){
							sellMessage.setSellPrice(Integer.parseInt(value));
						}else if("sellCount".equals(name)){
							sellMessage.setSellCount(Integer.parseInt(value));
						}else if("describe".equals(name)){
							sellMessage.setGoodsDescribe(value);
						}
					} 
					// �ļ�����
					else {
						/******** �ļ��ϴ� ***********/
						// a. ��ȡ�ļ�����
						String name = item.getName();
						
						// ----�����ϴ��ļ�����������----
						// a1. �ȵõ�Ψһ���
						/*String id = UUID.randomUUID().toString();
						// a2. ƴ���ļ���
						name = id + "#" + name;		*/
						System.out.println("name:"+name);
						sellMessage.setImagePath(name);
						// b. �õ��ϴ�Ŀ¼
						String basePath = getServletContext().getRealPath("/upload/userImage");
						System.out.println("path:"+basePath);
						// c. ����Ҫ�ϴ����ļ�����
						File file = new File(basePath,name);
						
						
				        
						// d. �ϴ�
						item.write(file);
						item.delete();  // ɾ���������ʱ��������ʱ�ļ�
					}
					
				}
				String buyTime = buyTimeYear +"-"+buyTimeMouth;
				sellMessage.setBuyTime(buyTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("sellMessage :"+sellMessage);
		
		
		
		
	/*	String userId = request.getParameter("userId");
		String typeId = request.getParameter("typeId");
		String sellName = request.getParameter("sellName");
		String brandName = request.getParameter("brandName");
		String buyTimeYear = request.getParameter("buyTimeYear");
		String buyTimeMouth =request.getParameter("buyTimeMouth");
		String buyTime = buyTimeYear+"-"+buyTimeMouth;
		
		String buyPrice = request.getParameter("buyPrice");
		String sellPrice = request.getParameter("sellPrice");
		System.out.println("sellPrice:"+sellPrice);
		String sellCount = request.getParameter("sellCount");
		String describe = request.getParameter("describe");
		Date dateTime = new Date();//��ȡ��ǰʱ��
		sellMessage.setUserId(Integer.parseInt(userId));
		sellMessage.setType_id(Integer.parseInt(typeId));
		sellMessage.setSellName(sellName);
		sellMessage.setBrandName(brandName);
		sellMessage.setBuyTime(buyTime);
		sellMessage.setBuyPrice(Integer.parseInt(buyPrice));
		sellMessage.setSellPrice(Integer.parseInt(sellPrice));
		System.out.println("getSellPrice:"+sellMessage.getSellPrice());
		sellMessage.setSellCount(Integer.parseInt(sellCount));
		sellMessage.setGoodsDescribe(describe);
		sellMessage.setUpTime(dateTime);*/
		System.out.println("sellMessage:"+sellMessage);
		
		userSellServiceImpl.save(sellMessage);
		
		
		
	
		
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		System.out.println("user:"+user);
		request.setAttribute("user",user);
		List<SellMessage> sellList = userSellServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("sellList", sellList);
		//�빺�б�
		List<BuyMessage> buyList = userBuyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("buyList", buyList);
		
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
	//�����޸�ҳ���޸�
	public Object toUpdateSell(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		
		//������
		List<ShowType> typeList = typeManageServiceImpl.getAll();
		request.setAttribute("typeList", typeList);
		System.out.println("typeList"+typeList);
		//��ȡ��Ϣ
		String sellMessageId = request.getParameter("sellMessageId");
		System.out.println("sellMessageId:"+sellMessageId);
		SellMessage sellMessage = userSellServiceImpl.findById(Integer.parseInt(sellMessageId));
		System.out.println("sellMessage   "+sellMessage);
		//�������id�����������
		int typeId =  sellMessage.getType_id();
		AllType type = typeManageServiceImpl.findById(typeId);
		String typeName = type.getTypeName();
		sellMessage.setTypeName(typeName);
		System.out.println("typeName"+typeName);
		//�ϴ����������
		String buyTime = sellMessage.getBuyTime();
		System.out.println("buyTime"+buyTime);
		String buyTimeYear= buyTime.substring(0, 4);
		System.out.println("buyTimeYear"+buyTimeYear);
		String buyTimeMouth = buyTime.substring(5);
		System.out.println("buyTimeMouth"+buyTimeMouth);
		sellMessage.setBuyTimeYear(buyTimeYear);
		sellMessage.setBuyTimeMouth(buyTimeMouth);
		
		
		
		//��������
		request.setAttribute("sellMessage", sellMessage);
		System.out.println("sellMessage:"+sellMessage);
		
		String userId = request.getParameter("userId");
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		System.out.println("user:"+user);
		request.setAttribute("user",user);
		
		/*//��ȡ���۱�
		List<SellMessage> sellList = userSellServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("sellList", sellList);
		//�빺�б�
		List<BuyMessage> buyList = userBuyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("buyList", buyList);
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
		request.setAttribute("buySolveList", buySolveList);*/
		uri = request.getRequestDispatcher("/app/user/userSellUpdate.jsp");
		return uri;
	}
	//�����޸�
	public Object updateSell(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String sellMessageId = request.getParameter("sellMessageId");
		SellMessage sellMessage =userSellServiceImpl.findById(Integer.parseInt(sellMessageId));
		System.out.println("sellM:"+sellMessage);
		Date dateTime = new Date();//��ȡ��ǰʱ��
		sellMessage.setUpTime(dateTime);
		
		//ͼƬ�ϴ�
		try {
			// 1. ������������
			FileItemFactory factory = new DiskFileItemFactory();
			// 2. �ļ��ϴ����Ĺ�����
			ServletFileUpload upload = new ServletFileUpload(factory);
			// ���ô�С���Ʋ���
			upload.setFileSizeMax(10*1024*1024);	// �����ļ���С����
			upload.setSizeMax(50*1024*1024);		// ���ļ���С����
			upload.setHeaderEncoding("UTF-8");		// �������ļ����봦��

			// �жϱ����ͣ��Ƿ�Ϊ�ϴ���
			if (upload.isMultipartContent(request)) {
				// 3. ����������ת��Ϊlist����
				List<FileItem> list = upload.parseRequest(request);
				String buyTimeMouth = null;
				String buyTimeYear = null;
				// ����
				for (FileItem item : list){
					// �жϣ���ͨ�ı�����
					if (item.isFormField()){
						
						// ��ȡ����
						String name = item.getFieldName();
						System.out.println("name:"+name);
						// ��ȡֵ
						String value = item.getString("utf-8");//Ҫ���ñ���utf-8�����������
						System.out.println(value);
						if("typeId".equals(name)){
							sellMessage.setType_id(Integer.parseInt(value));
						}else if("sellName".equals(name)){
							sellMessage.setSellName(value);
						}else if("brandName".equals(name)){
							sellMessage.setBrandName(value);
						}else if("buyTimeYear".equals(name)){
							buyTimeYear = value;
						}else if("buyTimeMouth".equals(name)){
							buyTimeMouth = value;
						}else if("buyPrice".equals(name)){
							sellMessage.setBuyPrice(Integer.parseInt(value));
						}else if("sellPrice".equals(name)){
							sellMessage.setSellPrice(Integer.parseInt(value));
						}else if("sellCount".equals(name)){
							sellMessage.setSellCount(Integer.parseInt(value));
						}else if("describe".equals(name)){
							sellMessage.setGoodsDescribe(value);
						}
					} 
					// �ļ�����
					else {
						/******** �ļ��ϴ� ***********/
						// a. ��ȡ�ļ�����
						String name = item.getName();
						
						// ----�����ϴ��ļ�����������----
						// a1. �ȵõ�Ψһ���
						/*String id = UUID.randomUUID().toString();
						// a2. ƴ���ļ���
						name = id + "#" + name;		*/
						System.out.println("name:"+name);
						sellMessage.setImagePath(name);
						// b. �õ��ϴ�Ŀ¼
						String basePath = getServletContext().getRealPath("/upload/userImage");
						System.out.println("path:"+basePath);
						// c. ����Ҫ�ϴ����ļ�����
						File file = new File(basePath,name);
						
						
				        
						// d. �ϴ�
						item.write(file);
						item.delete();  // ɾ���������ʱ��������ʱ�ļ�
					}
					
				}
				String buyTime = buyTimeYear +"-"+buyTimeMouth;
				sellMessage.setBuyTime(buyTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("sellMessage :"+sellMessage);
		
	
		userSellServiceImpl.update(sellMessage);
		System.out.println("�������ݳɹ���");
		
		request.setAttribute("sellMessage", sellMessage);
		
		System.out.println("ͷ��·����"+sellMessage.getImagePath());
				
		
		
		//userSellServiceImpl.update(sellMessage);
		
		String userId = request.getParameter("userId");
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		System.out.println("user:"+user);
		request.setAttribute("user",user);
		
		
		List<SellMessage> sellList = userSellServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("sellList", sellList);
		//�빺�б�
		List<BuyMessage> buyList = userBuyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("buyList", buyList);
		
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
		String sellMessageId = request.getParameter("sellMessageId");
		System.out.println("sellMessageId:"+sellMessageId);
		userSellServiceImpl.delete(Integer.parseInt(sellMessageId));
		
		String userId = request.getParameter("userId");
		System.out.println("userId"+userId);
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user",user);
		
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
