/**
 * 
 */
package cn.log.app.report.web;

import cn.log.app.report.test.Test;
import cn.log.tool.util.ActionHelp;
import cn.log.tool.web.AbstractBaseAction;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author zouqone
 * @date 2014-5-18 下午10:47:50 
 * @Description: TODO
 */
public class ReportAction extends AbstractBaseAction implements
ModelDriven<Object>{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -4936313422817234057L;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void exampleReport(){
		Test t = new Test();
		String rootPath = request.getRealPath("");
		String rp = t.makeReport(rootPath);
		System.out.println(rp);
		ActionHelp.WriteStrToOut(response, rp);
	}

}
