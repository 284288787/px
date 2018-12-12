var basePath = $("#basePath").val();
var teamHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '球队',
	winWidth: '556px',
	winHeight: '260px',
	primaryKey: 'teamId',
	urls:{
		list: basePath+'team/list',        //列表
		addBefore: basePath+'common/management/team/addTeam',   //添加之前
		editBefore: basePath+'team/editBefore',          //编辑之前
		enabled: basePath+'team/enabled',      //启用
		disabled: basePath+'team/disabled',    //禁用
	}
},{
	view: function(teamId){
		artDialog.open(basePath+'team/view/'+teamId,{
			title: "球队信息",
			width : '770px',
			height: '590px',
			drag:true,
			resize:true,
			lock:true
		});
	},
	useService: function(teamId){
		artDialog.open(basePath+'competition/useServiceByTeam/'+teamId,{
			title: "使用服务",
			width : '90%',
			height: '590px',
			drag:true,
			resize:true,
			lock:true
		});
	},
	textPopularize: function(teamId, teamName){
		artDialog.open(basePath+'member/textPopularize/'+teamId,{
			title: teamName + "的图文推广内容",
			width : '90%',
			height: '80%',
			drag:true,
			resize:true,
			lock:true
		});
	},
	videoPopularize: function(teamId, teamName){
		artDialog.open(basePath+'member/videoPopularize/'+teamId,{
			title: teamName + "的视频推广内容",
			width : '50%',
			height: '80%',
			drag:true,
			resize:true,
			lock:true
		});
	}
});
$(function(){
	var colNames = [ 'teamId', '所有者', '队徽', '球队名称', '所属企业', '城市', '是否可用', '创建时间', '操作' ];
	var colModel = [
        {name: 'teamId', index: 'teamId', width: 35, align: "center"}, 
		{name: 'userName', index: 'userName', width: 35, align: "center"}, 
		{name: 'logo', index: 'logo', width: 60, align: "center", formatter: function(cellvalue, options, rowObject){
			var val = "";
			if(cellvalue){
				val = "<img src='"+basePath+cellvalue+"' height='60px'";
			}
			return val;
		}}, 
		{name: 'teamName', index: 'teamName', width: 60, align: "center"}, 
		{name: 'companyName', index: 'companyName', width: 100, align: "center"}, 
		{name: 'areaName', index: 'areaName', width: 40, align: "center"}, 
		{name: 'enabled', index: 'enabled', width: 30, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
		{name: 'createTime', index: 'createTime', width: 80, align: "center"}, 
		{width: 150, align: "left", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<a class="linetaga" href="javascript: teamHandle.view(\'' + rowObject.teamId + '\');" >查看</a>';
			temp += '<a class="linetaga" href="javascript: teamHandle.useService(\'' + rowObject.teamId + '\');" >使用服务</a>';
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: teamHandle.disabled(\'' + rowObject.teamId + '\');" >禁用</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: teamHandle.enabled(\'' + rowObject.teamId + '\');" >启用</a>';
			}
			if(rowObject.textPopularize==1){
				temp += '<a class="linetaga" href="javascript: teamHandle.textPopularize(\'' + rowObject.teamId + '\',\'' + rowObject.teamName + '\');" >设置图文推广内容</a>';
			}
			if(rowObject.videoPopularize==1){
				temp += '<a class="linetaga" href="javascript: teamHandle.videoPopularize(\'' + rowObject.teamId + '\',\'' + rowObject.teamName + '\');" >设置视频推广内容</a>';
			}
			return temp;
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "球队列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	teamHandle.init(config);
});