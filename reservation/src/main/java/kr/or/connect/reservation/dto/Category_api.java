package kr.or.connect.reservation.dto;

public class Category_api {
	private int id;			// 카테고리 id & PK
	private String name;	// 카테고리 이름\
	private int count;		// 카테고리 당 보유 프로덕트 개수
	
	public Category_api() {}

	public Category_api(int id, String name, int count) {
		this.id = id;
		this.name = name;
		this.count = count;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Category_api [id=" + id + ", name=" + name + ", count=" + count + "]";
	}
	
}
