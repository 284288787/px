<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@ taglib uri="http://www.99love.net/jsp/tag/star-tags" prefix="st"%><!DOCTYPE html>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
    	<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    	<meta http-equiv="Cache-Control" content="no-transform">
    	<meta http-equiv="Cache-Control" content="no-siteapp">
    	<title>来米啦</title>
		<link rel="stylesheet" href="${basePath}static/css/header.css">
	</head>
	<body>
		<div class="container">
			<div class="header">立即开奖</div> 
		</div>
		<script src="${basePath}static/js/jquery.min-1.11.3.js"></script>
		<script type="text/javascript">var $j = jQuery.noConflict();</script>
		<script src="${basePath}static/js/h5/ichart.1.2.1.min.js"></script>
		<script type="text/javascript">
			var basePath='${basePath}';
			$(function(){
				var width = $j("body").width();
				var data = [
		        	{name : '1',value : 6078,color:'#4572a7'},
		        	{name : '2',value : 5845,color:'#4572a7'},
		        	{name : '3',value : 5008,color:'#4572a7'},
		        	{name : '4',value : 2662,color:'#4572a7'},
		        	{name : '5',value : 2445,color:'#4572a7'},
		        	{name : '6',value : 2389,color:'#4572a7'},
		        	{name : '7',value : 2147,color:'#4572a7'},
		        	{name : '8',value : 2135,color:'#4572a7'},
		        	{name : '9',value : 820,color:'#4572a7'},
		        	{name : '10',value : 763,color:'#4572a7'},
		        	{name : '11',value : 421,color:'#4572a7'},
		        	{name : '12',value : 272,color:'#FF0000'}
	        	];
	        	var opts1={
        			turn_off_touchmove: true,
					animation : true,
					render : 'canvasDiv1',
					background_color : '#EEEEEE',
					data: data,
					title: '',
					width : width,
					height : 350,
					coordinate:{
						width:'90%',
						height:280,
						scale:[{
							 position:'bottom',	
							 start_scale:0,
							 end_scale:12,
							 scale_space:1
						}]
					},
					sub_option:{
						border:{
							enable : true
						},
						label:{color:'#333333'}
					},
					shadow:true,
					shadow_color:'#8d8d8d',
					shadow_blur:1,
					shadow_offsety:1,
					shadow_offsetx:1,
					legend:{enable:false}
				};
	        	var lun = {"lun0": "一","lun1": "二","lun2": "三","lun3": "四","lun4": "五","lun5": "六","lun6": "七","lun7": "八","lun8": "九"};
				$j.ajax({
					url: basePath+'api/1.0/open',
					type: 'post',
					dataType: 'json',
					success: function(res){
						if(res.status=='SUCCESS'){
							var ballNums = eval("("+res.data.ballNums+")");
							var details = res.data.details;
							var times = res.data.times * 1;
							var j = 0;
							for(var o in ballNums){
								var detail = ballNums[o];
								var min = detail.min * 1;
								var max = detail.max * 1;
								if(min == 0 && max == 0){
									if(o == 'lun0'){
										$j(".container").append("<div style='text-align:center;padding-top:20px'>未成功开奖</div>");
									}
									break;
								}
								var space = Math.ceil((max - min) / 8);
								var i = 1;
								var an = 0;
								for(var p in data){
									var v = detail["ball"+i] * 1;
									data[p].value = v;
									if(v == min){
										data[p].color = "#FF0000";
										an += v;
									}else{
										data[p].color = "#4572a7";
									}
									i++;
								}
								$j(".container").append("<div id='"+o+"'></div>");
								//opts1.title = "第"+lun["lun"+j]+"轮";
								var tempText = "";
								if(min == 0){
									var temp = "";
									if(j <= times - 2){
										temp = "，第"+lun["lun"+(j+1)]+"轮取消开奖";
									}
									tempText = "无人中奖" + temp;
// 									$j(".container").append("<div style='font-size:16px;background-color:"+opts1.background_color+";color:#de7624;padding:10px 5px;'>无人中奖"+temp+"</div>");
								}else{
// 									$j(".container").append("<div style='font-size:16px;background-color:"+opts1.background_color+";color:#de7624;padding:10px 5px 0px 5px;'>中奖号码："+details[j].awardNum+"</div>");
// 									$j(".container").append("<div style='font-size:16px;background-color:"+opts1.background_color+";color:#de7624;padding:0px 5px 10px 5px;'>中奖人数："+an+"</div>");
									tempText = "中奖号码：" + details[j].awardNum + "\n中奖人数："+an;
								}
								opts1.title = {
										text: "第"+lun["lun"+j]+"轮",
										color:'darkorange',
										fontsize: '18',
										fontweight: 'normal',
										padding:'0 0',
										height:20,
										line_height:20
								};
								opts1.footnote={
										text: tempText,
										color:'darkorange',
										fontsize: '15',
										fontweight: 'normal',
										padding:'10 0',
										height:60,
										textAlign: 'left'
								}
								opts1.background_color = j%2==0? "#EEE" : "#FFF";
								opts1.render = o;
								opts1.coordinate.scale=[{
									 position:'bottom',	
									 start_scale: 0,
									 end_scale: max,
									 scale_space: space
								}]
								opts1.data = data;
								new iChart.Bar2D(opts1).draw();
								j ++;
							}
						}
					}
				});
			});
		</script>

	</body>
</html>