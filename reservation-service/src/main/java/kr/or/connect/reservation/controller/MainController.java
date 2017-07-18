package kr.or.connect.reservation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.reservation.service.MainpageService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	MainpageService mainpageService;
	
    @GetMapping
    public String index(HttpServletRequest request){
    	List<Map<String, Object>> list = mainpageService.getAllCategory();
    	List<Map<String, Object>> productList = mainpageService.getMainPostLimit(10, 0, 0);
    	Map<String, Object> productCount = mainpageService.getProductCountById(0);
    	//limit defualt 4, offset 0
    	request.setAttribute("CategoryList", list);
    	request.setAttribute("productList", productList);
    	request.setAttribute("active", 0);
    	request.setAttribute("imgUrl", "https://ssl.phinf.net/naverbooking/20170111_225/1484116579024rNkXW_JPEG/2016_%B9%C2%C1%F6%C4%C3_%C0%CE_%B4%F5_%C7%CF%C0%CC%C3%F7_%C6%F7%BD%BA%C5%CD%2820MB%29.jpg?type=l591_945");
    	request.setAttribute("productCount", productCount);
        return "mainpage";
    }
    
    //@GetMapping("/changecategory/{id}")
    @RequestMapping(path = "/changecategory/{category_id}/offset/{offset}", method = RequestMethod.GET, produces="text/plain; charset=UTF-8")
    @ResponseBody
    public String choiceCateogry(@PathVariable Integer category_id, @PathVariable Integer offset ,HttpServletRequest request){
    
    	Map<String, Object> productCount = mainpageService.getProductCountById(category_id);
    	
    	List<Map<String, Object>> productList = mainpageService.getMainPostLimit(10, offset, category_id);
    
    	
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("count", productCount.get("count(*)"));
    	//jsonObj.put("imgUrl", "/resources/img/testimg.png");
    
    	for(int i = 0; i < productList.size(); i++){
    		productList.get(i).put("imgUrl", "https://ssl.phinf.net/naverbooking/20170111_225/1484116579024rNkXW_JPEG/2016_%B9%C2%C1%F6%C4%C3_%C0%CE_%B4%F5_%C7%CF%C0%CC%C3%F7_%C6%F7%BD%BA%C5%CD%2820MB%29.jpg?type=l591_945");
    	}
JSONArray jsonArray = new JSONArray();
		for( Map<String, Object> product : productList ) {
			jsonArray.add( product );
		}
		
    	jsonObj.put("products", jsonArray.toJSONString());
    
    	return jsonObj.toJSONString();
    
    } 
}