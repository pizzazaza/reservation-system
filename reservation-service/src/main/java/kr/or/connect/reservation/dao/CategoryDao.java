package kr.or.connect.reservation.dao;


import java.sql.SQLException;
import java.util.Collections;
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

import kr.or.connect.reservation.domain.Category;;

@Repository
public class CategoryDao {
	 private NamedParameterJdbcTemplate jdbc; // sql 을 실행하기 위해 사용되는 객체
	 private SimpleJdbcInsert insertAction; // insert 를 편리하게 하기 위한 객체
	 private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	 
	 // Spring은 생성자를 통하여 주입을 하게 된다.
	 public CategoryDao(DataSource dataSource) {
         this.jdbc = new NamedParameterJdbcTemplate(dataSource); // Datasource를 주입
         this.insertAction = new SimpleJdbcInsert(dataSource)  // Datasource를 주입
                .withTableName("category")   // table명을 지정
                .usingGeneratedKeyColumns("id"); // pk 칼럼을 지정
     }
	  
	 public List<Map<String, Object>> selectAll(){
		 Map<String, Object> params = new HashMap<>();
		 List<Map<String, Object>> categoryList = null;
		 try{
			 categoryList = jdbc.queryForList(ReservationSqls.SELECT_CATEGORY_ALL, params);
		 }catch(EmptyResultDataAccessException e){
			 return null;
		 }
		 return categoryList;
	 }
	 
	 public Integer delete(Integer id){
        Map<String, ?> params = Collections.singletonMap("id", id);
        Integer state = 0;
        try{
        	state = jdbc.update(ReservationSqls.DELETE_BY_ID, params);
        }catch(EmptyResultDataAccessException e){
        	return 0;
        }
        	
        return state;
	 }
	 
	 
	 public long insert(Category category){
		 SqlParameterSource params = new BeanPropertySqlParameterSource(category);
		 long state = 0;
		 try{
			 state = insertAction.executeAndReturnKey(params).longValue();
		 }catch(EmptyResultDataAccessException e){
			 return 0;
		 }
			
		 return state;
	 }
	
}
