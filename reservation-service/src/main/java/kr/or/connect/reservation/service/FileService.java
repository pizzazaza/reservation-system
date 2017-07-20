package kr.or.connect.reservation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.FileDao;
import kr.or.connect.reservation.dao.ProductImageDao;
import kr.or.connect.reservation.domain.Image;
import kr.or.connect.reservation.domain.ProductImage;
import kr.or.connect.reservation.domain.ReservationUserCommentImage;

@Service
public class FileService {

	@Autowired
	FileDao fileDao;
	@Autowired
	ProductImageDao productImageDao;
	
	@Transactional(readOnly = false)
	public void insertProductImage(Image file, ProductImage productImage){
	    int fileKey= fileDao.insertFileInfo(file);
	    productImage.setFile_id(fileKey);
		productImageDao.insertFileInfo(productImage);
	}
	
	@Transactional(readOnly = false)
	public void insertCommentImage(Image file, ReservationUserCommentImage reservationUserCommentImage){
		 int fileKey= fileDao.insertFileInfo(file);
		 reservationUserCommentImage.setFileId(fileKey);
		 productImageDao.insertCommentImage(reservationUserCommentImage);
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> selectProductList(){
		return productImageDao.selectProductList();
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> selectFileById(Integer id){
		return fileDao.selectFileById(id);
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> selectUserCommentInfo(Integer product_id){
		return fileDao.selectCommentUserInfoByProductId(product_id);
	}
	
}
