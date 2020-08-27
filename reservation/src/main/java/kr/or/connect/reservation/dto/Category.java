package kr.or.connect.reservation.dto;

public class Category {
	private int id;			// 카테고리 id & PK
	private String name;	// 카테고리 이름
	
	public Category() {}
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
}
