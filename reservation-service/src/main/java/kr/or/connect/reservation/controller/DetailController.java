package kr.or.connect.reservation.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.reservation.service.DetailService;
import kr.or.connect.reservation.service.ReservationService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Controller
@RequestMapping("/detail")
public class DetailController {

	@Autowired
	DetailService detailService;
	@Autowired
	ReservationService reservationService;
	
	
	//@RequestMapping(path="/?product={id}" ,method = RequestMethod.GET, produces="text/plain; charset=UTF-8")
	@GetMapping("/product_id/{id}")
	public String loadingDetail(@PathVariable Integer id,  HttpServletRequest request){
		
		List<Map<String, Object>> imageList = detailService.loadSameProductFile(id);
		
		for(int i = 0; i < imageList.size(); i++){
			imageList.get(i).put("imgUrl", "/imgLoading/"+imageList.get(i).get("imgUrl"));
		}
		
		Map<String, Object> infoMap = detailService.loadProductETCInfo(id);
		Map<String, Object> imageCount = detailService.loadProductImagesCount(id);
		request.setAttribute("imgeList", imageList);
		request.setAttribute("infoMap", infoMap);
		request.setAttribute("imageCount", imageCount);
		
		/*
		  [name, description, tel, email, homepage, event]
		  [file_name, imgUrl, product_id]
		  [count]
		*/
		
		
		Map<String, Object> scoreAndCount = detailService.loadProductScoreAndCount(id);
		request.setAttribute("scoreAndCount", scoreAndCount);
		
		List<Map<String, Object>> commentList = detailService.loadProductCommentList(id, 0); //
			
		
		if(commentList != null){
			for(Map<String, Object> comment : commentList){
				Map<String, Object> imgUrl = detailService.loadProductCommentImageList((Integer)comment.get("id"));
				
				Map<String, Object> count = detailService.countProductCommentImage(id,(Integer)comment.get("id"));
				if(imgUrl != null){
					comment.put("imgUrl",  "/imgLoading/"+ imgUrl.get("imgUrl"));
					comment.put("count",  count.get("count") );
				
				}
			}
		}
		request.setAttribute("productComment",commentList);
		request.setAttribute("productId", (Integer)id);
		if(scoreAndCount == null)
			System.out.println("null");
		else{
			System.out.println(commentList);
			
			System.out.println(commentList.isEmpty());
		}
		//
		System.out.println("$@#$#@#@$");
		return "detail";
	}
	
	@RequestMapping(path="/review/product/{id}/offset/{ofs}" ,method = RequestMethod.GET, produces="text/plain; charset=UTF-8")
	public String loadMoreComment(@PathVariable Integer id, @PathVariable Integer ofs, HttpServletRequest request){
		Map<String, Object> scoreAndCount = detailService.loadProductScoreAndCount(id);
		
		List<Map<String, Object>> commentList = detailService.loadProductCommentList(id, ofs, 100); //
		Map<String, Object> infoMap = detailService.loadProductETCInfo(id);
		if(commentList != null){
			for(Map<String, Object> comment : commentList){
				Map<String, Object> imgUrl = detailService.loadProductCommentImageList((Integer)comment.get("id"));
				
				Map<String, Object> count = detailService.countProductCommentImage(id,(Integer)comment.get("id"));
				if(imgUrl != null){
					comment.put("imgUrl",  "/imgLoading/"+ imgUrl.get("imgUrl"));
					comment.put("count",  count.get("count") );
				
				}
			}
		}
		request.setAttribute("scoreAndCount", scoreAndCount);
		request.setAttribute("productComment", commentList);
		request.setAttribute("infoMap", infoMap);
		
    
    	return "review";
	}
	
	@RequestMapping(path="/reservationattempt/{id}" ,method = RequestMethod.GET, produces="text/plain; charset=UTF-8")
	@ResponseBody
	public String atteptReservation(@PathVariable Integer id){
		
		Date nowTime = new Date();
		
		Map<String, Object> dateMap = detailService.checkReservation(id);
		JSONObject jsonObj = new JSONObject();
		Integer value = -1;
		
		if(nowTime.after((Date)dateMap.get("sales_end")) || nowTime.before((Date)dateMap.get("sales_start"))){
			value = 0;
			
		}
		else
			value = 1;
		
		jsonObj.put("state", value);
		
		return jsonObj.toJSONString();
	}
	

	
	
	@RequestMapping("/reserve/product_id/{id}")
	public String addReservation(@PathVariable Integer id ,HttpServletRequest request){
		Map<String, Object> productInfo = reservationService.loadProductInfo(id);
		List<Map<String, Object>> priceInfo = reservationService.loadProductPriceInfo(id);
		Map<String, Object> imageInfo = reservationService.loadProductImgInfo(id);
		HttpSession session = request.getSession();
		Map<String, Object> loginInfo = (Map<String, Object>)session.getAttribute("LoginInfo");
		System.out.println("SDFFSDFSDFSDF" + loginInfo);
		System.out.println(productInfo.keySet());
		imageInfo.put("imgUrl",  "/imgLoading/"+ imageInfo.get("imgUrl"));
		request.setAttribute("productInfo", productInfo);
		request.setAttribute("priceInfo", priceInfo);
		request.setAttribute("imageInfo", imageInfo);
		if(loginInfo == null){
       		loginInfo = new HashMap<String, Object>();
       		loginInfo.put("name","이름");
       		loginInfo.put("email","email");
       		loginInfo.put("tel","phone number");
       	}
		request.setAttribute("LoginInfo", loginInfo);
 		
		return "reserve";
	}
}
