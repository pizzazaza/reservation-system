package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.domain.Category;

@Repository
public class MainpageDao {
	private NamedParameterJdbcTemplate jdbc; // sql 을 실행하기 위해 사용되는 객체
	 private SimpleJdbcInsert insertAction; // insert 를 편리하게 하기 위한 객체
	 private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	 
	 // Spring은 생성자를 통하여 주입을 하게 된다.
	 public MainpageDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); // Datasource를 주입
        
    }
	 
	 public List<Map<String, Object>> selectAllCategory(){
		 Map<String, Object> params = new HashMap<>();
		 List<Map<String, Object>> categoryList = null;
		 try{
			 categoryList = jdbc.queryForList(ReservationSqls.SELECT_CATEGORY_ALL, params);
		 }catch(EmptyResultDataAccessException e){
			 return null;
		 }
		 return categoryList;
		 
	 }
	 
	 public List<Map<String, Object>> selectMainPostLimit(Integer limi, Integer ofs, Integer category_id){
		 Map<String, Object> params = new HashMap<>();
		 List<Map<String, Object>> postList = null;
		 params.put("ofs", ofs);
		 params.put("limi", limi);
		 params.put("category_id", category_id);
		
		 if(category_id == 0){
			 try{
				 postList = jdbc.queryForList(ReservationSqls.SELECT_PRODUCT_LIMIT, params);
			 }catch(EmptyResultDataAccessException e){
				 return null;
			 }
		 }
		 else{
			 try{
				 postList = jdbc.queryForList(ReservationSqls.SELECT_PRODUCT_LIMIT_ById, params);
			 }catch(EmptyResultDataAccessException e){
				 return null;
			 }
		 }
		 return postList;
	 }
	 
	 public Map<String, Object> selectProductCountById(Integer category_id){
		 Map<String, Object> params = new HashMap<>();
		 Map<String, Object> productCount = null;
		 params.put("category_id", category_id);
		 if(category_id == 0){
			 try{
				 productCount = jdbc.queryForMap(ReservationSqls.SELECT_PRODUCT_COUNT_ALL, params);
			 }catch(EmptyResultDataAccessException e){
				 return null;
			 }
		 }
		 else{
			 try{
				 productCount = jdbc.queryForMap(ReservationSqls.SELECT_PRODUCT_COUNT_BYID, params);
			 }catch(EmptyResultDataAccessException e){
				 return null;
			 }
		 }
		 
		 return productCount;
	 }
}
