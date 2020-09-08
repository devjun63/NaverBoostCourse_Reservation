package kr.or.connect.reservation.dto;

public class Category {
	private int id;			// Category Id
	private String name;	// Category Name
	private int count;		// Category 에 속한 전시상품 수
	
	public Category() {}

	public Category(int id, String name, int count) {
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
