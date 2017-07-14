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

	//进入出售信息，加载主类别和次类别类
	public Object toSellMessage(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		
		//获取用户
		String userId = request.getParameter("userId");
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user",user);
		//获取所有类别
		List<ShowType> typeList = typeManageServiceImpl.getAll();
		request.setAttribute("typeList", typeList);
		uri = request.getRequestDispatcher("/app/user/userSell.jsp");
		return uri;
	}
	//进入交易情况信息页面
	public Object toSellSituation(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		//获取用户id
		String userId = request.getParameter("userId");
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		System.out.println("user:"+user);
		request.setAttribute("user",user);
		//出售列表
		List<SellMessage> sellList = userSellServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("sellList", sellList);
		//想购列表
		List<BuyMessage> buyList = userBuyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("buyList", buyList);
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
	//添加信息保存
	public Object addSellMessage(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		SellMessage sellMessage = new SellMessage();
		Date dateTime = new Date();//获取当前时间
		sellMessage.setUpTime(dateTime);
		String userId = request.getParameter("userId");
		sellMessage.setUserId(Integer.parseInt(userId));
		
		
		
		
		//图片上传
		try {
			// 1. 创建工厂对象
			FileItemFactory factory = new DiskFileItemFactory();
			// 2. 文件上传核心工具类
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置大小限制参数
			upload.setFileSizeMax(10*1024*1024);	// 单个文件大小限制
			upload.setSizeMax(50*1024*1024);		// 总文件大小限制
			upload.setHeaderEncoding("UTF-8");		// 对中文文件编码处理

			// 判断表单类型，是否为上传表单
			if (upload.isMultipartContent(request)) {
				// 3. 把请求数据转换为list集合
				List<FileItem> list = upload.parseRequest(request);
				String buyTimeMouth = null;
				String buyTimeYear = null;
				// 遍历
				for (FileItem item : list){
					// 判断：普通文本数据
					if (item.isFormField()){
						
						// 获取名称
						String name = item.getFieldName();
						System.out.println("name:"+name);
						// 获取值
						String value = item.getString("utf-8");//要设置编码utf-8，否则会乱码
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
					// 文件表单项
					else {
						/******** 文件上传 ***********/
						// a. 获取文件名称
						String name = item.getName();
						
						// ----处理上传文件名重名问题----
						// a1. 先得到唯一标记
						/*String id = UUID.randomUUID().toString();
						// a2. 拼接文件名
						name = id + "#" + name;		*/
						System.out.println("name:"+name);
						sellMessage.setImagePath(name);
						// b. 得到上传目录
						String basePath = getServletContext().getRealPath("/upload/userImage");
						System.out.println("path:"+basePath);
						// c. 创建要上传的文件对象
						File file = new File(basePath,name);
						
						
				        
						// d. 上传
						item.write(file);
						item.delete();  // 删除组件运行时产生的临时文件
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
		Date dateTime = new Date();//获取当前时间
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
		//想购列表
		List<BuyMessage> buyList = userBuyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("buyList", buyList);
		
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
	//进入修改页面修改
	public Object toUpdateSell(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		
		//添加类别
		List<ShowType> typeList = typeManageServiceImpl.getAll();
		request.setAttribute("typeList", typeList);
		System.out.println("typeList"+typeList);
		//获取信息
		String sellMessageId = request.getParameter("sellMessageId");
		System.out.println("sellMessageId:"+sellMessageId);
		SellMessage sellMessage = userSellServiceImpl.findById(Integer.parseInt(sellMessageId));
		System.out.println("sellMessage   "+sellMessage);
		//根据类别id查找类别名称
		int typeId =  sellMessage.getType_id();
		AllType type = typeManageServiceImpl.findById(typeId);
		String typeName = type.getTypeName();
		sellMessage.setTypeName(typeName);
		System.out.println("typeName"+typeName);
		//上传购买的年月
		String buyTime = sellMessage.getBuyTime();
		System.out.println("buyTime"+buyTime);
		String buyTimeYear= buyTime.substring(0, 4);
		System.out.println("buyTimeYear"+buyTimeYear);
		String buyTimeMouth = buyTime.substring(5);
		System.out.println("buyTimeMouth"+buyTimeMouth);
		sellMessage.setBuyTimeYear(buyTimeYear);
		sellMessage.setBuyTimeMouth(buyTimeMouth);
		
		
		
		//设置属性
		request.setAttribute("sellMessage", sellMessage);
		System.out.println("sellMessage:"+sellMessage);
		
		String userId = request.getParameter("userId");
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		System.out.println("user:"+user);
		request.setAttribute("user",user);
		
		/*//获取出售表
		List<SellMessage> sellList = userSellServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("sellList", sellList);
		//想购列表
		List<BuyMessage> buyList = userBuyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("buyList", buyList);
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
		request.setAttribute("buySolveList", buySolveList);*/
		uri = request.getRequestDispatcher("/app/user/userSellUpdate.jsp");
		return uri;
	}
	//保存修改
	public Object updateSell(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String sellMessageId = request.getParameter("sellMessageId");
		SellMessage sellMessage =userSellServiceImpl.findById(Integer.parseInt(sellMessageId));
		System.out.println("sellM:"+sellMessage);
		Date dateTime = new Date();//获取当前时间
		sellMessage.setUpTime(dateTime);
		
		//图片上传
		try {
			// 1. 创建工厂对象
			FileItemFactory factory = new DiskFileItemFactory();
			// 2. 文件上传核心工具类
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置大小限制参数
			upload.setFileSizeMax(10*1024*1024);	// 单个文件大小限制
			upload.setSizeMax(50*1024*1024);		// 总文件大小限制
			upload.setHeaderEncoding("UTF-8");		// 对中文文件编码处理

			// 判断表单类型，是否为上传表单
			if (upload.isMultipartContent(request)) {
				// 3. 把请求数据转换为list集合
				List<FileItem> list = upload.parseRequest(request);
				String buyTimeMouth = null;
				String buyTimeYear = null;
				// 遍历
				for (FileItem item : list){
					// 判断：普通文本数据
					if (item.isFormField()){
						
						// 获取名称
						String name = item.getFieldName();
						System.out.println("name:"+name);
						// 获取值
						String value = item.getString("utf-8");//要设置编码utf-8，否则会乱码
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
					// 文件表单项
					else {
						/******** 文件上传 ***********/
						// a. 获取文件名称
						String name = item.getName();
						
						// ----处理上传文件名重名问题----
						// a1. 先得到唯一标记
						/*String id = UUID.randomUUID().toString();
						// a2. 拼接文件名
						name = id + "#" + name;		*/
						System.out.println("name:"+name);
						sellMessage.setImagePath(name);
						// b. 得到上传目录
						String basePath = getServletContext().getRealPath("/upload/userImage");
						System.out.println("path:"+basePath);
						// c. 创建要上传的文件对象
						File file = new File(basePath,name);
						
						
				        
						// d. 上传
						item.write(file);
						item.delete();  // 删除组件运行时产生的临时文件
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
		System.out.println("更新数据成功！");
		
		request.setAttribute("sellMessage", sellMessage);
		
		System.out.println("头像路径："+sellMessage.getImagePath());
				
		
		
		//userSellServiceImpl.update(sellMessage);
		
		String userId = request.getParameter("userId");
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		System.out.println("user:"+user);
		request.setAttribute("user",user);
		
		
		List<SellMessage> sellList = userSellServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("sellList", sellList);
		//想购列表
		List<BuyMessage> buyList = userBuyServiceImpl.getAll(Integer.parseInt(userId));
		request.setAttribute("buyList", buyList);
		
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
		String sellMessageId = request.getParameter("sellMessageId");
		System.out.println("sellMessageId:"+sellMessageId);
		userSellServiceImpl.delete(Integer.parseInt(sellMessageId));
		
		String userId = request.getParameter("userId");
		System.out.println("userId"+userId);
		User user = userServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user",user);
		
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
