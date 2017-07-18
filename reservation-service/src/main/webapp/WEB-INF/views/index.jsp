<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<!--  <script type="text/javascript" src="../javascript/delete.js"></script> -->
<title>Insert title here</title>
</head>
<body>
	<form action="/category/form" method="GET">
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
	<script>
		//script로 모듈화 하기 
		function deleteCategory(cid){
			var deleteUrl = "/category/"+cid;
			//console.log(deleteUrl);
			$.ajax({
				type : "DELETE",
				url : deleteUrl,
				//dataType : "json",
				//data : {id: cid},
				error : function(err){
					console.log(err);
				},
				success : function(data){
					console.log(data);
					$('p[value='+cid+']').remove();
				}
			});
			
		}
		
		$(document).on('click', '.categoryList p button',function(){
			var id = $(this).parent().attr('value');
			//console.log(id);
			deleteCategory(id);
		});
		
		
	</script>
</body>
</html>