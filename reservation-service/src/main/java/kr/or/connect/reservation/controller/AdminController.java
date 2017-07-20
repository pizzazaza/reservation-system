package kr.or.connect.reservation.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.domain.Category;
import kr.or.connect.reservation.domain.Image;
import kr.or.connect.reservation.domain.ProductImage;
import kr.or.connect.reservation.domain.ReservationUserCommentImage;
import kr.or.connect.reservation.service.CategoryService;
import kr.or.connect.reservation.service.FileService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	FileService fileService;
	
	
	@GetMapping("/category")
    public String index(HttpServletRequest request){
    	List<Map<String, Object>> cList = categoryService.getAllCategory();
    	List<Map<String, Object>> pList = fileService.selectProductList();
    	request.setAttribute("CategoryList", cList);
    	request.setAttribute("ProductList", pList);
        return "index";
    }

	//@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@DeleteMapping("/category/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteCategory(@PathVariable Integer id){
		categoryService.delete(id);
		
		return "redirect:/admin/category";
	}
	
	
	@GetMapping("/category/form")
	public String insertCategory(@RequestParam(name="category") String categoryValue){
		Category category = new Category();
		category.setName(categoryValue);
		categoryService.insert(category);
		
		return "redirect:/admin/category";
	}
	
	@RequestMapping(path = "/category/file", method = RequestMethod.POST, produces="text/plain; charset=UTF-8")
	public String insertFileInfo( @RequestParam("Img_name") String[] title,
            @RequestParam("Img") MultipartFile[] files,
            @RequestParam("product_id") Integer p_id){
		
		String baseDir = "/Users/sejun/Image/";
		String formattedDate = baseDir + new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
        File f = new File(formattedDate);
        if(!f.exists()){ // 파일이 존재하지 않는다면
            f.mkdirs(); // 해당 디렉토리를 만든다. 하위폴더까지 한꺼번에 만든다.
        }
        
        int titleCou = title.length;
        int fileCou = files.length; 
        if(titleCou != fileCou){
        	System.out.println("errrrr");
        }
        else{
	        for(int i = 0; i < titleCou; i++){
	        	String contentType = files[i].getContentType();
	            String name = files[i].getName();
	            String originalFilename = files[i].getOriginalFilename();
	            Long size = files[i].getSize();
	            Integer product_img_type;
	            if(size == 0)
	            	continue;
	            String uuid = UUID.randomUUID().toString(); // 중복될 일이 거의 없다.
	            String saveFileName = formattedDate + File.separator + uuid; // 실제 저장되는 파일의 절대 경로

	            // 아래에서 출력되는 결과는 모두 database에 저장되야 한다.
	            // pk 값은 자동으로 생성되도록 한다.
	            System.out.println("title :" + title);
	            System.out.println("contentType :" + contentType);
	            System.out.println("name :" + name);
	            System.out.println("originalFilename : " + originalFilename);
	            System.out.println("size : " + size);
	            System.out.println("saveFileName : " + saveFileName);

	            // 실제 파일을 저장함.
	            // try-with-resource 구문. close()를 할 필요가 없다. java 7 이상에서 가능
	            try(
	                InputStream in = files[i].getInputStream();
	                FileOutputStream fos = new FileOutputStream(saveFileName)){
	                int readCount = 0;
	                byte[] buffer = new byte[512];
	                while((readCount = in.read(buffer)) != -1){
	                    fos.write(buffer,0,readCount);
	                }
	                //userid는 session으로 처리하도록 해야지 
	                Image img = new Image(1,title[i] ,saveFileName,size, contentType,0);
	                if(i == 0)
	                	product_img_type = 1;
	                else
	                	product_img_type = 2;
	            	ProductImage product = new ProductImage(p_id, product_img_type);//type 1 poster 2 sub 3 etc
	            	fileService.insertProductImage(img, product);
	                
	            }catch(Exception ex){
	                ex.printStackTrace();
	            }
	        }
        }
     
		return "redirect:/admin/category";
	}
	
	@RequestMapping(path = "/comment/file", method = RequestMethod.POST, produces="text/plain; charset=UTF-8")
	public String insertFileCommentImage( @RequestParam("Img_name") String[] title,
            @RequestParam("Img") MultipartFile[] files,
            @RequestParam("product_id") Integer p_id){
		
		String baseDir = "/Users/sejun/Image/";
		String formattedDate = baseDir + new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date());
        File f = new File(formattedDate);
        if(!f.exists()){ // 파일이 존재하지 않는다면
            f.mkdirs(); // 해당 디렉토리를 만든다. 하위폴더까지 한꺼번에 만든다.
        }
        
        int titleCou = title.length;
        int fileCou = files.length; 
        if(titleCou != fileCou){
        	System.out.println("errrrr");
        }
        else{
	        for(int i = 0; i < titleCou; i++){
	        	String contentType = files[i].getContentType();
	            String name = files[i].getName();
	            String originalFilename = files[i].getOriginalFilename();
	            Long size = files[i].getSize();
	            Integer product_img_type;
	            if(size == 0)
	            	continue;
	            String uuid = UUID.randomUUID().toString(); // 중복될 일이 거의 없다.
	            String saveFileName = formattedDate + File.separator + uuid; // 실제 저장되는 파일의 절대 경로

	            // 아래에서 출력되는 결과는 모두 database에 저장되야 한다.
	            // pk 값은 자동으로 생성되도록 한다.
	            System.out.println("title :" + title);
	            System.out.println("contentType :" + contentType);
	            System.out.println("name :" + name);
	            System.out.println("originalFilename : " + originalFilename);
	            System.out.println("size : " + size);
	            System.out.println("saveFileName : " + saveFileName);

	            // 실제 파일을 저장함.
	            // try-with-resource 구문. close()를 할 필요가 없다. java 7 이상에서 가능
	            try(
	                InputStream in = files[i].getInputStream();
	                FileOutputStream fos = new FileOutputStream(saveFileName)){
	                int readCount = 0;
	                byte[] buffer = new byte[512];
	                while((readCount = in.read(buffer)) != -1){
	                    fos.write(buffer,0,readCount);
	                }
	                //userid는 session으로 처리하도록 해야지 
	                Map<String, Object> userCommentInfo = fileService.selectUserCommentInfo(p_id);
	                Image img = new Image((Integer)userCommentInfo.get("user_id"), title[i] ,saveFileName,size, contentType,0); //userId
	               
	            	ReservationUserCommentImage reservationUserCommentImage = 
	            			new ReservationUserCommentImage((Integer)userCommentInfo.get("id")); //reservationUserCommentId
	            	fileService.insertCommentImage(img, reservationUserCommentImage);
	                
	            }catch(Exception ex){
	                ex.printStackTrace();
	            }
	        }
        }
     
		return "redirect:/admin/category";
	}
}
/*
/categories 는 전체목록
/categories/${id} 는 한건 상세정보보기
/categories [post방식] 은 등록
/categories/form [get방식] 은 등록폼
/categories [put방식]은 수정
*/