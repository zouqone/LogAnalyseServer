/**
 * 
 */
package cn.log.app.database.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import cn.log.app.database.service.IDBService;
import cn.log.app.database.vo.DatabaseVo;
import cn.log.tool.util.ActionHelp;
import cn.log.tool.util.FileHelp;
import cn.log.tool.web.AbstractBaseAction;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author zouqone date 2014年5月11日 下午4:32:37
 * 
 */
public class DBAction extends AbstractBaseAction implements
		ModelDriven<DatabaseVo> {

	/**
	 * 
	 */
	private static long serialVersionUID = 1L;

	public IDBService mysqlDBService;
	
	public DatabaseVo databaseVo;
	
	
	
	/**
	 * 
	 */
	public DBAction() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public DatabaseVo getModel() {
		// TODO Auto-generated method stub
		if(databaseVo == null){
			databaseVo = new DatabaseVo();
		}
		return databaseVo;
	}

	public void getDBInfo(){
		String dbInfoStr = request.getParameter("dbInfo");
		String dbName = request.getParameter("pCode");
		String jsonString = "hi";
		if(dbName == null){
			return ;
		}
		databaseVo.setDbName(dbName);
		DatabaseVo db = mysqlDBService.findDBInfo(databaseVo);
		jsonString = ActionHelp.beanToStr(db);
		ActionHelp.WriteStrToOut(response, jsonString);
	}
	
	/**
	 * 获取所有的数据库名
	 * @param databaseVo
	 * @return
	 */
	public void getAllDBName(){
		List<String> dbNameList = mysqlDBService.getAllDBName(databaseVo);
		String jsonString = "";
		jsonString = dbNameList.toString();//ActionHelp.beanListToStr(dbNameList);
		jsonString = jsonString.substring(1,jsonString.length()-1);
		ActionHelp.WriteStrToOut(response, jsonString);
	}
	
	public void getFiles(){
		String path = request.getRealPath("/")+request.getParameter("path");
		File file = new File(path);
		StringBuffer fileStr = new StringBuffer();
		if(file!=null&&file.exists()){
			File[] files = file.listFiles();
			fileStr.append("[");
			int k = 0;
			for (File fs : files) {
				if(k>0){
					fileStr.append(",");
				}
				fileStr.append("{");
				fileStr.append("name:'").append(fs.getName()).append("',");
				fileStr.append("size:'").append(FileHelp.FormatSize(fs.length())).append("'");
				fileStr.append("}");
				k++;
			}
			fileStr.append("]");
		}
		ActionHelp.WriteStrToOut(response, fileStr.toString());
	}
	public void down(){
		String path = request.getRealPath("/")+request.getParameter("path");
		File file = new File(path);
		String outFileName= file.getName();
		
		InputStream in = null;
		OutputStream os = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			outFileName = new String(outFileName.getBytes("GB2312"), "ISO-8859-1"); 
			response.reset();
			response.setHeader("Content_Length", String.valueOf(file.length()));
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename="+outFileName);
			//service.makeZipFile(os, fileNames, ins);
			os = response.getOutputStream();
			in = new FileInputStream(file);
			bis = new BufferedInputStream(in);
			bos =  new BufferedOutputStream(os);
			
			 byte[] buff = new byte[2048];
	         int bytesRead;
	         while(-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	             bos.write(buff,0,bytesRead);
	         }
			
		}catch (Exception e) {
			// TODO: handle exception
			 System.out.println ( "出现IOException." + e );
		}finally{
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param serialversionuid the serialversionuid to set
	 */
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	/**
	 * @return the mysqlDBService
	 */
	public IDBService getMysqlDBService() {
		return mysqlDBService;
	}

	/**
	 * @param mysqlDBService the mysqlDBService to set
	 */
	public void setMysqlDBService(IDBService mysqlDBService) {
		this.mysqlDBService = mysqlDBService;
	}

	/**
	 * @return the databaseVo
	 */
	public DatabaseVo getDatabaseVo() {
		return databaseVo;
	}

	/**
	 * @param databaseVo the databaseVo to set
	 */
	public void setDatabaseVo(DatabaseVo databaseVo) {
		this.databaseVo = databaseVo;
	}

	
}
