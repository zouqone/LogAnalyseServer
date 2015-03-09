/**
 * 
 */
package cn.log.app.schedule.service;

import java.util.List;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;

/**
 * @author zouqone
 * @date 2015年3月9日 下午11:52:09
 */
public interface IJobDetailService {

	/**
	 * 新增工作
	 * @param job
	 * @return
	 * @throws SchedulerException 
	 */
	public String insert(JobDetail job) throws SchedulerException;
	
	/**
	 * 根据查询条件查询工作
	 * @param cindition
	 * @return
	 */
	public List<JobDetail> query(String condition) throws SchedulerException;
	
	/**
	 * 根据ID查询工作
	 * @param id
	 * @return
	 */
	public JobDetail find(String id) throws SchedulerException;
	
	/**
	 * 删除工作
	 * @param id
	 * @return
	 */
	public String delete(String id) throws SchedulerException;
	
	/**
	 * 执行工作
	 * @param id
	 * @return
	 */
	public String execute(String id) throws SchedulerException;
	
}
