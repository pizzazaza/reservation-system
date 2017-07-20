package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.domain.Category;
import kr.or.connect.reservation.domain.Image;
import kr.or.connect.reservation.domain.ProductImage;
import kr.or.connect.reservation.domain.ReservationUserCommentImage;

@Repository
public class ProductImageDao {
	 private NamedParameterJdbcTemplate jdbc; // sql 을 실행하기 위해 사용되는 객체
	 private SimpleJdbcInsert insertActionIntoProductImage; // insert 를 편리하게 하기 위한 객체
	 private SimpleJdbcInsert insertActionIntoReservationUserCommentImage;
	 private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	 
	 public ProductImageDao(DataSource dataSource){
		 this.jdbc = new NamedParameterJdbcTemplate(dataSource); // Datasource를 주입
         this.insertActionIntoProductImage = new SimpleJdbcInsert(dataSource)
        		 .withTableName("PRODUCT_IMAGE")
        		 .usingGeneratedKeyColumns("id");
         this.insertActionIntoReservationUserCommentImage = new SimpleJdbcInsert(dataSource)
        		 .withTableName("RESERVATION_USER_COMMENT_IMAGE")
        		 .usingGeneratedKeyColumns("id");
	 }
	 
	 public Integer insertFileInfo(ProductImage productImage){
		 
		 SqlParameterSource productImageParams = new BeanPropertySqlParameterSource(productImage);
		
		 int proImgKey = 0;
		 try{
			 proImgKey = insertActionIntoProductImage.executeAndReturnKey(productImageParams).intValue();
			
		 }catch(EmptyResultDataAccessException e){
			 return 0;
		 }
			
		 return proImgKey;
	 
	 }
	 
	 public Integer insertCommentImage(ReservationUserCommentImage reservationUserCommentImage){
		 
		 SqlParameterSource commentImageParams = new BeanPropertySqlParameterSource(reservationUserCommentImage);
		
		 int commentImgKey = 0;
		 try{
			 commentImgKey = insertActionIntoReservationUserCommentImage.executeAndReturnKey(commentImageParams).intValue();
			
		 }catch(EmptyResultDataAccessException e){
			 return 0;
		 }
		 	
		 return commentImgKey;
	 
	 }
	 
	 public List<Map<String, Object>> selectProductImg(Integer product_id){
		 Map<String, Object> params = new HashMap();
		 params.put("product_id", product_id);
		 
		 try{
			 return jdbc.queryForList(ReservationSqls.SELECT_PRODUCT_IMG_BY_ID, params);
		 }catch(EmptyResultDataAccessException e){
			 return null;
		 }
		  
	 }
	 
	 public List<Map<String, Object>> selectProductList(){
		 Map<String, Object> params = new HashMap<>();
		 
		 try{
			 return jdbc.queryForList(ReservationSqls.SELECT_PRODUCT_NAMEANDID, params);
		 }catch(EmptyResultDataAccessException e){
			 return null;
		 }
		 
	 }
	 
	 public Map<String, Object> selectProductImageCount(Integer id){
		 Map<String, Object> params = new HashMap<>();
		 params.put("id", id);
		 try{
			 return jdbc.queryForMap(ReservationSqls.SELECT_PRODUCT_IMAGE_COUNT, params);
		 }catch(EmptyResultDataAccessException e){
			 return null;
		 }
	 }
}
