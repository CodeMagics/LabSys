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

import codemagic.LabSys.model.Plan;
import codemagic.LabSys.service.PlanService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-mybatis.xml" })
public class PlanTest {
	private static final Logger LOGGER = Logger
			.getLogger(PlanTest.class);

	private PlanService planService;

	public PlanService getPlanService() {
		return planService;
	}
	@Autowired
	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery1() {
		Plan plan = new Plan();
		plan.setPlanPubliser(3);
		plan.setPlanTitle("ck");
		plan.setPlanDetails("test");
		plan.setPlanDate("test");
		planService.AddPlan(plan);
	}
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery2() {
		Plan plan = planService.CheckPlan(4);
		LOGGER.info(JSON.toJSON(plan));
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery3() {
		boolean tmp = planService.DeletePlan(4);
		Assert.assertEquals(true, tmp);
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery4() {
		List<Plan> plans = planService.ShowList(2);
		LOGGER.info(JSON.toJSON(plans));
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery5() {
		Plan plan = new Plan();
		plan.setPlanId(4);
		plan.setPlanTitle("ck");
		plan.setPlanDetails("test");
		plan.setPlanDate("test");
		boolean tmp = planService.UpdatePlan(plan);
		Assert.assertEquals(true, tmp);
	}
	
	@Test
	public void testQuery6() {
		Plan plan = new Plan();
		String tmp = null;
		plan.setPlanDate(tmp);
		plan.setPlanDetails(tmp);
		plan.setPlanTitle(tmp);
	}
}
