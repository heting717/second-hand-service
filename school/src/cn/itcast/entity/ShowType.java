package cn.itcast.entity;

public class ShowType {
	private int id;
	private String mainTypeName;
	private String typeName;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "ShowType [id=" + id + ", mainTypeName=" + mainTypeName
				+ ", typeName=" + typeName + "]";
	}
	
	
}
