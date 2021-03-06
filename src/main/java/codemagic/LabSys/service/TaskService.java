package codemagic.LabSys.service;

import java.util.List;

import codemagic.LabSys.model.Task;

public interface TaskService {

	/**
	 * 显示所有任务
	 * @return
	 */
	public List<Task> ShowList();
	
	/**
	 * 根据userid查询该用户发布的所有任务
	 * @param userid
	 * @return
	 */
	public List<Task> SelectList(int userId);
	
	/**
	 * 根据taskid查询单个任务
	 * @param taskId
	 * @return
	 */
	public Task SelectByid(int taskId);
	
	/**
	 * 根据taskid查询发布者
	 * @param taskId
	 * @return
	 */
	public String SelectPublisher(int taskId);
	
	/**
	 * 发布任务
	 * @param task
	 * @return
	 */
	public boolean Publish(Task task);
	
	/**
	 * 修改任务
	 * @param task
	 * @return
	 */
	public boolean Updata(Task task);
	
	public boolean Delete(int taskId);
}
