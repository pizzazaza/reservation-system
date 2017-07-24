package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDao {

	private NamedParameterJdbcTemplate jdbc; 
	
	public UserInfoDao(DataSource dataSource){
		 this.jdbc = new NamedParameterJdbcTemplate(dataSource); 
	}
	
	public Map<String, Object> selectUserPhoneByEmailAndName(String email){
		Map<String, Object> params = new HashMap<>();
		params.put("email", email);
	
		try{
			return jdbc.queryForMap(UserInfoSqls.SELECT_USER_PHONE_BY_EMAIL, params);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
}
