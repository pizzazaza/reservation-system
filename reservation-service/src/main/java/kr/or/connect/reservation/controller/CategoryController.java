package kr.or.connect.reservation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.or.connect.reservation.domain.Category;
import kr.or.connect.reservation.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping
    public String index(HttpServletRequest request){
    	//HttpSession session = request.getSession();
    	List<Map<String, Object>> list = categoryService.getAllCategory();
    	//session.setAttribute("CategoryList", list);
    	request.setAttribute("CategoryList", list);
        return "index";
    }

	//@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteCategory(@PathVariable Integer id){
		categoryService.delete(id);
		
		return "redirect:/category";
	}
	
	
	@GetMapping("/form")
	public String insertCategory(@RequestParam(name="category") String categoryValue){
		Category category = new Category();
		category.setName(categoryValue);
		categoryService.insert(category);
		
		return "redirect:/category";
	}
}
/*
/categories 는 전체목록
/categories/${id} 는 한건 상세정보보기
/categories [post방식] 은 등록
/categories/form [get방식] 은 등록폼
/categories [put방식]은 수정
*/