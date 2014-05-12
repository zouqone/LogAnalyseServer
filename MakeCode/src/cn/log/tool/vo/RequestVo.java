/**
 * 
 */
package cn.log.tool.vo;

import java.util.Enumeration;

/**
 * @author Administrator
 *
 */
public class RequestVo {

	/**
	 * 请求的方法POST,GET
	 */
	private String method;
	
	/**
	 * 请求的URI
	 */
	private String URI;
	
	/**
	 * 接受客户提交信息的页面路径
	 */
	private String servletPath;
	
	/**
	 * 请求的协议
	 */
	private String protocol;

	/**
	 * 请求中的查询字符串
	 */
	private String queryString;

	/**
	 * 请求信息的总长度
	 */
	private int contentLength;

	/**
	 * 服务器名称
	 */
	private String serverName;

	/**
	 * 提供HTTP服务的服务器端口号
	 */
	private String port;

	/**
	 * 客户端IP地址
	 */
	private String remoteAddr;

	/**
	 * 客户端机器的名称
	 */
	private String remoteHost;

	/**
	 * HTTP头文件中User-Agent的值
	 */
	private String headerUserAgent;

	/**
	 * HTTP头文件中accept的值
	 */
	private String headerAccept;

	/**
	 * HTTP头文件中Host的值
	 */
	private String headerHost;

	/**
	 * HTTP头文件中accept-encoding的值
	 */
	private String headerAcceptEncoding;

	/**
	 * 头名字的一个枚举
	 */
	private Enumeration enumHead;
	
	
	/**
	 * 
	 */
	public RequestVo() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param method
	 * @param uRI
	 * @param servletPath
	 * @param protocol
	 * @param queryString
	 * @param contentLength
	 * @param serverName
	 * @param port
	 * @param remoteAddr
	 * @param remoteHost
	 * @param headerUserAgent
	 * @param headerAccept
	 * @param headerHost
	 * @param headerAcceptEncoding
	 * @param enumHead
	 */
	public RequestVo(String method, String uRI, String servletPath,
			String protocol, String queryString, int contentLength,
			String serverName, String port, String remoteAddr,
			String remoteHost, String headerUserAgent, String headerAccept,
			String headerHost, String headerAcceptEncoding, Enumeration enumHead) {
		this.method = method;
		URI = uRI;
		this.servletPath = servletPath;
		this.protocol = protocol;
		this.queryString = queryString;
		this.contentLength = contentLength;
		this.serverName = serverName;
		this.port = port;
		this.remoteAddr = remoteAddr;
		this.remoteHost = remoteHost;
		this.headerUserAgent = headerUserAgent;
		this.headerAccept = headerAccept;
		this.headerHost = headerHost;
		this.headerAcceptEncoding = headerAcceptEncoding;
		this.enumHead = enumHead;
	}


	/**
	 * 打印头名字
	 */
	public void printEnumHead(){
		if(this.enumHead!=null){
			printEnumHead(enumHead);
		}
	}
	
	/**
	 * 打印头名字
	 * @param enumHead
	 */
	public void printEnumHead(Enumeration enumHead){
		if(enumHead!=null){
			while(enumHead.hasMoreElements()){
				System.out.println(enumHead.nextElement().toString());
			}
		}
	}
	
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}


	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}


	/**
	 * @return the uRI
	 */
	public String getURI() {
		return URI;
	}


	/**
	 * @param uRI the uRI to set
	 */
	public void setURI(String uRI) {
		URI = uRI;
	}


	/**
	 * @return the servletPath
	 */
	public String getServletPath() {
		return servletPath;
	}


	/**
	 * @param servletPath the servletPath to set
	 */
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}


	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}


	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}


	/**
	 * @return the queryString
	 */
	public String getQueryString() {
		return queryString;
	}


	/**
	 * @param queryString the queryString to set
	 */
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}


	/**
	 * @return the contentLength
	 */
	public int getContentLength() {
		return contentLength;
	}


	/**
	 * @param contentLength the contentLength to set
	 */
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}


	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}


	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}


	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}


	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}


	/**
	 * @return the remoteAddr
	 */
	public String getRemoteAddr() {
		return remoteAddr;
	}


	/**
	 * @param remoteAddr the remoteAddr to set
	 */
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}


	/**
	 * @return the remoteHost
	 */
	public String getRemoteHost() {
		return remoteHost;
	}


	/**
	 * @param remoteHost the remoteHost to set
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}


	/**
	 * @return the headerUserAgent
	 */
	public String getHeaderUserAgent() {
		return headerUserAgent;
	}


	/**
	 * @param headerUserAgent the headerUserAgent to set
	 */
	public void setHeaderUserAgent(String headerUserAgent) {
		this.headerUserAgent = headerUserAgent;
	}


	/**
	 * @return the headerAccept
	 */
	public String getHeaderAccept() {
		return headerAccept;
	}


	/**
	 * @param headerAccept the headerAccept to set
	 */
	public void setHeaderAccept(String headerAccept) {
		this.headerAccept = headerAccept;
	}


	/**
	 * @return the headerHost
	 */
	public String getHeaderHost() {
		return headerHost;
	}


	/**
	 * @param headerHost the headerHost to set
	 */
	public void setHeaderHost(String headerHost) {
		this.headerHost = headerHost;
	}


	/**
	 * @return the headerAcceptEncoding
	 */
	public String getHeaderAcceptEncoding() {
		return headerAcceptEncoding;
	}


	/**
	 * @param headerAcceptEncoding the headerAcceptEncoding to set
	 */
	public void setHeaderAcceptEncoding(String headerAcceptEncoding) {
		this.headerAcceptEncoding = headerAcceptEncoding;
	}


	/**
	 * @return the enumHead
	 */
	public Enumeration getEnumHead() {
		return enumHead;
	}


	/**
	 * @param enumHead the enumHead to set
	 */
	public void setEnumHead(Enumeration enumHead) {
		this.enumHead = enumHead;
	}

	
}
