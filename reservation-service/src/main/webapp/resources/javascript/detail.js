/**
 * 
 */

$(document).ready(function(){
	

	
	
	var $more_btn = $('.btn_review_more');

	$more_btn.on('click', function(event){
		event.preventDefault();
		//var offset=$('.list_item').length;
		var id = $('.visual_img').data('product');
		location.href = "/detail/review/product/"+id+"/offset/0"; 
		
	});
	
	
	
	var close_px = "73px";
	
	var $size_bk = $('.bk_more');
	var $txt_more_btn = $size_bk.find('_open');
	var txt_close_btn = $size_bk.find('_close');
	var $txt_board = $('.store_details');
	
	
	$size_bk.on('click', function(){
		event.preventDefault();
		if($(this).hasClass('_open')){
			$(this).css('display','none');
			$($size_bk[1]).css('display','block');
			$txt_board.attr('class','store_details' );
		}
		else if($(this).hasClass('_close')){
			$(this).css('display','none');
			$($size_bk[0]).css('display', 'block');
			$txt_board.attr('class','store_details close3' );
		}
		
	});
		
		
	
	var animateFunc = (function(){
		
		var pre_b = $('.btn_prev');
		var nxt_b = $('.btn_nxt');
		var move = 414;
		function bindEvent(){
			pre_b.on('click', function(){
			
				animateFunc.promoMovePrev();
			});
			
			nxt_b.on('click', function(){
				animateFunc.promoMoveNxt();
			});
		}
		function promoMovePrev(){
			var $figure_pag = $('.figure_pagination');
		
			var fi_cur = $figure_pag.children()[0];
		
			var fi_tot = $figure_pag.children()[1];
			var cur = $(fi_cur).text()-1;
			if(cur <= 0){
				cur = $(fi_tot).text().split('/')[1];
			}
			$(fi_cur).text(cur);
			var leftEmpty = $('.visual_img').children().filter(":last-child").clone(true);
			
			leftEmpty.prependTo($('.visual_img'));
			$('.visual_img').children().filter(":last-child").remove();
			$('.visual_img').css('left','-'+move+"px");
			$('.visual_img').animate({"left": "0px"}, 200);
		}
		function promoMoveNxt(){
			var $figure_pag = $('.figure_pagination');
		
			var fi_cur = $figure_pag.children()[0];
		
			var fi_tot = $figure_pag.children()[1];
			var cur = $(fi_cur).text()-0+1;
	
			if(cur > ($(fi_tot).text().split('/')[1]-0) ){
				cur = 1;
			}
			$(fi_cur).text(cur);
			var rightEmpty = $('.visual_img').children().filter(":first-child").clone(true);
			
			rightEmpty.appendTo($('.visual_img'));
			$('.visual_img').children().filter(":first-child").remove();
			$('.visual_img').css('left','0');
			$('.visual_img').animate({"left": "-"+move+"px"}, 200);
			
		}
		return {
			promoMovePrev : promoMovePrev,
			promoMoveNxt : promoMoveNxt,
			bindEvent : bindEvent
		};
	}());
	animateFunc.bindEvent();
	
	var infoTap = (function(){
		var item = $('.item');
		var areaWrap = $('.detail_area_wrap');
		var location = $('.detail_location');
		function bindEvent(){
			item.on('click', function(event){
				event.preventDefault();
				if($(this).hasClass('_detail')){
					
					if(!$(this).hasClass('active')){
						
						
						location.attr('class', 'detail_location hide');
						areaWrap.attr('class', 'detail_area_wrap');
						
						$('.item._path').attr('class', 'item _path');
						$(this).attr('class', 'item active _detail');
					}
				}
				else if($(this).hasClass('_path')){
					
					if(!$(this).hasClass('active')){
						
						
						location.attr('class', 'detail_location');
						areaWrap.attr('class', 'detail_area_wrap hide');
						
						$('.item._detail').attr('class', 'item _detail');
						$(this).attr('class', 'item active _path' );
					}
				}
			});
		}
		return {
			bindEvent : bindEvent
		}
	}());
	infoTap.bindEvent();
	
	
	var imageLayerPopUp = (function(){
		var thumbArea = $('.thumb_area');
		
		function bindEvent(){
			thumbArea.on('click',function(){
				
				var $href = $(this).attr('href');
		        layer_popup($href);
			});
		}
		function layer_popup(el){

	       

	    }
		return {
			bindEvent : bindEvent,
			layer_popup:layer_popup
		}
	}());
	
	imageLayerPopUp.bindEvent();
	
	var reservationProcess = (function(){
	
		var bk_btn = $('.bk_btn');
		
		
		
		function bindEvent(){
			
			bk_btn.on('click',function(){
				var id = $('.visual_img').data('product');
				
				$.ajax({
					type : "GET",
					url : "/detail/reservationattempt/"+id,
					dataType : "text",
					error : function(err){
						console.log(err);
					},
					success : function(data){
						
						data = JSON.parse(data);
						//reserve 
						
						if(data.state === 0){
							
							alert("예매 기간이 아닙니다.");
						}
						else if(data.state === 1){
							location.href="/detail/reserve/product_id/"+id;
						}
					}
				});
			});
		}
		return {
			bindEvent : bindEvent
		}
	}());
		
	reservationProcess.bindEvent();
	
	
	
	
	var review_loading = (function(){
		var liEleImg =
			 '<li class="list_item">'+
                '<div>'+
                 	'<div class="review_area">'+
                '<div class="thumb_area">'+
                	'<a href="#pop_up_layer" class="thumb" title="이미지 크게 보기"> <img width="90" height="90" class="img_vertical_top" src="<%=productComment.get(i).get("imgUrl")%>" alt="리뷰이미지"> </a> <span class="img_count"><%=productComment.get(i).get("count") %></span>'+                                                
            	'<!-- pop up layer -->'+
            	
				'<!--  -->'+
				'</div>'+
                        '<h4 class="resoc_name"><%=infoMap.get("name") %></h4>'+
                        '<p class="review"><%=productComment.get(i).get("comment") %></p>'+
                	'</div>'+
                '<div class="info_area">'+
                        '<div class="review_info"> '+
                        	'<span class="grade"><%=productComment.get(i).get("score")%></span> '+
                        	'<span class="name"><%=productComment.get(i).get("nickname")%></span> '+
                        	'<span class="date"><%=(productComment.get(i).get("create_date").toString()).substring(0,10)%> 방문</span>'+ 
                       	'</div>'+
                    '</div>'+
                '</div>'+
            '</li>';
		var liEle =
			 '<li class="list_item">'+
               '<div>'+
                	'<div class="review_area no_img">'+
                       '<h4 class="resoc_name"><%=infoMap.get("name") %></h4>'+
                       '<p class="review"><%=productComment.get(i).get("comment") %></p>'+
               	'</div>'+
               '<div class="info_area">'+
                       '<div class="review_info"> '+
                       	'<span class="grade"><%=productComment.get(i).get("score")%></span> '+
                       	'<span class="name"><%=productComment.get(i).get("nickname")%></span> '+
                       	'<span class="date"><%=(productComment.get(i).get("create_date").toString()).substring(0,10)%> 방문</span>'+ 
                      	'</div>'+
                   '</div>'+
               '</div>'+
           '</li>';
		function bindEvent(){
			$(document).scroll(function() {
				var maxHeight = $(document).height();
				var currentScroll = $(window).scrollTop() + $(window).height();
				
				if(maxHeight <= currentScroll){
					var id = $('.visual_img').data('product');
					var offset=$('.list_item').length;
					review_loading.scroll_down_bottom(offset, id);
				}
			});
		}
		
		function initModule(){
			review_loading.bindEvent();
		}
		function scroll_down_bottom(id, ofs){
			$.ajax({
				type : "GET",
				url : "/detail/commentmore/"+id+"/offset/"+ofs,
				dataType : "json",
				error : function(err){
					console.log(err);
				},
				success : function(data){
					more_product_review(data);
				}
					
			});
		}
		function more_product_review(data){
			var reviewList = JSON.parse(data);
			var ulEle = $('.list_short_review');
			
			var cou = 0;
			reviewList = JSON.parse(reviewList);
			
			$.each(reviewList, function(key, review){
				
					
				var li = $(liEle);
				var template = Handlebars.compile(liEle[0].outerHTML); 
				var html = template(product);
				
				$(ulEle[cou%2]).append(html);
				cou++;
			});
		}
		
		return {
			scroll_down_bottom : scroll_down_bottom,
			more_product_review : more_product_review,
			bindEvent : bindEvent,
			initModule : initModule
		}
	}());

	$("img.img_thumb").lazyload()
});