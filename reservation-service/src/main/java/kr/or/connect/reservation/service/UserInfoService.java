package kr.or.connect.reservation.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.UserInfoDao;

@Service
public class UserInfoService {

	@Autowired
	UserInfoDao userInfoDao;
	
	@Transactional(readOnly = true)
	public Map<String, Object> loadUserPhone(String email){
		return userInfoDao.selectUserPhoneByEmailAndName(email);
	}
	
	
}
