var basePath = $("#basePath").val();
var roleHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '角色',
	winWidth: '456px',
	winHeight: '145px',
	primaryKey: 'roleId',
	urls:{
		list: basePath+'role/roleList',        //列表
		addBefore: basePath+'common/management/role/addRole',   //添加之前
		editBefore: basePath+'role/editBefore',          //编辑之前
		enabled: basePath+'role/enabled',      //启用
		disabled: basePath+'role/disabled',    //禁用
	}
},{
	setResource: function(roleId, roleRemark){
		var params={roleId: roleId, name: roleRemark};
		artDialog.data("params", params);
		artDialog.open(basePath+'common/management/role/setResource',{
			title: "分配资源",
			width : '770px',
			height: '590px',
			drag:true,
			resize:true,
			lock:true
		});
	},
	viewResource: function(roleId, roleRemark){
		var params={roleId: roleId, name: roleRemark};
		artDialog.data("params", params);
		artDialog.open(basePath+'common/management/role/viewResource',{
			title: "查看资源",
			width : '770px',
			height: '590px',
			drag:true,
			resize:true,
			lock:true
		});
	}
});
$(function(){
	var colNames = [ 'roleId', '角色', '角色编码', '是否可用','创建时间', '操作' ];
	var colModel = [
		{name: 'roleId', index: 'roleId', width: 55, align: "center"}, 
		{name: 'roleRemark', index: 'roleRemark', width: 90, align: "left"}, 
		{name: 'roleName', index: 'roleName', width: 90, align: "center"}, 
		{name: 'enabled', index: 'enabled', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
		{name: 'createTime', index: 'createTime', width: 80, align: "center"}, 
		{width: 150, align: "center", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<a class="linetaga" href="javascript: roleHandle.edit(\'' + rowObject.roleId + '\');" >编辑</a>';
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: roleHandle.disabled(\'' + rowObject.roleId + '\');" >禁用</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: roleHandle.enabled(\'' + rowObject.roleId + '\');" >启用</a>';
			}
			temp += '<a class="linetaga" href="javascript: roleHandle.setResource(\'' + rowObject.roleId + '\', \'' + rowObject.roleRemark + '\');" >分配资源</a>';
			temp += '<a class="linetaga" href="javascript: roleHandle.viewResource(\'' + rowObject.roleId + '\', \'' + rowObject.roleRemark + '\');" >查看资源</a>';
			temp += '<a class="linetaga" href="javascript: roleHandle.setUser(\'' + rowObject.roleId + '\', \'' + rowObject.roleRemark + '\');" >指定用户</a>';
			temp += '<a class="linetaga" href="javascript: roleHandle.viewUser(\'' + rowObject.roleId + '\', \'' + rowObject.roleRemark + '\');" >查看用户</a>';
			return temp;
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "角色列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	roleHandle.init(config);
});