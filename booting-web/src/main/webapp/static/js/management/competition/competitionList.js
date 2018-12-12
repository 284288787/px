var basePath = $("#basePath").val();
var competitionHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '赛事信息',
	winWidth: '90%',
	winHeight: '80%',
	primaryKey: 'competitionId',
	urls:{
		list: basePath+'competition/list',        //列表
		editBefore: basePath+'member/editBefore',          //编辑之前
		deleted: basePath+'userAccount/deleted',      //删除
	}
},{
	viewCompetition: function(competitionId){
		artDialog.open(basePath+'competition/viewCompetition/'+competitionId,{
			title: "赛事详情",
			width : '90%',
			height: '80%',
			drag:true,
			resize:true,
			lock:true
		});
	},
	useService: function(competitionId){
		artDialog.open(basePath+'competition/useService/'+competitionId,{
			title: "使用服务",
			width : '90%',
			height: '80%',
			drag:true,
			resize:true,
			lock:true
		});
	}
});
$(function(){
	var colNames = [ 'competitionId', '比赛性质', '比赛类型', '比赛赛制', '比赛时间', '比赛球场', '发起方球队', '发起方是否有保险', '发起方是否用教练', '应战方球队', '应战方是否用教练', '应战方是否有保险', '状态','创建时间', '操作' ];
	var colModel = [
		{name: 'competitionId', index: 'competitionId', width: 50, align: "center"}, 
		{name: 'initiatorTeamId', index: 'initiatorTeamId', width: 70, align: "center", sortable: false, formatter: function(cellvalue, options, rowObject){
			if(rowObject.initiatorTeamId){
				return '赛事';
			}else{
				return '球局';
			}
		}}, 
		{name: 'competitionType', index: 'competitionType', width: 70, align: "center", formatter: 'select', editoptions:{value:"1:邀请赛;2:友谊赛;3:挑战赛"}}, 
		{name: 'competitionFormat', index: 'competitionFormat', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:11人制;2:8人制;3:5人制"}}, 
		{name: 'competitionTime', index: 'competitionTime', width: 80, align: "center"},  
		{name: 'courtName', index: 'courtName', width: 60, align: "center"}, 
		{name: 'initiatorTeamName', index: 'initiatorTeamName', width: 60, align: "center"}, 
		{name: 'initiatorBuyInsurance', index: 'initiatorBuyInsurance', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:有保险;2:无保险"}}, 
		{name: 'initiatorTeach', index: 'initiatorTeach', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:有教练;2:无教练"}}, 
		{name: 'challengerTeamName', index: 'challengerTeamName', width: 60, align: "center"}, 
		{name: 'challengerBuyInsurance', index: 'challengerBuyInsurance', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:有保险;2:无保险"}}, 
		{name: 'challengerTeach', index: 'challengerTeach', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:有教练;2:无教练"}}, 
		{name: 'status', index: 'status', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:已发起;2:已建立;3:发起方取消;4:应战方取消;5:系统取消;6:已结束;7:已删除"}}, 
		{name: 'createTime', index: 'createTime', width: 80, align: "center"}, 
		{width: 150, align: "left", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '<a class="linetaga" href="javascript: competitionHandle.viewCompetition(\'' + rowObject.competitionId + '\');" >赛事详情</a>';
			temp += '<a class="linetaga" href="javascript: competitionHandle.useService(\'' + rowObject.competitionId + '\');" >使用服务</a>';
			return "<span class='listBtnsSpan'>"+temp+"</span>";
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "赛事列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	competitionHandle.init(config);
});