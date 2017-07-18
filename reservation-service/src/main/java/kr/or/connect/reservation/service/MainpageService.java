package kr.or.connect.reservation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.MainpageDao;

@Service
public class MainpageService {
	@Autowired
	MainpageDao mainpageDao;
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getAllCategory(){
		return mainpageDao.selectAllCategory();
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getMainPostLimit(Integer limit, Integer ofs, Integer id){
		return mainpageDao.selectMainPostLimit(limit, ofs, id);
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> getProductCountById(Integer category_id){
		return mainpageDao.selectProductCountById(category_id);
	}
	
}
