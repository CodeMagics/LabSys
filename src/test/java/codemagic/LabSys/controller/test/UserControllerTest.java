package codemagic.LabSys.controller.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import codemagic.LabSys.controller.UserController;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.FunctionService;
import codemagic.LabSys.service.StudentService;
import codemagic.LabSys.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-mybatis.xml" })
public class UserControllerTest{

  // 模拟request,response  
	
    private MockHttpServletRequest request;  

    private MockHttpServletResponse response;   
  
    private UserController test = new UserController();
      
    public UserService userService;
	public FunctionService functionService;
	public StudentService studentService;
	public HttpSession session;

	public StudentService getStudentService() {
		return studentService;
	}
	@Autowired
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	public FunctionService getFunctionService() {
		return functionService;
	}
	@Autowired
	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}
	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	// 执行测试方法之前初始化模拟request,response  
    @Before    
    public void setUp(){    
        request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        response = new MockHttpServletResponse();      
    }           
    @Test 
    public void test1() {  
    	assertEquals("error",test.selectUserById("111","111", request, response).getViewName()); 
    	test.setUserService(userService);
    	test.getUserService();
    }  
    
    @Test 
    public void test2() {
    	userService = mock(UserService.class);
        test.userService = userService;
        User user = new User();
        user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		assertEquals("false",test.selectUserById("111","111", request, response).getViewName());
    	}
    @Test 
    public void test3() {
        userService = mock(UserService.class);
        test.userService = userService;
        User user = new User();
        user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		when(userService.login("111", "111")).thenReturn(user);
		assertEquals("true",test.selectUserById("111","111", request, response).getViewName()); 
    	}
    @Test 
    public void test4() {     
    	assertEquals("false",test.showAuthorityList(request, response).getViewName()); 
    	test.getFunctionService();
    	test.setFunctionService(functionService);
    } 
    @Test 
    public void test5() {   
    	User user = new User();
        user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		session = request.getSession();
		session.putValue("user", user);
        functionService = mock(FunctionService.class);
        test.functionService = functionService;
        session = mock(HttpSession.class);
        test.session = session;  
		assertEquals("true",test.showAuthorityList(request, response).getViewName()); 
    }
     @Test 
    public void test6() {    
    	 User user = new User();
         user.setUserAccount("233");
 		 user.setUserPassword("233");
 		 user.setUserType(2);
 		 session = request.getSession();
 		 session.putValue("user", user);
         session = mock(HttpSession.class);
         test.session = session;  
 		assertEquals("error",test.showAuthorityList(request, response).getViewName()); 
    }
    
    @Test 
    public void test7() {     
    	assertEquals("false",test.showStuDeatilInfo(request, response).getViewName());
    }
    
    @Test 
    public void test8() {  
    	User user = new User();
        user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		session = request.getSession();
		session.putValue("user", user);
        session = mock(HttpSession.class);
        test.session = session;  
		assertEquals("true",test.showStuDeatilInfo(request, response).getViewName()); 
    }
    
    @Test 
    public void test9() {   
    	assertEquals("true",test.logout(request, response).getViewName());
    }
    
    @Test 
    public void test10() {   
    	assertEquals("true",test.CheckPassword(request, response).getViewName());
    }
    
    @Test 
    public void test11() {   
    	assertEquals("error",test.showUser(3, request).getViewName()); 
    }
    
    @Test 
    public void test12() {   
    	userService = mock(UserService.class);
        test.userService = userService;
        User user = new User();
        user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		when(userService.findUserById(1)).thenReturn(user);
		assertEquals("true",test.showUser(3, request).getViewName()); 
    }
    
    @Test 
    public void test13() {   
    	assertEquals("error",test.EditPassword("111", request, response).getViewName());
    }
    
    @Test 
    public void test14() {   
    	User user = new User();
        user.setUserAccount("233");
		user.setUserPassword("233");
		session = request.getSession();
		session.putValue("user", user);
        session = mock(HttpSession.class);
        test.session = session; 
    	assertEquals("false",test.EditPassword("111", request, response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test15() {   
    	User user = new User();
        user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		session = request.getSession();
		session.putValue("user", user);
        session = mock(HttpSession.class);
        test.session = session; 
    	assertEquals("true2",test.EditPassword("111", request, response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test16() {   
    	User user = new User();
        user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		user.setUserId(3);
		session = request.getSession();
		session.putValue("user", user);
        session = mock(HttpSession.class);
        test.session = session; 
        userService = mock(UserService.class);
        test.userService = userService;
    	assertEquals("true1",test.EditPassword("111", request, response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test17() {   
    	assertEquals("error",test.Delete(3, response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test18() {   
    	userService = mock(UserService.class);
        test.userService = userService;
        when(userService.Delete(12)).thenReturn(true);
    	assertEquals("true",test.Delete(12, response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test19() {
    	userService = mock(UserService.class);
        test.userService = userService;
        when(userService.Delete(12)).thenReturn(false);
    	assertEquals("false",test.Delete(12, response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test20() {
    	assertEquals("error",test.addUser("1", "1", response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test21() {
    	userService = mock(UserService.class);
        test.userService = userService;
        when(userService.addUser(new User())).thenReturn(true);
        assertEquals("true",test.addUser("1", "1", response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test22() {
    	userService = mock(UserService.class);
        test.userService = userService;
        when(userService.addUser(new User())).thenReturn(false);
        assertEquals("false",test.addUser("1", "1", response).getViewName());
    }
    @Test 
    @Transactional    
    @Rollback(true)
    public void test23() {
    	assertEquals("error",test.resetPassword("111", response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test24() {
    	userService = mock(UserService.class);
        test.userService = userService;
        when(userService.resetPassword("111")).thenReturn(true);
        assertEquals("true",test.resetPassword("111", response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test25() {
    	userService = mock(UserService.class);
        test.userService = userService;
        when(userService.resetPassword("111")).thenReturn(false);
        assertEquals("false",test.resetPassword("111", response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test26() {
    	assertEquals("error",test.ShowList(1, "1", request, response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test27() {
    	userService = mock(UserService.class);
        test.userService = userService;
        User user = new User();
		user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		List<User> list = new ArrayList<User>();
		list.add(user);
        when(userService.ShowList()).thenReturn(list);
        assertEquals("true",test.ShowList(1, "1", request, response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test28() {
    	userService = mock(UserService.class);
        test.userService = userService;
        List<User> list = new ArrayList<User>();
        when(userService.ShowList()).thenReturn(list);
        assertEquals("false",test.ShowList(1, "1", request, response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test29() {
    	assertEquals("error",test.SelectByType(1, "1", request, response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test30() {
    	userService = mock(UserService.class);
        test.userService = userService;
        User user = new User();
		user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		List<User> list = new ArrayList<User>();
		list.add(user);
        when(userService.ShowList()).thenReturn(list);
        assertEquals("true",test.SelectByType(1, "0", request, response).getViewName());
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test31() {
    	userService = mock(UserService.class);
        test.userService = userService;
        List<User> list = new ArrayList<User>();
        when(userService.ShowList()).thenReturn(list);
        assertEquals("false",test.SelectByType(1, "1", request, response).getViewName());
    }
}
