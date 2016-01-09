package codemagic.LabSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codemagic.LabSys.dao.TaskMapper;
import codemagic.LabSys.model.Task;
import codemagic.LabSys.service.TaskService;

@Service("/taskService")
public class TaskServiceImpl implements TaskService{

	private TaskMapper taskMapper;

	public TaskMapper getTaskMapper() {
		return taskMapper;
	}
	
	@Autowired
	public void setTaskMapper(TaskMapper taskMapper) {
		this.taskMapper = taskMapper;
	}

	@SuppressWarnings("finally")
	public List<Task> ShowList() {
		// TODO Auto-generated method stub
		List<Task> list = new ArrayList<Task>();
		try{
			list = taskMapper.selectAll();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return list;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Task> SelectList(int userId) {
		// TODO Auto-generated method stub
		List<Task> list = new ArrayList<Task>();
		try{
			list = taskMapper.selectByPublisher(userId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return list;
		}
	}
	
	@SuppressWarnings("finally")
	public Task SelectByid(int taskId) {
		// TODO Auto-generated method stub
		Task task = null;
		try{
			task = taskMapper.selectByPrimaryKey(taskId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return task;
		}
	}
	
	@SuppressWarnings("finally")
	public String SelectPublisher(int taskId) {
		// TODO Auto-generated method stub4Task task = null;
		String publisher = null;
		try{
			Task task=null;
			task= taskMapper.selectPublisher(taskId);
			publisher=task.getUserRealname();
			System.out.println(publisher+"/////////////////////////////");
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return publisher;
		}
	}

	public boolean Publish(Task task) {
		// TODO Auto-generated method stub
		try{
			taskMapper.insert(task);
			return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
	}

	public boolean Updata(Task task) {
		// TODO Auto-generated method stub
		try{
			taskMapper.updateByPrimaryKey(task);
		return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean Delete(int taskId) {
		// TODO Auto-generated method stub
		try{
			taskMapper.deleteByPrimaryKey(taskId);
		return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
