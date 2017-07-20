package kr.or.connect.reservation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.connect.reservation.service.UserInfoService;

//@Controller
//@RequestMapping("/userinfo")
public class UserInfoController {

	//@Autowired
	UserInfoService userInfoService;
	
	//@RequestMapping("/myreservationinfo")
	public String CheckMyreservation(HttpServletRequest request, HttpSession session ){
		//session.get
		
		return "";
	}
	
}
