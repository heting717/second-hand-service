package cn.itcast.entity;

public class MainType {
	
	private int id;//����
	private String mainTypeName;//�����
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMainTypeName() {
		return mainTypeName;
	}
	public void setMainTypeName(String mainTypeName) {
		this.mainTypeName = mainTypeName;
	}
	@Override
	public String toString() {
		return "MainType [id=" + id + ", mainTypeName=" + mainTypeName + "]";
	}
	
}
