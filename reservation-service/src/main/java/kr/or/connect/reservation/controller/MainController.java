package kr.or.connect.reservation.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.connect.reservation.service.CategoryService;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	CategoryService categoryService;
	
    @GetMapping
    public String index(HttpServletRequest request){
    	List<Map<String, Object>> list = categoryService.getAllCategory();
    	
    	request.setAttribute("CategoryList", list);
    	request.setAttribute("active", 1);
        return "mainpage";
    }
}