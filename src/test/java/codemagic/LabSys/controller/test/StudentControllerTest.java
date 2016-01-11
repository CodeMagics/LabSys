package codemagic.LabSys.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import codemagic.LabSys.controller.StudentController;
import codemagic.LabSys.model.Student;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.StudentService;
import codemagic.LabSys.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-mybatis.xml" })

public class StudentControllerTest {

    private MockHttpServletRequest request;  

    private MockHttpServletResponse response;  
    
    private StudentController test = new StudentController();
    
    public HttpSession session;
    
    private UserService userService;
	 private StudentService studentService;
		public UserService getUserService() {
	        return userService;
	    }
		@Autowired
	    public void setUserService(UserService userService) {
	        this.userService = userService;
	    }
		
		public StudentService getStudentService() {
			return studentService;
		}

		@Autowired
		public void setStudentService(StudentService studentService) {
			this.studentService = studentService;
		}
		@Before    
	    public void setUp(){    
	        request = new MockHttpServletRequest();      
	        request.setCharacterEncoding("UTF-8");      
	        response = new MockHttpServletResponse();      
	    }           
	    @Test 
	    public void test1() throws Exception {  
	    	test.readStudentByStudNumber(1, request, response);
	    	assertEquals("false", test.message); 
	    	test.getUserService();
	    	test.getStudentService();
	    }  
	    
	    @Test 
	    public void test2() throws Exception {  
	    	userService = mock(UserService.class);
	    	test.setUserService(userService);
	    	studentService = mock(StudentService.class);
	    	test.setStudentService(studentService);
	    	User user = new User();
	        user.setUserId(1);
			session = request.getSession();
			session.putValue("user", user);
			user = mock(User.class);
			test.user = user;
			when(user.getUserId()).thenReturn(1);
			when(userService.findUserById(1)).thenReturn(new User());
			when(studentService.selectStuByUserId(1)).thenReturn(new Student());
	    	test.readStudentByStudNumber(1, request, response);
	    	assertEquals("true", test.message); 
	    	test.getUserService();
	    }  
	    
	    @Test 
	    public void test3() throws Exception {  
	    	User user = new User();
	        user.setUserId(1);
			session = request.getSession();
			session.putValue("user", user);
	    	test.readStudentByStudNumber(-1, request, response);
	    	assertEquals("error", test.message); 
	    	test.getUserService();
	    }  
	    
	    @Test 
	    public void test4() throws Exception {  
	    	test.updateStudent("1", "1", "1", "1", "1", "1", request, response);
	    	assertEquals("other", test.message); 
	    }
	    @Test 
	    public void test5() throws Exception {
	    	User user = new User();
	        user.setUserId(1);
			session = request.getSession();
			session.putValue("user", user);
			user = mock(User.class);
			user.setUserType(2);
			user = mock(User.class);
			test.user = user;
			when(user.getUserType()).thenReturn(2);
	    	test.updateStudent("1", "1", "1", "1", "1", "1", request, response);
	    	assertEquals("error", test.message); 
	    }
	    @Test 
	    public void test6() throws Exception {  
	    	userService = mock(UserService.class);
	    	test.setUserService(userService);
	    	studentService = mock(StudentService.class);
	    	test.setStudentService(studentService);
	    	User user = new User();
	    	user.setUserId(1);
	    	user.setUserType(1);
			session = request.getSession();
			session.putValue("user", user);
			user = mock(User.class);
			test.user = user;
			when(user.getUserType()).thenReturn(1);
			when(user.getUserId()).thenReturn(1);
			when(studentService.updateStuByUserId(1, "1", 1, "1", "1", "1", "1")).thenReturn(true);
			when(userService.updateUser(user)).thenReturn(true);
	    	test.updateStudent("1", "1", "1", "1", "1", "1", request, response);
	    	assertEquals("true", test.message); 
	    }
	    @Test 
	    public void test7() throws Exception {  
	    	userService = mock(UserService.class);
	    	test.setUserService(userService);
	    	studentService = mock(StudentService.class);
	    	test.setStudentService(studentService);
	    	User user = new User();
	    	user.setUserId(1);
	    	user.setUserType(1);
			session = request.getSession();
			session.putValue("user", user);
			user = mock(User.class);
			test.user = user;
			when(user.getUserType()).thenReturn(1);
			when(user.getUserId()).thenReturn(1);
			when(studentService.updateStuByUserId(1, "1", 1, "1", "1", "1", "1")).thenReturn(false);
			when(userService.updateUser(new User())).thenReturn(false);
	    	test.updateStudent("1", "1", "1", "1", "1", "1", request, response);
	    	assertEquals("false", test.message); 
	    }
	    
	    @Test 
	    public void test8() throws Exception {  
	    	test.updateStudentInfo("1", 1, "1", "1", "1", "1", request, response);
	    	assertEquals("other", test.message); 
	    }
	    @Test 
	    public void test9() throws Exception {
	    	User user = new User();
	        user.setUserId(1);
			session = request.getSession();
			session.putValue("user", user);
			user = mock(User.class);
			user.setUserType(2);
			user = mock(User.class);
			test.user = user;
			when(user.getUserType()).thenReturn(2);
			test.updateStudentInfo("1", 1, "1", "1", "1", "1", request, response);
	    	assertEquals("error", test.message); 
	    }
	    @Test 
	    public void test10() throws Exception {  
	    	userService = mock(UserService.class);
	    	test.setUserService(userService);
	    	studentService = mock(StudentService.class);
	    	test.setStudentService(studentService);
	    	User user = new User();
	    	user.setUserId(1);
	    	user.setUserType(1);
			session = request.getSession();
			session.putValue("user", user);
			user = mock(User.class);
			test.user = user;
			when(user.getUserType()).thenReturn(1);
			when(user.getUserId()).thenReturn(1);
			when(studentService.updateStuByUserId(1, "1", 1, "1", "1", "1", "1")).thenReturn(true);
			test.updateStudentInfo("1", 1, "1", "1", "1", "1", request, response);
	    	assertEquals("true", test.message); 
	    }
	    @Test 
	    public void test11() throws Exception {  
	    	userService = mock(UserService.class);
	    	test.setUserService(userService);
	    	studentService = mock(StudentService.class);
	    	test.setStudentService(studentService);
	    	User user = new User();
	    	user.setUserId(1);
	    	user.setUserType(1);
			session = request.getSession();
			session.putValue("user", user);
			user = mock(User.class);
			test.user = user;
			when(user.getUserType()).thenReturn(1);
			when(user.getUserId()).thenReturn(1);
			when(studentService.updateStuByUserId(1, "1", 1, "1", "1", "1", "1")).thenReturn(false);
			test.updateStudentInfo("1", 1, "1", "1", "1", "1", request, response);
	    	assertEquals("false", test.message); 
	    }
	     
}
