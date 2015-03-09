/**
 * 
 */
package cn.log.app.schedule.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;

import cn.log.app.schedule.service.IJobDetailService;

/**
 * @author zouqone
 * @date 2015年3月10日 上午12:01:10
 */
public class JobDetailImpl implements IJobDetailService {

	private Scheduler scheduler = null;
	

	/* (non-Javadoc)
	 * @see cn.log.app.schedule.service.IJobDetailService#insert(org.quartz.JobDetail)
	 * @param job
	 * @return
	 */
	@Override
	public String insert(JobDetail job) throws SchedulerException {
		// TODO Auto-generated method stub
		scheduler.addJob(job, false);
		String id = (String) job.getJobDataMap().get("id");
		return id;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.schedule.service.IJobDetailService#query(java.lang.String)
	 * @param cindition
	 * @return
	 */
	@Override
	public List<JobDetail> query(String condition)  throws SchedulerException{
		// TODO Auto-generated method stub
		List<JobDetail> jobs = new ArrayList<JobDetail>();
		if(condition == null){
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			for(JobKey jobKey : jobKeys){
				JobDetail job = scheduler.getJobDetail(jobKey);
				jobs.add(job);
			}
		}
		return jobs;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.schedule.service.IJobDetailService#find(java.lang.String)
	 * @param id
	 * @return
	 */
	@Override
	public JobDetail find(String id)  throws SchedulerException{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.schedule.service.IJobDetailService#delete(java.lang.String)
	 * @param id
	 * @return
	 */
	@Override
	public String delete(String id)  throws SchedulerException{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.log.app.schedule.service.IJobDetailService#execute(java.lang.String)
	 * @param id
	 * @return
	 */
	@Override
	public String execute(String id)  throws SchedulerException{
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/**
	 * @return the scheduler
	 */
	public Scheduler getScheduler() {
		return scheduler;
	}

	/**
	 * @param scheduler the scheduler to set
	 */
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
}
