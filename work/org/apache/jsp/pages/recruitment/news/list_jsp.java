package org.apache.jsp.pages.recruitment.news;

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
      out.write("<title>资讯管理</title>\r\n");
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
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var moduleguid='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.moduleguid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\r\n");
      out.write("$(document).ready(function() {\r\n");
      out.write("\t//加载模块\r\n");
      out.write("\tloadNewsModule();\r\n");
      out.write("\t\r\n");
      out.write("\t//加载信息\r\n");
      out.write("\tloadGrid();\r\n");
      out.write("\t\r\n");
      out.write("\t//日期\r\n");
      out.write("    $(\".datepicker\").datepicker({\r\n");
      out.write("        dateFormat: 'yy-mm-dd',\r\n");
      out.write("       \tbeforeShow: function(input, inst) {\r\n");
      out.write("   \t\t\tif($(input).attr(\"readonly\"))\r\n");
      out.write("   \t\t\t\tinst.inline=true;\r\n");
      out.write("   \t\t}\r\n");
      out.write("    });\r\n");
      out.write("  \t\r\n");
      out.write("\t//按钮hover\r\n");
      out.write("    $('.more a').hover(\r\n");
      out.write("\t\tfunction() {$(this).addClass('ui-state-hover');}, \r\n");
      out.write("\t\tfunction() {$(this).removeClass('ui-state-hover');}\r\n");
      out.write("\t);\r\n");
      out.write("\t\r\n");
      out.write("  //关闭等待层\r\n");
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
      out.write("\r\n");
      out.write("//加载模块\r\n");
      out.write("function loadNewsModule(){\r\n");
      out.write("\t$.getJSON(\"recruitment/getNewsModuleById.do\",{id:moduleguid}, function(data) {\r\n");
      out.write("\t\tif(data!=null){\r\n");
      out.write("\t\t\t$(\".modulename\").text(data.modulename);\r\n");
      out.write("\t\t}\r\n");
      out.write("    });\r\n");
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
      out.write("//参数设置\r\n");
      out.write("var pam=null;\r\n");
      out.write("function initPagePam(){\r\n");
      out.write("\tpam={};\r\n");
      out.write("\tpam.expAll=0;\r\n");
      out.write("\tpam.title=$(\"#title\").val();\r\n");
      out.write("\tpam.moduleguid=moduleguid;\r\n");
      out.write("\tpam.valid=$(\"#valid\").attr(\"checked\")?1:0;\r\n");
      out.write("\tpam.isaudited=$(\"#isaudited\").attr(\"checked\")?1:'';\r\n");
      out.write("\tpam.modtime_s=$(\"#modtime_s\").val();\r\n");
      out.write("\tpam.modtime_e=$(\"#modtime_e\").val();\r\n");
      out.write("\tpam.keyword=$(\"#keyword\").val();\r\n");
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
      out.write("//失效、还原\r\n");
      out.write("function validNews(valid){\r\n");
      out.write("\tvar prompt=(valid==0?\"失效\":\"恢复\");\r\n");
      out.write("\tvar array=new Array();\r\n");
      out.write("\tvar cords=mygrid.getSelectedRecords();\r\n");
      out.write("\tfor(var i=0;i<cords.length;i++){\r\n");
      out.write("\t\tvar obj=cords[i];\r\n");
      out.write("\t\tif(obj.valid!=valid)\r\n");
      out.write("\t\t\tarray.push(obj.newsguid);\r\n");
      out.write("\t}\r\n");
      out.write("\tif(array.length<=0){\r\n");
      out.write("\t\talert(\"请选择要\"+prompt+\"的数据！\");\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(!confirm(\"确认要\"+prompt+\"选中数据吗？\")){\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("   \t$.post(\"recruitment/validNewsById.do\",{ids:array.toString(),state:valid}, function() {\r\n");
      out.write("\t\tmygrid.reload();\r\n");
      out.write("    });\r\n");
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
      out.write("//删除\r\n");
      out.write("function remove(){\r\n");
      out.write("\tvar array=new Array();\r\n");
      out.write("\tvar cords=mygrid.getSelectedRecords();\r\n");
      out.write("\tfor(var i=0;i<cords.length;i++){\r\n");
      out.write("\t\tarray.push(cords[i].newsguid);\r\n");
      out.write("\t}\r\n");
      out.write("\tif(array.length<=0){\r\n");
      out.write("\t\talert('请选择要删除的数据！');\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(!confirm('确认要删除选中数据吗？')){\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\t$.post(\"recruitment/delNewsById.do\",{ids:array.toString()}, function() {\r\n");
      out.write("\t\tmygrid.reload();\r\n");
      out.write("    });\r\n");
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
      out.write("//失效、还原\r\n");
      out.write("function validOneNews(id,valid){\r\n");
      out.write("\tvar prompt=(valid==0?\"失效\":\"恢复\");\r\n");
      out.write("\tvar array=new Array();\r\n");
      out.write("\tarray.push(id);\r\n");
      out.write("\tif(!confirm(\"确认要\"+prompt+\"选中数据吗？\")){\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("   \t$.post(\"recruitment/validNewsById.do\",{ids:array.toString(),state:valid}, function() {\r\n");
      out.write("\t\tmygrid.reload();\r\n");
      out.write("    });\r\n");
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
      out.write("//批量审核\r\n");
      out.write("function audit(valid){\r\n");
      out.write("\tvar prompt=(valid==0?\"审核发布\":\"取消发布\");\r\n");
      out.write("\tvar array=new Array();\r\n");
      out.write("\tvar cords=mygrid.getSelectedRecords();\r\n");
      out.write("\tfor(var i=0;i<cords.length;i++){\r\n");
      out.write("\t\tvar obj=cords[i];\r\n");
      out.write("\t\tif(obj.isaudited!=valid)\r\n");
      out.write("\t\t\tarray.push(obj.newsguid);\r\n");
      out.write("\t}\r\n");
      out.write("\tif(array.length<=0){\r\n");
      out.write("\t\talert(\"请选择要\"+prompt+\"的数据！\");\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(!confirm(\"确认要\"+prompt+\"选中数据吗？\")){\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("   \t$.post(\"recruitment/auditNewsById.do\",{ids:array.toString(),state:valid}, function() {\r\n");
      out.write("\t\tmygrid.reload();\r\n");
      out.write("    });\r\n");
      out.write("\t\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function add(){\r\n");
      out.write("\tconvertView('news.do?page=form&moduleguid='+moduleguid);\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\t\r\n");
      out.write("var mygrid=null;\r\n");
      out.write("function loadGrid(){\r\n");
      out.write("\tvar size=getGridSize();\r\n");
      out.write("\tvar grid_demo_id = \"myGrid1\";\r\n");
      out.write("\tvar dsOption= {\r\n");
      out.write("\t\t\tfields :[\r\n");
      out.write("\t\t\t\t{name : 'newsguid'},\r\n");
      out.write("\t\t\t\t{name : 'moduleguid'},\r\n");
      out.write("\t\t\t\t{name : 'title'},\r\n");
      out.write("\t\t\t\t{name : 'subtitle'},\r\n");
      out.write("\t\t\t\t{name : 'source'},\r\n");
      out.write("\t\t\t\t{name : 'keyword'},\r\n");
      out.write("\t\t\t\t{name : 'valid'},\r\n");
      out.write("\t\t\t\t{name : 'isaudited'},\r\n");
      out.write("\t\t\t\t{name : 'traffic'},\r\n");
      out.write("\t\t\t\t{name : 'titlepic'},\r\n");
      out.write("\t\t\t\t{name : 'audituser'},\r\n");
      out.write("\t\t\t\t{name : 'pubuser'},\r\n");
      out.write("\t\t\t\t{name : 'modtime'},\r\n");
      out.write("\t\t\t\t{name : 'rmk'}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t]\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\tvar colsOption = [\r\n");
      out.write("\t\t\t{id: '选择' ,isCheckColumn : true, editable:false,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'valid' , header: \"操作\" , width :80 ,headAlign:'center',align:'center',hidden:true,renderer:function(value ,record,colObj,grid,colNo,rowNo){\r\n");
      out.write("\t\t\t\tvar htm='';\r\n");
      out.write("        \t\t\thtm+='<a href=\"javascript:convertView(\\'news.do?page=form&id='+record.newsguid+'&moduleguid='+moduleguid+'\\');\" class=\"ui-button ui-button-icon-only\" title=\"修改\"><span class=\"ui-icon ui-icon-pencil\"></span>&nbsp;</a>';\r\n");
      out.write("       \t\t\tif(value==1)\r\n");
      out.write("\t        \t\thtm+='<a href=\"javascript:validOneNews(\\''+record.newsguid+'\\',0);\" class=\"ui-button ui-button-icon-only\" title=\"置为无效\"><span class=\"ui-icon ui-icon-trash\"></span>&nbsp;</a>';\r\n");
      out.write("\t        \telse if(value==0)\r\n");
      out.write("\t        \t\thtm+='<a href=\"javascript:validOneNews(\\''+record.newsguid+'\\',1);\" class=\"ui-button ui-button-icon-only\" title=\"还原\"><span class=\"ui-icon ui-icon-check\"></span>&nbsp;</a>';\r\n");
      out.write("\t\t\t\treturn htm;\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{id: 'isaudited' , header: \"状态\" , width :80 ,headAlign:'center',align:'center',renderer:function(value ,record,colObj,grid,colNo,rowNo){\r\n");
      out.write("\t\t\t\tif(value==1){\r\n");
      out.write("\t\t\t\t\treturn '未审核';\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\treturn '已审核';\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{id: 'dorder' , header: \"排序号\" , width :50 ,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'title' , header: \"主标题\" , width :300  ,headAlign:'center'},\r\n");
      out.write("\t\t\t{id: 'subtitle' , header: \"副标题\" , width :200 ,headAlign:'center'},\r\n");
      out.write("\t\t\t{id: 'source' , header: \"来源\" , width :120 ,headAlign:'center'},\r\n");
      out.write("\t\t\t{id: 'keyword' , header: \"关键字\" , width :150 ,headAlign:'center'},\r\n");
      out.write("\t\t\t{id: 'traffic' , header: \"点击量\" , width :100,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'istop' , header: \"是否置顶\" , width :100  ,headAlign:'center',align:'center',hidden:true},\r\n");
      out.write("\t\t\t{id: 'iscommend' , header: \"是否推荐\" , width :100  ,headAlign:'center',align:'center',hidden:true},\r\n");
      out.write("\t\t\t{id: 'pubuser' , header: \"发布人\" , width :100 ,headAlign:'center'},\r\n");
      out.write("\t\t\t{id: 'modtime' , header: \"发布时间\" , width :150,headAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{id: 'audituser' , header: \"审核人\" , width :100,headAlign:'center'},\r\n");
      out.write("\t\t\t{id: 'rmk' , header: \"备注\" , width :300 ,headAlign:'center',align:'left'}\r\n");
      out.write("\t\t];\r\n");
      out.write("\t\t\r\n");
      out.write("\tvar gridOption={\r\n");
      out.write("\t\tid : grid_demo_id,\r\n");
      out.write("\t\tloadURL :'recruitment/searchNews.do',\r\n");
      out.write("\t\tbeforeLoad:function(reqParam){\r\n");
      out.write("\t\t\tinitPagePam();\r\n");
      out.write("\t\t\treqParam['parameters']=pam;\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\texportURL : 'recruitment/searchNews.do?export=true',\r\n");
      out.write("\t\tbeforeExport:function(){\r\n");
      out.write("\t\t\tinitPagePam();\r\n");
      out.write("\t\t\tpam.expAll=1;\r\n");
      out.write("\t\t\tmygrid.parameters=pam;\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\texportFileName : '资讯管理表.xls',\r\n");
      out.write("\t\twidth: \"99.8%\",//\"100%\", // 700,\r\n");
      out.write("\t\theight: \"100%\",  //\"100%\", // 330,\r\n");
      out.write("\t\tcontainer : 'gridbox', \r\n");
      out.write("\t\tpageSizeList : [size,size*2,size*4,size*6,size*8,size*10],\r\n");
      out.write("\t\tstripeRows: false,\r\n");
      out.write("\t\tremoteFilter:true,\r\n");
      out.write("\t\tshowIndexColumn:true,\r\n");
      out.write("\t\tselectRowByCheck:true,\r\n");
      out.write("\t\treplaceContainer : true,\r\n");
      out.write("\t\tdataset : dsOption ,\r\n");
      out.write("\t\tcolumns : colsOption,\r\n");
      out.write("\t\ttoolbarContent : 'nav | pagesize  state',\r\n");
      out.write("\t\tpageSize:size,\r\n");
      out.write("\t\tskin:getGridSkin(),\r\n");
      out.write("\t\tcustomRowAttribute : function(record,rn,grid){\r\n");
      out.write("\t\t\t//未审核\r\n");
      out.write("\t\t\tif(record.isaudited==1){\r\n");
      out.write("\t\t\t\treturn 'style=\"background:#ffffec;\"';\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tonDblClickCell:function(value, record , cell, row, colNO, rowNO,columnObj,grid){\r\n");
      out.write("\t\t\tif(colNO!=0){\r\n");
      out.write("\t\t\t\tconvertView('news.do?page=form&moduleguid='+moduleguid+'&id='+record.newsguid);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t};\r\n");
      out.write("\tmygrid=new Sigma.Grid( gridOption );\r\n");
      out.write("\tSigma.Util.onLoad( Sigma.Grid.render(mygrid) );\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<div class=\"sort\">\r\n");
      out.write("\t<div class=\"sort-title\">\r\n");
      out.write("\t\t<h3><span class=\"modulename\"></span></h3>\r\n");
      out.write("\t\t<div class=\"title-ctrl\">\r\n");
      out.write("\t\t\t<a class=\"btn-ctrl\" href=\"javascript:chevronUpDown('.sort-cont',false);\"><i class=\"icon icon-chevron-up\"></i></a>\r\n");
      out.write("\t\t\t<a class=\"btn-ctrl\" href=\"javascript:chevronUpDown('.sort-cont',true);\"><i class=\"icon icon-chevron-down\"></i></a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"sort-cont sort-table\">\r\n");
      out.write("\t\t<div class=\"table\">\r\n");
      out.write("\t\t\t<div class=\"table-bar\">\r\n");
      out.write("\t\t\t\t<div class=\"table-title\">\r\n");
      out.write("\t\t\t\t\t表格名称\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"table-ctrl\">\r\n");
      out.write("\t\t\t\t\t<div style=\"float:left;\">\r\n");
      out.write("\t\t\t\t\t\t<!-- \r\n");
      out.write("\t\t\t\t\t\t<label for=\"valid\">\r\n");
      out.write("\t\t\t\t\t\t\t<input id=\"valid\" type=\"checkbox\" class=\"checkboxstyle\" onclick=\"mygrid.reload();\" checked=\"true\"/>只显示当前有效数据\r\n");
      out.write("\t\t\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t\t\t&nbsp;\r\n");
      out.write("\t\t\t\t\t\t&nbsp;\r\n");
      out.write("\t\t\t\t\t\t -->\r\n");
      out.write("\t\t\t\t\t\t<label for=\"isaudited\">\r\n");
      out.write("\t\t\t\t\t\t\t<input id=\"isaudited\" type=\"checkbox\" class=\"checkboxstyle\" onclick=\"mygrid.reload();\"/>只显示未审核的数据\r\n");
      out.write("\t\t\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t\t\t&nbsp;\r\n");
      out.write("\t\t\t\t\t\t&nbsp;\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<a class=\"btn\" href=\"javascript:searchGrid();\" style=\"display:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${search?'':'none'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"><i class=\"icon icon-search\"></i><span>搜索</span></a>\r\n");
      out.write("\t\t\t\t\t<a class=\"btn\" href=\"javascript:expGrid();\" style=\"display:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${exp?'':'none'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"><i class=\"icon icon-download\" ></i><span>导出</span></a>\r\n");
      out.write("\t\t\t\t\t<a class=\"btn\" href=\"javascript:add();\"  style=\"display:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${add?'':'none'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"><i class=\"icon icon-plus\"></i><span>新增</span></a>\r\n");
      out.write("\t\t\t\t\t<a class=\"btn\"  href=\"javascript:audit(0);\" style=\"display:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${audit?'':'none'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" ><i class=\"icon icon-ok-circle\"></i><span>审核发布</span></a>\r\n");
      out.write("\t\t\t\t\t<a class=\"btn\" href=\"javascript:audit(1);\" style=\"display:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cancel?'':'none'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" ><i class=\"icon icon-ban-circle\"></i><span>取消发布</span></a>\r\n");
      out.write("\t\t\t\t\t<!-- \r\n");
      out.write("\t\t\t\t\t<a class=\"btn\"  href=\"javascript:validNews(0);\" style=\"display:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${valid?'':'none'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" ><i class=\"icon icon-trash\"></i><span>失效</span></a>\r\n");
      out.write("\t\t   \t\t\t<a class=\"btn\"  href=\"javascript:validNews(1);\" style=\"display:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${revert?'':'none'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" ><i class=\"icon icon-retweet\"></i><span>恢复</span></a>\r\n");
      out.write("\t\t   \t\t\t -->\r\n");
      out.write("\t\t\t\t\t<a class=\"btn\" href=\"javascript:remove();\" style=\"display:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${del?'':'none'}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"><i class=\"icon icon-remove\"></i><span>删除</span></a>\r\n");
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
      out.write("<!-- 搜索 -->\r\n");
      out.write("<div id=\"search\" title=\"搜索条件设置\" style=\"display:none;\">\r\n");
      out.write("\t<form action=\"\" id=\"searchForm\" class=\"form\">\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("\t\t\t    <span>主标题：</span>\r\n");
      out.write("\t\t\t    <input id=\"title\" name=\"title\" class=\"inputstyle\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t    <li>\r\n");
      out.write("\t\t\t    <span>关键字：</span>\r\n");
      out.write("\t\t\t    <input id=\"keyword\" name=\"keyword\" class=\"inputstyle\"/>\r\n");
      out.write("\t\t\t</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("                <span>发布日期从：</span>\r\n");
      out.write("                <input id=\"modtime_s\" name=\"modtime_s\" class=\"inputselectstyle datepicker\"/>\r\n");
      out.write("               <div class=\"date-trigger\" onclick=\"$('#modtime_s').focus();\"/>\r\n");
      out.write("            </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("        <ul>\r\n");
      out.write("\t\t\t<li>\r\n");
      out.write("                <span>至：</span>\r\n");
      out.write("\t\t\t\t<input id=\"modtime_e\" name=\"modtime_e\" class=\"inputselectstyle datepicker\"/>\r\n");
      out.write("\t\t\t\t<div class=\"date-trigger\" onclick=\"$('#modtime_e').focus();\"/>\r\n");
      out.write("            </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("\t</form>\r\n");
      out.write("</div>\r\n");
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