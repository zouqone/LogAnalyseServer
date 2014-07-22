/**
 * 
 */
package cn.log.app.file.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;

import cn.log.tool.util.ActionHelp;
import cn.log.tool.web.AbstractBaseAction;

/**
 * @author zouqone
 * @date 2014年7月18日 下午9:51:25
 */
public class FileAction extends AbstractBaseAction{
	private List<File> fileUpload;  
    private List<String> fileUploadFileName;  
    private List<String> fileUploadContentType;  
    
	
	private static final long serialVersionUID = 1L;

	/**
	 * 普通文件上传
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public String fileUpload() throws IOException{
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8"); 
		
		String message = "";
		for(int i = 0;i<fileUpload.size();i++ ){  
            InputStream is = new FileInputStream(this.fileUpload.get(i));  
            String path = this.request.getRealPath("/upload");  
            OutputStream os = new FileOutputStream(new File(path,this.fileUploadFileName.get(i)));  
            byte[] b = new byte[1024*200];  
            int length = 0;  
            while((length = is.read(b))>0){  
                os.write(b, 0, length);  
            }  
            System.out.println("path = " + path);  
            System.out.println("fileName : " + this.fileUploadFileName.get(i) + "  contentType : " +this.fileUploadContentType.get(i));  
            os.close();  
            is.close();  
            message += this.fileUploadFileName.get(i)+" ";
        }
        request.setAttribute("message", message);
        //ActionHelp.WriteStrToOut(response, message);
        return "success";
	}
	
	/**
	 * Ajax文件上传
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public void struts2FileUpload() throws IOException{
		String message = "";
		for(int i = 0;i<fileUpload.size();i++ ){  
            InputStream is = new FileInputStream(this.fileUpload.get(i));  
            String path = this.request.getRealPath("/upload");  
            OutputStream os = new FileOutputStream(new File(path,this.fileUploadFileName.get(i)));  
            byte[] b = new byte[1024*200];  
            int length = 0;  
            while((length = is.read(b))>0){  
                os.write(b, 0, length);  
            }  
            System.out.println("path = " + path);  
            System.out.println("fileName : " + this.fileUploadFileName.get(i) + "  contentType : " +this.fileUploadContentType.get(i));  
            os.close();  
            is.close();  
            message += this.fileUploadFileName.get(i)+" ";
        }   
		ActionHelp.WriteStrToOut(response, message);
	}

	/**
	 * @return the fileUpload
	 */
	public List<File> getFileUpload() {
		return fileUpload;
	}

	/**
	 * @param fileUpload the fileUpload to set
	 */
	public void setFileUpload(List<File> fileUpload) {
		this.fileUpload = fileUpload;
	}

	/**
	 * @return the fileUploadFileName
	 */
	public List<String> getFileUploadFileName() {
		return fileUploadFileName;
	}

	/**
	 * @param fileUploadFileName the fileUploadFileName to set
	 */
	public void setFileUploadFileName(List<String> fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	/**
	 * @return the fileUploadContentType
	 */
	public List<String> getFileUploadContentType() {
		return fileUploadContentType;
	}

	/**
	 * @param fileUploadContentType the fileUploadContentType to set
	 */
	public void setFileUploadContentType(List<String> fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
	
	
}
