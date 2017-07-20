/**
 * 
 */

$(document).ready(function(){
	
	
	var $more_btn = $('.btn_review_more');

	$more_btn.on('click', function(event){
		event.preventDefault();
		var offset=$('.list_item').length;
		var id = $('.visual_img').data('product');
	
		$.ajax({
			type : "GET",
			url : "/detail/commentmore/"+id+"/offset/"+offset,
			dataType : "json",
			error : function(err){
				console.log(err);
			},
			success : function(data){
				
				var commentList = JSON.parse(data.commentList);
				var $ulEle = $('.list_short_review');
				
				$.each(commentList, function(key, comment){
					
					comment.create_date = (comment.create_date).substring(0,10);
					var liEle =' <li class="list_item">'+ 
                                    '<div>'+
                                        '<div class="review_area no_img">'+
                                        	
                                            '<h4 class="resoc_name">'+data.productName+'</h4>'+
                                            '<p class="review">{{comment}}</p>'+
                                        '</div>'+
                                        '<div class="info_area">'+
                                            '<div class="review_info"> <span class="grade">{{score}}</span> <span class="name">{{nickname}}</span> <span class="date">{{create_date}} 방문</span> </div>'+
                                        '</div>'+
                                    '</div>'+
                                '</li>';
					
					var liEle = $(liEle);
					var template = Handlebars.compile(liEle[0].outerHTML); 
					var html = template(comment);
					
					$ulEle.append(html);
				
				});
		
			}
		});
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
					console.log("detail");
					if(!$(this).hasClass('active')){
						var path = $(this).find('_path').context;
						
						location.attr('class', 'detail_location hide');
						areaWrap.attr('class', 'detail_area_wrap');
						
						$('.item._path').attr('class', 'item _path');
						$(this).attr('class', 'item active _detail');
					}
				}
				else if($(this).hasClass('_path')){
					console.log("path");
					if(!$(this).hasClass('active')){
						var detail = $('.item').find('_detail');
						
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
	
		var bkBtn = $('.bk_btn');
	
		function bindEvent(){
			bkBtn.on('click',function(){
				var id = $('.visual_img').data('product');
				
				$.ajax({
					type : "GET",
					url : "/detail/reservationattempt/"+id,
					dataType : "text",
					error : function(err){
						console.log(err);
					},
					success : function(data){
						//console.log(data);
						//reserve 
						if(state === 0){
							
							alert("예매 기간이 아닙니다.");
						}
						else if(state === 1){
							location.href="/resources/html/reservation.html";
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
	
	
});