package kr.or.connect.reservation.domain;

public class MainProduct {
	String title;
	String description;
	String location;
	String imgUrl;
	
	public MainProduct(){
		
	}
	public MainProduct(String ti, String des, String loc){
		this.title = ti;
		this.description = des;
		this.location = loc;
		this.imgUrl = "/resources/img/testimg.png";
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	
}
