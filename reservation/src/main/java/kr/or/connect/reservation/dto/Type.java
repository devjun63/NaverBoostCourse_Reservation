package kr.or.connect.reservation.dto;

public enum Type {

	MAIN("ma"),
	THUMBNAIL("th"),
	ETC("et");

	private String type;

	Type(String type){
		this.type = type; 
	}
}
