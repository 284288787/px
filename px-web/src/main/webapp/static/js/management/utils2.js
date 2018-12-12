function UtilsHandle(options, funcs){
	var handle = {};
	
	handle.chooseCity = function(opts){
		artDialog.data("opts", opts);
		artDialog.open(options.basePath + "common/chooseCity",{
			title: "选择地区",
			width : opts.width,
			height: opts.height,
			drag:true,
			resize:false,
			lock:true/* ,
			close:function(){
				document.location.reload();
			} */
		});
	}
	/**
	 * opts.urlParams 是数组，表示可以有多个参数，每个元素包含的属性介绍：
	 * 	   type: value的值的类型 input表示文本框，需要在请求前获取input框的值
	 *                         text 表示文本，可以直接使用，基本数据类型都是text 例如 数字
	 *     key: 参数键；
	 *     value: 参数值，根据type确定值的内容
	 * **/
	handle.choose = function(opts){
		artDialog.data("opts", opts);
		var url = opts.url;
		if(opts.urlParams){
			var args = "";
			for(var o in opts.urlParams){
				var arg = opts.urlParams[o];
				if(arg.type=='input'){
					var val=arg.value.val();
					if(val){
						args += "&" + arg.key + "=" + val;
					}else{
						artDialog.alert("请先选择上面的数据");
						return;
					}
				}else{
					args += "&" + arg.key + "=" + arg.value;
				}
			}
			if(args.length>0){
				url += "?" + args.substring(1);
			}
		}
		console.log("url: " + url);
		artDialog.open(options.basePath + url,{
			title: options.title,
			width : opts.width,
			height: opts.height,
			drag:true,
			resize:false,
			lock:true/* ,
			close:function(){
				document.location.reload();
			} */
		});
	}
	
	handle.addUploadFile = function(uf){
		if(options.uploadFile){
			var l = options.uploadFile.items.length;
			options.uploadFile.items.push(uf);
			uf.uploadBtn.attr("data-tag", l);
			uf.uploadBtn.unbind("click").click(function(){
				$("#"+options.uploadFile.uploadFileId).attr("data-tag", $(this).attr("data-tag"));
				$("#"+options.uploadFile.uploadFileId).click();
			});
		}
	}
	
	$.extend(handle, funcs);
	if(options.chooseCity){
		options.chooseCity.object.click(function(){
			handle.chooseCity(options.chooseCity);
		});
	}
	if(options.choose){
		var optsss={};
		for(var temp in options.choose){
			var o = options.choose[temp];
			optsss[o.object.attr("placeholder")] = o;
			o.object.click(function(){
				var oo = $(this).attr("placeholder");
				handle.choose(optsss[oo]);
			});
		}
	}
	if(options.kindEditor){
		for(var o in options.kindEditor){
			var temp=options.kindEditor[o];
			handle["kindEditorOptions"]={
				cssPath : options.basePath+'static/js/tools/kindeditor/plugins/code/prettify.css',
				uploadJson : options.basePath+'static/js/tools/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : options.basePath+'static/js/tools/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				width: temp.width,
				height: temp.height,
				resizeType:1,
				autoHeightMode : false,
				items : [
					'justifyleft', 'justifycenter', 'justifyright',
					'justifyfull', 'insertorderedlist', 'insertunorderedlist',
					'|', 'forecolor', 'bold', 'italic', 'underline', 'lineheight', 'removeformat', '|',
					'hr'
				],
				afterCreate : function() {
				},
				afterChange: function(){
				}
			};
			var kk=KindEditor.create(temp.object, handle.kindEditorOptions);
			temp.afterCreate(kk);
		}
	}
	if(options.uploadFile){
		handle["uploadFunc"] = function(uploadFileId, data, beforeSend, success, complete){
			var formData = new FormData();
			var files=$('#'+uploadFileId)[0].files;
			if(files.length <= 0) return;
			for(var i in files){
				formData.append(files[i].name, files[i]);
			}
			if(data){
				for(var j in data){
					formData.append(j, data[j]);
				}
			}
			$.ajax({
			    url: options.basePath+'common/uploadFile',
			    type: 'POST',
			    cache: false,
			    data: formData,
			    processData: false,
			    contentType: false,
			    beforeSend: function(xhr){
			    	if(beforeSend) beforeSend(xhr);
			    }
			}).done(function(res) {
				if(success) success(res);
				if(complete) complete(res);
//				$("#uploadDiv").html('<input type="file" name="'+options.uploadFile.uploadFileId+'" id="'+options.uploadFile.uploadFileId+'">');
//				$("#uploadDiv #"+options.uploadFile.uploadFileId).on("change", handle.uploadFunc);
			}).fail(function(res) {
				var msg = "服务器出错，错误内容：" + XMLHttpRequest.responseText;
	     		alert(msg)
			});
		};
		$("#"+options.uploadFile.uploadFileId).on("change", function(a){
			var idx = $(this).attr("data-tag")
			var uf2 = options.uploadFile.items[idx];
			handle.uploadFunc(options.uploadFile.uploadFileId, uf2.data, uf2.beforeSend, uf2.success, uf2.complete);
		});
		for(var o in options.uploadFile.items){
			var uf = options.uploadFile.items[o];
			uf.uploadBtn.attr("data-tag", o);
			uf.uploadBtn.click(function(){
				$("#"+options.uploadFile.uploadFileId).attr("data-tag", $(this).attr("data-tag"));
				$("#"+options.uploadFile.uploadFileId).click();
			});
		}
	}
	return handle;
}
//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.format = function (fmt) {
 var o = {
     "M+": this.getMonth() + 1, //月份 
     "d+": this.getDate(), //日 
     "h+": this.getHours(), //小时 
     "m+": this.getMinutes(), //分 
     "s+": this.getSeconds(), //秒 
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
     "S": this.getMilliseconds() //毫秒 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}