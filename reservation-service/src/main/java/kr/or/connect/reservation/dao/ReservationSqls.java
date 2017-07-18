package kr.or.connect.reservation.dao;

public class ReservationSqls {
	final static public String SELECT_CATEGORY_ALL = "select id,name from category";
	final static public String DELETE_BY_ID = "delete from category where id =:id";
	final static public String INSERT_CATEGORY = "insert into category(name) values(:name)";
	
	final static public String SELECT_PRODUCT_LIMIT = ""
			+ "											select pro.name, pro.description, dis.place_name "
			+ "											from product pro, display_info dis "
			+ "											where pro.id = dis.product_id "
			+ "											group by pro.id, dis.id "
			+ "											limit :limi offset :ofs";
	final static public String SELECT_PRODUCT_LIMIT_ById = ""
			+ "											select pro.name, pro.description, dis.place_name "
			+ "											from product pro, display_info dis "
			+ "											where pro.id = dis.product_id and pro.category_id =:category_id "
			+ "											group by pro.id, dis.id "
			+ "											limit :limi offset :ofs";
	//이미지 주소에 대한 쿼리는 나중에 추가 필요 
	final static public String SELECT_PRODUCT_COUNT_BYID = "select count(*) from product where category_id =:category_id";
	final static public String SELECT_PRODUCT_COUNT_ALL = "select count(*) from product";
	final static public String SELECT_CATEGORY_ById = "select from where id=:id";
	
}
