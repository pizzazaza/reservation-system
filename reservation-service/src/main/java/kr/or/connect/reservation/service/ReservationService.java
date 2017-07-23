package kr.or.connect.reservation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductInfoDao;
import kr.or.connect.reservation.dao.ReservationDao;

@Service
public class ReservationService {

	@Autowired
	ReservationDao reservationDao;
	@Autowired
	ProductInfoDao productInfoDao;
	
	@Transactional(readOnly = true)
	public Map<String, Object> loadProductInfo(Integer id){
		return productInfoDao.selectProductInfoByProductId(id);
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> loadProductPriceInfo(Integer id){
		return productInfoDao.selectProductPriceByProductId(id);
	}
	
	
	@Transactional(readOnly = true)
	public Map<String, Object> loadProductImgInfo(Integer id){
		return productInfoDao.selectProductPosterByProductId(id);
	}
}
