/**
 * 
 */
package cn.log.app.schedule.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author zouqone
 * @date 2015年3月10日 上午1:22:45
 */
public class TestJob implements Job {

	/**
	 * 
	 */
	public TestJob() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 * @param paramJobExecutionContext
	 * @throws JobExecutionException
	 */
	@Override
	public void execute(JobExecutionContext paramJobExecutionContext)
			throws JobExecutionException {
		// TODO Auto-generated method stub

		System.out.println("HelloWorld! This is my First Job.");
	}

}
