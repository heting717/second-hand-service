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
	 * getString(key);从此资源包或它的某个父包中获取给定键的字符串
	 * 
	 * forName(String className) : 返回与带有给定字符串名的类或接口相关联的 Class 对象。
	 * newInstance() : 创建此 Class 对象所表示的类的一个新实例。
	 */
}

