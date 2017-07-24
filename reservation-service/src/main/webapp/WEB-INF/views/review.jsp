<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="/resources/css/style.css" rel="stylesheet">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	
</head>

<body>
    <div id="container">
		<!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
					<a href="/" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
				</h1>
				<a href="http://localhost:8080/login/myreservation" class="btn_my"> <span title="내 예약">MY</span> </a>
			</header>
		</div>
        <div class="ct">
            <div class="wrap_review_list">
                <div class="review_header">
                    <div class="top_title gr">
                    <%
                    Map<String, Object> infoMap = (Map<String, Object>)request.getAttribute("infoMap");
          			Map<String, Object> scoreAndCount = (Map<String, Object>)request.getAttribute("scoreAndCount"); 
	               	BigDecimal bd = (BigDecimal)scoreAndCount.get("score");
	               	DecimalFormat format = new DecimalFormat(".#");
	                String str = format.format(bd);
                    List<Map<String, Object>> productComment = (List<Map<String, Object>>)request.getAttribute("productComment");
                    
                    Double starScore = (Double)(bd.doubleValue()*100/5.0);
                    
                    %>
                        <a href="javascript:history.back()" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
                        <h2><a class="title" href="#"><%=infoMap.get("name") %></a></h2>
                    </div>
                </div>
                <div class="section_review_list">
                    <div class="review_box">
                        <h3 class="title_h3">예매자 한줄평</h3>
                        <div class="short_review_area">
                            <div class="grade_area"> <span class="graph_mask"> <em class="graph_value" style="width: <%=starScore%>%;"></em> </span> <strong class="text_value"> <span><%=str %></span> <em class="total">5.0</em> </strong> <span class="join_count"><em class="green"><%=scoreAndCount.get("count")%>건</em> 등록</span>                                </div>
                            <ul class="list_short_review">
	                        <% 
	                       
                            	for(int i = 0; i < productComment.size(); i++){
                            %>
                                <li class="list_item">
                                    <div>
                                    <% 
                                    	if(productComment.get(i).containsKey("imgUrl")){ 
                                    %>
                                        <div class="review_area">
                                            <div class="thumb_area">
                                                <a href="#pop_up_layer" class="thumb" title="이미지 크게 보기"> <img width="90" height="90" class="img_vertical_top" src="<%=productComment.get(i).get("imgUrl")%>" alt="리뷰이미지"> </a> <span class="img_count"><%=productComment.get(i).get("count") %></span>                                                
                                            	<!-- pop up layer -->
                                            	
												<!--  -->
                                            </div>
                                            <%
                                            }
                                            else{
                                            %>
                                     	<div class="review_area no_img">
			                                <%
			                                } 
			                                %> 
			                                <h4 class="resoc_name"><%=infoMap.get("name") %></h4>
                                            <p class="review"><%=productComment.get(i).get("comment") %></p>
                                    	</div>
                                    <div class="info_area">
                                            <div class="review_info"> 
                                            	<span class="grade"><%=productComment.get(i).get("score")%></span> 
                                            	<span class="name"><%=productComment.get(i).get("nickname")%></span> 
                                            	<span class="date"><%=(productComment.get(i).get("create_date").toString()).substring(0,10)%> 방문</span> 
                                           	</div>
                                        </div>
                                    </div>
                                </li>
                            <%
                        	}
                            %>
                                
                            </ul>
                        </div>
                        <p class="guide"> <i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span> </p>
                    </div>
                </div>
            </div>
        </div>
        <hr> </div>
		<footer>
	        <div class="gototop">
	            <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
	        </div>
	        <div id="footer" class="footer">
	            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
	            <span class="copyright">© NAVER Corp.</span>
	        </div>
	    </footer>
</body>

</html>
