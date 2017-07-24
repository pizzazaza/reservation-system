package kr.or.connect.reservation.dao;

public class ReservationSqls {
	final static public String SELECT_CATEGORY_ALL = "select id,name from category ";
	final static public String DELETE_BY_ID = "delete from category where id =:id ";
	final static public String INSERT_CATEGORY = "insert into category(name) values(:name) ";
	
	final static public String SELECT_PRODUCT_LIMIT = ""
			+ "SELECT pro.name, pro.description, dis.place_name, fi.id \"imgUrl\" , pro.id "
			+ "FROM product_image img, file fi, product pro, display_info dis "
			+ "WHERE pro.id = dis.product_id AND pro.id = img.product_id AND img.file_id = fi.id AND type = 1 " 
			+ "LIMIT :limi OFFSET :ofs"; 
	final static public String SELECT_FILE_BY_ID = ""
			+ "select save_file_name, file_name, content_type, file_length "
			+ "from FILE "
			+ "where id =:id ";		
			
	final static public String SELECT_PRODUCT_LIMIT_ById =""
			+ "SELECT pro.name, pro.description, dis.place_name, fi.id \"imgUrl\" "
			+ "FROM product_image img, file fi, product pro, display_info dis "
			+ "WHERE pro.id = dis.product_id AND pro.id = img.product_id AND img.file_id = fi.id AND type = 1 AND pro.category_id =:category_id " 
			+ "LIMIT :limi OFFSET :ofs"; 
	
	final static public String SELECT_SAME_PRODUCT_FILE_BY_PRODUCT_ID = ""
			+ "select fi.file_name, fi.id \"imgUrl\", img.product_id "
			+ "from product_image img, file fi "
			+ "where product_id =:id AND fi.id = img.file_id";
	
	final static public String SELECT_PRODUCT_IMAGE_COUNT =""
			+ "select count(product_id) \"count\" " 
			+ "from product_image " 
			+ "where product_id =:id";
	
	final static public String SELECT_PRODUCT_INFO_BY_ID = ""
			+ "select pro.name, pro.description, dis.tel, dis.email, dis.homepage ,pro.event "
			+ "			,dis.place_name, dis.place_street, dis.place_lot "
			+ "from product pro, display_info dis "
			+ "where pro.id =:id AND pro.id = dis.product_id ";
	
	
	final static public String SELECT_RESERVATION_USER_COMMENT_USER_INFO_BY_PRODUCT_ID = ""
			+ "select id, user_id "
			+ "from reservation_user_comment "
			+ "where product_id =:id "
			+ "limit 1 offset 0";
	
	final static public String SELECT_USER_COMMENT_LIST_BY_PRODUCT_ID = ""
			+ "select  comm.id, comm.user_id, comm.score, u.nickname, comm.create_date, comm.comment "
			+ "from reservation_user_comment comm, users u " 
			+ "where product_id =:id AND comm.user_id = u.id " 
			+ "limit :lim offset :ofs";

	
	final static public String SELECT_USER_COMMENT_IMAGELIST_BY_PRODUCT_ID = ""
			+ "select file_id \"imgUrl\" "
			+ "from reservation_user_comment_image "
			+ "where reservation_user_comment_id=:id "
			+ "limit 1";
	final static public String SELECT_COUNT_COMMENT_IMAGE = ""
			+ "select count(*) \"count\" "
			+ "from reservation_user_comment_image img, reservation_user_comment comm " 
			+ "where comm.product_id=:product_id AND img.reservation_user_comment_id = comm.id";	
	
	final static public String SELECT_COMMENT_SOCRE_AND_COUNT = ""
			+ "select count(product_id) \"count\", avg(score) \"score\" "
			+ "from reservation_user_comment "
			+ "where product_id =:id ";

	final static public String SELECT_PRODUCT_NAME_BY_ID = ""
			+ "select name "
			+ "from product "
			+ "where id=:id";
	
	final static public String SELECT_LOCATION_INFO_BY_PRODUCT_ID = ""
			+ "select place_name, ,  "
			+ "from display_info "
			+ "where product_id =:product_id";
	final static public String SELECT_PRODUCT_NAMEANDID = "select id, name from product";
	//admin page product list 
	final static public String SELECT_PRODUCT_IMG_BY_ID = ""
			+ "select img.id, img.product_id, img.type, fi.file_name, fi.save_file_name "
			+ "from product_image img, file fi "
			+ "where img.product_id =: product_id, img.file_id == fi.id "
			+ "group by img.id , img.product_id ";
	final static public String SELECT_PRODUCT_RESERVATION_DATE_BY_PRODUCT_ID = ""
			+ "select sales_start, sales_end "
			+ "from product "
			+ "where id =:id";
	//이미지 주소에 대한 쿼리는 나중에 추가 필요 
	final static public String SELECT_PRODUCT_COUNT_BYID = "select count(*) from product where category_id =:category_id ";
	final static public String SELECT_PRODUCT_COUNT_ALL = "select count(*) from product ";
	final static public String SELECT_CATEGORY_ById = "select from where id=:id ";
	
}
