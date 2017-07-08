package kr.or.connect.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.reservation.domain.Category;
import kr.or.connect.reservation.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable long id){
		
		categoryService.delete(id);
		return "redirect:/";
	}
	
	@GetMapping("/add")
	public String insertCategory(@RequestParam(name="category") String categoryValue){
		Category category = new Category();
		category.setName(categoryValue);
		categoryService.insert(category);
		
		return "redirect:/";
	}
}
