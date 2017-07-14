package cn.itcast.factory;

import java.util.ResourceBundle;

public class BeanFactory {
	private static ResourceBundle bundle;
	static{
		bundle = ResourceBundle.getBundle("instance");
	}
	
	public static <T> T getInstance(String key,Class<T> clazz){
		String className = bundle.getString(key);
		try {
			return (T)Class.forName(className).newInstance();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	/*
	 * getString(key);�Ӵ���Դ��������ĳ�������л�ȡ���������ַ���
	 * 
	 * forName(String className) : ��������и����ַ����������ӿ�������� Class ����
	 * newInstance() : ������ Class ��������ʾ�����һ����ʵ����
	 */
}

