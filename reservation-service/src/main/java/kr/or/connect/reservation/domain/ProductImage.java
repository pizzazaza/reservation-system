package kr.or.connect.reservation.domain;

public class ProductImage {
	private Integer id;
	private Integer product_id;
	private Integer file_id;
	private Integer type;
	
	public ProductImage(){
		
	}
	public ProductImage(Integer id, Integer product_id, Integer file_id, Integer type){
		this.id = id;
		this.product_id = product_id;
		this.file_id = file_id;
		this.type = type;
	}
	public ProductImage(Integer product_id, Integer type){
		this.file_id = -1;
		this.product_id = product_id;
		this.type = type;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getFile_id() {
		return file_id;
	}
	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
