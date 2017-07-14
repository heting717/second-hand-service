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

	//��ʾ������Ϣҳ��
	public Object showInformation(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String userId = request.getParameter("userId");
		System.out.println("userId:"+userId);
		User user = userMessageServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user", user);
		uri = request.getRequestDispatcher("/app/user/user.jsp");
		return uri;
	}
	//�鿴������Ϣ
	public Object checkInformation(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String userId = request.getParameter("userId");
		System.out.println("userId:"+userId);
		User user = userMessageServiceImpl.findById(Integer.parseInt(userId));
		/*HttpSession session = request.getSession();*/
		request.setAttribute("user", user);
		System.out.println("ͷ��·����"+user.getImagePath());
		uri = request.getRequestDispatcher("/app/user/userMessage.jsp");
		return uri;
	}
	
	//�޸ĸ�����Ϣ
	public Object updateInformation(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String userId = request.getParameter("userId");
		User user = userMessageServiceImpl.findById(Integer.parseInt(userId));
		System.out.println("user  "+user);
		
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
						user.setImagePath(name);
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("user:"+user);
		
		
		userMessageServiceImpl.update(user);
		System.out.println("�������ݳɹ���");
		
		request.setAttribute("user", user);
		
		System.out.println("ͷ��·����"+user.getImagePath());
		
		uri = request.getRequestDispatcher("/app/user/userMessage.jsp");
		return uri;
	}
	//�����޸�����ҳ��
	public Object toUpdatePwd(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String userId = request.getParameter("userId");
		User user = userMessageServiceImpl.findById(Integer.parseInt(userId));
		request.setAttribute("user", user);
		uri = request.getRequestDispatcher("/app/user/updatePwd.jsp");
		return uri;
	}
	//�޸�����
	public Object updatePwd(HttpServletRequest request,HttpServletResponse response){
		Object uri = null;
		String userId = request.getParameter("userId");
		User user = userMessageServiceImpl.findById(Integer.parseInt(userId));
		String oldPwd = request.getParameter("oldPwd");
		if(user.getUserPassword().equals(oldPwd)){
			String newPwd = request.getParameter("newPwd");
			userMessageServiceImpl.updatePwd(newPwd, Integer.parseInt(userId));
			System.out.println("�����޸ĳɹ���");
		}else{
			System.out.println("�������");
		}
		uri = request.getRequestDispatcher("/app/user/userMessage.jsp");
		return uri;
	}
	
}
