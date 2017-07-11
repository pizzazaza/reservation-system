package kr.or.connect.reservation.domain;

public class Category {
	private String name;
	private Integer id;
	
	public Category(){
		
	}
	
	public Category(Integer Id, String Name){
		this.id = Id;
		this.name = Name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
