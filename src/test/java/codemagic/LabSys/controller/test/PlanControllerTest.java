package codemagic.LabSys.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import codemagic.LabSys.controller.PlanController;
import codemagic.LabSys.model.Plan;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.PlanService;
import codemagic.LabSys.service.TaskService;
import codemagic.LabSys.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-mybatis.xml" })
public class PlanControllerTest {
	
	private MockHttpServletRequest request;  

    private MockHttpServletResponse response; 
	public HttpSession session;
	
	public PlanController test = new PlanController();
	
	private PlanService planService;
    private UserService userService;
	
	public PlanService getPlanService() {
		return planService;
	}
	
	public UserService getuserService() {
		return userService;
	}
	@Autowired
	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}
	
	@Autowired
	public void setuserServiceService(UserService userService) {
		this.userService = userService;
	}
	
	@Before    
    public void setUp(){    
        request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        response = new MockHttpServletResponse();      
    }
	@Test 
    public void test1() {  
	    test.showList(1, 1, request);
    	assertEquals("error", test.message); 
    	test.getPlanService();
    	test.getuserService();
    } 
	
	@Test 
    public void test2() {  
	 User user = new User();
	 user.setUserId(1);
	 session = request.getSession();
	 session.putValue("user", user);
	 planService = mock(PlanService.class);
	 test.setPlanService(planService);
	 List<Plan> list = new ArrayList<Plan>();
	 Plan task = new Plan();
	 task.setPlanTitle("1");
	 list.add(task);
	 when(planService.ShowList(1)).thenReturn(list);
	 test.showList(-1, 1, request);
     assertEquals("true",test.message); 
    }
 @Test 
    public void test3() {  
	 planService = mock(PlanService.class);
	 test.setPlanService(planService);
	 List<Plan> list = new ArrayList<Plan>();
	 when(planService.ShowList(1)).thenReturn(list);
	 test.showList(1, 1, request);
     assertEquals("false",test.message); 
    }
 
 @Test 
 public void test4() {  
	 test.publishplan("1", "1", request);
	 assertEquals("error", test.message); 
 }
 
 @Test 
 public void test5() {  
	 User user = new User();
	 user.setUserId(1);
	 session = request.getSession();
	 session.putValue("user", user);
	 planService = mock(PlanService.class);
	 test.setPlanService(planService);
	 Plan plan = new Plan();
	 plan = mock(Plan.class);
	 test.record = plan;
	 when(planService.AddPlan(plan)).thenReturn(true);
	 test.publishplan("1", "1", request);
 	 assertEquals("true", test.message); 
 }
 
 @Test 
 public void test6() {  
	 User user = new User();
	 user.setUserId(1);
	 session = request.getSession();
	 session.putValue("user", user);
	 planService = mock(PlanService.class);
	 test.setPlanService(planService);
	 when(planService.AddPlan(new Plan())).thenReturn(false);
	 test.publishplan("1", "1", request);
 	 assertEquals("false", test.message); 
 }
 @Test 
 public void test7() {  
	 test.checkplan("1", request);
	 assertEquals("error", test.message); 
 }
 
 @Test 
 public void test8() {  
	 planService = mock(PlanService.class);
	 test.setPlanService(planService);
	 Plan plan = new Plan();
	 plan.setPlanPubliser(3);
	 when(planService.CheckPlan(1)).thenReturn(plan);
	 userService = mock(UserService.class);
	 test.setuserServiceService(userService);
	 User user = new User();
	 user.setUserId(3);
	 when(userService.findUserById(1)).thenReturn(new User());
	 user = mock(User.class);
	 when(user.getUserRealname()).thenReturn("1");
	 test.checkplan("1", request);
 	 assertEquals("true", test.message); 
 }
 
 @Test 
 public void test9() {  
	 planService = mock(PlanService.class);
	 test.setPlanService(planService);
	 when(planService.CheckPlan(1)).thenReturn(null);
	 test.checkplan("1", request);
 	 assertEquals("false", test.message); 
 }
 
 @Test 
 public void test10() {  
	 test.updateplan("1", "1", "1", request);
	 assertEquals("error", test.message); 
 }
 
 @Test 
 public void test11() {  
	 planService = mock(PlanService.class);
	 test.setPlanService(planService);
	 Plan plan = new Plan();
	 plan = mock(Plan.class);
	 test.record = plan;
	 when(planService.UpdatePlan(plan)).thenReturn(true);
	 test.updateplan("1", "1", "1", request);
 	 assertEquals("true", test.message); 
 }
 
 @Test 
 public void test12() {  
	 planService = mock(PlanService.class);
	 test.setPlanService(planService);
	 planService = mock(PlanService.class);
	 test.setPlanService(planService);
	 when(planService.UpdatePlan(new Plan())).thenReturn(false);
	 test.updateplan("1", "1", "1", request);
 	 assertEquals("false", test.message); 
 }
 @Test 
 public void test13() {  
	 test.deleteplan("1", request);
	 assertEquals("error", test.message); 
 }
 
 @Test 
 public void test14() {  
	 planService = mock(PlanService.class);
	 test.setPlanService(planService);
	 Plan plan = new Plan();
	 plan = mock(Plan.class);
	 test.record = plan;
	 when(planService.DeletePlan(1)).thenReturn(true);
	 test.deleteplan("1", request);
 	 assertEquals("true", test.message); 
 }
 
 @Test 
 public void test15() {  
	 planService = mock(PlanService.class);
	 test.setPlanService(planService);
	 planService = mock(PlanService.class);
	 test.setPlanService(planService);
	 when(planService.DeletePlan(1)).thenReturn(false);
	 test.deleteplan("1", request);
 	 assertEquals("false", test.message); 
 }

}
