package kr.or.connect.reservation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.FileDao;
import kr.or.connect.reservation.dao.ProductImageDao;
import kr.or.connect.reservation.dao.ProductInfoDao;
import kr.or.connect.reservation.dao.ReservationUserCommentDao;

@Service
public class DetailService {
	
	@Autowired
	FileDao fileDao;
	@Autowired
	ProductImageDao productImageDao;
	@Autowired
	ProductInfoDao productInfoDao;
	@Autowired
	ReservationUserCommentDao reservationUserCommentDao;
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> loadSameProductFile(Integer id){
		return fileDao.selectSameProductFileByProductId(id);
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> loadProductImagesCount(Integer id){
		return productImageDao.selectProductImageCount(id);
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> loadProductETCInfo(Integer id){
		return productInfoDao.selectProductETCInfoById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> loadProductCommentList(Integer id, Integer ofs){
		return reservationUserCommentDao.selectUserCommentByProductId(id, ofs);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> loadProductCommentList(Integer id, Integer ofs, Integer lim){
		return reservationUserCommentDao.selectUserCommentByProductId(id, ofs, lim);
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> loadProductScoreAndCount(Integer id){
		return reservationUserCommentDao.selectCommentCountAndScore(id);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> loadProductCommentImageList(Integer id){
		return reservationUserCommentDao.selectUserCommentImageByProductId(id);
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> countProductCommentImage(Integer productId, Integer commentId){
		return reservationUserCommentDao.selectUserCommentImageCount(productId, commentId);
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> loadProductName(Integer productId){
		return productInfoDao.selectProductNameById(productId);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> checkReservation(Integer productId){
		return productInfoDao.selectReservationDate(productId);
	}
	
	
}
