/**
 * 
 */
$(document).ready(function(){ 
	
	var categoryDelete = (function(){
		var deleteButton = $('.categoryList p button');
		
		function initDeleteEvent(){
			deleteButton.on('click', function(){
				var id = $(this).parent().attr('value');
				//console.log(id);
				deleteCategory(id);
			});
		}
		function deleteCategory(cid){
			var deleteUrl = "/admin/category/"+cid;
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
					
					$('p[value='+cid+']').remove();
				}
			});
			
		}
		
		return {
			initDeleteEvent : initDeleteEvent
		}
		
	}());
	

	
	
	var selectProduct = (function(){
		var product = $(".modify_product");
	
		function bindEvent(){
			product.on('change', function(){
				var selectedProduct = $(".modify_product option:selected")[0];
				
				var product= {'id': $(selectedProduct).attr('data-product-id').split('_')[1], 'name': $(selectedProduct).text()};
		
				var targetProduct = $('#selected_product');
				targetProduct.attr('product_id', $(selectedProduct).text());
				targetProduct.attr('value', $(selectedProduct).attr('data-product-id').split('_')[1]);
				
			});
		}
		
		return {
			bindEvent : bindEvent
		}
	}());
	
	//fileUpload.bindEvent();
	
	
	categoryDelete.initDeleteEvent();
	selectProduct.bindEvent();
	
	var selectCommentProduct = (function(){
		var product = $(".comment_product");
	
		function bindEvent(){
			product.on('change', function(){
				var selectedProduct = $(".comment_product option:selected")[0];
				
				var product= {'id': $(selectedProduct).attr('data-product-id').split('_')[1], 'name': $(selectedProduct).text()};
		
				var targetProduct = $('#selected_comment_product');
				targetProduct.attr('product_id', $(selectedProduct).text());
				targetProduct.attr('value', $(selectedProduct).attr('data-product-id').split('_')[1]);
				
			});
		}
		
		return {
			bindEvent : bindEvent
		}
	}());
	/*
	$(document).on('click', '.categoryList p button',function(){
		var id = $(this).parent().attr('value');
		//console.log(id);
		deleteCategory(id);
	});
	*/
});