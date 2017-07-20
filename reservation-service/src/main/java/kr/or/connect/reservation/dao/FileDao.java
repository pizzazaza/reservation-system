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

@Repository
public class FileDao {
	 private NamedParameterJdbcTemplate jdbc; // sql 을 실행하기 위해 사용되는 객체
	 private SimpleJdbcInsert insertActionIntoFILE; // insert 를 편리하게 하기 위한 객체
	 private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	 
	 public FileDao(DataSource dataSource){
		 this.jdbc = new NamedParameterJdbcTemplate(dataSource); // Datasource를 주입
         this.insertActionIntoFILE = new SimpleJdbcInsert(dataSource)  // Datasource를 주입
                .withTableName("FILE")   // table명을 지정
                .usingGeneratedKeyColumns("id"); // pk 칼럼을 지정
        
	 }
	 
	 public Integer insertFileInfo(Image file){
		 
		 SqlParameterSource fileParams = new BeanPropertySqlParameterSource(file);
		 
		 int fileKey = 0;
		 try{
			 fileKey = insertActionIntoFILE.executeAndReturnKey(fileParams).intValue();
			 
		 }catch(EmptyResultDataAccessException e){
			 return 0;
		 }
			
		 return fileKey;
	 
	 }
	 
	 public Map<String, Object> selectFileById(Integer id){
		 Map<String, Object> params = new HashMap<>();
		 params.put("id",id);
		 try{
			 return jdbc.queryForMap(ReservationSqls.SELECT_FILE_BY_ID, params);
		 }catch(EmptyResultDataAccessException e){
			 return null;
		 }
	 }
	 
	 public List<Map<String, Object>> selectSameProductFileByProductId(Integer id){
		 Map<String, Object> params = new HashMap<>();
		 params.put("id",id);
		 try{
			 return jdbc.queryForList(ReservationSqls.SELECT_SAME_PRODUCT_FILE_BY_PRODUCT_ID ,params);
		 }catch(EmptyResultDataAccessException e){
			 return null;
		 }
	 }
	 
	
	 public Map<String, Object> selectCommentUserInfoByProductId(Integer product_id){
		 Map<String, Object> params = new HashMap<>();
		 params.put("id", product_id);
		 try{
			 return jdbc.queryForMap(ReservationSqls.SELECT_RESERVATION_USER_COMMENT_USER_INFO_BY_PRODUCT_ID, params);
		 }catch(EmptyResultDataAccessException e){
			 return null;
		 }
	 }
}
