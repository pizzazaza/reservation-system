package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductInfoDao {
	 
	private NamedParameterJdbcTemplate jdbc; // sql 을 실행하기 위해 사용되는 객체
		
	public ProductInfoDao(DataSource dataSource){
		 this.jdbc = new NamedParameterJdbcTemplate(dataSource); // Datasource를 주입
	 }
	
	public Map<String, Object> selectProductETCInfoById(Integer id){
		 Map<String, Object> params = new HashMap<>();
		 params.put("id", id);
		 try{
			 return jdbc.queryForMap(ReservationSqls.SELECT_PRODUCT_INFO_BY_ID, params);
		 }catch(EmptyResultDataAccessException e){
			 return null;
		 }
	}
	 
	public Map<String, Object> selectProductNameById(Integer id){
		 Map<String, Object> params = new HashMap<>();
		 params.put("id", id);
		 try{
			 return jdbc.queryForMap(ReservationSqls.SELECT_PRODUCT_NAME_BY_ID, params);
		 }catch(EmptyResultDataAccessException e){
			 return null;
		 }
	}
	
	public Map<String, Object> selectReservationDate(Integer id){
		 Map<String, Object> params = new HashMap<>();
		 params.put("id", id);
		 try{
			 return jdbc.queryForMap(ReservationSqls.SELECT_PRODUCT_RESERVATION_DATE_BY_PRODUCT_ID, params);
		 }catch(EmptyResultDataAccessException e){
			 return null;
		 }
	}
}
