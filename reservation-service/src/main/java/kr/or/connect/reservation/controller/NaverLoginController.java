package kr.or.connect.reservation.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.reservation.service.UserInfoService;



@Controller
@PropertySource("classpath:/application.properties")
@RequestMapping("/login")
public class NaverLoginController {
	@Value("${spring.datasource.user-profile-url}")
	private String userProfileUrl;
	@Value("${spring.datasource.redirect-uri}")
	private String redirecturi;
	@Value("${spring.datasource.client-id}")
	private String clientId;
	@Value("${spring.datasource.client-secret}")
	private String ClientSecret;
	@Autowired
	UserInfoService userInfoService;
	
	@GetMapping("/myreservation")
	public String requestLogin( HttpServletRequest request){
		
		//encodeURIComponent('http://localhost:8080/login/callback')
		//final String callbackUrl = "http://127.0.0.1:8080/callback";
		
		HttpSession session = request.getSession();
		
		Map<String, Object> login = (Map<String, Object>)session.getAttribute("LoginInfo");
		if(login != null){
			//내부값 확인 추가 
			System.out.println("됨?? "+login);
			return "myreservation";
		}
	
		System.out.println("d안 됨 ?? ");
		String state = generateState();
		
		String Url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+clientId+"&redirect_uri="+redirecturi+"&state="+state;
		
		
		return "redirect:"+Url;
	
		
	
	}
	
	
	@RequestMapping("/callback")
	public String callbackLogin(@RequestParam String code, @RequestParam String state,HttpServletRequest request){
		 
		
		 String tokenRequerUrl = "https://nid.naver.com/oauth2.0/token?client_id="
				+ clientId+"&client_secret="+ ClientSecret + "&grant_type=authorization_code" + "&state=" + state + "&code=" + code;
		 String data = getHtml(tokenRequerUrl, null);
		
		 Map<String, Object> sessionMap;
		 
		 Map<String, String> dataMap = JSONStringToMap(data);
		 String accessToken = dataMap.get("access_token");
		 String tokenType = dataMap.get("token_type"); 
		
		 String profileDataXml = getHtml(userProfileUrl, tokenType +" "+ accessToken);
		 System.out.println(profileDataXml);
		 JSONObject jsonObject =  XML.toJSONObject(profileDataXml);     //xml을 json으로 파싱합니다.
		 JSONObject responseData = jsonObject.getJSONObject("data");  
		 Map<String,String> userMap = JSONStringToMap(responseData.get("response").toString()); 
		 Map<String,String> resultMap = JSONStringToMap(responseData.get("result").toString()); 
		 System.out.println(userMap.keySet());
		 System.out.println(resultMap.keySet());
		 //JSONObject jsonObj = new JSONObject(dataMap);
		 HttpSession session = request.getSession();
	
		 sessionMap = userInfoService.loadUserPhone(userMap.get("email"));
		 
		 sessionMap.put("email",userMap.get("email"));
		 sessionMap.put("name",userMap.get("name"));
		 
		 
		 System.out.println(sessionMap.keySet());
		 session.setAttribute("LoginInfo", sessionMap);
		 
		 //jsonObj.
		 return "myreservation";
	}
	
	public String generateState()
	{
	    SecureRandom random = new SecureRandom();
	    return new BigInteger(130, random).toString(32);
	}

	public static String getHtml(String url, String authorization) {
		HttpURLConnection httpRequest = null;
		String resultValue = null;
		try {
			URL u = new URL(url);
			httpRequest = (HttpURLConnection) u.openConnection();
			httpRequest.setRequestProperty("Content-type", "text/xml; charset=UTF-8");
   
			if(authorization != null){
				httpRequest.setRequestProperty("Authorization", authorization);
			}
			httpRequest.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(httpRequest.getInputStream(), "UTF-8"));
   
	    StringBuffer sb = new StringBuffer();
	    String line = null;
	    while((line = in.readLine()) != null){
		   sb.append(line);
		   sb.append("\n");
	    }
	    resultValue = sb.toString();
		} catch (IOException e) {

		} finally {
			if (httpRequest != null) {
				httpRequest.disconnect();
			}
		}
		return resultValue;
	}
		
	public static Map<String, String> JSONStringToMap(String str){
		Map<String,String> map = new HashMap<String,String>();
  		ObjectMapper mapper = new ObjectMapper();
  
  		try {
	  		map = mapper.readValue(str, new TypeReference<HashMap<String,String>>() {});
  		} catch (JsonParseException e) {
	  		e.printStackTrace();
  		} catch (JsonMappingException e) {
	  		e.printStackTrace();
  		} catch (IOException e) {
	  		e.printStackTrace();
  		}
  		return map;
	}

}
