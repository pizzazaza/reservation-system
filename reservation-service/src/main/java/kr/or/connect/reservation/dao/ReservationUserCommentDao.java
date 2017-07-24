package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.domain.Category;

@Repository
public class ReservationUserCommentDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	
	public ReservationUserCommentDao(DataSource dataSource){
		 this.jdbc = new NamedParameterJdbcTemplate(dataSource); // Datasource를 주입
	}
	
	public List<Map<String, Object>> selectUserCommentByProductId(Integer id, Integer ofs){
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		params.put("ofs", ofs);
		
		params.put("lim", 3);
		
		try{
			return jdbc.queryForList(ReservationSqls.SELECT_USER_COMMENT_LIST_BY_PRODUCT_ID, params);
		}catch(Exception e){
			return null;
		}
	}
	
	public List<Map<String, Object>> selectUserCommentByProductId(Integer id, Integer ofs, Integer lim){
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		params.put("ofs", ofs);
		
		params.put("lim", lim);
		
		try{
			return jdbc.queryForList(ReservationSqls.SELECT_USER_COMMENT_LIST_BY_PRODUCT_ID, params);
		}catch(Exception e){
			return null;
		}
	}
	
	public Map<String, Object> selectCommentCountAndScore(Integer id){
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		try{
			return jdbc.queryForMap(ReservationSqls.SELECT_COMMENT_SOCRE_AND_COUNT , params);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
	public Map<String , Object> selectUserCommentImageByProductId(Integer id){
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		try{
			return jdbc.queryForMap(ReservationSqls.SELECT_USER_COMMENT_IMAGELIST_BY_PRODUCT_ID, params);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
	
	public Map<String , Object> selectUserCommentImageCount(Integer productId, Integer commId){
		Map<String, Object> params = new HashMap<>();
		params.put("product_id", productId);
		params.put("comment_id", commId);
		try{
			return jdbc.queryForMap(ReservationSqls.SELECT_COUNT_COMMENT_IMAGE, params);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
	
}
