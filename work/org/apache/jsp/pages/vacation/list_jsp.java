package org.apache.jsp.pages.vacation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class list_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>请假管理</title>\r\n");
      out.write("<base href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseUrl }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<meta http-equiv=\"pragma\" content=\"no-cache\"/>\r\n");
      out.write("<meta http-equiv=\"cache-control\" content=\"no-cache\"/>\r\n");
      out.write("<meta http-equiv=\"expires\" content=\"0\"/>    \r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"skins/css/style.css\" type=\"text/css\" media=\"all\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"skins/css/form.css\" type=\"text/css\" media=\"all\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"skins/css/jquery-ui-1.8.15.custom.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugin/tree/zTreeStyle.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugin/tree/zTreeIcons.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugin/grid/gt_grid.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugin/grid/skin/default/skinstyle.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugin/grid/skin/green/skinstyle.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugin/grid/skin/mac/skinstyle.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugin/grid/skin/china/skinstyle.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugin/grid/skin/vista/skinstyle.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"plugin/grid/calendar/calendar-blue.css\"  />\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"skins/js/jquery-1.4.4.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"skins/js/public.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"skins/js/jquery-ui-1.8.5.custom.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugin/tree/jquery.ztree.all.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugin/form/jquery.form.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugin/form/jquery.validate.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugin/form/lib/jquery.metadata.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugin/grid/calendar/calendar.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugin/grid/calendar/calendar-en.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugin/grid/calendar/calendar-setup.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugin/grid/gt_grid_all.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plugin/grid/gt_msg_cn.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"pages/tree.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("$(document).ready(function () {\r\n");
      out.write("\t\r\n");
      out.write("    //列表加载\r\n");
      out.write("    loadGrid();\r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("  //日期\r\n");
      out.write("   $(\".datepicker\").datepicker({\r\n");
      out.write("       dateFormat: 'yy-mm-dd'\r\n");
      out.write("   });\r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("    //关闭等待层\r\n");
      out.write("    if(window.parent.hidenLoading)\r\n");
      out.write("    \twindow.parent.hidenLoading();\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("//导出\r\n");
      out.write("function expGrid(){\r\n");
      out.write("\t$(\"#dialog_exp\").dialog({\r\n");
      out.write("\t\tautoOpen: true,\r\n");
      out.write("\t\tmodal: true,\r\n");
      out.write("\t\tmodel:true,\r\n");
      out.write("\t\tresizable:false,\r\n");
      out.write("\t\twidth:400,\r\n");
      out.write("\t\theight:130,\r\n");
      out.write("\t\tbuttons: {\r\n");
      out.write("\t\t\t\"确定\": function() {\r\n");
      out.write("\t\t\t\tmygrid.exportGrid('xls');\r\n");
      out.write("\t\t\t\t$(this).dialog(\"close\");\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t\"取消\": function() {\r\n");
      out.write("\t\t\t\t$(this).dialog(\"close\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tclose: function() {\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("//部门选择回调\r\n");
      out.write("function callbackDept(){\r\n");
      out.write("\t$(\"#postid\").val(null);\r\n");
      out.write("\t$(\"#postname\").val(null);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("//公司回调\r\n");
      out.write("function callBackCompany(){\r\n");
      out.write("\t$(\"#deptid\").val(null);\r\n");
      out.write("\t$(\"#deptname\").val(null);\r\n");
      out.write("\t\r\n");
      out.write("\t//部门选择回调\r\n");
      out.write("\tcallbackDept();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("//删除\r\n");
      out.write("function remove(){\r\n");
      out.write("\tvar array=new Array();\r\n");
      out.write("\tvar cords=mygrid.getSelectedRecords();\r\n");
      out.write("\tfor(var i=0;i<cords.length;i++){\r\n");
      out.write("\t\tvar obj=cords[i];\r\n");
      out.write("\t\tarray.push(obj.vacationid);\r\n");
      out.write("\t}\r\n");
      out.write("\tif(array.length<=0){\r\n");
      out.write("\t\talert('请选择要删除的数据！');\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(!confirm('确认要删除选中数据吗？')){\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write(" \t$.post(\"vacation/delVacation.do\",{ids:array.toString()}, function() {\r\n");
      out.write("\t\tmygrid.reload();\r\n");
      out.write("  \t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("//搜索\r\n");
      out.write("var searchForm=null;\r\n");
      out.write("function searchGrid(){\r\n");
      out.write("\t$(\"#search\").dialog({\r\n");
      out.write("\t\tautoOpen: true,\r\n");
      out.write("\t\tmodal: true,\r\n");
      out.write("\t\tresizable:false,\r\n");
      out.write("\t\twidth:470,\r\n");
      out.write("\t\tbuttons: {\r\n");
      out.write("\t\t\t\"确定\":function(){\r\n");
      out.write("\t\t\t\tif(searchForm.form()){\r\n");
      out.write("\t\t\t\t\tmygrid.reload();\r\n");
      out.write("\t\t\t\t\t$(this).dialog(\"close\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t\"重置\":function(){\r\n");
      out.write("\t\t\t\t$(\"#searchForm\").clearForm();\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t\"关闭\": function() {\r\n");
      out.write("\t\t\t\t$(this).dialog(\"close\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\topen:function(){\r\n");
      out.write("\t\t\t//校验\r\n");
      out.write("\t\t\tif(searchForm==null)\r\n");
      out.write("\t\t\t\tsearchForm=$(\"#searchForm\").validate();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("//参数设置\r\n");
      out.write("var pam=null;\r\n");
      out.write("function initPagePam(){\r\n");
      out.write("\tpam={};\r\n");
      out.write("\tpam.expAll=0;\r\n");
      out.write("\tpam.userguid='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\tpam.companyid=$(\"#companyid\").val();\r\n");
      out.write("\tpam.deptid=$(\"#deptid\").val();\r\n");
      out.write("\tpam.postid=$(\"#postid\").val();\r\n");
      out.write("\tpam.name=$(\"#name\").val();\r\n");
      out.write("\tpam.vacationtype=$(\"#vacationtype\").val();\r\n");
      out.write("\tpam.startdate=$(\"#startdate\").val();\r\n");
      out.write("\tpam.enddate=$(\"#enddate\").val();\r\n");
      out.write("\tpam.annualyear=$(\"#annualyear\").val();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("//员工树选择回调\r\n");
      out.write("function employeeTreeCallback(id){\r\n");
      out.write("\t$.getJSON(\"employee/getEmployeeById.do\", {id:id}, function(data) {\r\n");
      out.write("\t\tif(data!=null){\r\n");
      out.write("\t\t\t$(\"#name\").val(data.name);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("//切换视图\r\n");
      out.write("function convertView(url){\r\n");
      out.write("\tif ($(\".sort\").css(\"display\")!=\"none\") {\r\n");
      out.write("\t\t$(\".sort\").hide();\r\n");
      out.write("\t\t$(\"#detail\").show();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#detail\").attr(\"src\",url);\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\t$(\"#detail\").height(0);\r\n");
      out.write("\t\t$(\"#detail\").removeAttr(\"src\");\r\n");
      out.write("\t\t$(\".sort\").show();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//计算高度\r\n");
      out.write("\t\t_autoHeight();\r\n");
      out.write("\t\tif(url==null)\r\n");
      out.write("\t\t\tmygrid.reload();\r\n");
      out.write("  }\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\t\r\n");
      out.write("var mygrid=null;\r\n");
      out.write("function loadGrid(){\r\n");
      out.write("\tvar size=getGridSize();\r\n");
      out.write("\tvar grid_demo_id = \"myGrid1\";\r\n");
      out.write("\tvar dsOption= {\r\n");
      out.write("\t\t\tfields :[\r\n");
      out.write("\t\t\t\t{name : 'vacationid'},\r\n");
      out.write("\t\t\t\t{name : 'employeeid'},\r\n");
      out.write("\t\t\t\t{name : 'reason'},\r\n");
      out.write("\t\t\t\t{name : 'vacationtype'},\r\n");
      out.write("\t\t\t\t{name : 'startdate'},\r\n");
      out.write("\t\t\t\t{name : 'datenumber'},\r\n");
      out.write("\t\t\t\t{name : 'enddate'},\r\n");
      out.write("\t\t\t\t{name : 'memo'},\r\n");
      out.write("\t\t\t\t{name : 'modiuser'},\r\n");
      out.write("\t\t\t\t{name : 'moditimestamp'},\r\n");
      out.write("\t\t\t\t{name : 'modimemo'}\r\n");
      out.write("\t\t\t]\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\tvar colsOption = [\r\n");
      out.write("\t\t\t{id: '选择' ,isCheckColumn : true, editable:false,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'annualyear' , header: \"年度\" , width :60,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'companyname' , header: \"公司名称\" , width :200,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'deptname' , header: \"部门名称\" , width :150,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'postname' , header: \"岗位名称\" , width :120,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'jobnumber' , header: \"员工工号\" , width :140,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'name' , header: \"请假人\" , width :80,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'cardnumber' , header: \"身份证\" , width :180,headAlign:'center',align:'left',hidden:true},\r\n");
      out.write("\t\t\t{id: 'vacationtypename' , header: \"请假类型\" , width :80,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'datenumber' , header: \"请假天数\" , width :80,headAlign:'center',align:'right'},\r\n");
      out.write("\t\t\t{id: 'startdate' , header: \"请假开始时间\" , width :120 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'enddate' , header: \"请假结束时间\" , width :120 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'reason' , header: \"请假原因\" , width :150,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'memo' , header: \"备注\" , width :200,headAlign:'left',align:'left'}\r\n");
      out.write("\t\t];\r\n");
      out.write("\t\t\r\n");
      out.write("\tvar gridOption={\r\n");
      out.write("\t\tid : grid_demo_id,\r\n");
      out.write("\t\tloadURL :'vacation/searchVacation.do',\r\n");
      out.write("\t\tbeforeLoad:function(reqParam){\r\n");
      out.write("\t\t\tinitPagePam();\r\n");
      out.write("\t\t\treqParam['parameters']=pam;\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\texportURL : 'vacation/searchVacation.do?export=true',\r\n");
      out.write("\t\tbeforeExport:function(){\r\n");
      out.write("\t\t\tinitPagePam();\r\n");
      out.write("\t\t\tpam.expAll=$('input[name=\"xls\"]:checked').val();\r\n");
      out.write("\t\t\tmygrid.parameters=pam;\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\texportFileName : '请假信息表.xls',\r\n");
      out.write("\t\twidth: \"99.8%\",//\"100%\", // 700,\r\n");
      out.write("\t\theight: \"100%\",  //\"100%\", // 330,\r\n");
      out.write("\t\tcontainer : 'gridbox', \r\n");
      out.write("\t\tpageSizeList : [size,size*2,size*4,size*6,size*8,size*10],\r\n");
      out.write("\t\tstripeRows: false,\r\n");
      out.write("\t\tremoteFilter:true,\r\n");
      out.write("\t\tshowIndexColumn:true,\r\n");
      out.write("\t\tselectRowByCheck:true,\r\n");
      out.write("\t\treplaceContainer : true,\r\n");
      out.write("\t\tshowGridMenu : true,\r\n");
      out.write("\t\tallowFreeze\t: true ,\r\n");
      out.write("\t\tallowHide\t: true ,\r\n");
      out.write("\t\tallowGroup\t: true ,\r\n");
      out.write("\t\tdataset : dsOption ,\r\n");
      out.write("\t\tcolumns : colsOption,\r\n");
      out.write("\t\ttoolbarContent : 'nav | pagesize  state',\r\n");
      out.write("\t\tpageSize:size,\r\n");
      out.write("\t\tskin:getGridSkin(),\r\n");
      out.write("\t\tonDblClickCell:function(value, record , cell, row, colNO, rowNO,columnObj,grid){\r\n");
      out.write("\t\t\tif(colNO!=0){\r\n");
      out.write("\t\t\t\tconvertView('vacation.do?page=form&id='+record.vacationid+'&edit=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${edit}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t};\r\n");
      out.write("\tmygrid=new Sigma.Grid( gridOption );\r\n");
      out.write("\tSigma.Util.onLoad( Sigma.Grid.render(mygrid) );\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<div class=\"sort\">\r\n");
      out.write("\t<div class=\"sort-title\">\r\n");
      out.write("\t\t<h3>请假管理</h3>\r\n");
      out.write("\t\t<div class=\"title-ctrl\">\r\n");
      out.write("\t\t\t<a class=\"btn-ctrl\" href=\"javascript:chevronUpDown('.sort-cont',false);\"><i class=\"icon icon-chevron-up\"></i></a>\r\n");
      out.write("\t\t\t<a class=\"btn-ctrl\" href=\"javascript:chevronUpDown('.sort-cont',true);\"><i class=\"icon icon-chevron-down\"></i></a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t<div class=\"sort-cont sort-table\">\r\n");
      out.write("\t\t<div class=\"table\">\r\n");
      out.write("\t\t\t<div class=\"table-bar\">\r\n");
      out.write("\t\t\t\t<div class=\"table-title\">\r\n");
      out.write("\t\t\t\t\t表格名称\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"table-ctrl\">\r\n");
      out.write("\t\t\t\t\t<a class=\"btn\" href=\"javascript:searchGrid();\" style=\"display:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${search?'':'none'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"><i class=\"icon icon-search\"></i><span>搜索</span></a>\r\n");
      out.write("\t\t\t\t\t<a class=\"btn\" href=\"javascript:convertView('vacation.do?page=import');\" style=\"display:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${imp?'':'none'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"><i class=\"icon icon-qrcode\"></i><span>导入</span></a>\r\n");
      out.write("\t\t\t\t\t<a class=\"btn\" href=\"javascript:expGrid();\" style=\"display:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${exp?'':'none'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"><i class=\"icon icon-download\" ></i><span>导出</span></a>\r\n");
      out.write("\t\t\t\t\t<a class=\"btn\" href=\"javascript:convertView('vacation.do?page=form&edit=true');\" style=\"display:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${add?'':'none'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"><i class=\"icon icon-plus\"></i><span>新增</span></a>\r\n");
      out.write("\t\t\t\t\t<a class=\"btn\" href=\"javascript:remove();\" style=\"display:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${del?'':'none'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"><i class=\"icon icon-remove\" ></i><span>删除</span></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"table-wrapper\">\r\n");
      out.write("\t\t\t\t<div id=\"myTable\" style=\"height:550px;margin:5px auto;\">\r\n");
      out.write("\t\t\t\t\t<div id=\"gridbox\" ></div>\r\n");
      out.write("                </div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<iframe id=\"detail\" name=\"detail\" width=\"100%\" height=\"100%\" frameborder=\"0\" src=\"\" scrolling=\"no\" style=\"display:none;\"></iframe>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 搜索 -->\r\n");
      out.write("<div id=\"search\" title=\"搜索条件设置\" style=\"display:none;\">\r\n");
      out.write("\t<form action=\"\" id=\"searchForm\" class=\"form\">\r\n");
      out.write("\t\t<ul id=\"myCompany\">\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("                <span>公司名称：</span>\r\n");
      out.write("                <input id=\"companyid\" name=\"companyid\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("\t\t\t    <input id=\"companyname\" name=\"companyname\" class=\"inputselectstyle\" onclick=\"chooseMyHasCompanyTree('#companyid','#companyname',callBackCompany);\"/>\r\n");
      out.write("\t\t\t    <div class=\"search-trigger\" onclick=\"chooseMyHasCompanyTree('#companyid','#companyname',callBackCompany);\"/>\r\n");
      out.write("            </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("        <ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t    <span>部门名称：</span>\r\n");
      out.write("\t\t\t    <input id=\"deptid\" name=\"deptid\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("\t\t\t    <input id=\"deptname\" name=\"deptname\" class=\"inputselectstyle\" onclick=\"chooseOneCompanyDeptTree('#deptid','#deptname',$('#companyid').val(),callbackDept);\"/>\r\n");
      out.write("\t\t\t    <div class=\"search-trigger\" onclick=\"chooseOneCompanyDeptTree('#deptid','#deptname',$('#companyid').val(),callbackDept);\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("        <ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t    <span>岗位名称：</span>\r\n");
      out.write("\t\t\t    <input id=\"postid\" name=\"postid\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("\t\t\t    <input id=\"postname\" name=\"postname\" class=\"inputselectstyle\" onclick=\"choosePostTree('#postid','#postname',$('#deptid').val());\"/>\r\n");
      out.write("\t\t\t    <div class=\"search-trigger\" onclick=\"choosePostTree('#postid','#postname',$('#deptid').val());\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("        </ul>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<span>姓名：</span>\r\n");
      out.write("\t\t\t\t<input id=\"name\" name=\"name\" class=\"inputstyle\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<span>年度：</span>\r\n");
      out.write("\t\t\t\t<input id=\"annualyear\" name=\"annualyear\" class=\"inputstyle\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<span>请假类型：</span>\r\n");
      out.write("\t\t\t\t<input id=\"vacationtype\" name=\"vacationtype\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("\t\t\t\t<input id=\"vacationtypename\" name=\"vacationtypename\" class=\"inputselectstyle\" onclick=\"chooseOptionTree('#vacationtype','#vacationtypename','VACATIONTYPE');\"/>\r\n");
      out.write("\t\t\t\t<div class=\"select-trigger\" onclick=\"chooseOptionTree('#vacationtype','#vacationtypename','VACATIONTYPE');\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<span>请假时间从：</span>\r\n");
      out.write("\t\t\t\t<input id=\"startdate\" name=\"startdate\" class=\"{dateISO:true} inputselectstyle datepicker\"/>\r\n");
      out.write("\t\t\t\t<div class=\"date-trigger\" onclick=\"$('#startdate').focus();\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<span>至时间：</span>\r\n");
      out.write("\t\t\t\t<input id=\"enddate\" name=\"enddate\" class=\"{dateISO:true} inputselectstyle datepicker\"/>\r\n");
      out.write("\t\t\t\t<div class=\"date-trigger\" onclick=\"$('#enddate').focus();\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</form>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 导出 -->\r\n");
      out.write("<div id=\"dialog_exp\" style=\"display:none;\" title=\"数据导出\" >\r\n");
      out.write("\t<table width=\"100%\" height=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>\r\n");
      out.write("\t\t\t\t&nbsp;\r\n");
      out.write("\t\t\t  \t&nbsp;\r\n");
      out.write("\t\t\t\t&nbsp;\r\n");
      out.write("\t\t\t  \t&nbsp;\r\n");
      out.write("\t\t\t\t<label for=\"xls1\">\r\n");
      out.write("\t\t\t\t\t<input id=\"xls1\" type=\"radio\" name=\"xls\" value=\"0\" checked=\"true\" class=\"checkboxstyle\"/>导出本页\r\n");
      out.write("\t\t\t\t</label>\r\n");
      out.write("\t\t\t  \t&nbsp;\r\n");
      out.write("\t\t\t  \t&nbsp;\r\n");
      out.write("\t\t\t  \t<label for=\"xls2\">\r\n");
      out.write("\t\t\t  \t\t<input id=\"xls2\" type=\"radio\" name=\"xls\" value=\"1\" class=\"checkboxstyle\"/>全部导出\r\n");
      out.write("\t\t\t  \t</label>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}