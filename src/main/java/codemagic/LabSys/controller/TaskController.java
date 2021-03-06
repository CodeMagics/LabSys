package codemagic.LabSys.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import codemagic.LabSys.model.Task;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.TaskService;



@Controller
@RequestMapping("/taskController")
public class TaskController {
	private TaskService taskService;
	
	public HttpSession session;
	
	public String message;

	public 	Task task = new Task();
	public TaskService getTaskService() {
		return taskService;
	}
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/ShowList")
	public ModelAndView ShowList(int page,int type,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		session = request.getSession();
	    User user = (User) session.getAttribute("user");
		try {
			List<Task> tasks = taskService.ShowList();
			if(!tasks.isEmpty()){
				int recordCount = tasks.size();// 总记录数
				int pageCount;// 总页数
				int temp = recordCount % 5;// 5条记录一页
				if (temp == 0) {
					pageCount = recordCount / 5;
				} else {
					pageCount = recordCount / 5 + 1;
				}
				
				List<Task> pageList=new ArrayList<Task>();
				int max=tasks.size()>page*5?page*5:tasks.size();
				for (int i = (page - 1) * 5; i <max; i++) {
					pageList.add(tasks.get(i));
				}
				map.put("pageList", pageList);
				map.put("pageCount", pageCount);
				map.put("page", page);
				
			    map.put("result", Boolean.TRUE);
			    map.put("tasks", tasks);
			    map.put("user", user);
			    message = "true";
			
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "没有任务！");
				message = "false";
			}
			
			
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！");
			message = "error";
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
			message = "true";
	
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "没有任务！");
				message = "false";
			}
			
			
		} catch (Exception e) {
			message = "error";
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/SelectById")
	public ModelAndView SelectById(String id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			Task task = taskService.SelectByid(Integer.parseInt(id));
			if(task!=null){
			String publisher = taskService.SelectPublisher(Integer.parseInt(id));
			if(publisher!=null){
			map.put("publisher", publisher);
			}
			map.put("result", Boolean.TRUE);
			map.put("task", task);
			message = "true";
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "获取失败！");
				message = "false";
			}
			
			
		} catch (Exception e) {
			message = "error";
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/SelectPublisher")
	public ModelAndView SelectPublisher(String id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			String publisher = taskService.SelectPublisher(Integer.parseInt(id));
			if(publisher!=null){
			map.put("result", Boolean.TRUE);
			map.put("publisher", publisher);
			message = "true";
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "获取失败！");
				message = "false";
			}
			
			
		} catch (Exception e) {
			message = "error";
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/Publish")
	public ModelAndView Publish(String title,String content,  HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			session = request.getSession();
		    User user = (User) session.getAttribute("user");
			int taskPublisher=user.getUserId();
			boolean successed;
			task.setTaskPubliser(taskPublisher);
			task.setTaskDetails(content);
			task.setTaskTitle(title);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			task.setTaskDate(df.format(new Date()));
			successed = taskService.Publish(task);
			if(successed){
			map.put("result", Boolean.TRUE);
			map.put("message", "发布成功");
			message = "true";
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "发布失败！");
				message = "false";
			}
			
			
		} catch (Exception e) {
			message = "error";
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/Updata")
	public ModelAndView Updata(String id, String title, String content, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean successed;
			task.setTaskId(Integer.parseInt(id));
			task.setTaskDetails(content);
			task.setTaskTitle(title);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			task.setTaskDate(df.format(new Date()));
			successed = taskService.Updata(task);
			if(successed){
			map.put("result", Boolean.TRUE);
			map.put("message", "更新成功");
			message = "true";
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "更新失败！");
				message = "false";
			}
			
			
		} catch (Exception e) {
			message = "error";
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/Delete")
	public ModelAndView Delete(String id, HttpServletRequest request,  HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean successed;
			
				successed = taskService.Delete(Integer.parseInt(id));
				if(successed){
				map.put("result", Boolean.TRUE);
				map.put("message", "删除成功");
				message = "true";
			}
			
			
			else {
	          
				map.put("result", Boolean.FALSE);
				map.put("message", "删除失败！");
				message = "false";
			}
			
			
		} catch (Exception e) {
			message = "error";
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
}
