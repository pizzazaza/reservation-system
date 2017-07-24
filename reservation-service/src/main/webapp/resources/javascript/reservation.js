/**
 * 
 */


$(document).ready(function(){
	var reservation_bit= [16,8,4,2,1];//agree, ticket, name, tel, email 
	var check_validation = 0;
	var btn_agreement = $('.btn_agreement');
	var bk_btn_wrap = $('.bk_btn_wrap');
	btn_agreement.on('click', function(){
		event.preventDefault();
		
		if($(this).closest('.agreement').hasClass('open')){
	
			$(this).closest('.agreement').attr('class', 'agreement');
		}
		else{
	
			$(this).closest('.agreement').attr('class', 'agreement open');
		}
	});
	
	var btn_plus_minus = $('.btn_plus_minus');
	
	btn_plus_minus.on('click', function(){
		event.preventDefault();
		
		if($(this).hasClass('ico_minus3') && !$(this).hasClass('disable')){
			var count = $(this).siblings('input').attr('value');
			
			if(count === '0'){
				return;
			}
			count--;
			var ind_price = $(this).parent().siblings('.individual_price');
			var price = ind_price.data();
			price = price.price;
			price = price*count;
			
			$(this).siblings('input').attr('value', count);
			$(ind_price).children('.total_price').text(price);
			var tot_tick = $('.total_ticket');
			tot_tick.text((tot_tick.text()-0) - 1);
			if(tot_tick.text()-0 === 0){
				check_validation = check_validation & (31-reservation_bit[1]);
			}
			if(count === 0){
				$(this).attr('class', 'btn_plus_minus spr_book2 ico_minus3 disabled');
				$(this).siblings('input').attr('class', 'count_control_input disabled');
				$(this).siblings('.individual_price').attr('class', 'individual_price');
				
			}
			
		}
		else if($(this).hasClass('ico_plus3')){
			var count = $(this).siblings('input').attr('value');
			count++;
			var ind_price = $(this).parent().siblings('.individual_price');
			var price = ind_price.data();
			price = price.price;
			price = price*count;
			$(this).siblings('input').attr('value', count);
			if(count > 0){
				$(this).siblings('.ico_minus3').attr('class', 'btn_plus_minus spr_book2 ico_minus3');
				$(this).siblings('input').attr('class', 'count_control_input');
				$(this).siblings('.individual_price').attr('class', 'individual_price on_color');
				$(ind_price).children('.total_price').text(price);
				var tot_tick = $('.total_ticket');
				tot_tick.text((tot_tick.text()-0) + 1);
				check_validation = check_validation | reservation_bit[1];
			}
		}
		$('.bk_btn_wrap').trigger('check');
	});
	

	
	var all_agree= $('#chk3');
	
	all_agree.on('click', function(){
		var checked = $(this).attr('checked')
	
		
		if(checked === undefined){
			$(this).attr('checked', 'checked');
			check_validation = check_validation | reservation_bit[0];
		}
		else if(checked === 'checked'){
			$(this).removeAttr('checked');
			check_validation = check_validation & (31-reservation_bit[0]);
		}
		$('.bk_btn_wrap').trigger('check');
	});

	
	var inline_control = $('.inline_control');
	var name_reg_exp = /^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/;
	var email_reg_exp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; 
	var phone_reg_exp = /^\d{3}-\d{3,4}-\d{4}$/;
	
	
	inline_control.on('click',function(){
		$(this).trigger('select');
	});
	inline_control.on('change',function(){
		
		$(this).trigger('select');
	});
	
	inline_control.on('select',function(){
		if($(this).find('input').hasClass('text')){
			if(name_reg_exp.test($(this).find('input').val())){
				check_validation = check_validation | reservation_bit[2];
			}
			else{
				check_validation = check_validation & (31-reservation_bit[2]);
			}
		}
		else if($(this).find('input').hasClass('tel')){
			
			if(phone_reg_exp.test($(this).find('input').val())){
				console.log(check_validation);
				check_validation = check_validation | reservation_bit[3];
			}
			else{
				check_validation = check_validation & (31-reservation_bit[3]);
			}
		}
		else if($(this).find('input').hasClass('email')){		
			if(email_reg_exp.test($(this).find('input').val())){
				check_validation = check_validation | reservation_bit[4];
			}
			else{
				check_validation = check_validation & (31-reservation_bit[4]);
			}
		}
		$('.bk_btn_wrap').trigger('check');
	});
	
	
	bk_btn_wrap.on('check', function(){
		if(check_validation === 31){
			
			$(this).attr('class', 'bk_btn_wrap');
		}
		else{
			
			$(this).attr('class', 'bk_btn_wrap disable');
		}
		
	});
});

