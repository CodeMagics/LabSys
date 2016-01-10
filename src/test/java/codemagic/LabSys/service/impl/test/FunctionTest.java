package codemagic.LabSys.service.impl.test;

import jxl.common.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import codemagic.LabSys.model.User;
import codemagic.LabSys.service.FunctionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-mybatis.xml" })
public class FunctionTest {
	private static final Logger LOGGER = Logger
			.getLogger(FunctionTest.class);

	private FunctionService functionService;

	public FunctionService getFunctionService() {
		return functionService;
	}
	@Autowired
	public void setFunctionService(FunctionService userService) {
		this.functionService = userService;
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery1() {
		User user = new User();
		user.setUserType(2);
		functionService.findFunListByUser(user);
	}
}
