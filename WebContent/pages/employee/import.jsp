<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>员工数据导入</title>
<base href="${baseUrl }"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="skins/css/style.css"/>
<link rel="stylesheet" type="text/css" href="skins/css/form.css"/>
<link rel="stylesheet" type="text/css" href="skins/css/jquery-ui-1.8.15.custom.css"/>
<link rel="stylesheet" type="text/css" href="plugin/tree/zTreeStyle.css"/>
<link rel="stylesheet" type="text/css" href="plugin/tree/zTreeIcons.css"/>
<link rel="stylesheet" type="text/css" href="plugin/grid/gt_grid.css" />
<link rel="stylesheet" type="text/css" href="plugin/grid/skin/default/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="plugin/grid/skin/green/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="plugin/grid/skin/mac/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="plugin/grid/skin/china/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="plugin/grid/skin/vista/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="plugin/grid/calendar/calendar-blue.css"  />


<script type="text/javascript" src="skins/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="skins/js/public.js"></script>
<script type="text/javascript" src="skins/js/jquery-ui-1.8.5.custom.min.js"></script>
<script type="text/javascript" src="plugin/tree/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="plugin/form/jquery.form.js"></script>
<script type="text/javascript" src="plugin/form/jquery.validate.js"></script>
<script type="text/javascript" src="plugin/form/lib/jquery.metadata.js"></script>

<script type="text/javascript" src="plugin/grid/calendar/calendar.js"></script>
<script type="text/javascript" src="plugin/grid/calendar/calendar-en.js"></script>
<script type="text/javascript" src="plugin/grid/calendar/calendar-setup.js"></script>
<script type="text/javascript" src="plugin/grid/gt_grid_all.js"></script>
<script type="text/javascript" src="plugin/grid/gt_msg_cn.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	//加载数据
    loadGrid();
	
	//默认exls导入
    checkUploadForm();
    impXLS();
	
});





//导入层
function impXLS(){
	$("#impXLS").dialog({
		autoOpen: true,
		modal: true,
		resizable:false,
		width:500,
		height:150,
		buttons: {
			"关闭": function() {
				$(this).dialog("close");
			}
		},
		open:function(){
			//清空记录
			mygrid.cleanContent();
			$("#error").html(null);
		}
	});
}





//exls校验
function checkUploadForm(){
	$('#uploadFile').ajaxForm({
		dataType :'json',
		success: function(file) {
			//清空记录
			mygrid.cleanContent();
			
			//渲染sheet
			var names=file.sheetNames;
			if(names!=null){
				var containt=$('#sheetnames');
				var htm='';
				for(var i=0;i<names.length;i++){
					var name=names[i];
					htm+='<li onclick="callbackSheetName(\''+name+'\');">';
					htm+='<span class="ui-button ui-button-text-icons">';
					htm+='<span class="ui-icon ui-icon-script"></span>';
					htm+='<span class="ui-button-text">';
					htm+=name;
					htm+='</span>';
					htm+='</span>';
					htm+='</li>';
				}
				containt.html(htm);
				
				//选择导入的sheet
				$("#selectSheet").dialog({
					autoOpen: true,
					modal: true,
					resizable:false,
					width:400,
					buttons: {
						"关闭": function() {
							$(this).dialog("close");
						}
					},
					close:function(){
						
						$("#impXLS").dialog("close");
						$("#error").html(null);
						$("#sheetname").val(null);
						$("#uploadFile").resetForm();
					}
				});
				return;
			}else
				$("#selectSheet").dialog("close");
			
			//检查数据有多少条出错记录
			var impData=file.data;
			var err=0;
			
			
			
			//检查
			for(var i=0;i<impData.length;i++){
				var obj=impData[i];
				if(obj.checkstate==1)
					err++;
			}
			
			//设置gird数据
			dataOk(impData,err);
		}
	}); 
}



//设置gird数据
function dataOk(impData,err){
	if(err>0)
		$('#error').html('有 '+err+' 条记录无法导入，请修正后重新导入！');
	mygrid.setContent(impData);
}







function callbackSheetName(name){
	$("#sheetname").val(name);
	$("#uploadFile").submit();
}


//保存导入
function saveImpGrid(){
	$("#save").hide();
	var error=0;
	var array=new Array();
	mygrid.forEachRow(function(row,record,i,grid){
		if(record.checkstate==1)
			error++;
		else
			array.push(record);
	});
	
	if(error>0){
		alert('有 '+error+' 条数据错误，请修正后再导入！');
		$("#save").show();
	}else if(array.length>=1){
		mygrid.setInsertedRecords(array);
		mygrid.save(true);
	}
}


</script>

<script type="text/javascript">	
var mygrid=null;
function loadGrid(){
	var grid_demo_id = "myGrid1";
	var dsOption= {
			fields :[
				{name : 'employeeid'},
				{name : 'dorder'},
				{name : 'name'},
				{name : 'sex'},
				{name : 'birthday'},
				{name : 'cardnumber'},
				{name : 'culture'},
				{name : 'nation'},
				{name : 'mobile'},
				{name : 'residenceplace'},
				{name : 'homephone'},
				{name : 'homeplace'},
				{name : 'bloodtype'},
				{name : 'domicilplace'},
				{name : 'nativeplace'},
				{name : 'politics'},
				{name : 'married'},
				{name : 'privateemail'},
				{name : 'photo'},
				{name : 'joinworkdate'},
				{name : 'joingroupdate'},
				{name : 'workstate'},
				{name : 'jobnumber'},
				{name : 'secrecy'},
				{name : 'dorder'},
				{name : 'classification'},
				{name : 'employtype'},
				{name : 'checknumber'},
				{name : 'joindate'},
				{name : 'startdate'},
				{name : 'enddate'},
				{name : 'officephone'},
				{name : 'email'},
				{name : 'shortphone'},
				{name : 'officeaddress'},
				{name : 'studymonth'},
				{name : 'officialdateplan'},
				{name : 'officialdate'},
				{name : 'officialmemo'},
				{name : 'resignationdate'},
				{name : 'resignationreason'},
				{name : 'interfacecode'},
				{name : 'contactphone'},
				{name : 'contactrelationship'},
				{name : 'contactname'},
				{name : 'memo'},
				{name : 'modiuser'},
				{name : 'moditimestamp'},
				{name : 'modimemo'},
				{name : 'secrecyname'},
				{name : 'sexname'},
				{name : 'culturename'},
				{name : 'nationname'},
				{name : 'bloodtypename'},
				{name : 'politicsname'},
				{name : 'marriedname'},
				{name : 'resignationreasonname'},
				{name : 'classificationname'},
				{name : 'employtypename'},
				{name : 'contactrelationshipname'},
				{name : 'domicilplacename'}
			]
		};
		var colsOption = [
			{id: '选择' ,isCheckColumn : true, editable:false,headAlign:'center',align:'center'},
			{id: 'checkstate', header: " ", width :30 ,headAlign:'center',align:'center',renderer:function(value ,record,colObj,grid,colNo,rowNo){
				if(value==1){
					return '<span class="ui-button ui-button-icon-only" title="'+record.msg+'"><span class="ui-icon ui-icon-close"></span>&nbsp;</span>';
				}
				return '';
			},resizable:false},
			{id: 'workstatename' , header: "状态" , width :60 ,headAlign:'center',align:'center'},
			{id: 'companyname' , header: "公司名称" , width :200 ,headAlign:'center',align:'left'},
			{id: 'deptname' , header: "部门名称" , width :150 ,headAlign:'center',align:'left'},
			{id: 'postname' , header: "岗位名称" , width :120 ,headAlign:'center',align:'left'},
			{id: 'jobname' , header: "职务名称" , width :120 ,headAlign:'center',align:'left'},
			{id: 'rankname' , header: "职级名称" , width :120 ,headAlign:'center',align:'left'},
			
			{id: 'dorder' , header: "排序号" , width :80 ,headAlign:'center',align:'center'},
			{id: 'jobnumber' , header: "工号" , width :100 ,headAlign:'center'},
			{id: 'name' , header: "姓名" , width :60 ,headAlign:'center',align:'left'},
			{id: 'sexname' , header: "性别" , width :60 ,headAlign:'center',align:'center'},
			{id: 'birthday' , header: "出生日期" , width :90 ,headAlign:'center',align:'center'},
			{id: 'birthdayname' , header: "年龄" , width :50 ,headAlign:'center',align:'center'},
			{id: 'cardnumber', header: "身份证号码", width :180 ,headAlign:'center'},
			{id: 'culturename' , header: "学历情况" , width :100 ,headAlign:'center'},
			{id: 'nationname' , header: "民族" , width :100 ,headAlign:'center'},
			{id: 'residenceplace' , header: "身份证地址" , width :200 ,headAlign:'center'},
			{id: 'mobile' , header: "手机" , width :120 ,headAlign:'center'},
			{id: 'addressmobile' , header: "通讯录手机" , width :120 ,headAlign:'center'},
			{id: 'homeplace' , header: "家庭住址" , width :200 ,headAlign:'center'},
			{id: 'homephone' , header: "家庭电话" , width :100 ,headAlign:'center'},
			{id: 'bloodtypename' , header: "血型" , width :100 ,headAlign:'center',align:'center'},
			{id: 'domicilplacename' , header: "户籍类型" , width :200 ,headAlign:'center',align:'left'},
			{id: 'nativeplace' , header: "籍贯" , width :100 ,headAlign:'center'},
			{id: 'politicsname' , header: "政治面貌" , width :100 ,headAlign:'center'},
			{id: 'marriedname' , header: "婚姻状况" , width :100 ,headAlign:'center',align:'center'},
			{id: 'privateemail' , header: "私人邮箱" , width :150 ,headAlign:'center'},
			{id: 'contactname' , header: "紧急联系人" , width :80 ,headAlign:'center'},
			{id: 'contactrelationshipname' , header: "与紧急联系关系" , width :100 ,headAlign:'center'},
			{id: 'contactphone' , header: "紧急联系人电话" , width :150 ,headAlign:'center'},
			
			{id: 'joinworkdate' , header: "参加工作时间" , width :90 ,headAlign:'center',align:'center'},
			{id: 'joingroupdate' , header: "加入集团时间" , width :90 ,headAlign:'center',align:'center'},
			{id: 'secrecyname' , header: "员工信息保密" , width :100 ,headAlign:'center',align:'center'},
			{id: 'classificationname' , header: "员工类别" , width :80 ,headAlign:'center'},
			{id: 'employtypename' , header: "用工性质" , width :80 ,headAlign:'center'},
			{id: 'jobname' , header: "职务" , width :150 ,headAlign:'center'},
			{id: 'quotaname' , header: "编制类别" , width :150 ,headAlign:'center'},
			{id: 'checknumber' , header: "考勤号" , width :80 ,headAlign:'center'},
			{id: 'joindate' , header: "加入公司时间" , width :90 ,headAlign:'center',align:'center'},
			{id: 'startdate' , header: "聘任开始时间" , width :90 ,headAlign:'center',align:'center'},
			
			{id: 'officephone' , header: "办公电话" , width :80 ,headAlign:'center'},
			{id: 'email' , header: "公司邮箱" , width :150 ,headAlign:'center'},
			{id: 'shortphone' , header: "手机内网号码" , width :120 ,headAlign:'center'},
			{id: 'officeaddress' , header: "办公地址" , width :200 ,headAlign:'center'},
			{id: 'studymonth' , header: "试用期（月）" , width :80 ,headAlign:'center',align:'center'},
			{id: 'officialdateplan' , header: "计划转正时间" , width :90 ,headAlign:'center',align:'center'},
			{id: 'officialdate' , header: "转正时间" , width :90 ,headAlign:'center',align:'center'},
			{id: 'officialmemo' , header: "转正备注" , width :200 ,headAlign:'center'},
			{id: 'resignationdate' , header: "离职时间" , width :90 ,headAlign:'center',align:'center'},
			{id: 'resignationreasonname' , header: "离职原因" , width :100 ,headAlign:'center'},
			{id: 'modimemo' , header: "备注" , width :200 ,headAlign:'center',headAlign:'left',align:'left'},
			{id: 'msg' , header: "问题描述" , width :700 ,headAlign:'center',headAlign:'left',align:'left'}
		];
		
	var gridOption={
		id : grid_demo_id,
		loadURL : '',
		saveURL :'employee/saveEmployeeGrid.do',
		width: "99.9%",//"100%", // 700,
		height: "100%",  //"100%", // 330,
		container : 'gridbox', 
		stripeRows: false,
		showIndexColumn:true,
		selectRowByCheck:true,
		replaceContainer : true,
		dataset : dsOption ,
		columns : colsOption,
		toolbarContent : 'state',
		skin:getGridSkin(),
		pageSize:1000,
		onComplete:function(){
		},
		saveResponseHandler:function(response,requestParameter){
			alert(response.text);
			$("#save").show();
			window.parent.convertView(null);
		}
	};
	mygrid=new Sigma.Grid( gridOption );
	Sigma.Util.onLoad( Sigma.Grid.render(mygrid) );
}




//选择部门回调
function callBackOrg(treeNode){
	$("#orgguid").val(treeNode.pId);
}
</script>

</head>
<body>


<div class="sort">
	<div class="sort-title">
		<h3>员工管理</h3>
		<div class="title-ctrl">
			<a class="btn-ctrl" href="javascript:chevronUpDown('.sort-cont',false);"><i class="icon icon-chevron-up"></i></a>
			<a class="btn-ctrl" href="javascript:chevronUpDown('.sort-cont',true);"><i class="icon icon-chevron-down"></i></a>
		</div>
	</div>
	
	<div class="sort-cont sort-table">
		<div class="table">
			<div class="table-bar">
				<div class="table-title">
					员工数据导入
				</div>
				<div class="table-ctrl">
					<a class="btn" href="javascript:impXLS();"><i class="icon icon-list-alt"></i><span>选择数据</span></a>
					<a class="btn" href="javascript:saveImpGrid();"><i class="icon icon-check"></i><span>确定导入</span></a>
					<a class="btn" href="javascript:window.parent.convertView('');"><i class="icon icon-share-alt"></i><span>返回列表</span></a>
				</div>
			</div>
			<div class="table-wrapper">
				<div id="myTable" style="height:550px;margin:5px auto;">
					<div id="gridbox" ></div>
					
					
					<!-- 错误提示 -->
					<div style="position: absolute;left:30px;bottom:20px;z-index:100;" > 
						信息:<span id="error"></span>
					</div>
                </div>
			</div>
		</div>
	</div>
</div>








<!-- 选择导入XLS -->
<div id="impXLS" style="display:none;" title="选择Microsoft Excel 工作表">
	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<form id="uploadFile" action="yewu/imp.do" method="post" enctype="multipart/form-data">
			 		<input id="sheetname" type="hidden" name="sheetname"/>
			       	<input id="file" type="file" name="file[]" style="width:100%;height:25px;line-height:25px;padding-left:5px;" size="65" onchange="$('#uploadFile').submit();" />
			   	</form>
			</td>
		</tr>
	</table>
</div>



<!-- 选择导入的sheet -->
<div id="selectSheet" style="display:none;overflow:hidden;" title="选择导入的工作表" class="chooseApp">
  	<ul id="sheetnames">
  	
  	</ul>
</div>




</body>
</html>