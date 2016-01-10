package codemagic.LabSys.service.impl.test;

import jxl.common.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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

	public void testQuery1() {
		Plan plan = new Plan();
		plan.setPlanPubliser(3);
		plan.setPlanTitle("ck");
		plan.setPlanDetails("test");
		plan.setPlanDate("test");
		planService.AddPlan(plan);
	}
}
