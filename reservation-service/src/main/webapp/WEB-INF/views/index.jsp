<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="/resources/javascript/admin.js"></script>

<!--  <script type="text/javascript" src="../javascript/delete.js"></script> -->


<title>Insert title here</title>
</head>
<body>
	<form action="/admin/category/form" method="GET">
		<p>Category 추가 </p>
		<div>
	        <label for="cateogry">Category:</label>
	        <input type="text" id="category" name="category"/>
	    </div> 
	    <div class="button">
	        <button type="submit">Add Category</button>
	    </div>
	</form>
	<br></br>
	<p>Category List</p>
	<div class="categoryList">
		
		<% 
			List<Map<String, Object>> cList = (List<Map<String, Object>>)request.getAttribute("CategoryList");

			for(Map<String, Object> category: cList) {%>
			    
			    	<p value=<%=category.get("id")%>>
			    		<%out.println(category.get("name"));%> 
			    		
			    		<button type="button">Delete</button>
			    		
			    	</p>	
			<%	//value<%=category.get("id") out.println(category.get("name"));
			} 
			%>
		
	</div>
	<div>
	<!-- file upload -->
	<select class="modify_product" name="product">
		<option value="" data-product-id="product_0">이미지를 추가할 product</option>
		<% 
			List<Map<String, Object>> pList = (List<Map<String, Object>>)request.getAttribute("ProductList");
			for(Map<String, Object> product: pList) {
		%>
			<option value="<%=product.get("name")%>" data-product-id="product_<%=product.get("id")%>"><%=product.get("name")%></option>
		<%
			}
		%>
		
	</select>
	<form class="upload_img" method="post" action="/admin/category/file" enctype="multipart/form-data">
		selected product : <input id="selected_product" type="text" name="product_id" value=""><br>
	    poster : <input id="post_name" type="text" name="Img_name"><br>
	    <input id="post_img" type="file" name="Img"><br>
	    subImg : <input type="text" name="Img_name"><br>
	    <input type="file" name="Img"><br>
	    subImg : <input type="text" name="Img_name"><br>
	    <input type="file" name="Img"><br>
	    subImg : <input type="text" name="Img_name"><br>
	    <input type="file" name="Img"><br>
	    subImg : <input type="text" name="Img_name"><br>
	    <input type="file" name="Img"><br>
	    subImg : <input type="text" name="Img_name"><br>
	    <input type="file" name="Img"><br>
	    subImg : <input type="text" name="Img_name"><br>
	    <input type="file" name="Img"><br>
	    subImg : <input type="text" name="Img_name"><br>
	    <input type="file" name="Img"><br>
	    subImg : <input type="text" name="Img_name"><br>
	    <input type="file" name="Img"><br>
	    <input id="submit_but" type="submit" value="등록">
	</form>
	</div>
	<br>
	<div>
	
	<select class="comment_product" name="product">
		<option value="" data-product-id="product_0">이미지를 추가할 product</option>
		<% 
			for(Map<String, Object> product: pList) {
		%>
			<option value="<%=product.get("name")%>" data-product-id="product_<%=product.get("id")%>"><%=product.get("name")%></option>
		<%
			}
		%>
		
	</select>
	<form class="upload_comment_img" method="post" action="/admin/comment/file" enctype="multipart/form-data">
		selected product : <input id="selected_comment_product" type="text" name="product_id" value=""><br>
	    poster : <input id="comment_name" type="text" name="Img_name"><br>
	    <input id="comment_img" type="file" name="Img">
	    <br>
	    <input id="submit_comment_but" type="submit" value="등록">
	</form>
	</div>

</body>
</html>