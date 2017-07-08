/**
 * 
 */
//(function (window) {
	//delete 선택
	
	
	$(document).on("click", ".categoryList",function(){
		//var id = $(this).attr('value');
		console.log("%%");
		//clearCompleted(id);
	});
//})(window);
/*
var el = document.getElementById(".categoryList");
el.addEventListener("click", function(){ console.log("delete!!!");}, false);
*/
function deleteCategory(cid){
	var deleteUrl = "/delete";
	
	$.ajax({
		type : "DELETE",
		url : deleteUrl,
		data : {id: cid},
		error : function(err){
			console.log(err);
		},
		success : function(data){
		//	var list = $('.completed');
		}
	});
	
}