package kr.or.connect.reservation.controller;

import java.io.FileInputStream;
import java.math.BigDecimal;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.reservation.service.FileService;
import kr.or.connect.reservation.service.MainpageService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	MainpageService mainpageService;
	@Autowired 
	FileService fileService;
	
    @GetMapping
    public String index(HttpServletRequest request){
    	
    	
    	List<Map<String, Object>> list = mainpageService.getAllCategory();
    	List<Map<String, Object>> productList = mainpageService.getMainPostLimit(5, 0, 0);
    	
    	Map<String, Object> productCount = mainpageService.getProductCountById(0);
    	//limit defualt 4, offset 0
    	for(int i = 0; i < productList.size(); i++){
    		productList.get(i).put("imgUrl", "/imgLoading/"+productList.get(i).get("imgUrl"));
    	}
    	request.setAttribute("CategoryList", list);
    	request.setAttribute("productList", productList);
    	request.setAttribute("active", 0);
    	//request.setAttribute("imgUrl", "https://ssl.phinf.net/naverbooking/20170111_225/1484116579024rNkXW_JPEG/2016_%B9%C2%C1%F6%C4%C3_%C0%CE_%B4%F5_%C7%CF%C0%CC%C3%F7_%C6%F7%BD%BA%C5%CD%2820MB%29.jpg?type=l591_945");
    	request.setAttribute("productCount", productCount);
        return "mainpage";
    }
    
    @RequestMapping(path="/imgLoading/{id}", method = RequestMethod.GET, produces="text/plain; charset=UTF-8")
    @ResponseBody
    public void sendProductImg(@PathVariable(name="id") Integer id,  HttpServletResponse response){
    
    	Map<String, Object> imgInfo = fileService.selectFileById(id);
    	String originalFilename = (String) imgInfo.get("file_name");
        String contentType = (String) imgInfo.get("content_type");
        Integer fileSize = (Integer)imgInfo.get("file_length");
        // 해당 파일이 이미 존재해야한다.
        String saveFileName = (String)imgInfo.get("save_file_name"); 
        		
        response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Length", ""+ fileSize);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        java.io.File readFile = new java.io.File(saveFileName);
        if(!readFile.exists()){ // 파일이 존재하지 않다면
            throw new RuntimeException("file not found");
        }

        FileInputStream fis = null;
        try{
            fis = new FileInputStream(readFile);
            FileCopyUtils.copy(fis, response.getOutputStream()); // 파일을 저장할때도 사용할 수 있다.
            response.getOutputStream().flush();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }finally {
            try {
                fis.close();
            }catch(Exception ex){
                // 아무것도 하지 않음 (필요한 로그를 남기는 정도의 작업만 함.)
            }
        }
    }
    
    
    @RequestMapping(path = "/changecategory/{category_id}/offset/{offset}", method = RequestMethod.GET, produces="text/plain; charset=UTF-8")
    @ResponseBody
    public String choiceCateogry(@PathVariable Integer category_id, @PathVariable Integer offset ,HttpServletRequest request){
    
    	Map<String, Object> productCount = mainpageService.getProductCountById(category_id);
    	
    	List<Map<String, Object>> productList = mainpageService.getMainPostLimit(10, offset, category_id);
    
    	
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("count", productCount.get("count(*)"));
    	//jsonObj.put("imgUrl", "/resources/img/testimg.png");
    
    	for(int i = 0; i < productList.size(); i++){
    		productList.get(i).put("imgUrl", "/imgLoading/"+productList.get(i).get("imgUrl"));
    	}
    	
    
    	JSONArray jsonArray = new JSONArray();
		for( Map<String, Object> product : productList ) {
			jsonArray.add( product );
		}
		
    	jsonObj.put("products", jsonArray.toJSONString());
    
    	return jsonObj.toJSONString();
    
    } 
}

//jpa