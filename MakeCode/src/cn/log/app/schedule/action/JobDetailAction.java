/**
 * 
 */
package cn.log.app.schedule.action;

import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;

import cn.log.app.database.vo.DatabaseVo;
import cn.log.app.schedule.service.IJobDetailService;
import cn.log.tool.util.ActionHelp;
import cn.log.tool.web.AbstractBaseAction;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author zouqone
 * @date 2015年3月10日 上午12:00:32
 */
public class JobDetailAction extends AbstractBaseAction implements
	ModelDriven<JobDetail>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5361380998534865379L;

	private JobDetail jobDetail;
	
	private IJobDetailService jobDetailService;
	
	public void addJob(){
		String group = request.getParameter("group");
		String name = request.getParameter("name");
		String jobClass = request.getParameter("jobClass");
		Class cls = null;
		String msg = "";
		String id = null;
		try {
			cls = Class.forName(jobClass);
			JobBuilder jobBuild = JobBuilder.newJob(cls).withIdentity(name, group);
			jobBuild.storeDurably(true);
			jobDetail = jobBuild.build();
			jobDetail.getJobDataMap().put("id", group+"_"+name);
			
			id = jobDetailService.insert(jobDetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = e.getMessage();
			ActionHelp.WriteStrToOut(response, msg);
		} 
		ActionHelp.WriteStrToOut(response, id);
	}
	
	public void query(){
		List<JobDetail> jobs = null;
		String msg = "";
		try {
			jobs = jobDetailService.query(null);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActionHelp.WriteStrToOut(response, msg);
		}
		ActionHelp.WriteStrToOut(response, jobs);
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 * @return
	 */
	@Override
	public JobDetail getModel() {
		// TODO Auto-generated method stub
		if(jobDetail == null){
			jobDetail = new JobDetailImpl();
		}
		return jobDetail;
	}

	/**
	 * @return the jobDetail
	 */
	public JobDetail getJobDetail() {
		return jobDetail;
	}

	/**
	 * @param jobDetail the jobDetail to set
	 */
	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	/**
	 * @return the jobDetailService
	 */
	public IJobDetailService getJobDetailService() {
		return jobDetailService;
	}

	/**
	 * @param jobDetailService the jobDetailService to set
	 */
	public void setJobDetailService(IJobDetailService jobDetailService) {
		this.jobDetailService = jobDetailService;
	}
	
	

}
