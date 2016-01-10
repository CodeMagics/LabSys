package codemagic.LabSys.test;

import jxl.common.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import codemagic.LabSys.model.Notice;
import codemagic.LabSys.service.NoticeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-mybatis.xml" })
public class NoticeTest {
	private static final Logger LOGGER = Logger
			.getLogger(NoticeTest.class);

	private NoticeService noticeService;

	public NoticeService getNoticeService() {
		return noticeService;
	}
	@Autowired
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery1() {
		noticeService.ShowList();
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery2() {
		noticeService.SelectList(3);
	}
	
	/*@Test
	@Transactional    
    @Rollback(true)
	public void testQuery3() {
		noticeService.SelectByid(14);
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery4() {
		noticeService.SelectPublisher(14);
	}*/
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery5() {
		Notice notice = new Notice();
		notice.setNoticeTitle("233");
		notice.setNoticeDetails("233");
		notice.setNoticePublisher(3);
		notice.setNoticeDate("233");
		noticeService.Publish(notice);
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery6() {
		Notice notice = new Notice();
		notice.setNoticeTitle("233");
		notice.setNoticeDetails("233");
		notice.setNoticePublisher(3);
		notice.setNoticeDate("233");
		noticeService.Updata(notice);
	}
	
	@Test
	@Transactional    
    @Rollback(true)
	public void testQuery7() {
		noticeService.Delete(14);
	}
}
