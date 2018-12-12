var basePath = $("#basePath").val();
var userHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '会员信息',
	winWidth: '456px',
	winHeight: '545px',
	primaryKey: 'userId',
	urls:{
		list: basePath+'member/userList',        //列表
		addBefore: basePath+'common/management/member/addUser',   //添加之前
		editBefore: basePath+'member/editBefore',          //编辑之前
		enabled: basePath+'userAccount/enabled',      //启用
		disabled: basePath+'userAccount/disabled',    //禁用
		deleted: basePath+'userAccount/deleted',      //删除
	}
},{
	changePassword: function(userId){
		var params={userId: userId};
		artDialog.data("params", params);
		artDialog.open(basePath+'common/management/user/changePassword',{
			title: "修改密码",
			width : '456px',
			height: '150px',
			drag:true,
			resize:true,
			lock:true/* ,
			close:function(){
				document.location.reload();
			} */
		});
	},
	becomeEnterprise: function(userId){
		artDialog.open(basePath+'member/becomeEnterprise/'+userId,{
			title: "设为球队管理员",
			width : '356px',
			height: '130px',
			drag:true,
			resize:true,
			lock:true
		});
	},
	buyIncrementPkg: function(userId){
		artDialog.open(basePath+'member/buyIncrementPkg/'+userId,{
			title: "购买增值套餐",
			width : '356px',
			height: '130px',
			drag:true,
			resize:true,
			lock:true
		});
	},
	viewPackages: function(userId, name){
		artDialog.open(basePath+'member/viewPackages/'+userId,{
			title: name + "的套餐",
			width : '90%',
			height: '80%',
			drag:true,
			resize:true,
			lock:true
		});
	}
});
$(function(){
	var colNames = [ 'userId', '帐号', '姓名', '身份', '球队', '地址', '是否可用','创建时间', '操作' ];
	var colModel = [
		{name: 'userId', index: 'userId', width: 20, align: "center"}, 
		{name: 'account', index: 'account', width: 70, align: "center"}, 
		{name: 'name', index: 'name', width: 60, align: "center"}, 
		{name: 'identity', index: 'identity', width: 70, align: "center", formatter: 'select', editoptions:{value:"1:普通会员;2:球队管理员;3:球员"}}, 
		{name: 'teamName', index: 'teamName', width: 60, align: "center"}, 
		{name: 'address', index: 'address', width: 90, align: "left", hidden:true}, 
		//{name: 'nonExpired', index: 'nonExpired', width: 100, align: "center", formatter: 'select', editoptions:{value:"1:未过期;0:已过期"}}, 
		//{name: 'nonLocked', index: 'nonLocked', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:未锁定;0:已锁定"}}, 
		{name: 'enabled', index: 'enabled', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
		{name: 'createTime', index: 'createTime', width: 80, align: "center"}, 
		{width: 150, align: "left", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '<a class="linetaga" href="javascript: userHandle.changePassword(\'' + rowObject.userId + '\');" >修改密码</a>';
			temp += '<a class="linetaga" href="javascript: userHandle.edit(\'' + rowObject.userId + '\');" >编辑</a>';
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: userHandle.disabled(\'' + rowObject.userId + '\');" >禁用</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: userHandle.enabled(\'' + rowObject.userId + '\');" >启用</a>';
			}
			temp += '<a class="linetaga" href="javascript: userHandle.viewPackages(\'' + rowObject.userId + '\',\'' + rowObject.name + '\');" >查看套餐</a>';
			if(rowObject.identity==1){
				temp += '<a class="linetaga" href="javascript: userHandle.becomeEnterprise(\'' + rowObject.userId + '\');" >设为球队管理员</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: userHandle.buyIncrementPkg(\'' + rowObject.userId + '\');" >购买增值套餐</a>';
			}
			return "<span class='listBtnsSpan'>"+temp+"</span>";
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "会员列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	userHandle.init(config);
});