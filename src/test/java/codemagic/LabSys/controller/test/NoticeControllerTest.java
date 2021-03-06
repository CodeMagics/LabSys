package codemagic.LabSys.controller.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import codemagic.LabSys.controller.NoticeController;
import codemagic.LabSys.model.Notice;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.NoticeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml","classpath:spring-mybatis.xml" })
public class NoticeControllerTest {

    private MockHttpServletRequest request;  

    private MockHttpServletResponse response; 
	public HttpSession session;
	
	public NoticeController test = new NoticeController();
	
	public NoticeService noticeService;

	public NoticeService getNoticeService() {
		return noticeService;
	}
	@Autowired
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	@Before    
	    public void setUp(){    
	        request = new MockHttpServletRequest();      
	        request.setCharacterEncoding("UTF-8");      
	        response = new MockHttpServletResponse();      
	    }
	 @Test 
	    public void test1() {  
		    test.ShowList(1, 1, request);
	    	assertEquals("error", test.message); 
	    	test.getNoticeService();
	    } 
	 
	 @Test 
	    public void test2() {  
		 noticeService = mock(NoticeService.class);
		 test.setNoticeService(noticeService);
		 List<Notice> list = new ArrayList<Notice>();
		 Notice notice = new Notice();
		 notice.setNoticeTitle("1");
		 list.add(notice);
		 when(noticeService.ShowList()).thenReturn(list);
		 test.ShowList(1, 1, request);
	     assertEquals("true",test.message); 
	    }
	 @Test 
	    public void test3() {  
		 noticeService = mock(NoticeService.class);
		 test.setNoticeService(noticeService);
		 List<Notice> list = new ArrayList<Notice>();
		 when(noticeService.ShowList()).thenReturn(list);
		 test.ShowList(1, 1, request);
	     assertEquals("false",test.message); 
	    }
	 
	 @Test 
	    public void test4() {  
		 test.SelectList(1, request);
	    	assertEquals("error",test.message); 
	    } 
	 
	 @Test 
	    public void test5() {  
		 noticeService = mock(NoticeService.class);
		 test.setNoticeService(noticeService);
		 List<Notice> list = new ArrayList<Notice>();
		 Notice notice = new Notice();
		 notice.setNoticeTitle("1");
		 list.add(notice);
		 when(noticeService.SelectList(1)).thenReturn(list);
		 test.SelectList(1, request);
	     assertEquals("true",test.message); 
	    }
	 @Test 
	    public void test6() {  
		 noticeService = mock(NoticeService.class);
		 test.setNoticeService(noticeService);
		 List<Notice> list = new ArrayList<Notice>();
		 when(noticeService.SelectList(1)).thenReturn(list);
		 test.SelectList(1, request);
	     assertEquals("false",test.message);  
	    }
	 @Test 
	    public void test7() { 
		 test.Publish("1", "1", request);
	    	assertEquals("error",test.message); 
	    } 
	 
	 @Test 
	    public void test8() {  
		 noticeService = mock(NoticeService.class);
		 test.setNoticeService(noticeService);
		 User user = new User();
		 user.setUserId(1);
		 session = request.getSession();
		 session.putValue("user", user);
	     session = mock(HttpSession.class);
	     test.session = session; 
	     Notice notice = new Notice();
	     notice = mock(Notice.class);
	     test.notice=notice;
		 when(noticeService.Publish(notice)).thenReturn(true);
		 test.Publish("1", "1", request);
	     assertEquals("true",test.message); 
	    }
	 @Test 
	    public void test9() {  
		 noticeService = mock(NoticeService.class);
		 test.setNoticeService(noticeService);
		 User user = new User();
		 user.setUserId(1);
		 session = request.getSession();
		 session.putValue("user", user);
	     session = mock(HttpSession.class);
	     test.session = session;
		 when(noticeService.Publish(new Notice())).thenReturn(false);
		 test.Publish("1", "1", request);
	     assertEquals("false",test.message); 
	    }
	 @Test 
	    public void test10() {  
		 test.Updata(1, "1", "1", request);
	    	assertEquals("error",test.message); 
	    } 
	 
	 @Test 
	    public void test11() {  
		 noticeService = mock(NoticeService.class);
		 test.setNoticeService(noticeService);
	     Notice notice = new Notice();
	     notice = mock(Notice.class);
	     test.notice=notice;
		 when(noticeService.Updata(notice)).thenReturn(true);
		 test.Updata(1, "1", "1", request);
	     assertEquals("true",test.message); 
	    }
	 @Test 
	    public void test12() {  
		 noticeService = mock(NoticeService.class);
		 test.setNoticeService(noticeService);
		 when(noticeService.Updata(new Notice())).thenReturn(false);
		 test.Updata(1, "1", "1", request);
	     assertEquals("false",test.message);  
	    }
	 @Test 
	    public void test13() {  
		 test.Delete(1, request);
	    	assertEquals("error",test.message); 
	    } 
	 
	 @Test 
	    public void test14() {  
		 noticeService = mock(NoticeService.class);
		 test.setNoticeService(noticeService);
		 when(noticeService.Delete(1)).thenReturn(true);
		 test.Delete(1, request);
	     assertEquals("true",test.message); 
	    }
	 @Test 
	    public void test15() {  
		 noticeService = mock(NoticeService.class);
		 test.setNoticeService(noticeService);
		 when(noticeService.Delete(1)).thenReturn(false);
		 test.Delete(1, request);
	     assertEquals("false",test.message);  
	    }
	 @Test 
	    public void test16() {  
		 test.SelectById("1", request);
	    	assertEquals("error",test.message); 
	    } 
	 
	 @Test 
	    public void test17() {  
		 noticeService = mock(NoticeService.class);
		 test.setNoticeService(noticeService);
		 Notice notice = new Notice();
		 when(noticeService.SelectByid(1)).thenReturn(notice);
		 when(noticeService.SelectPublisher(1)).thenReturn("1");
		 test.SelectById("1", request);
	     assertEquals("true",test.message); 
	    }
	 @Test 
	    public void test18() {  
		 noticeService = mock(NoticeService.class);
		 test.setNoticeService(noticeService);
		 Notice notice = null;
		 when(noticeService.SelectByid(1)).thenReturn(notice);
		 test.SelectById("1", request);
	     assertEquals("false",test.message);  
	    }
}
