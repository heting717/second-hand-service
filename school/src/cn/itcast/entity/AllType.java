package cn.itcast.entity;

public class AllType {
	
	private int id;//主键
	private int mainType_id;//外键，主要类别的id
	private String typeName;//次类别名
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMainType_id() {
		return mainType_id;
	}
	public void setMainType_id(int mainType_id) {
		this.mainType_id = mainType_id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "AllType [id=" + id + ", mainType_id=" + mainType_id
				+ ", typeName=" + typeName + "]";
	}
	
	
}
