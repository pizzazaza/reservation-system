package kr.or.connect.reservation.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NaverLoginController {

	@GetMapping("/login")
	public String requestLogin(HttpSession session){
		String redirecturi = null;
		String callbackUrl = "http://127.0.0.1:8080/callback";
		String clientId = "PWaFcP1RP1p_LliiULjZ";
		try {
			redirecturi = URLEncoder.encode(callbackUrl, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String state = generateState();
		
		session.setAttribute("state", state);
		
		String Url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+clientId+"&redirect_uri="+redirecturi+"&state="+state;

		
		return "redirect:"+Url;
	}
	
	
	@RequestMapping(path="/callback")
	public String callbackLogin(@RequestParam String state, @RequestParam String code, HttpServletRequest request){
		 String storedState = (String) request.getSession().getAttribute("state");
		if(!state.equals(storedState)){
		System.out.println("#$%#$$#");
			return "redirect:/";
		}


		return "myreservation";
	}
	
	public String generateState()
	{
	    SecureRandom random = new SecureRandom();
	    return new BigInteger(130, random).toString(32);
	}
}
