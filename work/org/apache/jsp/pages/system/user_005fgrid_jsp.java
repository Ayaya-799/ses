package org.apache.jsp.pages.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class user_005fgrid_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<title>用户管理</title>\r\n");
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
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(document).ready(function() {\r\n");
      out.write("\t\r\n");
      out.write("\t//加载布局\r\n");
      out.write("\t$('#myContent').layout({\r\n");
      out.write("    });\r\n");
      out.write("\t\r\n");
      out.write("\t//列表加载\r\n");
      out.write("    loadGrid();\r\n");
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
      out.write("\tpam.expAll=0;\r\n");
      out.write("\tpam.userguid='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\tpam.companyid=$(\"#companyid\").val();\r\n");
      out.write("\tpam.deptid=$(\"#deptid\").val();\r\n");
      out.write("\tpam.postid=$(\"#postid\").val();\r\n");
      out.write("\tpam.name=$(\"#name\").val();\r\n");
      out.write("\tpam.jobnumber=$(\"#jobnumber\").val();\r\n");
      out.write("\t\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\t\r\n");
      out.write("var mygrid=null;\r\n");
      out.write("function loadGrid(){\r\n");
      out.write("\tvar size=getGridSize();\r\n");
      out.write("\tvar grid_demo_id = \"myGrid1\";\r\n");
      out.write("\tvar dsOption= {\r\n");
      out.write("\t\t\tfields :[\r\n");
      out.write("\t\t\t\t{name : 'userguid'},\r\n");
      out.write("\t\t\t\t{name : 'employeeid'},\r\n");
      out.write("\t\t\t\t{name : 'loginname'},\r\n");
      out.write("\t\t\t\t{name : 'password'},\r\n");
      out.write("\t\t\t\t{name : 'isadmin'},\r\n");
      out.write("\t\t\t\t{name : 'state'},\r\n");
      out.write("\t\t\t\t{name : 'memo'},\r\n");
      out.write("\t\t\t\t{name : 'modiuser'},\r\n");
      out.write("\t\t\t\t{name : 'moditimestamp'},\r\n");
      out.write("\t\t\t\t{name : 'modimemo'},\r\n");
      out.write("\t\t\t\t{name : 'companyname'},\r\n");
      out.write("\t\t\t\t{name : 'deptname'},\r\n");
      out.write("\t\t\t\t{name : 'postname'},\r\n");
      out.write("\t\t\t\t{name : 'username'},\r\n");
      out.write("\t\t\t\t{name : 'rolename'},\r\n");
      out.write("\t\t\t\t{name : 'jobnumber'}\r\n");
      out.write("\t\t\t]\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\tvar colsOption = [\r\n");
      out.write("\t\t\t{id: 'isstaff' , header: \" \" , width :120 ,headAlign:'center',align:'center',renderer:function(value ,record,colObj,grid,colNo,rowNo){\r\n");
      out.write("\t\t\t\tvar htm='';\r\n");
      out.write("\t\t\t\t//角色授权\r\n");
      out.write("\t\t\t\thtm+='<a href=\"javascript:openRole(\\''+record.userguid+'\\');\" title=\"角色授权\">';\r\n");
      out.write("\t\t\t\thtm+='角色';\r\n");
      out.write("\t\t\t\thtm+='</a>';\r\n");
      out.write("\t\t\t\thtm+='&nbsp;&nbsp;';\r\n");
      out.write("\t\t\t\t//公司授权\r\n");
      out.write("\t\t\t\thtm+='<a href=\"javascript:openCompany(\\''+record.userguid+'\\');\"  title=\"公司授权\">';\r\n");
      out.write("\t\t\t\thtm+='公司';\r\n");
      out.write("\t\t\t\thtm+='</a>';\r\n");
      out.write("\t\t\t\thtm+='&nbsp;&nbsp;';\r\n");
      out.write("\t\t\t\t//通讯录授权\r\n");
      out.write("\t\t\t\thtm+='<a href=\"javascript:openAddress(\\''+record.userguid+'\\');\" title=\"通讯录授权\">';\r\n");
      out.write("\t\t\t\thtm+='通讯录';\r\n");
      out.write("\t\t\t\thtm+='</a>';\r\n");
      out.write("\t\t\t\treturn htm;\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{id: 'companyname' , header: \"公司名称\" , width :250 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'deptname' , header: \"部门名称\" , width :120 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'postname' , header: \"岗位名称\" , width :120 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'username' , header: \"用户名\" , width :80 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'loginname' , header: \"登陆名\" , width :80 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'rolename' , header: \"用户角色\" , width :200 ,headAlign:'center',align:'left'},\r\n");
      out.write("\t\t\t{id: 'modimemo' , header: \"备注\" , width :200 ,headAlign:'center',headAlign:'left',align:'left'}\r\n");
      out.write("\t\t];\r\n");
      out.write("\tvar gridOption={\r\n");
      out.write("\t\tid : grid_demo_id,\r\n");
      out.write("\t\tloadURL :'system/searchUser.do',\r\n");
      out.write("\t\tbeforeLoad:function(reqParam){\r\n");
      out.write("\t\t\tinitPagePam();\r\n");
      out.write("\t\t\treqParam['parameters']=pam;\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\texportURL : 'system/searchUser.do?export=true',\r\n");
      out.write("\t\tbeforeExport:function(){\r\n");
      out.write("\t\t\tinitPagePam();\r\n");
      out.write("\t\t\tpam.expAll=$('input[name=\"xls\"]:checked').val();\r\n");
      out.write("\t\t\tmygrid.parameters=pam;\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\texportFileName : '系统用户列表.xls',\r\n");
      out.write("\t\twidth: \"99.8%\",//\"100%\", // 700,\r\n");
      out.write("\t\theight: \"99.8%\",  //\"100%\", // 330,\r\n");
      out.write("\t\tcontainer : 'gridbox', \r\n");
      out.write("\t\tpageSizeList : [size,size*2,size*4,size*6,size*8,size*10],\r\n");
      out.write("\t\tstripeRows: false,\r\n");
      out.write("\t\tremoteFilter:true,\r\n");
      out.write("\t\tshowIndexColumn:true,\r\n");
      out.write("\t\tselectRowByCheck:true,\r\n");
      out.write("\t\treplaceContainer : true,\r\n");
      out.write("\t\tdataset : dsOption ,\r\n");
      out.write("\t\tcolumns : colsOption,\r\n");
      out.write("\t\ttoolbarContent : 'nav | pagesize | reload  state',\r\n");
      out.write("\t\tpageSize:size,\r\n");
      out.write("\t\tskin:getGridSkin()\r\n");
      out.write("\t};\r\n");
      out.write("\tmygrid=new Sigma.Grid( gridOption );\r\n");
      out.write("\tSigma.Util.onLoad( Sigma.Grid.render(mygrid) );\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"table\">\r\n");
      out.write("\t<div class=\"table-bar\">\r\n");
      out.write("\t\t<div class=\"table-title\">\r\n");
      out.write("\t\t\t表格名称\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"table-ctrl\">\r\n");
      out.write("\t\t\t<a class=\"btn\" href=\"javascript:searchGrid();\"><i class=\"icon icon-search\"></i><span>搜索</span></a>\r\n");
      out.write("\t\t\t<a class=\"btn\" href=\"javascript:expGrid();\"><i class=\"icon icon-list-alt\"></i><span>导出</span></a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"table-wrapper\" id=\"myContent\" style=\"height:550px;\">\r\n");
      out.write("\t\t<div class=\"ui-layout-center\" style=\"overflow:hidden;border:0px;\">\r\n");
      out.write("\t\t\t<div id=\"gridbox\" ></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 搜索 -->\r\n");
      out.write("<div id=\"search\" title=\"搜索条件设置\" style=\"display:none;\">\r\n");
      out.write("\t<form action=\"\" id=\"searchForm\" class=\"form\">\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t    <span>公司名称：</span>\r\n");
      out.write("\t\t\t    <input id=\"companyid\" name=\"companyid\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("\t\t\t    <input id=\"companyname\" name=\"companyname\" class=\"inputstyle disabled\" disabled=\"true\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("                <span>部门名称：</span>\r\n");
      out.write("                <input id=\"deptid\" name=\"deptid\" type=\"hidden\" value=\"\"/>\r\n");
      out.write("\t\t\t    <input id=\"deptname\" name=\"deptname\" class=\"inputselectstyle\" onclick=\"chooseMyDeptTree('#deptid','#deptname',callbackDept);\"/>\r\n");
      out.write("\t\t\t    <div class=\"search-trigger\" onclick=\"chooseMyDeptTree('#deptid','#deptname',callbackDept);\"/>\r\n");
      out.write("            </li>\r\n");
      out.write("        </ul>\r\n");
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
      out.write("                <span>姓名：</span>\r\n");
      out.write("                <input id=\"name\" name=\"name\" class=\"inputstyle\"/>\r\n");
      out.write("            </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("                <span>员工工号：</span>\r\n");
      out.write("                <input id=\"jobnumber\" name=\"jobnumber\" class=\"inputstyle\"/>\r\n");
      out.write("            </li>\r\n");
      out.write("        </ul>\r\n");
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
      out.write("<!-- 岗位授权 -->\r\n");
      out.write("<div id=\"roleAudit\" style=\"display:none;\" title=\"岗位授权\" >\r\n");
      out.write("\t<ul id=\"roleTree\" class=\"ztree\"></ul>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("//岗位授权\r\n");
      out.write("var roleTree;\r\n");
      out.write("function openRole(userguid){\r\n");
      out.write("\t$(\"#roleAudit\").dialog({\r\n");
      out.write("\t\tautoOpen: true,\r\n");
      out.write("\t\tmodal: true,\r\n");
      out.write("\t\tmodel:true,\r\n");
      out.write("\t\tresizable:false,\r\n");
      out.write("\t\twidth:400,\r\n");
      out.write("\t\theight:500,\r\n");
      out.write("\t\tbuttons: {\r\n");
      out.write("\t\t\t\"确定\": function() {\r\n");
      out.write("\t\t\t\tvar nodes=roleTree.getCheckedNodes(true);\r\n");
      out.write("\t\t\t\tvar array=new Array();\r\n");
      out.write("\t\t\t\tfor(var i=0;i<nodes.length;i++){\r\n");
      out.write("\t\t\t\t\tvar node=nodes[i];\r\n");
      out.write("\t\t\t\t\tif(node.id!=null&&node.id!=''){\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\tvar obj={};\r\n");
      out.write("\t\t\t\t\t\tobj.roleid=node.id\r\n");
      out.write("\t\t\t\t\t\tobj.userguid=userguid;\r\n");
      out.write("\t\t\t\t\t\tarray.push(obj);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//参数\r\n");
      out.write("\t\t\t\tvar data={userguid:userguid,list:array};\r\n");
      out.write("\t\t\t\t$.ajax({  \r\n");
      out.write("\t\t\t        url: \"system/saveUserRole.do\",  \r\n");
      out.write("\t\t\t        contentType: \"application/json; charset=utf-8\",  \r\n");
      out.write("\t\t\t        type: \"POST\",  \r\n");
      out.write("\t\t\t        dataType: \"json\",  \r\n");
      out.write("\t\t\t        data: JSON.stringify(data),\r\n");
      out.write("\t\t\t        success: function(result) { \r\n");
      out.write("\t\t\t        \talert('授权成功！');\r\n");
      out.write("\t\t\t        \t$(\"#roleAudit\").dialog(\"close\");\r\n");
      out.write("\t\t\t        \tmygrid.reload();\r\n");
      out.write("\t\t\t        }\r\n");
      out.write("\t\t\t    });\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t\"取消\": function() {\r\n");
      out.write("\t\t\t\t$(this).dialog(\"close\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tclose: function() {\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\topen:function(){\r\n");
      out.write("\t\t\t$(\"#roleTree\").html(null);\r\n");
      out.write("\t\t    $.getJSON(\"system/buildRoleCheckTree.do\",{userguid:userguid}, function(tdata) {\r\n");
      out.write("\t\t    \t//配置项\r\n");
      out.write("\t\t    \tvar setting = {check: {\r\n");
      out.write("\t\t\t\t\tenable: true\r\n");
      out.write("\t\t\t\t}};\r\n");
      out.write("\t\t    \troleTree = $.fn.zTree.init($(\"#roleTree\"),setting, tdata);\r\n");
      out.write("\t\t    });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 公司授权 -->\r\n");
      out.write("<div id=\"companyAudit\" style=\"display:none;\" title=\"公司授权\" >\r\n");
      out.write("\t<ul id=\"companyTree\" class=\"ztree\"></ul>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("//公司树\r\n");
      out.write("var companyTree;\r\n");
      out.write("function openCompany(userguid){\r\n");
      out.write("\t$(\"#companyAudit\").dialog({\r\n");
      out.write("\t\tautoOpen: true,\r\n");
      out.write("\t\tmodal: true,\r\n");
      out.write("\t\tmodel:true,\r\n");
      out.write("\t\tresizable:false,\r\n");
      out.write("\t\twidth:400,\r\n");
      out.write("\t\theight:500,\r\n");
      out.write("\t\tbuttons: {\r\n");
      out.write("\t\t\t\"确定\": function() {\r\n");
      out.write("\t\t\t\tvar nodes=companyTree.getCheckedNodes(true);\r\n");
      out.write("\t\t\t\tvar array=new Array();\r\n");
      out.write("\t\t\t\tfor(var i=0;i<nodes.length;i++){\r\n");
      out.write("\t\t\t\t\tvar node=nodes[i];\r\n");
      out.write("\t\t\t\t\tif(node.id!=null&&node.id!=''){\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\tvar obj={};\r\n");
      out.write("\t\t\t\t\t\tobj.userguid=userguid;\r\n");
      out.write("\t\t\t\t\t\tobj.companyid=node.id;\r\n");
      out.write("\t\t\t\t\t\tobj.isdefault=1;\r\n");
      out.write("\t\t\t\t\t\tarray.push(obj);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//参数\r\n");
      out.write("\t\t\t\tvar data={userguid:userguid,list:array};\r\n");
      out.write("\t\t\t\t$.ajax({  \r\n");
      out.write("\t\t\t        url: \"system/saveUserCompany.do\",  \r\n");
      out.write("\t\t\t        contentType: \"application/json; charset=utf-8\",  \r\n");
      out.write("\t\t\t        type: \"POST\",  \r\n");
      out.write("\t\t\t        dataType: \"json\",  \r\n");
      out.write("\t\t\t        data: JSON.stringify(data),\r\n");
      out.write("\t\t\t        success: function(result) { \r\n");
      out.write("\t\t\t        \talert('授权成功！');\r\n");
      out.write("\t\t\t        \t$(\"#companyAudit\").dialog(\"close\");\r\n");
      out.write("\t\t\t        }\r\n");
      out.write("\t\t\t    });\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t\"取消\": function() {\r\n");
      out.write("\t\t\t\t$(this).dialog(\"close\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tclose: function() {\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\topen:function(){\r\n");
      out.write("\t\t\t$(\"#companyTree\").html(null);\r\n");
      out.write("\t\t    $.getJSON(\"system/buildCompanyCheckTree.do\",{userguid:userguid}, function(tdata) {\r\n");
      out.write("\t\t    \t//配置项\r\n");
      out.write("\t\t    \tvar setting = {check: {\r\n");
      out.write("\t\t\t\t\tenable: true,\r\n");
      out.write("\t\t\t\t\tchkboxType: { \"Y\": \"\", \"N\": \"\" }\r\n");
      out.write("\t\t\t\t}};\r\n");
      out.write("\t\t    \tcompanyTree = $.fn.zTree.init($(\"#companyTree\"),setting, tdata);\r\n");
      out.write("\t\t    });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 通讯录授权 -->\r\n");
      out.write("<div id=\"addressAudit\" style=\"display:none;\" title=\"通讯录授权\" >\r\n");
      out.write("\t<ul id=\"addressTree\" class=\"ztree\"></ul>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("//通讯录树\r\n");
      out.write("var addressTree;\r\n");
      out.write("function openAddress(userguid){\r\n");
      out.write("\t$(\"#addressAudit\").dialog({\r\n");
      out.write("\t\tautoOpen: true,\r\n");
      out.write("\t\tmodal: true,\r\n");
      out.write("\t\tmodel:true,\r\n");
      out.write("\t\tresizable:false,\r\n");
      out.write("\t\twidth:400,\r\n");
      out.write("\t\theight:500,\r\n");
      out.write("\t\tbuttons: {\r\n");
      out.write("\t\t\t\"确定\": function() {\r\n");
      out.write("\t\t\t\tvar array=new Array();\r\n");
      out.write("\t\t\t\tvar checknodes=addressTree.getCheckedNodes(true);\r\n");
      out.write("\t\t\t\tvar unchecknodes=addressTree.getCheckedNodes(false);\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif(unchecknodes.length==0){\r\n");
      out.write("\t\t\t\t\tvar obj={};\r\n");
      out.write("\t\t\t\t\tobj.userguid=userguid;\r\n");
      out.write("\t\t\t\t\tarray.push(obj);\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tfor(var i=0;i<checknodes.length;i++){\r\n");
      out.write("\t\t\t\t\t\tvar node=checknodes[i];\r\n");
      out.write("\t\t\t\t\t\tif(node.id!=null&&node.id!=''){\r\n");
      out.write("\t\t\t\t\t\t\tvar obj={};\r\n");
      out.write("\t\t\t\t\t\t\tobj.userguid=userguid;\r\n");
      out.write("\t\t\t\t\t\t\tobj.companyid=node.id;\r\n");
      out.write("\t\t\t\t\t\t\tarray.push(obj);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t//参数\r\n");
      out.write("\t\t\t\tvar data={userguid:userguid,list:array};\r\n");
      out.write("\t\t\t\t$.ajax({  \r\n");
      out.write("\t\t\t        url: \"system/saveUserAddressCompany.do\",  \r\n");
      out.write("\t\t\t        contentType: \"application/json; charset=utf-8\",  \r\n");
      out.write("\t\t\t        type: \"POST\",  \r\n");
      out.write("\t\t\t        dataType: \"json\",  \r\n");
      out.write("\t\t\t        data: JSON.stringify(data),\r\n");
      out.write("\t\t\t        success: function(result) { \r\n");
      out.write("\t\t\t        \talert('授权成功！');\r\n");
      out.write("\t\t\t        \t$(\"#addressAudit\").dialog(\"close\");\r\n");
      out.write("\t\t\t        }\r\n");
      out.write("\t\t\t    });\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t\"取消\": function() {\r\n");
      out.write("\t\t\t\t$(this).dialog(\"close\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tclose: function() {\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\topen:function(){\r\n");
      out.write("\t\t\t$(\"#addressTree\").html(null);\r\n");
      out.write("\t\t    $.getJSON(\"system/buildAddressCompanyCheckTree.do\",{userguid:userguid}, function(tdata) {\r\n");
      out.write("\t\t    \t//配置项\r\n");
      out.write("\t\t    \tvar setting = {check: {\r\n");
      out.write("\t\t\t\t\tenable: true,\r\n");
      out.write("\t\t\t\t\tchkboxType: { \"Y\": \"s\", \"N\": \"\" }\r\n");
      out.write("\t\t\t\t}};\r\n");
      out.write("\t\t    \taddressTree = $.fn.zTree.init($(\"#addressTree\"),setting, tdata);\r\n");
      out.write("\t\t    });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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