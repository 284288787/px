//var kk;
var basePath=$('#basePath').val(); 
var parentParams=artDialog.data('params'); 
var utilsHandle = new UtilsHandle({
	basePath: basePath, 
	choose: [{
	  'multiselect': true,
    'title': '选择教练',
    'object': $('input[name=coachNames]'),
    'url': '/common/management/kindergartenCoach/chooseKindergartenCoach',
    'width': '70%',
    'height': '500px', 
    'choosedId': $(':hidden[name=coachNames]'), 
    'callback':function(items){
      var ids = "";
      var names = "";
      for(var o in items){
        var item = items[o];
        names += item.name + " ";
        ids += "," + item.coachId;
      }
      if(ids){
        $(':hidden[name=coachIds]').val(ids.substring(1)); 
        $(':input[name=coachNames]').val(names);
      }
    }
  }], 
	chooseCity: { 
		'object': $('input[name=areaName]'),
		'width': '70%', 
		'height': '600px',
		'areaId': $(':hidden[name=areaId]').val(),
		'callback':function(areaId, areaName){
			$(':hidden[name=areaId]').val(areaId);
			$(':input[name=areaName]').val(areaName); 
		} 
	},
});
$(function(){
	$('#editPhysicalClassForm').validate({ 
    rules: {
      title: {
        required: true, 
        rangelength: [2, 50]
      },
      schoolTime: {
        required: true, 
      },
      price: {
        required: true, 
        money: true
      },
      address: {
        required: true, 
        rangelength: [8, 100]
      }
    },
    messages: { 
      title: {
        required: '必填', 
        rangelength: '标题长度范围[2,50]'
      },
      schoolTime: {
        required: '必选', 
      },
      price: {
        required: '必填', 
      },
      address: {
        required: '必填', 
        rangelength: '地址长度范围[8,100]'
      }
    } 
  }); 
	
	$('#saveBtn').click(function(){ 
		var flag = $('#editPhysicalClassForm').valid();
		if(! flag) return;
		var data=$('#editPhysicalClassForm').serializeArray(); 
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value; 
			console.log(name + '' + field.value)
		}); 
    if(!params.deadlineTime){
      params.deadlineTime = params.schoolTime;
    }
    params.price = params.price * 100;
		console.log(JSON.stringify(params));
		$.ajax({
			contentType:'application/json', 
			url: basePath+'physicalClass/edit', 
			data: JSON.stringify(params), 
			type: 'post', 
			dataType: 'json', 
			success: function(res){ 
				if(res.status=='SUCCESS'){
					parentParams.query(); 
					art.dialog.close(); 
				}else{
					artDialog.alert(res.errorMessage) 
				} 
			} 
		}); 
	});
});