package kr.or.connect.reservation.dao;

public class ProductSqls {

	final static public String SELECT_PRODUCT_DETAIL_INFO_BY_PRODUCT_ID = ""
			+ "select pro.name , info.observation_time, DATE_FORMAT(info.display_end, \"%Y.%c.%e\") \"display_end\", DATE_FORMAT(info.display_start, \"%Y.%c.%e\") \"display_start\", info.place_lot, info.place_street, info.place_name "
			+ "from product pro, display_info info "
			+ "where pro.id = info.product_id AND pro.id =:id";

	final static public String SELECT_PRODUCT_PRICE_BY_PRODUCT_ID = ""
			+ "select price_type, price, round(price*(1-discount_rate)) \"discount_price\" "
			+ "from product_price "
			+ "where product_id =:id";
	
	final static public String SELECT_PRODUCT_POSTER_IMAGE_BY_PRODUCT_ID = ""
			+ "select fi.id \"imgUrl\" "
			+ "from file fi, product_image img "
			+ "where img.product_id =:id AND img.type = 1 AND fi.id = img.file_id";



}
