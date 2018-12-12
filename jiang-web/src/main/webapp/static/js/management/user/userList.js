var basePath = $("#basePath").val();
var userHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '用户信息',
	winWidth: '456px',
	winHeight: '245px',
	primaryKey: 'userId',
	urls:{
		list: basePath+'userAccount/userList',        //列表
		addBefore: basePath+'common/management/user/addUser',   //添加之前
		editBefore: basePath+'userAccount/editBefore',          //编辑之前
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
	setRole: function(userId, name){
		var params={userId: userId, name: name};
		artDialog.data("params", params);
		artDialog.open(basePath+'common/management/user/setRole',{
			title: "分配角色",
			width : '456px',
			height: '590px',
			drag:true,
			resize:true,
			lock:true
		});
	},
	viewRole: function(userId, name){
		var params={userId: userId, name: name};
		artDialog.data("params", params);
		artDialog.open(basePath+'common/management/user/viewRole',{
			title: "查看角色",
			width : '456px',
			height: '590px',
			drag:true,
			resize:true,
			lock:true
		});
	}
});
$(function(){
	var colNames = [ 'userId', '帐号', '姓名', '地址', '是否可用','创建时间', '操作' ];
	var colModel = [
		{name: 'userId', index: 'userId', width: 55, align: "center"}, 
		{name: 'account', index: 'account', width: 90, align: "center"}, 
		{name: 'name', index: 'name', width: 90, align: "center"}, 
		{name: 'address', index: 'address', width: 90, align: "left"}, 
		//{name: 'nonExpired', index: 'nonExpired', width: 100, align: "center", formatter: 'select', editoptions:{value:"1:未过期;0:已过期"}}, 
		//{name: 'nonLocked', index: 'nonLocked', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:未锁定;0:已锁定"}}, 
		{name: 'enabled', index: 'enabled', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
		{name: 'createTime', index: 'createTime', width: 80, align: "center"}, 
		{width: 150, align: "center", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '<a class="linetaga" href="javascript: userHandle.changePassword(\'' + rowObject.userId + '\');" >修改密码</a>';
			temp += '<a class="linetaga" href="javascript: userHandle.edit(\'' + rowObject.userId + '\');" >编辑</a>';
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: userHandle.disabled(\'' + rowObject.userId + '\');" >禁用</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: userHandle.enabled(\'' + rowObject.userId + '\');" >启用</a>';
			}
			temp += '<a class="linetaga" href="javascript: userHandle.setRole(\'' + rowObject.userId + '\',\''+rowObject.name+'\');" >分配角色</a>';
			temp += '<a class="linetaga" href="javascript: userHandle.viewRole(\'' + rowObject.userId + '\',\''+rowObject.name+'\');" >查看角色</a>';
			return temp;
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "用户列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	userHandle.init(config);
});