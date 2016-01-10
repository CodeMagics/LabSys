package codemagic.LabSys.service.impl.test;

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

import codemagic.LabSys.model.Student;
import codemagic.LabSys.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-mybatis.xml" })
public class StudentTest {
	private static final Logger LOGGER = Logger
			.getLogger(StudentTest.class);

	private StudentService studentService;

	public StudentService getStudentService() {
		return studentService;
	}
	@Autowired
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery1() {
		List<Student> list = studentService.showList();
		LOGGER.info(JSON.toJSON(list));
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery2() {
		studentService.selectStuByUserId(3);
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery() {
		boolean tmp = studentService.updateStuByUserId(2, "test", 233, "111", "111", "111", "111");
		Assert.assertEquals(true, tmp);
	}
}
