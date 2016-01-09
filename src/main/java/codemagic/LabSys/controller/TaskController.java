package codemagic.LabSys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import codemagic.LabSys.model.Task;
import codemagic.LabSys.service.TaskService;

public class TaskController {
	private TaskService taskService;

	public TaskService getTaskService() {
		return taskService;
	}
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/ShowList")
	public ModelAndView ShowList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			List<Task> tasks = taskService.ShowList();
			if(!tasks.isEmpty()){
			map.put("result", Boolean.TRUE);
			map.put("tasks", tasks);
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "没有任务！");
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/SelectList")
	public ModelAndView SelectList(int userId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			List<Task> tasks = taskService.SelectList(userId);
			if(!tasks.isEmpty()){
			map.put("result", Boolean.TRUE);
			map.put("tasks", tasks);
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "没有任务！");
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/Publish")
	public ModelAndView Publish(int taskPublisher, String taskDetails, String taskTitle, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean successed;
			Task task = new Task();
			task.setTaskPubliser(taskPublisher);
			task.setTaskDetails(taskDetails);
			task.setTaskTitle(taskTitle);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			task.setTaskDate(df.format(new Date()));
			successed = taskService.Publish(task);
			if(successed){
			map.put("result", Boolean.TRUE);
			map.put("message", "发布成功");
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "发布失败！");
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/Updata")
	public ModelAndView Updata(int taskId, String taskDetails, String taskTitle, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean successed;
			Task task = new Task();
			task.setTaskId(taskId);
			task.setTaskDetails(taskDetails);
			task.setTaskTitle(taskTitle);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			task.setTaskDate(df.format(new Date()));
			successed = taskService.Updata(task);
			if(successed){
			map.put("result", Boolean.TRUE);
			map.put("message", "更新成功");
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "更新失败！");
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/Delete")
	public ModelAndView Delete(int taskId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean successed;
			successed = taskService.Delete(taskId);
			if(successed){
			map.put("result", Boolean.TRUE);
			map.put("message", "删除成功");
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "删除失败！");
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
}
