/**
 * 
 */
package cn.log.tool.util;

/**
 * @author zouqone
 * date 2014年5月15日   下午7:28:15
 *
 */
public class FileHelp {

	/**
	 * 
	 */
	public FileHelp() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 格式化转化数据类型Long为String类型
	 * 
	 * @param flsize
	 * @return
	 */
	public static String FormatSize(long flsize) {
		String fsize = null;
		if (flsize > 1024 * 1024 * 1024) {
			fsize = String.valueOf(flsize / (1024 * 1024 * 1024)) + "GB";
		} else if (flsize > 1024 * 1024) {
			fsize = String.valueOf(flsize / (1024 * 1024)) + "MB";
		} else if (flsize > 1024) {
			fsize = String.valueOf(flsize / 1024) + "KB";
		} else {
			fsize = String.valueOf(flsize) + "B";
		}
		return fsize;
	}
}
