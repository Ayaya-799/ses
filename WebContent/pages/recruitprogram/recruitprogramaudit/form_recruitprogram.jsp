<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>招聘计划</title>
<base href="${baseUrl }" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="skins/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="skins/css/form.css" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="skins/css/jquery-ui-1.8.15.custom.css" />
<link rel="stylesheet" type="text/css" href="plugin/tree/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="plugin/tree/zTreeIcons.css" />
<link rel="stylesheet" type="text/css" href="plugin/grid/gt_grid.css" />
<link rel="stylesheet" type="text/css" href="plugin/grid/skin/default/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="plugin/grid/skin/green/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="plugin/grid/skin/mac/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="plugin/grid/skin/china/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="plugin/grid/skin/vista/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="plugin/grid/calendar/calendar-blue.css" />


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

<script type="text/javascript" src="pages/tree.js"></script>

<script type="text/javascript">
var tid='${param.id}';

$(document).ready(function() {
	//列表加载
    loadGrid();
    
	
  //日期
    $(".datepicker").datepicker({
        dateFormat: 'yy-mm-dd',
       	beforeShow: function(input, inst) {
   			if($(input).attr("readonly"))
   				inst.inline=true;
   		}
    });
	
  	//关闭等待层
    if(window.parent.hidenLoading)
    	window.parent.hidenLoading();
});







//搜索
var searchForm=null;
function searchGrid(){
	$("#search").dialog({
		autoOpen: true,
		modal: true,
		resizable:false,
		width:470,
		buttons: {
			"确定":function(){
				if(searchForm.form()){
					mygrid.reload();
					$(this).dialog("close");
				}
			},
			"重置":function(){
				$("#searchForm").clearForm();
			},
			"关闭": function() {
				$(this).dialog("close");
			}
		},
		open:function(){
			//校验
			if(searchForm==null)
				searchForm=$("#searchForm").validate();
		}
	});
}








//参数设置
var pam=null;
function initPagePam(){
	pam={};
	pam.expAll=0;
	pam.companyid=$("#companyid").val();
	pam.deptid=$("#deptid").val();
	pam.postname=$("#postname").val();
	pam.rankid=$("#rankid").val();
	pam.recruitprogramauditguid=tid;
}









//导出
function expGrid(){
	$("#dialog_exp").dialog({
		autoOpen: true,
		modal: true,
		model:true,
		resizable:false,
		width:400,
		height:130,
		buttons: {
			"确定": function() {
				mygrid.exportGrid('xls');
				$(this).dialog("close");
			},
			"取消": function() {
				$(this).dialog("close");
			}
		},
		close: function() {
		}
	});
}



//部门选择回调
function callbackDept(){
	$("#postid").val(null);
	$("#postname").val(null);
}






//公司回调
function callBackCompany(){
	$("#deptid").val(null);
	$("#deptname").val(null);
	
	//部门选择回调
	callbackDept();
}






//删除
function remove(){
	var array=new Array();
	var cords=mygrid.getSelectedRecords();
	for(var i=0;i<cords.length;i++){
		array.push(cords[i].recruitprogramguid);
	}
	if(array.length<=0){
		alert('请选择要删除的数据！');
		return;
	}
	if(!confirm('确认要删除选中数据吗？')){
		return;
	}
	
	$.post("recruitprogram/delRecruitprogramById.do",{ids:array.toString()}, function() {
		mygrid.reload();
    });
}



//返回
function back(){
	window.parent.convertView('');
}

</script>


<script type="text/javascript">	
var mygrid=null;
function loadGrid(){
	var size=getGridSize();
	var grid_demo_id = "myGrid1";
	var dsOption= {
			fields :[
				{name : 'recruitprogramguid'},
				{name : 'recruitprogramcode'},
				{name : 'quotaid'},
				{name : 'companyid'},
				{name : 'deptid'},
				{name : 'postid'},
				{name : 'rankid'},
				{name : 'applydate'},
				{name : 'hillockdate'},
				{name : 'postnum'},
				{name : 'state'},
				{name : 'processinstanceid'},
				{name : 'memo'},
				{name : 'modiuser'},
				{name : 'moditimestamp'},
				{name : 'modimemo'}
			]
		};
		var colsOption = [
			{id: '选择' ,isCheckColumn : true, editable:false,headAlign:'center',align:'center'},
			/*{id: 'taskid' , header: "操作" , width :50 ,headAlign:'center',align:'center',renderer:function(value ,record,colObj,grid,colNo,rowNo){
				if(record.state==2&&record.assignee=='${userid}')
					return '<a href="javascript:convertView(\'recruitprogram.do?page=tab&id='+record.recruitprogramguid+'&taskid='+record.taskid+'\')">审核</a>';
				return '';
			}},*/
			{id: 'statename' , header: "状态" , width :60 ,headAlign:'center',align:'left'},
			//{id: 'recruitprogramcode' , header: "编号" , width :120,headAlign:'center',align:'center'},
			{id: 'companyname' , header: "公司名称" , width :180,headAlign:'center',align:'left'},
			{id: 'deptname' , header: "部门名称" , width :150,headAlign:'center',align:'left'},
			{id: 'postname' , header: "岗位名称" , width :120,headAlign:'center',align:'left'},
			{id: 'rankname' , header: "职级" , width :100,headAlign:'center',align:'center'},
			{id: 'quotaname' , header: "编制类型" , width :120,headAlign:'center',align:'center'},
			{id: 'quotacode' , header: "编制编号" , width :120,headAlign:'center',align:'center'},
			{id: 'vacancynumber' , header: "缺编人数" , width :80,headAlign:'center',align:'left',number:true},
			{id: 'postnum' , header: "招聘人数" , width :80,headAlign:'center',align:'left',number:true},
			{id: 'applydate' , header: "申请时间" , width :120,headAlign:'center',align:'center'},
			{id: 'hillockdate' , header: "计划到岗时间" , width :120,headAlign:'center',align:'center'},
			{id: 'memo' , header: "备注" , width :250 ,headAlign:'center',align:'left'}
		];
		
	var gridOption={
		id : grid_demo_id,
		loadURL :'recruitprogram/searchRecruitprogram.do',
		beforeLoad:function(reqParam){
			initPagePam();
			reqParam['parameters']=pam;
		},
		exportURL : 'recruitprogram/searchRecruitprogram.do?export=true',
		beforeExport:function(){
			initPagePam();
			pam.expAll=1;
			mygrid.parameters=pam;
		},
		exportFileName : '招聘计划管理.xls',
		width: "99.8%",//"100%", // 700,
		height: "100%",  //"100%", // 330,
		container : 'gridbox', 
		pageSizeList : [size,size*2,size*4,size*6,size*8,size*10],
		stripeRows: false,
		remoteFilter:true,
		showIndexColumn:true,
		selectRowByCheck:true,
		replaceContainer : true,
		dataset : dsOption ,
		columns : colsOption,
		toolbarContent : 'nav | pagesize  state',
		pageSize:size,
		skin:getGridSkin(),
		loadResponseHandler:function(response,requestParameter){
			var obj = jQuery.parseJSON(response.text);
			var page=obj.pageInfo;
			var hg=(page.pageSize+1)*33+50;
			
			mygrid.setSize('99.9%',hg);
			pageResize(hg);
		}
	};
	mygrid=new Sigma.Grid( gridOption );
	Sigma.Util.onLoad( Sigma.Grid.render(mygrid) );
}


function pageResize(height){
	$("#myTable").height(height);
	//计算高度
	_autoHeight();
}
</script>
</head>
<body>

<div class="table">
	<div class="table-bar">
		<div class="table-title">
			表格名称
		</div>
		<div class="table-ctrl">
			<a class="btn" href="javascript:searchGrid();"><i class="icon icon-search"></i><span>搜索</span></a>
			<a class="btn" href="javascript:expGrid();"><i class="icon icon-download"></i><span>导出</span></a>
			<a class="btn" href="javascript:remove();"><i class="icon icon-remove"></i><span>删除</span></a>
			<a class="btn" href="javascript:back();"><i class="icon icon-share-alt"></i><span>返回列表</span></a>
		</div>
	</div>
	<div class="table-wrapper">
		<div id="myTable" style="height:550px;margin:5px auto;">
			<div id="gridbox" ></div>
              </div>
	</div>
</div>
<iframe id="detail" name="detail" width="100%" height="100%"frameborder="0" src="" scrolling="no" style="display: none;"></iframe>


<!-- 搜索 -->
<div id="search" title="搜索条件设置" style="display: none;">
	<form action="" id="searchForm" class="form">
		<ul>
			<li>
                <span>公司名称：</span>
                <input id="companyid" name="companyid" type="hidden" value=""/>
			    <input id="companyname" name="companyname" class="inputselectstyle" onclick="chooseMyHasCompanyTree('#companyid','#companyname',callBackCompany);"/>
			    <div class="search-trigger" onclick="chooseMyHasCompanyTree('#companyid','#companyname',callBackCompany);"/>
            </li>
		</ul>
		<ul>
			<li>
			    <span>部门名称：</span>
			    <input id="deptid" name="deptid" type="hidden" value=""/>
			    <input id="deptname" name="deptname" class="inputselectstyle" onclick="chooseOneCompanyMultipleDeptTree('#deptid','#deptname',$('#companyid').val());"/>
			    <div class="search-trigger" onclick="chooseOneCompanyMultipleDeptTree('#deptid','#deptname',$('#companyid').val());"/>
			</li>
		</ul>
		<ul>
			<li>
				<span>岗位名称：</span>
				<input id="postname" name="postname" class="inputstyle"/>
			</li>
		</ul>
		<ul>
			<li>
			    <span>职级：</span>
			     <input id="rankid" name="position.rankid" type="hidden" value=""/>
			    <input id="rankname" name="position.rankname" class="inputselectstyle" onclick="chooseRankTree('#rankid','#rankname',$('#companyid').val());"/>
			    <div class="select-trigger" onclick="chooseRankTree('#rankid','#rankname',$('#companyid').val());" />
			</li>
		</ul>
	</form>
</div>


<!-- 导出 -->
<div id="dialog_exp" style="display: none;" title="数据导出">
	<table width="100%" height="100%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td>&nbsp; &nbsp; &nbsp; &nbsp; <label for="xls1"> 
				<input id="xls1" type="radio" name="xls" value="0" checked="true" class="checkboxstyle" />导出本页
			</label> &nbsp; &nbsp; 
			<label for="xls2"> 
				<input id="xls2" type="radio" name="xls" value="1" class="checkboxstyle" />全部导出
			</label>
			</td>
		</tr>
	</table>
</div>

</body>
</html>
