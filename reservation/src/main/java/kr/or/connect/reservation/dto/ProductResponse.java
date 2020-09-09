package kr.or.connect.reservation.dto;

import java.util.List;

public class ProductResponse {
	/* description: items field 에 전시상품전시상품 (Product) 들을 담는다.
	   totalCount 는 카테고리에 해당하는 전체 상품 수이다.
	 */
	private List<Product> items; 	// 상품 (Product) 모델
	private int totalCount;			// 카테고리에 해당하는 전체 상품 수
	
	public ProductResponse() {}
	
	public ProductResponse(List<Product> items, int totalCount) {
		super();
		this.items = items;
		this.totalCount = totalCount;
	}

	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "ProductResponse [items=" + items + ", totalCount=" + totalCount + "]";
	}
	
}
