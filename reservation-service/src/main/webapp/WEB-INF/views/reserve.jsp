<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="/resources/css/style.css" rel="stylesheet">
    <script src="/resources/node_modules/jquery/dist/jquery.js"></script>
	<script src="/resources/node_modules/handlebars/dist/handlebars.js"></script>
	<script src="/resources/javascript/reservation.js"></script>
	
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
        <%
        	Map<String, Object> productInfo = (Map<String, Object>)request.getAttribute("productInfo");
        	List<Map<String, Object>> priceInfo = (List<Map<String, Object>>)request.getAttribute("priceInfo");
        	Map<String, Object> imageInfo = (Map<String, Object>)request.getAttribute("imageInfo");
        	
        	String[] priceType = {"?","성인(만 19~64세)", "청소년(만 13~18세)","어린이(만 4~12세)"};
        	String[] priceTypeShort = {"?", "성인", "청소년", "어린이"};
        %>
        <div class="ct">
            <div class="ct_wrap">
                <div class="top_title">
                    <a href="javascript:history.back()" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
                    <h2><span class="title"><%=productInfo.get("name") %></span></h2>
                </div>
                <div class="group_visual">
                    <div class="container_visual" style="width: 414px;">
                        <ul class="visual_img">
                            <li class="item" style="width: 414px;"> <img alt="" class="img_thumb" src="<%=imageInfo.get("imgUrl")%>"> <span class="img_bg"></span>
                                <div class="preview_txt">
                                    <!--  <h2 class="preview_txt_tit">클림트 인사이드</h2> <em class="preview_txt_dsc">₩12,000 ~ </em><em class="preview_txt_dsc">2017.2.17.(금)~2017.4.18.(화), 잔여티켓 ??매</em> </div> -->
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="section_store_details">
                    <div class="store_details">
                        <h3 class="in_tit"><%=productInfo.get("name") %></h3>
                        <p class="dsc">
                            장소 : <%=productInfo.get("place_street") %>(<%=productInfo.get("place_lot") %>)<br> 기간 : <%=productInfo.get("display_start") %> ~ <%=productInfo.get("display_end") %> 
                        </p>
                        <h3 class="in_tit">관람시간</h3>
                        <p class="dsc">
                            <%=productInfo.get("observation_time") %>
                        </p>
                        <h3 class="in_tit">요금</h3>
                        <p class="dsc">
                        	<%for(int i = 0; i < priceInfo.size(); i++){ 
                        		out.print(priceType[(Integer)priceInfo.get(i).get("price_type")] + " " + priceInfo.get(i).get("price") + "원" + " -> "+ priceInfo.get(i).get("discount_price") +" 할인");
                        		if(i < priceInfo.size()-1)
                        			out.print(" / ");
                        	}
                        	%>
                            
                        </p>
                    </div>
                </div>
                <div class="section_booking_ticket">
                    <div class="ticket_body">
                    	<%
                    	float discount_percent = 0;
                    	for(int i = 0; i < priceInfo.size(); i++){
                    	%>
                        <div class="qty">
                            <div class="count_control">
                                <!-- [D] 수량이 최소 값이 일때 ico_minus3, count_control_input에 disabled 각각 추가, 수량이 최대 값일 때는 ico_plus3에 disabled 추가 -->
                                <div class="clearfix">
                                    <a href="#" class="btn_plus_minus spr_book2 ico_minus3 disabled" title="빼기"> </a> <input type="tel" class="count_control_input disabled" value="0" readonly title="수량">
                                    <a href="#" class="btn_plus_minus spr_book2 ico_plus3" title="더하기">
                                    </a>
                                </div>
                                <!-- [D] 금액이 0 이상이면 individual_price에 on_color 추가 -->
                                <div class="individual_price" data-price="<%=priceInfo.get(i).get("discount_price") %>"><span class="total_price">0</span><span class="price_type">원</span></div>
                            </div>
                            <div class="qty_info_icon"> 
                            	<strong class="product_amount"> 
                            		<span><%=priceTypeShort[(Integer)priceInfo.get(i).get("price_type")] %></span> 
                            	</strong> 
                            	<strong class="product_price"> 
                            		<span class="price"><%=priceInfo.get(i).get("price") %> </span> 
                            		<span class="price_type">원</span> </strong> 
                            		<% 
                            		BigDecimal discount_price = (BigDecimal)(priceInfo.get(i).get("discount_price"));
                            		
                            		discount_percent = (((Integer)(priceInfo.get(i).get("price"))-discount_price.intValue())*100)/((Integer)priceInfo.get(i).get("price")); %>
                            	<em class="product_dsc">(<span class="discount_price"><%=priceInfo.get(i).get("discount_price") %></span>원<span class="discount_percent"> <%=discount_percent %></span>% 할인가)</em> 
                            </div>
                        </div>
                        <%
                        } 
                        %>
                       
                    </div>
                </div>
                <div class="section_booking_form">
                    <div class="booking_form_wrap">
                        <div class="form_wrap">
                        <%	Map<String, Object> loginInfo = (Map<String, Object>)request.getAttribute("LoginInfo");
	                       	
                        %>
                            <h3 class="out_tit">예매자 정보</h3>
                            <div class="agreement_nessasary help_txt"> <span class="spr_book ico_nessasary"></span> <span>필수입력</span> </div>
                            <form class="form_horizontal">
                                <div class="inline_form"> <label class="label" for="name"> <span class="spr_book ico_nessasary">필수</span> <span>예매자</span> </label>
                                    <div class="inline_control"> <input type="text" name="name" id="name" class="text" value="<%=loginInfo.get("name") %>" maxlength="17"> </div>
                                </div>
                                <div class="inline_form"> <label class="label" for="tel"> <span class="spr_book ico_nessasary">필수</span> <span>연락처</span> </label>
                                    <div class="inline_control"> <input type="tel" name="tel" id="tel" class="tel" value="<%=loginInfo.get("tel") %>" placeholder="휴대폰 입력 시 예매내역 문자발송"> </div>
                                </div>
                                <div class="inline_form"> <label class="label" for="email">  <span>이메일</span> </label>
                                    <div class="inline_control"> <input type="email" name="email" id="email" class="email" value="<%=loginInfo.get("email") %>" maxlength="50"> </div>
                                </div>
                                <div class="inline_form last"> <label class="label" for="message">예매내용</label>
                                    <div class="inline_control">
                                        <p class="inline_txt selected"><span class="display_date"><%=productInfo.get("display_start") %> ~ <%=productInfo.get("display_end") %></span>, 총 <span class="total_ticket">0</span>매</p>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="section_booking_agreement">
                        <div class="agreement all"> <input type="checkbox" id="chk3" class="chk_agree"> <label for="chk3" class="label chk_txt_label"> <span>이용자 약관 전체동의</span> </label>
                            <div class="agreement_nessasary">
                                <span>필수동의</span> </div>
                        </div>
                        <!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
                        <div class="agreement"> <span class="chk_txt_span"> <i class="spr_book ico_arr_ipc2"></i> <span>개인정보 수집 및 이용 동의</span> </span>
                            <a href="#" class="btn_agreement"> <span class="btn_text">보기</span> <i class="fn fn-down2"></i> </a>
                            <div class="useragreement_details">&lt;개인정보 수집 및 이용 동의&gt;<br><br> 1. 수집항목 : [필수] 이름, 연락처, [선택] 이메일주소<br><br> 2. 수집 및 이용목적 : 사업자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 분쟁조정 해결을 위한 기록보존, 네이버 예약 이용 후 리뷰작성에 따른 네이버페이 포인트 지급 및 관련 안내<br><br> 3. 보관기간<br> - 회원탈퇴 등
                                개인정보 이용목적 달성 시까지 보관<br> - 단, 상법 및 ‘전자상거래 등에서의 소비자 보호에 관한 법률’ 등 관련 법령에 의하여 일정 기간 보관이 필요한 경우에는 해당 기간 동안 보관함<br><br> 4. 동의 거부권 등에 대한 고지: 정보주체는 개인정보의 수집 및 이용 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다.<br></div>
                        </div>
                        <!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
                        <div class="agreement"> <span class="chk_txt_span"> <i class="spr_book ico_arr_ipc2"></i> <span>개인정보 제3자 제공 동의</span> </span>
                            <a href="#" class="btn_agreement"> <span class="btn_text">보기</span> <i class="fn fn-down2"></i> </a>
                            <div class="useragreement_details custom_details_wrap">
                                <div class="custom_details">&lt;개인정보 제3자 제공 동의&gt;<br><br> 1. 개인정보를 제공받는 자 : 미디어앤아트<br><br> 2. 제공하는 개인정보 항목 : [필수] 네이버 아이디, 이름, 연락처 [선택] 이메일 주소<br><br> 3. 개인정보를 제공받는 자의 이용목적 : 사업자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 서비스 이용에 따른 설문조사 및 혜택 제공, 분쟁조정
                                    해결을 위한 기록보존<br><br> 4. 개인정보를 제공받는 자의 개인정보 보유 및 이용기간 : 개인정보 이용목적 달성 시 까지 보관합니다.<br><br> 5. 동의 거부권 등에 대한 고지 : 정보주체는 개인정보 제공 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다.<br></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="box_bk_btn">
                    <!-- [D] 약관 전체 동의가 되면 disable 제거 -->
                    <div class="bk_btn_wrap disable"> <button type="button" class="bk_btn"> <i class="spr_book ico_naver_s"></i>  <span>예약하기</span> </button> </div>
                </div>
            </div>
        </div>
    </div>
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
