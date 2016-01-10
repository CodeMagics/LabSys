package codemagic.LabSys.test;

import java.util.List;

import jxl.common.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import codemagic.LabSys.model.User;
import codemagic.LabSys.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-mybatis.xml" })
public class UserTest {
	private static final Logger LOGGER = Logger
			.getLogger(UserTest.class);

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery1() {
		userService.checkUser("111", "111");
//		LOGGER.info(JSON.toJSON(userService));
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery2() {
		userService.resetPassword("111");
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery3() {
		userService.login("111", "123456");
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery4() {
		userService.EditInfoByUserId(3, "111");
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery5() {
		User user = new User();
		user.setUserId(3);
		user.setUserPassword("111");
		userService.updateUser(user);
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery6() {
		User user = new User();
		user.setUserAccount("233");
		user.setUserPassword("233");
		user.setUserType(2);
		boolean tmp = userService.addUser(user);
		Assert.assertEquals(true, tmp);
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery7() {
		List<User> list = userService.ShowList();
		LOGGER.info(JSON.toJSON(list));
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery8() {
		List<User> list = userService.SelectByType(2);
		LOGGER.info(JSON.toJSON(list));
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery9() {
		userService.Delete(12);
	}
}
