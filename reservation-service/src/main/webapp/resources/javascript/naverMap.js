/**
 * 
 */

$(document).ready(function(){
	var naverMapApi = (function(){
		var oPoint = new nhn.api.map.LatLng(37.5010226, 127.0396037);
		nhn.api.map.setDefaultPoint('LatLng');
		oMap = new nhn.api.map.Map('LocationMap', {
		    point: oPoint,
		    zoom: 10,
		    enableWheelZoom: true,
		    enableDragPan: true,
		    enableDblClickZoom: false,
		    mapMode: 0,
		    activateTrafficMap: false,
		    activateBicycleMap: false,
		    minMaxLevel: [1, 14],
		    size: new nhn.api.map.Size(370, 370)
		});
	}());
});