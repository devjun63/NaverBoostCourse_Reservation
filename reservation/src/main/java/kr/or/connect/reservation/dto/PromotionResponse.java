package kr.or.connect.reservation.dto;

import java.util.List;

public class PromotionResponse {
	// description: items field 에 Promotion 들을 담는다
	private List<Promotion> items;	// 프로모션 (promotion) 모델

	public PromotionResponse() {}

	public PromotionResponse(List<Promotion> items) {
		super();
		this.items = items;
	}

	public List<Promotion> getItems() {
		return items;
	}

	public void setItems(List<Promotion> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "PromotionResponse [items=" + items + "]";
	}
	
}
