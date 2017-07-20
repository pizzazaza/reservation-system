<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="/resources/css/style.css" rel="stylesheet"/>
	<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>-->
	<script src="resources/node_modules/jquery/dist/jquery.js"></script>
	<script src="resources/node_modules/handlebars/dist/handlebars.js"></script>
	<script src="resources/javascript/init.js"></script>

</head>

<body>
    <div id="container">
        <div class="header">
            <header class="header_tit">
                <h1 class="logo">
                    <a href="#" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                    <a href="#" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                </h1>
                		<% 
		String clientid="PWaFcP1RP1p_LliiULjZ"; 
	String redirecturi=URLEncoder.encode("http://127.0.0.1:8080/login", "utf-8"); 
	 SecureRandom random = new SecureRandom();
	 String state = new BigInteger(130, random).toString(32);
	    
	String apiurl = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+clientid+"&" + "redirecturi="+ redirecturi+"&state="+state;
	// (String)session.getAttribute("state"); 
	%>
                <a href="<%=apiurl %>" class="btn_my"> <span title="내 예약">MY</span> </a>
            </header>
        </div>
        <hr>
        <div class="event">
            <div class="section_visual">
                <div class="group_visual">
                    <div class="container_visual">
                        <div class="prev_e">
                            <div class="prev_inn">
                                <a href="#" class="btn_pre_e" title="이전"> <i class="spr_book_event spr_event_pre">이전</i> </a>
                            </div>
                        </div>
                        <div class="nxt_e">
                            <div class="nxt_inn">
                                <a href="#" class="btn_nxt_e" title="다음"> <i class="spr_book_event spr_event_nxt">다음</i> </a>
                            </div>
                        </div>
                        <div>
                            <div class="container_visual">
                                <!-- [D] 이전,다음 버튼을 클릭할때마다 캐러셀 형태로 순환 됨 --->
                                <ul class="visual_img">                          	
                                    <li class="item" style="background-image: url(http://naverbooking.phinf.naver.net/20170209_66/1486628146913la6nC_JPEG/image.jpg); width: 338px;">
                                        <a href="#"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
                                            <div class="event_txt">
                                                <h4 class="event_txt_tit"></h4>
                                                <p class="event_txt_adr"></p>
                                                <p class="event_txt_dsc"></p>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="item" style="background-image: url(http://naverbooking.phinf.naver.net/20170119_48/1484802596907hmVDm_JPEG/image.jpg); width: 338px;">
                                        <a href="#"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
                                            <div class="event_txt">
                                                <h4 class="event_txt_tit">뮤지컬-김종욱찾기 네이버 예약</h4>
                                                <p class="event_txt_adr">대학로 쁘띠첼씨어터</p>
                                                <p class="event_txt_dsc">네이버 예매시, 손크림/발크림(중 래덤)을 드립니다</p>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="item" style="background-image: url(http://naverbooking.phinf.naver.net/20170209_66/1486628146913la6nC_JPEG/image.jpg); width: 338px;">
                                        <a href="#"> <span class="img_btm_border"></span> <span class="img_right_border"></span> <span class="img_bg_gra"></span>
                                            <div class="event_txt">
                                                <h4 class="event_txt_tit"></h4>
                                                <p class="event_txt_adr"></p>
                                                <p class="event_txt_dsc"></p>
                                            </div>
                                        </a>
                                    </li>
                
                                </ul>
                            </div>
                            <span class="nxt_fix"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="section_event_tab">
                <ul class="event_tab_lst tab_lst_min">
                <% 
                	int cSize = 0;
                	int cou = 0;
                	Integer activeId = 0;
                	String className = "anchor";
					List<Map<String, Object>> cList;
					try{
						cList = (List<Map<String, Object>>)request.getAttribute("CategoryList");
                		int size = cList.size();
						activeId = (Integer)request.getAttribute("active");
						cSize = cList.size();
					}
					catch(Exception e){
						cList = null;
						
					}
					%>
					<li class="item" data-category="category_0">
		    			<a class="<% 
						if(activeId == 0){
		    				out.print(className+" active");
						}
						else{
							out.print(className);
						}
					%>"><span> 
						<%out.print("전체");%>
					</span></a>
					</li>
					<%
					//kid확인 
					
					for(Map<String, Object> category: cList) {
						cou++;
						
					%>
			    		
				    	<li class="item" data-category="category_<%=category.get("id")%>">
				    		<a class="<% 
				    		
				    		if(cou == activeId){
				    			out.print(className+" active");
				    		}
				    		else if(cou == cSize){
				    			out.print(className+" last");
				    		}
				    		else{
				    			out.print(className);
				    		}
				    	%>"> <span>
				    		<%
				    		out.print(category.get("name"));
				    		%> 
				    		</span> </a>
				    	</li>	
						
					<% } 
					%>
				
			
            
                    <!-- [D] 활성화 된 anchor는 active 추가 -->
                </ul>
            </div>
            <div class="section_event_lst">
	            <%
	            	Map<String, Object> pCount = (Map<String, Object>)request.getAttribute("productCount");
	            %>
                <p class="event_lst_txt">바로 예매 가능한 전시, 공연, 행사가 <span class="pink"><%=pCount.get("count(*)") %>개</span> 있습니다</p>
                <div class="wrap_event_box">
                    <!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
                    <%
                    	List<Map<String, Object>> pList = (List<Map<String, Object>>)request.getAttribute("productList");
                    	//String imgUrl = (String)request.getAttribute("imgUrl");
                    %>
                    <ul class="lst_event_box"><!-- 홀수 -->
                    
                    <%
                    	for (int listIndex = 0; listIndex < pList.size(); listIndex++) {
                    		if((listIndex+1)%2 == 1){
                    %>
                    
                     	<li class="item">
                            <a href="#" class="item_book" data-item-id="item-<%=pList.get(listIndex).get("id") %>">
                                <div class="item_preview"> <img alt="<%=pList.get(listIndex).get("name")%>" class="img_thumb" src="<%=pList.get(listIndex).get("imgUrl") %>"> <span class="img_border"></span> </div>
                                 <div class="event_txt">
                                    <h4 class="event_txt_tit"> <span><%=pList.get(listIndex).get("name") %></span> <small class="sm"><%=pList.get(listIndex).get("place_name") %></small> </h4>
                                    <p class="event_txt_dsc"> <%=pList.get(listIndex).get("description") %> 
                                    </p>
                                </div>
                            </a>
                        </li>
                    <%
                    		}
                    }
                    %>
                    </ul>
                    <ul class="lst_event_box"><!-- 짝수 -->
                    	<%
                    	for (int listIndex = 0; listIndex < pList.size(); listIndex++) {	
                    		if((listIndex+1)%2 == 0){
                    %>
                    
                    	<li class="item" >
                            <a href="#" class="item_book" data-item-id="item-<%=pList.get(listIndex).get("id") %>">
                                <div class="item_preview"> <img alt="<%=pList.get(listIndex).get("name")%>" class="img_thumb" src="<%=pList.get(listIndex).get("imgUrl") %>"> <span class="img_border"></span> </div>
                                 <div class="event_txt">
                                    <h4 class="event_txt_tit"> <span><%=pList.get(listIndex).get("name") %></span> <small class="sm"><%=pList.get(listIndex).get("place_name") %></small> </h4>
                                    <p class="event_txt_dsc"> <%=pList.get(listIndex).get("description") %> 
                                    </p>
                                </div>
                            </a>
                        </li>
                    <%
                    		}
                    }
                    %>
                    </ul>
                    <!-- 더보기 -->
                    <div class="more">
                        <button class="btn"><span>더보기</span></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer>
        <div class="gototop">
            <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
        </div>
        <div class="footer">
            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
            <span class="copyright">© NAVER Corp.</span>
        </div>
    </footer>
</body>

</html>
