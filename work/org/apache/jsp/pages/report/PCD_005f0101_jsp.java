package org.apache.jsp.pages.report;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class PCD_005f0101_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<title>传媒花名册</title>\r\n");
      out.write("<base href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${baseUrl }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<meta http-equiv=\"pragma\" content=\"no-cache\"/>\r\n");
      out.write("<meta http-equiv=\"cache-control\" content=\"no-cache\"/>\r\n");
      out.write("<meta http-equiv=\"expires\" content=\"0\"/>    \r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"skins/css/style.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"skins/css/form.css\"/>\r\n");
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
      out.write("<script type=\"text/javascript\" src=\"skins/js/jquery.json-2.2.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"skins/js/jquery.layout.js\"></script>\r\n");
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
      out.write("$(document).ready(function () {\r\n");
      out.write("\t\r\n");
      out.write("    //列表加载\r\n");
      out.write("    loadGrid();\r\n");
      out.write("    searchGrid();\r\n");
      out.write("    \r\n");
      out.write("    //日期\r\n");
      out.write("    $(\".datepicker\").datepicker({\r\n");
      out.write("        dateFormat: 'yy-mm-dd'\r\n");
      out.write("    });\r\n");
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
      out.write("//导出\r\n");
      out.write("function expGrid(){\r\n");
      out.write("\tmygrid.exportGrid('xls');\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//打印\r\n");
      out.write("function printGrid(){\r\n");
      out.write("\tmygrid.printGrid();\r\n");
      out.write("}\r\n");
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
      out.write("\t\t\t\t\t$(this).dialog(\"close\");\r\n");
      out.write("\t\t    \t\tmygrid.loadURL=\"report/searchPCD0101.do\";\r\n");
      out.write("\t\t    \t\tmygrid.reload();\r\n");
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
      out.write("//参数设置\r\n");
      out.write("var pam=null;\r\n");
      out.write("function initPagePam(){\r\n");
      out.write("\tpam={};\r\n");
      out.write("\tpam.userguid='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\tpam.companyid=$(\"#companyid\").val();\r\n");
      out.write("\tpam.deptid=$(\"#deptid\").val();\r\n");
      out.write("\tpam.isstaff=$(\"#isstaff\").val();\r\n");
      out.write("\tpam.sex=$(\"#sex\").val();\r\n");
      out.write("\tpam.culture=$(\"#culture\").val();\r\n");
      out.write("\tpam.politics=$(\"#politics\").val();\r\n");
      out.write("\tpam.workstate=$(\"#workstate\").val();\r\n");
      out.write("\tpam.classification=$(\"#classification\").val();\r\n");
      out.write("\tpam.employtype=$(\"#employtype\").val();\r\n");
      out.write("}\r\n");
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
      out.write("\t\t\t\t{name : 'COMPANYNAME'},\r\n");
      out.write("\t\t\t\t{name : 'DEPTNAME_1'},\r\n");
      out.write("\t\t\t\t{name : 'DEPTNAME_2'},\r\n");
      out.write("\t\t\t\t{name : 'POSTNAME'},\r\n");
      out.write("\t\t\t\t{name : 'JZXX'},\r\n");
      out.write("\t\t\t\t{name : 'JOBNUMBER'},\r\n");
      out.write("\t\t\t\t{name : 'NAME'},\r\n");
      out.write("\t\t\t\t{name : 'WORKSTATE'},\r\n");
      out.write("\t\t\t\t{name : 'BUDGETTYPE'},\r\n");
      out.write("\t\t\t\t{name : 'EMPLOYTYPE'},\r\n");
      out.write("\t\t\t\t{name : 'CLASSIFICATION'},\r\n");
      out.write("\t\t\t\t{name : 'CULTURE'},\r\n");
      out.write("\t\t\t\t{name : 'JOBNAME'},\r\n");
      out.write("\t\t\t\t{name : 'BIRTHDAY'},\r\n");
      out.write("\t\t\t\t{name : 'CARDNUMBER'},\r\n");
      out.write("\t\t\t\t{name : 'SEX'},\r\n");
      out.write("\t\t\t\t{name : 'JOINGROUPDATE'},\r\n");
      out.write("\t\t\t\t{name : 'JOINDATE'},\r\n");
      out.write("\t\t\t\t{name : 'HOMEPLACE'},\r\n");
      out.write("\t\t\t\t{name : 'MOBILE'},\r\n");
      out.write("\t\t\t\t{name : 'EMAIL'},\r\n");
      out.write("\t\t\t\t{name : 'SHORTPHONE'},\r\n");
      out.write("\t\t\t\t{name : 'CERTIFICATION'},\r\n");
      out.write("\t\t\t\t{name : 'EDU_PROFESSIONAL'},\r\n");
      out.write("\t\t\t\t{name : 'EDU_EDUORG'},\r\n");
      out.write("\t\t\t\t{name : 'CONTRACT_CONTRACTTYPE'},\r\n");
      out.write("\t\t\t\t{name : 'CONTRACT_STARTDATE'},\r\n");
      out.write("\t\t\t\t{name : 'CONTRACT_ENDDATE'}\r\n");
      out.write("\t\t\t]\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\tvar colsOption = [\r\n");
      out.write("\t\t\t{id: 'COMPANYNAME' , header: \"公司名称\" , width :200 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'DEPTNAME_1' , header: \"一级部门\" , width :120 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'DEPTNAME_2' , header: \"二级部门\" , width :120 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'POSTNAME' , header: \"职位\" , width :120 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'JZXX' , header: \"兼职职位\" , width :150 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'JOBNUMBER' , header: \"工号\" , width :120 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'NAME' , header: \"姓名\" , width :80 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'WORKSTATE' , header: \"员工状态\" , width :80 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'BUDGETTYPE' , header: \"编制类型\" , width :100 ,headAlign:'center'},\r\n");
      out.write("\t\t\t{id: 'EMPLOYTYPE' , header: \"用工性质\" , width :150 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'LEVELNAME' , header: \"职级\" , width :100 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'CLASSIFICATION' , header: \"员工类别\" , width :120 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'CULTURE' , header: \"学历情况\" , width :80 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'JOBNAME' , header: \"职务名称\" , width :80 ,headAlign:'center'},\r\n");
      out.write("\t\t\t{id: 'BIRTHDAY' , header: \"出生日期\" , width :90 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'CARDNUMBER' , header: \"身份证号码\" , width :180 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'SEX' , header: \"性别\" , width :60 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'JOINWORKDATE' , header: \"参加工作时间\" , width :90 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'JOINGROUPDATE', header: \"加入集团时间\", width :90 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'JOINDATE' , header: \"加入公司时间\" , width :90 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'OFFICIALDATE' , header: \"转正时间\" , width :90 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'SOCIAL' , header: \"社保归属地\" , width :150 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'HOMEPLACE' , header: \"身份证地址\" , width :300 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'MOBILE' , header: \"手机\" , width :120 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'EMAIL' , header: \"公司邮箱\" , width :150 ,headAlign:'center'},\r\n");
      out.write("\t\t\t{id: 'SHORTPHONE' , header: \"手机内网号码\" , width :100 ,headAlign:'center'},\r\n");
      out.write("\t\t\t{id: 'CERTIFICATION' , header: \"职称认证\" , width :200 ,headAlign:'center'},\r\n");
      out.write("\t\t\t{id: 'EDU_PROFESSIONAL' , header: \"专业\" , width :200 ,headAlign:'center'},\r\n");
      out.write("\t\t\t{id: 'EDU_EDUORG' , header: \"毕业学校\" , width :200 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'CONTRACT_CONTRACTTYPE' , header: \"合同类型\" , width :150 ,headAlign:'center'},\r\n");
      out.write("\t\t\t{id: 'CONTRACT_STARTDATE' , header: \"合同生效日期\" , width :90 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'CONTRACT_ENDDATE' , header: \"合同到期日期\" , width :90 ,headAlign:'center',align:'center'}\r\n");
      out.write("\t\t];\r\n");
      out.write("\tvar gridOption={\r\n");
      out.write("\t\tid : grid_demo_id,\r\n");
      out.write("\t\tbeforeLoad:function(reqParam){\r\n");
      out.write("\t\t\tinitPagePam();\r\n");
      out.write("\t\t\treqParam['parameters']=pam;\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\texportURL : 'report/searchPCD0101.do?export=true',\r\n");
      out.write("\t\tbeforeExport:function(){\r\n");
      out.write("\t\t\tinitPagePam();\r\n");
      out.write("\t\t\tpam.expAll=$('input[name=\"xls\"]:checked').val();\r\n");
      out.write("\t\t\tmygrid.parameters=pam;\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\texportFileName : '传媒花名册.xls',\r\n");
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
      out.write("\t\tskin:getGridSkin()\r\n");
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
      out.write("\t\t<h3>传媒花名册</h3>\r\n");
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
      out.write("\t\t\t\t\t<a class=\"btn\" href=\"javascript:searchGrid();\"><i class=\"icon icon-search\"></i><span>搜索</span></a>\r\n");
      out.write("\t\t\t\t\t<a class=\"btn\" href=\"javascript:expGrid();\"><i class=\"icon icon-list-alt\"></i><span>导出</span></a>\r\n");
      out.write("\t\t\t\t\t<a class=\"btn\" href=\"javascript:printGrid();\"><i class=\"icon icon-print\"></i><span>打印</span></a>\r\n");
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 搜索 -->\r\n");
      out.write("<div id=\"search\" title=\"搜索条件设置\" style=\"display:none;\">\r\n");
      out.write("\t<form action=\"\" id=\"searchForm\" class=\"form\">\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("                <span>部门名称：</span>\r\n");
      out.write("                <input id=\"companyid\" name=\"companyid\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("                <input id=\"deptid\" name=\"deptid\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("\t\t\t    <input id=\"deptname\" name=\"deptname\" class=\"inputselectstyle\" onclick=\"chooseMyMultipleDeptTree('#deptid','#deptname');\"/>\r\n");
      out.write("\t\t\t    <div class=\"search-trigger\" onclick=\"chooseMyMultipleDeptTree('#deptid','#deptname');\"/>\r\n");
      out.write("            </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("        <ul>\r\n");
      out.write("\t       <li>\r\n");
      out.write("\t\t\t    <span>编制：</span>\r\n");
      out.write("\t\t\t    <input id=\"isstaff\" name=\"isstaff\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("\t\t\t    <input id=\"isstaffname\" name=\"isstaffname\" class=\"inputselectstyle\" onclick=\"chooseMyMultipleOptionTree('#isstaff','#isstaffname','ISSTAFF');\"/>\r\n");
      out.write("\t\t\t    <div class=\"select-trigger\" onclick=\"chooseMyMultipleOptionTree('#isstaff','#isstaffname','ISSTAFF');\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("        </ul>\r\n");
      out.write("        <ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t\t<span>员工状态：</span>\r\n");
      out.write("                <input id=\"workstate\" name=\"workstate\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("\t\t\t    <input id=\"workstatename\" name=\"workstatename\" class=\"inputselectstyle\" onclick=\"chooseMyMultipleOptionTree('#workstate','#workstatename','WORKSTATE');\"/>\r\n");
      out.write("\t\t\t    <div class=\"select-trigger\" onclick=\"chooseMyMultipleOptionTree('#workstate','#workstatename','WORKSTATE');\"/>\r\n");
      out.write("            </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("        <ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t    <span>员工类别：</span>\r\n");
      out.write("\t\t\t    <input id=\"classification\" name=\"classification\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("\t\t\t    <input id=\"classificationname\" name=\"classificationname\" class=\"inputselectstyle\" onclick=\"chooseMyMultipleOptionTree('#classification','#classificationname','CLASSIFICATION');\"/>\r\n");
      out.write("\t\t\t    <div class=\"select-trigger\" onclick=\"chooseMyMultipleOptionTree('#classification','#classificationname','CLASSIFICATION');\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t   <ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t    <span>用工性质：</span>\r\n");
      out.write("\t\t\t    <input id=\"employtype\" name=\"employtype\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("\t\t\t    <input id=\"employtypename\" name=\"employtypename\" class=\"inputselectstyle\" onclick=\"chooseMyMultipleOptionTree('#employtype','#employtypename','EMPLOYTYPE');\"/>\r\n");
      out.write("\t\t\t    <div class=\"select-trigger\" onclick=\"chooseMyMultipleOptionTree('#employtype','#employtypename','EMPLOYTYPE');\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("        </ul>\r\n");
      out.write("        <ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t    <span>性别：</span>\r\n");
      out.write("\t\t\t    <input id=\"sex\" name=\"sex\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("\t\t\t    <input id=\"sexname\" name=\"sexname\" class=\"inputselectstyle\" onclick=\"chooseMyMultipleOptionTree('#sex','#sexname','SEX');\"/>\r\n");
      out.write("\t\t\t    <div class=\"select-trigger\" onclick=\"chooseMyMultipleOptionTree('#sex','#sexname','SEX');\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t    <span>文化程度：</span>\r\n");
      out.write("\t\t\t    <input id=\"culture\" name=\"culture\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("\t\t\t    <input id=\"culturename\" name=\"culturename\" class=\"inputselectstyle\" onclick=\"chooseMyMultipleOptionTree('#culture','#culturename','CULTURE');\"/>\r\n");
      out.write("\t\t\t    <div class=\"select-trigger\" onclick=\"chooseMyMultipleOptionTree('#culture','#culturename','CULTURE');\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t    <span>政治面貌：</span>\r\n");
      out.write("\t\t\t    <input id=\"politics\" name=\"politics\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("\t\t\t    <input id=\"politicsname\" name=\"politicsname\" class=\"inputselectstyle\" onclick=\"chooseMyMultipleOptionTree('#politics','#politicsname','POLITICS');\"/>\r\n");
      out.write("\t\t\t    <div class=\"select-trigger\" onclick=\"chooseMyMultipleOptionTree('#politics','#politicsname','POLITICS');\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("        \r\n");
      out.write("\t</form>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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