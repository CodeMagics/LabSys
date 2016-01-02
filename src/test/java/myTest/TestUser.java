package myTest;

import java.util.List;

import jxl.common.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import codemagic.LabSys.model.Function;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.FunctionService;
import codemagic.LabSys.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-mybatis.xml" })
public class TestUser {
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

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Test
	public void testQueryById1() {
		User user=new User();
		user.setUserType(1);
		List<Function> list=functionService.findFunListByUser(user);
		LOGGER.info(JSON.toJSON(list));
	}
}
