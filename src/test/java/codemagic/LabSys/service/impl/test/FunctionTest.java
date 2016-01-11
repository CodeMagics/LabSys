package codemagic.LabSys.service.impl.test;

import jxl.common.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import codemagic.LabSys.model.Function;
import codemagic.LabSys.model.ReUserFunction;
import codemagic.LabSys.model.StudentDetailInfo;
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
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery2() {
		Function function = new Function();
		function.setFuncDescription("123");
		function.setFuncId(10);
		function.setFuncName("1");
		function.setFuncOrdinal(1);
		function.setFuncPareid(1);
		function.setFuncType("1");
		function.setFuncUrl("1");
		String tmp = null;
		function.setFuncDescription(tmp);
		function.setFuncName(tmp);
		function.setFuncType(tmp);
		function.setFuncUrl(tmp);
		function.getFuncDescription();
		function.getFuncId();
		function.getFuncName();
		function.getFuncOrdinal();
		function.getFuncPareid();
		function.getFuncType();
		function.getFuncUrl();
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery3() {
		ReUserFunction reUserFunction = new ReUserFunction();
		reUserFunction.setReufFunction(1);
		reUserFunction.setReufId(1);
		reUserFunction.setReufUser(1);
		reUserFunction.getReufFunction();
		reUserFunction.getReufId();
		reUserFunction.getReufUser();
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery4() {
		StudentDetailInfo studentDetailInfo = new StudentDetailInfo();
		String tmp = "1";
		studentDetailInfo.setStudClass(tmp);
		studentDetailInfo.setStudMajor(tmp);
		studentDetailInfo.setStudNum(1);
		studentDetailInfo.setStudUserid(1);
		studentDetailInfo.setUserEMail(tmp);
		studentDetailInfo.setUserPhone(tmp);
		studentDetailInfo.setUserRealname(tmp);
		studentDetailInfo.setUserType(1);
		tmp = null;
		studentDetailInfo.setStudClass(tmp);
		studentDetailInfo.setStudMajor(tmp);
		studentDetailInfo.setUserEMail(tmp);
		studentDetailInfo.setUserPhone(tmp);
		studentDetailInfo.setUserRealname(tmp);
		studentDetailInfo.getStudClass();
		studentDetailInfo.getStudMajor();
		studentDetailInfo.getStudNum();
		studentDetailInfo.getStudUserid();
		studentDetailInfo.getUserEMail();
		studentDetailInfo.getUserPhone();
		studentDetailInfo.getUserRealname();
		studentDetailInfo.getUserType();
	}
	
}
