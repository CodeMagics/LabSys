package myTest;

import static org.junit.Assert.*;

import java.util.List;

import jxl.common.Logger;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

import codemagic.LabSys.model.Function;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.FunctionService;
import codemagic.LabSys.service.UserService;

public class UserTest {
      public UserService userService ;
      private static final Logger LOGGER = Logger
  			.getLogger(TestUser.class);
  	private FunctionService functionService;
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
  	
	 @Before
	    public void before(){                                                                    
	        @SuppressWarnings("resource")
	        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"
	                ,"classpath:spring-mybatis.xml"});
	        userService = (UserService) context.getBean("UserService");
	    }
	     
	 @Test
		public void testQueryById() {
			
		}
}
