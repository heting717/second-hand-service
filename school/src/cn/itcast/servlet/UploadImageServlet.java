package cn.itcast.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadImageServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1.使用文件上传工厂
			FileItemFactory factory = new DiskFileItemFactory();
			//2.创建文件上传核心工具类
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//设置单个文件允许的最大大小
			upload.setFileSizeMax(30);
			//设置文件上传表单允许的最大大小
			upload.setSizeMax(80);
			//设置上传表单文件名编码
			upload.setHeaderEncoding("utf-8");
			
			//3.判断：当前表单是否为文件上传表单
			if(upload.isMultipartContent(request)){
				//将数据转换为一个FileItem对象，再用集合封装
				List<FileItem> list = upload.parseRequest(request);
				//遍历：得到每一个上传数据
				for(FileItem item:list){
					//判断：是普通文本还是文件上传文本
					if(item.isFormField()){//普通文本
						String name = item.getFieldName();//表单元素名称
						String value = item.getString();//该表单元素对应的值
						System.out.println("name:"+name+"value:"+value);
					}else{//上传文件
						String name = item.getFieldName();//表单元素名称
						String value = item.getString();//对应的值
						String fileName = item.getName();//文件名称
						String type = item.getContentType();//文件类型
						InputStream in = item.getInputStream();//上传文件流
						
						//随机产生一个随机标记
						String id = UUID.randomUUID().toString();
						fileName = id +"#"+fileName;
						//获取上传路劲
						String path = getServletContext().getRealPath("/app/upload");
						
						//创建目标文件
						File file = new File(path,fileName);
						//文件上传
						item.write(file);
						item.delete();//删除系统产生的临时文件
					}
				}
				
				
				
			}else{
				System.out.println("当前表单不是文件上传表单，上传失败！");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
	}
	//获取上传数据
	public void upload(HttpServletRequest request) throws IOException{
		//获取表单数据流
		InputStream in = request.getInputStream();
		//转换流
		InputStreamReader inputStream = new InputStreamReader(in,"utf-8");
		//缓冲流
		BufferedReader reader = new BufferedReader(inputStream);
		
		//输出数据
		String str = null;
		while((str = reader.readLine())!=null){
			System.out.println(str);
		}
		//关闭
		reader.close();
		inputStream.close();
		in.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
