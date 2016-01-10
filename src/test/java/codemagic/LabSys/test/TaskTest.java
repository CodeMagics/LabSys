package codemagic.LabSys.test;

import jxl.common.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import codemagic.LabSys.model.Task;
import codemagic.LabSys.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-mybatis.xml" })
public class TaskTest {
	private static final Logger LOGGER = Logger
			.getLogger(TaskTest.class);

	private TaskService taskService;

	public TaskService getTaskService() {
		return taskService;
	}
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery1() {
		taskService.ShowList();
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery2() {
		taskService.SelectList(3);
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery3() {
		taskService.SelectByid(14);
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery4() {
		taskService.SelectPublisher(14);
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery5() {
		Task task = new Task();
		task.setTaskTitle("233");
		task.setTaskDetails("233");
		task.setTaskPubliser(3);
		task.setTaskDate("233");
		taskService.Publish(task);
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery6() {
		Task task = new Task();
		task.setTaskTitle("233");
		task.setTaskDetails("233");
		task.setTaskPubliser(3);
		task.setTaskDate("233");
		taskService.Updata(task);
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery7() {
		taskService.Delete(14);
	}
}
