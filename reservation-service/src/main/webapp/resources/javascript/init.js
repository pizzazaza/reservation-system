/**
 * 
 */
var move = 338;

$(document).ready(function(){
	
	var lnkLogo = $('.lnk_logo');

	lnkLogo.on('click', function(){
		location.href="/";
	});
	
	var btnMy = $('.btn_my');

	btnMy.on('click', function(){
		location.href="/resources/html/myreservation.html";
	});
	
	
	
	
	
	
///////////////////////////////////////////////////////////
	
	
	$.ajaxSetup({
		  contentType: "application/json; charset=utf-8"
	});
	
	
	
//////////////////////////////////////////////////////////////

	var viewProductDetail = (function(){
		var imgbtmborder = $('.img_bg_gra');
		var itembook = $('.item_book');
		
		function bindClickEvent(){
		
			imgbtmborder.on('click', function(){
				var id = $(this).attr('data-item-id');
				id = id.split('-')[1];
				console.log(id);
				location.href="/detail/product_id/"+id;
			});
			
			itembook.on('click', function(){
				var id = $(this).attr('data-item-id');
				console.log(id);
				id = id.split('-')[1];
				console.log(id);
				location.href="/detail/product_id/"+id;
			});
		}
		
		return {
			bindClickEvent : bindClickEvent
		}
	}());
	
	viewProductDetail.bindClickEvent();
	
	
	var animateFunc = (function(){
		
		var promoMoveIntervalId;
		var promoPrevOverEvent;
		var promoNextOverEvent;
		var pre_b = $('.prev_e');
		var nxt_b = $('.nxt_e');
		var promoButtonOver_n = $('.nxt_inn');
		var promoButtonOver_p = $('.prev_inn');
	
		
		function bindEvent(){
			pre_b.on('click', function(){
			
				animateFunc.promoMovePrev();
			});
			
			nxt_b.on('click', function(){
				animateFunc.promoMoveNxt();
			});
		
			promoButtonOver_n.on('mouseover', function(){
				animateFunc.stopPromoNextOver();
			});
			promoButtonOver_n.on('mouseout', function(){
				animateFunc.startPromoNextOut();
			});
			
			promoButtonOver_p.on('mouseover', function(){
				animateFunc.stopPromoPrevOver();
			});
			promoButtonOver_p.on('mouseout', function(){
				animateFunc.startPromoPrevOut();
			});
		}
		function promoAutoMove(){
			var rightEmpty = $('.visual_img').children().filter(":first-child").clone(true);
			
			rightEmpty.appendTo($('.visual_img'));
			$('.visual_img').children().filter(":first-child").remove();
			$('.visual_img').css('left','0');
			$('.visual_img').animate({left: "-"+move+"px"}, 200);
		}
		
		function promoStartMove(){
			promoMoveIntervalId = setInterval(animateFunc.promoAutoMove,2000);
		}
		function clearFunctionCall(){
			clearTimeout(promoPrevOverEvent);
			clearTimeout(promoNextOverEvent);
			clearInterval(promoMoveIntervalId);
		}
		
		function promoRestartMove(){
			animateFunc.clearFunctionCall();
			promoMoveIntervalId = setInterval(animateFunc.promoAutoMove,2000);
		}
		
		function promoMovePrev(){
			animateFunc.clearFunctionCall();
			var leftEmpty = $('.visual_img').children().filter(":last-child").clone(true);
			
			leftEmpty.prependTo($('.visual_img'));
			$('.visual_img').children().filter(":last-child").remove();
			$('.visual_img').css('left','-'+move+"px");
			$('.visual_img').animate({"left": "0px"}, 200);
		}
		
		function promoMoveNxt(){
			animateFunc.clearFunctionCall();
			var rightEmpty = $('.visual_img').children().filter(":first-child").clone(true);
			
			rightEmpty.appendTo($('.visual_img'));
			$('.visual_img').children().filter(":first-child").remove();
			$('.visual_img').css('left','0');
			$('.visual_img').animate({"left": "-"+move+"px"}, 200);
			
		}
		
		function stopPromoNextOver(){
			animateFunc.clearFunctionCall();
			promoNextOverEvent = setTimeout(animateFunc.promoRestartMove, 4000);
		}	
	
		function startPromoNextOut(){
			animateFunc.clearFunctionCall();
			promoMoveIntervalId = setInterval(animateFunc.promoAutoMove,2000);
		}
		function stopPromoPrevOver(){
			animateFunc.clearFunctionCall();
			promoPrevOverEvent = setTimeout(animateFunc.promoRestartMove, 4000);
		}
		function startPromoPrevOut(){
			animateFunc.clearFunctionCall();
			promoMoveIntervalId = setInterval(animateFunc.promoAutoMove,2000);
		}
		
		function initModule(){
			animateFunc.bindEvent();
			animateFunc.promoStartMove();
		}
		return {
			promoMovePrev : promoMovePrev,
			promoMoveNxt : promoMoveNxt,
			promoAutoMove : promoAutoMove,
			promoRestartMove : promoRestartMove,
			stopPromoNextOver : stopPromoNextOver,
			startPromoNextOut : startPromoNextOut,
			stopPromoPrevOver : stopPromoPrevOver,
			startPromoPrevOut : startPromoPrevOut,
			clearFunctionCall : clearFunctionCall,
			promoStartMove : promoStartMove,
			initModule : initModule,
			bindEvent : bindEvent
		};
	}())
	
	
	/*
	var slideMove = function(){
		var promo = $('.visual_img');
		//var promo = document.getElementsByClassName('visual_img');
		promo.children;
	
	}
	*/
	
	var productLoading = (function(){
		var anchor = $('.anchor');
		var more_btn = $('.btn');
		
		function bindEvent(){
			anchor.on('click', function(){
				if(!$(this).hasClass('active')){
					var id = $(this).parent().attr('data-category').split('_')[1];
					
					productLoading.changeCategory(this, 0, id);
				}
			});
			
			more_btn.on('click', function(){
				var id = $('.anchor.active')[0];
				id = $(id).parent().attr('data-category').split('_')[1];
				var offset  = $('.lst_event_box');
				offset = offset[0].childElementCount + offset[1].childElementCount;
				productLoading.changeCategory(this, offset,id);
			});
			
			$(document).scroll(function() {
				var maxHeight = $(document).height();
				var currentScroll = $(window).scrollTop() + $(window).height();
				
				if(maxHeight <= currentScroll){
					id = $('.anchor.active')[0];
					id = $(id).parent().attr('data-category').split('_')[1];
					var offset  = $('.lst_event_box');
					offset = offset[0].childElementCount + offset[1].childElementCount;
					productLoading.changeCategory(this ,offset, id);
				}
			});
		}
		function initModule(){
			productLoading.bindEvent();
		}
		function changeCategory(category, offset, id){
			
			var c_id = $(category).parent().attr('data-category');
			
			$.ajax({
				type : "GET",
				url : "/changecategory/"+id+"/offset/"+offset,
				dataType : "json",
				error : function(err){
					console.log(err);
				},
				success : function(data){
					var jsonData = JSON.stringify(data);
					//active 변경 
				
					$('.pink').text(data.count+"개");
					
					if(offset == 0)
						productLoading.changeActiveCategory(c_id);
					
					if(offset == 0)
						productLoading.changeCategoryProduct(data.products);
					else
						productLoading.moreCategoryProduct(data.products);
				}
			});
		}
		
		function changeCategoryProduct(productList){
		
			var ulEle = $('.lst_event_box');
			$('.lst_event_box').children().remove();
			
			var cou = 0;
			productList = JSON.parse(productList);
		
			$.each(productList, function(key, product){
				var liEle = "<li class='item'>"+
				"<a href='#' class='item_book'>"+
			    	"<div class='item_preview'> <img alt='"+product.name+"' class='img_thumb' src='"+product.imgUrl+"'> <span class='img_border'></span> </div>"+
			    	"<div class='event_txt'>"+
			        	"<h4 class='event_txt_tit'> <span>"+product.name+"</span> <small class='sm'>"+product.place_name+"</small> </h4>"+
			        	"<p class='event_txt_dsc'>"+ product.description+""+ 
			        	"</p></div> </a></li> ";
				$(ulEle[cou%2]).append(liEle);
				cou++;
			});
		}
		function moreCategoryProduct(productList){
		
			var ulEle = $('.lst_event_box');
			
			var cou = 0;
			productList = JSON.parse(productList);
			
			$.each(productList, function(key, product){
				var liEle =
					'<li class="item">'+
	                '<a href="#" class="item_book">'+
	                    '<div class="item_preview"> <img alt="{{name}}" class="img_thumb" src="{{imgUrl}}"> <span class="img_border"></span> </div>'+
	                     '<div class="event_txt">'+
	                        '<h4 class="event_txt_tit"> <span>{{name}}</span> <small class="sm">{{place_name}}</small> </h4>'+
	                        '<p class="event_txt_dsc"> {{description}} '+
	                        '</p>'+
	                    '</div>'+
	                '</a>'+
	            '</li>';
				
				var liEle = $(liEle);
				var template = Handlebars.compile(liEle[0].outerHTML); 
				var html = template(product);
				
				$(ulEle[cou%2]).append(html);
				cou++;
			});
		}
		
		function changeActiveCategory(category_id){
		
			var targetTag = $('.anchor.active');
			var targetClass = targetTag.attr('class');
			targetClass.replace(" active" ,"");
			targetTag.attr('class', "anchor");
			
			var newTargetTag = $('[data-category='+category_id+'] a');
			var newTargetClass = newTargetTag.attr('class');
			newTargetClass = newTargetClass + " active";
			newTargetTag.attr('class', newTargetClass);
		}
		
		return {
			changeCategory: changeCategory,
			changeCategoryProduct : changeCategoryProduct,
			moreCategoryProduct : moreCategoryProduct,
			changeActiveCategory : changeActiveCategory,
			bindEvent : bindEvent,
			initModule : initModule
		}
	}());
	var aniFunc = animateFunc;
	
	aniFunc.initModule();
	
	
	var lodeProduct = productLoading;
	lodeProduct.initModule();
});



