/**
 * 
 */
package cn.log.app.report.test;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import cn.log.db.util.JdbcUtils;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

/**
 * @author zouqone
 * @date 2014-5-18 下午11:43:18
 * @Description: TODO 生成报表
 */
public class Test {

	public String  makeReport(String rootPath) {

		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;

		Connection conn = JdbcUtils.getConnection();
		Map<String, Object> params = new HashMap<String, Object>();

		String jrxmlPath = "/upload/test.jrxml";
		String reportPath = "/app/report/"+"test.html";
		String pdf = "/app/report/"+"test.pdf";
		try {
			
			params.put("parentncode", "rootdir");
			
			// 获取模板文件
			jasperReport = JasperCompileManager.compileReport(rootPath+jrxmlPath);

			// 填充数据，生成打印文件
			jasperPrint = JasperFillManager.fillReport(jasperReport, params,conn);

			// 生成PDF文件
			JasperExportManager.exportReportToPdfFile(jasperPrint,rootPath+pdf);
					

			// 生成html文件
			JasperExportManager.exportReportToHtmlFile(jasperPrint,rootPath+reportPath);
					
		} catch (JRException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return reportPath;
	}

}
