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
			//1.ʹ���ļ��ϴ�����
			FileItemFactory factory = new DiskFileItemFactory();
			//2.�����ļ��ϴ����Ĺ�����
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//���õ����ļ����������С
			upload.setFileSizeMax(30);
			//�����ļ��ϴ������������С
			upload.setSizeMax(80);
			//�����ϴ����ļ�������
			upload.setHeaderEncoding("utf-8");
			
			//3.�жϣ���ǰ���Ƿ�Ϊ�ļ��ϴ���
			if(upload.isMultipartContent(request)){
				//������ת��Ϊһ��FileItem�������ü��Ϸ�װ
				List<FileItem> list = upload.parseRequest(request);
				//�������õ�ÿһ���ϴ�����
				for(FileItem item:list){
					//�жϣ�����ͨ�ı������ļ��ϴ��ı�
					if(item.isFormField()){//��ͨ�ı�
						String name = item.getFieldName();//��Ԫ������
						String value = item.getString();//�ñ�Ԫ�ض�Ӧ��ֵ
						System.out.println("name:"+name+"value:"+value);
					}else{//�ϴ��ļ�
						String name = item.getFieldName();//��Ԫ������
						String value = item.getString();//��Ӧ��ֵ
						String fileName = item.getName();//�ļ�����
						String type = item.getContentType();//�ļ�����
						InputStream in = item.getInputStream();//�ϴ��ļ���
						
						//�������һ��������
						String id = UUID.randomUUID().toString();
						fileName = id +"#"+fileName;
						//��ȡ�ϴ�·��
						String path = getServletContext().getRealPath("/app/upload");
						
						//����Ŀ���ļ�
						File file = new File(path,fileName);
						//�ļ��ϴ�
						item.write(file);
						item.delete();//ɾ��ϵͳ��������ʱ�ļ�
					}
				}
				
				
				
			}else{
				System.out.println("��ǰ�������ļ��ϴ������ϴ�ʧ�ܣ�");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
	}
	//��ȡ�ϴ�����
	public void upload(HttpServletRequest request) throws IOException{
		//��ȡ��������
		InputStream in = request.getInputStream();
		//ת����
		InputStreamReader inputStream = new InputStreamReader(in,"utf-8");
		//������
		BufferedReader reader = new BufferedReader(inputStream);
		
		//�������
		String str = null;
		while((str = reader.readLine())!=null){
			System.out.println(str);
		}
		//�ر�
		reader.close();
		inputStream.close();
		in.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
