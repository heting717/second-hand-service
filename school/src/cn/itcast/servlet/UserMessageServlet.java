package cn.itcast.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.entity.User;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.UserMessageService;

public class UserMessageServlet extends BaseServlet {
	
	private UserMessageService userMessageServiceImpl = BeanFactory.getInstance("userMessageServiceImpl", UserMessageService.class); 

	//显示个人信息页面
	public Object showInformation(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String userId = request.getParameter("userId");
		System.out.println("userId:"+userId);
		User user = userMessageServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user", user);
		uri = request.getRequestDispatcher("/app/user/user.jsp");
		return uri;
	}
	//查看个人信息
	public Object checkInformation(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String userId = request.getParameter("userId");
		System.out.println("userId:"+userId);
		User user = userMessageServiceImpl.findById(Integer.parseInt(userId));
		/*HttpSession session = request.getSession();*/
		request.setAttribute("user", user);
		System.out.println("头像路径："+user.getImagePath());
		uri = request.getRequestDispatcher("/app/user/userMessage.jsp");
		return uri;
	}
	
	//修改个人信息
	public Object updateInformation(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String userId = request.getParameter("userId");
		User user = userMessageServiceImpl.findById(Integer.parseInt(userId));
		System.out.println("user  "+user);
		
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
						if("userphone".equals(name)){
							user.setPhone(value);
						}else if("mail".equals(name)){
							user.setMail(value);
						}else if("college".equals(name)){
							user.setSchool(value);
						}else if("inTime".equals(name)){
							user.setInTime(value);
						}else if("outTime".equals(name)){
							user.setOutTime(value);
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
						user.setImagePath(name);
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("user:"+user);
		
		
		userMessageServiceImpl.update(user);
		System.out.println("更新数据成功！");
		
		request.setAttribute("user", user);
		
		System.out.println("头像路径："+user.getImagePath());
		
		uri = request.getRequestDispatcher("/app/user/userMessage.jsp");
		return uri;
	}
	//进入修改密码页面
	public Object toUpdatePwd(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String userId = request.getParameter("userId");
		User user = userMessageServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user", user);
		uri = request.getRequestDispatcher("/app/user/updatePwd.jsp");
		return uri;
	}
	//修改密码
	public Object updatePwd(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String userId = request.getParameter("userId");
		User user = userMessageServiceImpl.findById(Integer.parseInt(userId));
		String oldPwd = request.getParameter("oldPwd");
		if(user.getUserPassword().equals(oldPwd)){
			String newPwd = request.getParameter("newPwd");
			userMessageServiceImpl.updatePwd(newPwd, Integer.parseInt(userId));
			System.out.println("密码修改成功！");
		}else{
			System.out.println("密码错误！");
		}
		uri = request.getRequestDispatcher("/app/user/userMessage.jsp");
		return uri;
	}
	
}
