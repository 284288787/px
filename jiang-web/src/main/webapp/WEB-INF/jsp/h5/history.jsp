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
            	
            </div>
        </div>
		<script src="${basePath}static/js/jquery.min-1.11.3.js"></script>
		<script src="${basePath}static/js/h5/common.js"></script>
		<script type="text/javascript">var $j = jQuery.noConflict();</script>
		<script src="${basePath}static/js/h5/ichart.1.2.1.src.js"></script>
		<script type="text/javascript">
			var basePath='${basePath}';
			$(function(){
				var width = $j("body").width();
				var lun = {"lun0": "一","lun1": "二","lun2": "三","lun3": "四","lun4": "五","lun5": "六","lun6": "七","lun7": "八","lun8": "九"};
				var opts = {
					align:'center',
        			turn_off_touchmove: true,
					animation : true,
					render : 'canvasDiv',
					data: [
				         	{
				         		name : '中奖号码',
				         		value: [1,3,5,7,9,2,4,6,8,10,12,7],
				         		color:'#0d8ecf',
				         		line_width: 2
				         	}
				         ],
					title : {
						text: "第一轮中奖号码走势",
						color:'darkorange',
						fontsize: '18',
						fontweight: 'normal',
						padding:'-5px 0 5px 0',
						height:30,
						line_height:30
					},
					subtitle :{
						text: '(最近12次)',
						color:'darkorange',
						fontsize: '12',
						fontweight: 'normal',
//  						textAlign: 'right',
//  						padding: '0 40 0 0'
					},
					width : width,
					height : 350,
					sub_option:{
						smooth : true,//平滑曲线
						point_size:15
					},
					tip:{
						enable:true,
						shadow:true
					},
					legend : {
						enable : false
					},
					crosshair:{
						enable:true,
						line_color:'#62bce9'
					},
					coordinate:{
						width:"90%",
						valid_width:"80%",
						height:260,
						axis:{
							color:'#9f9f9f',
							width:[0,0,1,1]
						},
						grids:{
							vertical:{
								way:'share_alike',
						 		value: 14
							}
						},
						scale:[{
							scale_enable:false,
							 position:'left',	
							 start_scale:0,
							 end_scale:12,
							 scale_space: 1,
							 scale_size: 2,
							 scale_color:'#9f9f9f'
						},{
							scale_enable:false,
							 position:'bottom',	
							 labels: ["2018-04-12","","","至","","","2018-04-23"]
						}]
					}
				};
				$j.ajax({
					url: basePath+'api/1.0/history',
					type: 'post',
					dataType: 'json',
					success: function(res){
						var luns = res.data;
						for(var lu in luns){
							var llist = luns[lu];
							var flow = new Array();
							var dates = new Array();
							for(var item in llist){
								flow.push(llist[item].min * 1);
								dates.push(llist[item].openTime);
							}
							if(flow.length>0){
								$j(".tab-content").append('<div id="'+lu+'"></div>')
								opts.render = lu;
								opts.title.text = "第"+lun[lu]+"轮中奖号码走势";
								opts.data[0].value=flow;
								opts.coordinate.scale[1].labels=dates;
								new iChart.LineBasic2D(opts).draw();
							}
						}
					}
				});
			});
		</script>
	</body>
</html>