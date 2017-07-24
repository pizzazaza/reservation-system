package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Map<String, Object> selectProductInfoByProductId(Integer id){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		try{
			return jdbc.queryForMap(ProductSqls.SELECT_PRODUCT_DETAIL_INFO_BY_PRODUCT_ID, params);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
				
	}
	
	public List<Map<String, Object>> selectProductPriceByProductId(Integer id){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		try{
			return jdbc.queryForList(ProductSqls.SELECT_PRODUCT_PRICE_BY_PRODUCT_ID, params);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
				
	}
	
	public Map<String, Object> selectProductPosterByProductId(Integer id){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		try{
			return jdbc.queryForMap(ProductSqls.SELECT_PRODUCT_POSTER_IMAGE_BY_PRODUCT_ID, params);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
				
	}
	
}
