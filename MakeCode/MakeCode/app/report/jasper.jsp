<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSON"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.data.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.view.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="cn.log.db.util.JdbcUtils" %>
<%@ page import="java.io.*" %>
<%@ page import="org.dom4j.*" %>
<%@ page import="org.dom4j.io.*" %>
<%@ page import="cn.log.tool.util.*" %>
<%
String root = "/upload/";
String type = request.getParameter("type");
String paramStr = request.getParameter("params");
String jrxml = request.getParameter("report");
JasperReport jasperReport = null;
JasperPrint jasperPrint = null;
JasperReportsContext jasperReportsContext = null;
JasperRunManager jasperRunManager = null;

Connection conn = JdbcUtils.getConnection();
Map<String, Object> params = new HashMap<String, Object>();
if(paramStr!=null&&!paramStr.trim().equals("")){
	JSONObject jsonObject = JSONObject.fromObject(paramStr);
	if(jsonObject!=null){
		for(Object key : jsonObject.keySet()){
			Object value = jsonObject.get(key);
			params.put(key.toString(), value);
			System.out.print(key+" : "+value+"  ");
		}
	}
	System.out.println("");
}
//params.put("parentncode", "rootdir");

InputStream in = null;
OutputStream os = null;

if(jrxml==null){
	jrxml = "test";
}
try {
	String real = application.getRealPath(root+jrxml+".jrxml");
	//Document document = XmlHelp.ireportEx(real);
	
	//InputStream is = new ByteArrayInputStream(document.asXML().getBytes("utf-8"));
	//jasperReport=JasperCompileManager.compileReport(is);
	jasperReport=JasperCompileManager.compileReport(real);
	//jasperReport=JasperCompileManager.compileReport("C:/Users/Administrator/Documents/report/ireport/test.jrxml");
	
	// 填充数据，生成打印文件
	jasperPrint = JasperFillManager.fillReport(jasperReport, params,conn);

	os = response.getOutputStream();
	
	if("pdf".equals(type)){
		response.setContentType("application/pdf;charset=utf-8");
		/* byte[] bytes =null;
		bytes = JasperExportManager.exportReportToPdf(jasperPrint);
		
		response.setContentLength(bytes.length);
		os = response.getOutputStream();
		os.write(bytes, 0, bytes.length);	*/
		JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,os );
        exporter.exportReport();
	}else if("xls".equals(type)){
		String fileName = new String("test.xls".getBytes("gbk"), "utf-8");
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);  
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,os);  
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);  
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);  
        //exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);  
	    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
	    exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
	    exporter.exportReport();  
	}else if("doc".equals(type)){
		String fileName = new String("test.doc".getBytes("gbk"), "utf-8");
		response.setContentType("application/msword;charset=utf-8");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);  
		
		JRExporter exporter = new JRRtfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);     
        exporter.exportReport();
	}else if("print".equals(type)){
		//response.setContentType("application/pdf;charset=utf-8");
		//response.setContentType("text/html; charset=utf-8");
		JasperViewer view = new JasperViewer(jasperPrint,false);
		view.setVisible(true);
		//JasperPrintManager.printReport(jasperPrint, true);
		//JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
	}else{
		response.setContentType("text/html; charset=utf-8");
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
		exporter.exportReport();
	}
	out.clear();
	out=pageContext.pushBody();
} catch (JRException e) {
	e.printStackTrace();
	System.out.println(e);
}finally{

	if(os!=null){
		os.flush();
		os.close();
	}
}


%>