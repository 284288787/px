<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@ taglib uri="http://www.99love.net/jsp/tag/star-tags" prefix="st"%><!DOCTYPE html>
<!DOCTYPE html>
<html class="bg-write">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
		<link rel="stylesheet" href="${basePath}static/css/mui.min.css">
        <link rel="stylesheet" href="${basePath}static/css/app.css">
        <script src="${basePath}static/js/flexible.js"></script>
	</head>
	<body class="bg-write">
		<nav class="mui-bar mui-bar-tab lf-bar">
            <a class="mui-tab-item link" href="${basePath}lottery">
                <span class="icon hb"></span>
                <span class="mui-tab-label">红包广场</span>
            </a>
            <a class="mui-tab-item mui-active" href="#">
                <span class="icon kf active"></span>
                <span class="mui-tab-label">开奖大厅</span>
            </a>
            <a class="mui-tab-item link" href="${basePath}o-user">
                <span class="icon persion"></span>
                <span class="mui-tab-label">个人中心</span>
            </a>
        </nav>
        <div class="mui-content hbgc-content">
            <div class="tab">
                <div class="mui-table">
                    <div class="mui-table-cell mui-col-xs-6 link" href="${basePath}o-openAward">
                        <span >开奖结果</span>
                    </div>
                    <div class="mui-table-cell mui-col-xs-6">
                        <span class="active">开奖走势</span>
                    </div>
                </div>
            </div>
            <div class="tab-content">
            	<div id='canvasDiv1'></div>
            </div>
        </div>
		<script src="${basePath}static/js/jquery.min-1.11.3.js"></script>
		<script src="${basePath}static/js/h5/common.js"></script>
		<script type="text/javascript">var $j = jQuery.noConflict();</script>
		<script src="${basePath}static/js/h5/ichart.1.2.1.src.js"></script>
		<script type="text/javascript">
			$(function(){
				var width = $j("body").width();
				var data = [
					{
						index: 0,
						name : '全部',
						value:[],
						color:'#050505'
					},
					{
						index: 1,
						name : '第一轮',
						value:[1,2,3,4,5,6,4,2,3,8],
						color:'#47b2c8'
					},
					{
						index: 2,
						name : '第二轮',
						value:[2,3,4,7,9,10,12,12,11,10],
						color:'#76a871'
					},
					{
						index: 3,
						name : '第三轮',
						value:[,2,3,4,5,6,,7],
						color:'#db6086'
					}
				];
				var dataBak = JSON.parse(JSON.stringify(data));
				var chart = new iChart.BarMulti2D({
					align: "right",
					turn_off_touchmove: true,
					padding: '0px 30px 0px 0px',
					animation : true,
					id:'historyChart',
					render : 'canvasDiv1',
					data: data,
					labels:["02.24\n09点","02.24\n14点","02.24\n19点",
					        "02.23\n09点","02.23\n14点","02.23\n19点",
					        "02.22\n09点","02.22\n14点","02.22\n19点",
					        "02.21\n09点","02.21\n14点","02.21\n19点"],
					width : width,
					height : 12 * 3 * 25,
					background_color : '#ffffff',
					legend:{
						enable:true,
						row:1,//设置在一行上显示，与column配合使用
						column : 'max',
						valign: 'top',
						align: 'right',
						background_color : null,
						border : {
							enable : false
						},
						listeners:{
							click: function(l,e,m){
								clickFunc(m.index);
							}
						}
					},
					coordinate:{
						scale:[{
							 position:'bottom',	
							 start_scale:0,
							 end_scale: 12,
							 scale_space: 1
						}],
						background_color : null,
						width: '85%',
						height: '90%'
					},
					sub_option:{
						listeners:{
							click:function(s,e,m){
								clickFunc(s.get("index"));
							}
						}
					}
				});
				chart.draw();
				
				function clickFunc(index){
					var temp = new Array();
					if(index != 0){
						for(var i in dataBak){
							temp.push(JSON.parse(JSON.stringify(dataBak[i])));
							if(index != i){
								temp[i].value=[];
							}
						}
					}else{
						temp = dataBak;
					}
					review(temp);
				}
				function review(data){
					var chart = $.get('historyChart');
					if(data)
						chart.push("data", data);
					chart.resize(chart.width, chart.height);
				}
			});
		</script>
	</body>
</html>