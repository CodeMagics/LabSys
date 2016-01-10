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

import codemagic.LabSys.model.Summary;
import codemagic.LabSys.service.SummaryService;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-mybatis.xml" })
public class SummaryTest {
	private static final Logger LOGGER = Logger
			.getLogger(SummaryTest.class);

	private SummaryService summaryService;

	public SummaryService getSummaryService() {
		return summaryService;
	}
	@Autowired
	public void setSummaryService(SummaryService summaryService) {
		this.summaryService = summaryService;
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery1() {
		Summary summary = new Summary();
		summary.setSumPubliser(3);
		summary.setSumTitle("ck");
		summary.setSumDetails("test");
		summary.setSumDate("test");
		summaryService.AddSummary(summary);
	}
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery2() {
		Summary summary = summaryService.CheckSummary(1);
		LOGGER.info(JSON.toJSON(summary));
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery3() {
		boolean tmp = summaryService.DeleteSummary(5);
		Assert.assertEquals(true, tmp);
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery4() {
		List<Summary> summarys = summaryService.ShowList(3);
		LOGGER.info(JSON.toJSON(summarys));
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery5() {
		Summary summary = new Summary();
		summary.setSumId(7);
		summary.setSumTitle("ck");
		summary.setSumDetails("test");
		summary.setSumDate("test");
		boolean tmp = summaryService.UpdateSummary(summary);
		Assert.assertEquals(true, tmp);
	}
}
