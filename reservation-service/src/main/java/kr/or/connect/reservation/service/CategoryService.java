package kr.or.connect.reservation.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.domain.Category;;

@Service
public class CategoryService {
	@Autowired
	CategoryDao categoryDao;
	
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getAllCategory(){
		return categoryDao.selectAll();
	}
	
	public int delete(long i) {
        return categoryDao.delete(i);
    }
	
	public long insert(Category category){
		return categoryDao.insert(category);
	}
}
