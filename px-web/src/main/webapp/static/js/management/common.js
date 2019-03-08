function ListHandle(options, funcs){
	var handle = {};
	var selectedRows={};
	var historyIds = ",";
	
	handle.init = function(config){
		var obj = jQuery(options.tableId).jqGrid({
			url : options.urls.list,
			datatype : config.dataType || "json",
			colNames : config.colNames,
			colModel : config.colModel,
			rowNum : 10,
			rowList : config.rowList,
			pager : options.pagerId,
			mtype : "post",
			rownumbers: config.rownumbers || false,
			multiselect: config.multiselect || false,
			viewrecords : true,
			autowidth: true,
			hidegrid: false,
			loadtext: '加载中...',
			height: config.height || '300px',
			caption : config.caption,
			gridComplete: function(){
//				var ids=jQuery(options.tableId).jqGrid('getDataIDs');
//				$.each(ids, function(i, rowIdx){
//					var curChk = $("#"+rowIdx+"").find(":checkbox");
//					var rowData = $(options.tableId).jqGrid("getRowData", rowIdx);
//					if(rowData.enabled==0){
//						curChk.attr("disabled", true);
//					}
//				});
				$("#cb_grid-table").on("click", function(){
					var checked = this.checked;
					var ids=jQuery(options.tableId).jqGrid('getDataIDs');
					$.each(ids, function(i, rowIdx){
						selectedRows[rowIdx] = checked;
						if(!checked){
							var rowData = $(options.tableId).jqGrid("getRowData", rowIdx);
							var temp = ","+rowData[options.primaryKey]+",";
							historyIds = historyIds.replace(temp, ",");
						}
					});
				});
				if(config.callback){
					config.callback(obj);
				}
			},
			onSelectRow: function(rowId, status, obj){
				selectedRows[rowId] = status;
				if(!status){
					var rowData = $(options.tableId).jqGrid("getRowData", rowId);
					var temp = ","+rowData[options.primaryKey]+",";
					historyIds = historyIds.replace(temp, ",");
				}
			},
			onPaging: function(pgButton){
				var history = handle.getIds2();
				if(history){
					var tempIds = history.split(",");
					for(var i in tempIds){
						var temp = "," + tempIds[i] + ",";
						if(historyIds.indexOf(temp) == -1){
							historyIds = "," + tempIds[i] + historyIds;
						}
					}
				}
				selectedRows={};
			},
			loadComplete: function(xhr){
				var ids=jQuery(options.tableId).jqGrid('getDataIDs');
				$.each(ids, function(i, rowIdx){
					var rowData = $(options.tableId).jqGrid("getRowData", rowIdx);
					var keyValue = rowData[options.primaryKey];
					if(historyIds.indexOf(","+keyValue+",") != -1){
						selectedRows[rowIdx] = true;
						var curChk = $("#"+rowIdx+"").find(":checkbox");
						curChk.attr("checked", true);
					}
				});
				if(config.callback){
					config.callback(obj);
				}
			}
		});
		jQuery(options.tableId).jqGrid('navGrid', options.pagerId, {edit: false, add: false, del: false, search: false});
	}
	
	handle.ajax = function(opts){
		$.ajax({
			url: opts.url,
			data: opts.data,
			type: opts.type || 'post',
			dataType: 'json',
			success: opts.success,
			error: function(a,b,c){
				artDialog.alert(b)
			}
		});
	}
	handle.setId = function(id){
		selectedRows[id] = true;
	}
	handle.getIds2 = function(){
		var ids="";
		$.each(selectedRows, function(i, obj){
			if(obj){
				var rowData = $(options.tableId).jqGrid("getRowData", i);
				ids += "," + rowData[options.primaryKey];
			}
		});
		if(ids.length > 0){
			return ids.substring(1);
		}else{
			return null;
		}
	}
	handle.getIds = function(){
		var history = handle.getIds2();
		if(history){
			var tempIds = history.split(",");
			for(var i in tempIds){
				var temp = "," + tempIds[i] + ",";
				if(historyIds.indexOf(temp) == -1){
					historyIds = "," + tempIds[i] + historyIds;
				}
			}
		}
		if(historyIds.length==1){
			return null;
		}
		var temp = historyIds.substring(1, historyIds.length-1);
		return temp;
	}
	handle.query = function(){
		 var data=$(options.formId).serializeArray();
		 var params = {};
		 $.each(data, function(i, field){
			 var name = field.name;
			 params[name] = field.value;
			 console.log(name + "  " + field.value)
		 });
		$(options.tableId).jqGrid('setGridParam',{
			datatype:'json',
			postData: params,
			page:1
		}).trigger("reloadGrid");
	}
	handle.reset = function(){
		$(options.formId+" input[type='text']").each(function(i){
		      $(this).val("");
		});
		$(options.formId+" input[type='checkbox']").each(function(i){
		      $(this).attr("checked",false);
		});
		$(options.formId+" select").each(function(i){
			var id = $($(this).find("option:first")).val();
			$(this).val(id);
		});
		$(options.formId+" input[type='hidden']").each(function(i){
			$(this).val("");
		});
		handle.query();
	}
	handle.addNew = function(params){
		if(params){
			$.extend(handle, params);
		}
		artDialog.data("params", handle);
		artDialog.open(options.urls.addBefore,{
			title: "新增 "+options.entityName,
			width : options.winWidth,
			height: options.winHeight,
			drag:true,
			resize:true,
			lock:true/* ,
			close:function(){
				document.location.reload();
			} */
		});
	}
	handle.edit = function(recId){
		artDialog.data("params", handle);
		artDialog.open(options.urls.editBefore + "/" + recId,{
			title: "编辑 "+options.entityName,
			width : options.winWidth,
			height: options.winHeight,
			drag:true,
			resize:true,
			lock:true/* ,
			close:function(){
				document.location.reload();
			} */
		});
	}
	handle.enabled = function(recId){
		var ids = handle.getIds();
		if(recId){
			ids = recId;
		}
		if(ids){
			handle.ajax({
				url: options.urls.enabled,
				data: {ids: ids},
				success: function(res){
					if(res.status){
						artDialog.alert("启用成功")
						selectedRows={};
						historyIds=",";
						handle.query();
					}else{
						artDialog.alert(res.errorMessage)
					}
				}
			});
		}else{
			artDialog.alert("请选择要启用的记录");
		}
	}
	handle.disabled = function(recId){
		var ids = handle.getIds();
		if(recId){
			ids = recId;
		}
		if(ids){
			artDialog.confirm("确认禁用？", function(){
				handle.ajax({
					url: options.urls.disabled,
					data: {ids: ids},
					success: function(res){
						if(res.status){
							artDialog.alert("禁用成功")
							selectedRows={};
							historyIds=",";
							handle.query();
						}else{
							alert(res.errorMessage)
						}
					}
				});
			})
		}else{
			artDialog.alert("请选择要禁用的记录");
		}
	}
	handle.remove = function(){
		var ids = handle.getIds();
		if(ids){
			handle.ajax({
				url: options.urls.deleted,
				data: {ids: ids},
				success: function(res){
					if(res.status){
						artDialog.alert("删除成功")
						selectedRows={};
						historyIds=",";
						handle.query();
					}else{
						alert(res.errorMessage)
					}
				}
			});
		}else{
			artDialog.alert("请选择要删除的记录");
		}
	}
	$.extend(handle, funcs);
	return handle;
}