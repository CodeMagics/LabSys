package codemagic.LabSys.controller.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import codemagic.LabSys.controller.TaskController;
import codemagic.LabSys.model.Task;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-mybatis.xml" })
public class TaskControllerTest {

    private MockHttpServletRequest request;  

    private MockHttpServletResponse response; 
	public HttpSession session;
	
	public TaskController test = new TaskController();
	
	public TaskService taskService;

	public TaskService getTaskService() {
		return taskService;
	}
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	@Before    
	    public void setUp(){    
	        request = new MockHttpServletRequest();      
	        request.setCharacterEncoding("UTF-8");      
	        response = new MockHttpServletResponse();      
	    }
	 @Test 
	    public void test1() {  
		    test.ShowList(1, 1, request);
	    	assertEquals("error", test.message); 
	    	test.getTaskService();
	    } 
	 
	 @Test 
	    public void test2() {  
		 taskService = mock(TaskService.class);
		 test.setTaskService(taskService);
		 List<Task> list = new ArrayList<Task>();
		 Task task = new Task();
		 task.setTaskTitle("1");
		 list.add(task);
		 when(taskService.ShowList()).thenReturn(list);
		 test.ShowList(1, 1, request);
	     assertEquals("true",test.message); 
	    }
	 @Test 
	    public void test3() {  
		 taskService = mock(TaskService.class);
		 test.setTaskService(taskService);
		 List<Task> list = new ArrayList<Task>();
		 when(taskService.ShowList()).thenReturn(list);
		 test.ShowList(1, 1, request);
	     assertEquals("false",test.message); 
	    }
	 
	 @Test 
	    public void test4() {  
		 test.SelectList(1, request);
	    	assertEquals("error",test.message); 
	    } 
	 
	 @Test 
	    public void test5() {  
		 taskService = mock(TaskService.class);
		 test.setTaskService(taskService);
		 List<Task> list = new ArrayList<Task>();
		 Task task = new Task();
		 task.setTaskTitle("1");
		 list.add(task);
		 when(taskService.SelectList(1)).thenReturn(list);
		 test.SelectList(1, request);
	     assertEquals("true",test.message); 
	    }
	 @Test 
	    public void test6() {  
		 taskService = mock(TaskService.class);
		 test.setTaskService(taskService);
		 List<Task> list = new ArrayList<Task>();
		 when(taskService.SelectList(1)).thenReturn(list);
		 test.SelectList(1, request);
	     assertEquals("false",test.message);  
	    }
	 @Test 
	    public void test7() { 
		 test.Publish("1", "1", request);
	    	assertEquals("error",test.message); 
	    } 
	 
	 @Test 
	    public void test8() {  
		 taskService = mock(TaskService.class);
		 test.setTaskService(taskService);
		 User user = new User();
		 user.setUserId(1);
		 session = request.getSession();
		 session.putValue("user", user);
	     session = mock(HttpSession.class);
	     test.session = session; 
	     Task task = new Task();
	     task = mock(Task.class);
	     test.task=task;
		 when(taskService.Publish(task)).thenReturn(true);
		 test.Publish("1", "1", request);
	     assertEquals("true",test.message); 
	    }
	 @Test 
	    public void test9() {  
		 taskService = mock(TaskService.class);
		 test.setTaskService(taskService);
		 User user = new User();
		 user.setUserId(1);
		 session = request.getSession();
		 session.putValue("user", user);
	     session = mock(HttpSession.class);
	     test.session = session;
		 when(taskService.Publish(new Task())).thenReturn(false);
		 test.Publish("1", "1", request);
	     assertEquals("false",test.message); 
	    }
	 @Test 
	    public void test10() {  
		 test.Updata("1", "1", "1", request);
	    	assertEquals("error",test.message); 
	    } 
	 
	 @Test 
	    public void test11() {  
		 taskService = mock(TaskService.class);
		 test.setTaskService(taskService);
	     Task task = new Task();
	     task = mock(Task.class);
	     test.task=task;
		 when(taskService.Updata(task)).thenReturn(true);
		 test.Updata("1", "1", "1", request);
	     assertEquals("true",test.message); 
	    }
	 @Test 
	    public void test12() {  
		 taskService = mock(TaskService.class);
		 test.setTaskService(taskService);
		 when(taskService.Updata(new Task())).thenReturn(false);
		 test.Updata("1", "1", "1", request);
	     assertEquals("false",test.message);  
	    }
	 @Test 
	    public void test13() {  
		 test.Delete("1", request , response);
	    	assertEquals("error",test.message); 
	    } 
	 
	 @Test 
	    public void test14() {  
		 taskService = mock(TaskService.class);
		 test.setTaskService(taskService);
		 when(taskService.Delete(1)).thenReturn(true);
		 test.Delete("1", request, response);
	     assertEquals("true",test.message); 
	    }
	 @Test 
	    public void test15() {  
		 taskService = mock(TaskService.class);
		 test.setTaskService(taskService);
		 when(taskService.Delete(1)).thenReturn(false);
		 test.Delete("1", request , response);
	     assertEquals("false",test.message);  
	    }
	 @Test 
	    public void test16() {  
		 test.SelectById("1", request);
	    	assertEquals("error",test.message); 
	    } 
	 
	 @Test 
	    public void test17() {  
		 taskService = mock(TaskService.class);
		 test.setTaskService(taskService);
		 Task task = new Task();
		 when(taskService.SelectByid(1)).thenReturn(task);
		 when(taskService.SelectPublisher(1)).thenReturn("1");
		 test.SelectById("1", request);
	     assertEquals("true",test.message); 
	    }
	 @Test 
	    public void test18() {  
		 taskService = mock(TaskService.class);
		 test.setTaskService(taskService);
		 Task task = null;
		 when(taskService.SelectByid(1)).thenReturn(task);
		 test.SelectById("1", request);
	     assertEquals("false",test.message);  
	    }
}
