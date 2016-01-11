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

import codemagic.LabSys.controller.SummaryController;
import codemagic.LabSys.model.Summary;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.SummaryService;
import codemagic.LabSys.service.SummaryService;
import codemagic.LabSys.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-mybatis.xml" })
public class SummaryControllerTest {
	
	private MockHttpServletRequest request;  

    private MockHttpServletResponse response; 
	public HttpSession session;
	
	public SummaryController test = new SummaryController();
	
	private SummaryService summaryService;
    private UserService userService;
	
	public SummaryService getSummaryService() {
		return summaryService;
	}
	
	public UserService getuserService() {
		return userService;
	}
	@Autowired
	public void setSummaryService(SummaryService summaryService) {
		this.summaryService = summaryService;
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
	    test.ShowList(1, 1, request);
    	assertEquals("error", test.message); 
    	test.getsummaryService();
    	test.getuserService();
    } 
	
	@Test 
    public void test2() {  
	 User user = new User();
	 user.setUserId(1);
	 session = request.getSession();
	 session.putValue("user", user);
	 summaryService = mock(SummaryService.class);
	 test.setsummaryService(summaryService);
	 List<Summary> list = new ArrayList<Summary>();
	 Summary summary = new Summary();
	 summary.setSumTitle("1");
	 list.add(summary);
	 when(summaryService.ShowList(1)).thenReturn(list);
	 test.ShowList(-1, 1, request);
     assertEquals("true",test.message); 
    }
 @Test 
    public void test3() {  
	 summaryService = mock(SummaryService.class);
	 test.setsummaryService(summaryService);
	 List<Summary> list = new ArrayList<Summary>();
	 when(summaryService.ShowList(1)).thenReturn(list);
	 test.ShowList(1, 1, request);
     assertEquals("false",test.message); 
    }
 
 @Test 
 public void test4() {  
	 test.PublishSummary("1", "1", request);
	 assertEquals("error", test.message); 
 }
 
 @Test 
 public void test5() {  
	 User user = new User();
	 user.setUserId(1);
	 session = request.getSession();
	 session.putValue("user", user);
	 summaryService = mock(SummaryService.class);
	 test.setsummaryService(summaryService);
	 Summary summary = new Summary();
	 summary = mock(Summary.class);
	 test.record = summary;
	 when(summaryService.AddSummary(summary)).thenReturn(true);
	 test.PublishSummary("1", "1", request);
 	 assertEquals("true", test.message); 
 }
 
 @Test 
 public void test6() {  
	 User user = new User();
	 user.setUserId(1);
	 session = request.getSession();
	 session.putValue("user", user);
	 summaryService = mock(SummaryService.class);
	 test.setsummaryService(summaryService);
	 when(summaryService.AddSummary(new Summary())).thenReturn(false);
	 test.PublishSummary("1", "1", request);
 	 assertEquals("false", test.message); 
 }
 @Test 
 public void test7() {  
	 test.CheckSummary("1", request);
	 assertEquals("error", test.message); 
 }
 
 @Test 
 public void test8() {  
	 summaryService = mock(SummaryService.class);
	 test.setsummaryService(summaryService);
	 Summary summary = new Summary();
	 summary.setSumPubliser(3);
	 when(summaryService.CheckSummary(1)).thenReturn(summary);
	 userService = mock(UserService.class);
	 test.setuserServiceService(userService);
	 User user = new User();
	 user.setUserId(3);
	 when(userService.findUserById(1)).thenReturn(new User());
	 user = mock(User.class);
	 when(user.getUserRealname()).thenReturn("1");
	 test.CheckSummary("1", request);
 	 assertEquals("true", test.message); 
 }
 
 @Test 
 public void test9() {  
	 summaryService = mock(SummaryService.class);
	 test.setsummaryService(summaryService);
	 when(summaryService.CheckSummary(1)).thenReturn(null);
	 test.CheckSummary("1", request);
 	 assertEquals("false", test.message); 
 }
 
 @Test 
 public void test10() {  
	 test.UpdateSummary("1", "1", "1", request);
	 assertEquals("error", test.message); 
 }
 
 @Test 
 public void test11() {  
	 summaryService = mock(SummaryService.class);
	 test.setsummaryService(summaryService);
	 Summary summary = new Summary();
	 summary = mock(Summary.class);
	 test.record = summary;
	 when(summaryService.UpdateSummary(summary)).thenReturn(true);
	 test.UpdateSummary("1", "1", "1", request);
 	 assertEquals("true", test.message); 
 }
 
 @Test 
 public void test12() {  
	 summaryService = mock(SummaryService.class);
	 test.setsummaryService(summaryService);
	 summaryService = mock(SummaryService.class);
	 test.setsummaryService(summaryService);
	 when(summaryService.UpdateSummary(new Summary())).thenReturn(false);
	 test.UpdateSummary("1", "1", "1", request);
 	 assertEquals("false", test.message); 
 }
 @Test 
 public void test13() {  
	 test.DeleteSummary("1", request);
	 assertEquals("error", test.message); 
 }
 
 @Test 
 public void test14() {  
	 summaryService = mock(SummaryService.class);
	 test.setsummaryService(summaryService);
	 Summary summary = new Summary();
	 summary = mock(Summary.class);
	 test.record = summary;
	 when(summaryService.DeleteSummary(1)).thenReturn(true);
	 test.DeleteSummary("1", request);
 	 assertEquals("true", test.message); 
 }
 
 @Test 
 public void test15() {  
	 summaryService = mock(SummaryService.class);
	 test.setsummaryService(summaryService);
	 summaryService = mock(SummaryService.class);
	 test.setsummaryService(summaryService);
	 when(summaryService.DeleteSummary(1)).thenReturn(false);
	 test.DeleteSummary("1", request);
 	 assertEquals("false", test.message); 
 }

}
