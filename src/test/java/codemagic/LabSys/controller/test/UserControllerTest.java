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
	public HttpSession session;

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
    	test.selectUserById("111","111", request, response);
    	assertEquals("error", test.message); 
    	test.getUserService();
    }  
    
    @Test 
    public void test2() {
    	userService = mock(UserService.class);
    	test.setUserService(userService);
        User user = new User();
        user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		test.selectUserById("111","111", request, response);
		assertEquals("false",test.message);
    	}
    @Test 
    public void test3() {
        userService = mock(UserService.class);
        test.setUserService(userService);
        User user = new User();
        user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		when(userService.login("111", "111")).thenReturn(user);
		test.selectUserById("111","111", request, response);
		assertEquals("true",test.message); 
    	}
    @Test 
    public void test4() {     
    	test.showAuthorityList(request, response);
    	assertEquals("false",test.message); 
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
        test.setFunctionService(functionService);
        session = mock(HttpSession.class);
        test.session = session;  
        test.showAuthorityList(request, response);
		assertEquals("true",test.message); 
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
         test.showAuthorityList(request, response);
 		assertEquals("error",test.message); 
    }
    
    @Test 
    public void test7() {   
    	test.showStuDeatilInfo(request, response);
    	assertEquals("false",test.message);
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
        test.showStuDeatilInfo(request, response);
		assertEquals("true",test.message); 
    }
    
    @Test 
    public void test9() {   
    	test.logout(request, response).getViewName();
    	assertEquals("true",test.message);
    }
    
    @Test 
    public void test10() {   
    	test.CheckPassword(request, response);
    	assertEquals("true",test.message);
    }
    
    @Test 
    public void test11() {   
    	test.showUser(3, request).getViewName();
    	assertEquals("error",test.message); 
    }
    
    @Test 
    public void test12() {   
    	userService = mock(UserService.class);
        test.setUserService(userService);
        User user = new User();
        user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		when(userService.findUserById(1)).thenReturn(user);
		test.showUser(3, request);
		assertEquals("true",test.message); 
    }
    
    @Test 
    public void test13() {   
    	test.EditPassword("111", request, response);
    	assertEquals("error",test.message);
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
        test.EditPassword("111", request, response);
    	assertEquals("false",test.message);
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
        test.EditPassword("111", request, response);
    	assertEquals("true2",test.message);
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
        test.setUserService(userService);
        test.EditPassword("111", request, response);
    	assertEquals("true1",test.message);
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test17() {   
    	test.Delete(3, response);
    	assertEquals("error",test.message);
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test18() {   
    	userService = mock(UserService.class);
    	test.setUserService(userService);
        when(userService.Delete(12)).thenReturn(true);
        test.Delete(12, response);
    	assertEquals("true",test.message);
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test19() {
    	userService = mock(UserService.class);
    	test.setUserService(userService);
        when(userService.Delete(12)).thenReturn(false);
        test.Delete(12, response);
    	assertEquals("false",test.message);
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test20() {
    	test.addUser("1", "1", response);
    	assertEquals("error",test.message);
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test21() {
    	userService = mock(UserService.class);
    	test.setUserService(userService);
    	User user = new User();
    	user = mock(User.class);
    	test.user = user;
        when(userService.addUser(user)).thenReturn(true);
        test.addUser("1", "1", response);
        assertEquals("true",test.message);
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test22() {
    	userService = mock(UserService.class);
    	test.setUserService(userService);
        when(userService.addUser(new User())).thenReturn(false);
        test.addUser("1", "1", response);
        assertEquals("false",test.message);
    }
    @Test 
    @Transactional    
    @Rollback(true)
    public void test23() {
    	test.resetPassword("111", response);
    	assertEquals("error",test.message);
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test24() {
    	userService = mock(UserService.class);
    	test.setUserService(userService);
        when(userService.resetPassword("111")).thenReturn(true);
        test.resetPassword("111", response);
        assertEquals("true",test.message);
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test25() {
    	userService = mock(UserService.class);
    	test.setUserService(userService);
        when(userService.resetPassword("111")).thenReturn(false);
        test.resetPassword("111", response);
        assertEquals("false",test.message);
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test26() {
    	test.ShowList(1, "1", request, response);
    	assertEquals("error",test.message);
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test27() {
    	userService = mock(UserService.class);
    	test.setUserService(userService);
        User user = new User();
		user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		List<User> list = new ArrayList<User>();
		list.add(user);
        when(userService.ShowList()).thenReturn(list);
        test.ShowList(1, "1", request, response);
        assertEquals("true",test.message);
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test28() {
    	userService = mock(UserService.class);
    	test.setUserService(userService);
        List<User> list = new ArrayList<User>();
        when(userService.ShowList()).thenReturn(list);
        test.ShowList(1, "1", request, response);
        assertEquals("false",test.message);
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test29() {
    	test.SelectByType(1, "1", request, response);
    	assertEquals("error",test.message);
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test30() {
    	userService = mock(UserService.class);
    	test.setUserService(userService);
        User user = new User();
		user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		List<User> list = new ArrayList<User>();
		list.add(user);
        when(userService.ShowList()).thenReturn(list);
        test.SelectByType(1, "0", request, response);
        assertEquals("true",test.message);
    }
    
    @Test 
    @Transactional    
    @Rollback(true)
    public void test31() {
    	userService = mock(UserService.class);
    	test.setUserService(userService);
        List<User> list = new ArrayList<User>();
        when(userService.ShowList()).thenReturn(list);
        test.SelectByType(1, "1", request, response);
        assertEquals("false",test.message);
    }
}
