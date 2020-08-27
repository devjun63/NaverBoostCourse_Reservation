package kr.or.connect.reservation.dto;

public class Promotion {
	private int id;				// 프로모션 id PK 
	private int product_id;		// 프로덕트 id FK - References product (id)
	
	public Promotion() {}
	
	public Promotion(int id, int product_id) {
		this.id = id;
		this.product_id = product_id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	@Override
	public String toString() {
		return "Promotion [id=" + id + ", product_id=" + product_id + "]";
	}
	
}
