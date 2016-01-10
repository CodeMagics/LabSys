package codemagic.LabSys.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import codemagic.LabSys.model.Notice;
import codemagic.LabSys.model.Task;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.NoticeService;



@Controller
@RequestMapping("/noticeController")
public class NoticeController {
	private NoticeService noticeService;

	public NoticeService getNoticeService() {
		return noticeService;
	}
	@Autowired
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/ShowList")
	public ModelAndView ShowList(int page,int type,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
		try {
			List<Notice> notices = noticeService.ShowList();
			if(notices!=null){
				int recordCount = notices.size();// 总记录数
				int pageCount;// 总页数
				int temp = recordCount % 5;// 5条记录一页
				if (temp == 0) {
					pageCount = recordCount / 5;
				} else {
					pageCount = recordCount / 5 + 1;
				}
				
				List<Notice> pageList=new ArrayList<Notice>();
				int max=notices.size()>page*5?page*5:notices.size();
				for (int i = (page - 1) * 5; i <max; i++) {
					pageList.add(notices.get(i));
				}
				map.put("pageList", pageList);
				map.put("pageCount", pageCount);
				map.put("page", page);
				
			    map.put("result", Boolean.TRUE);
			    map.put("notices", notices);
			    map.put("user", user);
			
			
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "没有公告！");
			}
			
			
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！");
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/SelectList")
	public ModelAndView SelectList(int userId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			List<Notice> notices = noticeService.SelectList(userId);
			if(!notices.isEmpty()){
			map.put("result", Boolean.TRUE);
			map.put("notices", notices);
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "没有公告！");
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/Publish")
	public ModelAndView Publish( String noticeDetails, String noticeTitle, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
		
			HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("user");
			int noticePublisher=user.getUserId();
			boolean successed;
			Notice notice = new Notice();
			notice.setNoticePublisher(noticePublisher);
			notice.setNoticeDetails(noticeDetails);
			notice.setNoticeTitle(noticeTitle);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			notice.setNoticeDate(df.format(new Date()));
			successed = noticeService.Publish(notice);
			if(successed){
			map.put("result", Boolean.TRUE);
			map.put("message", "发布成功");
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "发布失败！");
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/Updata")
	public ModelAndView Updata(int noticeId, String noticeDetails, String noticeTitle, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean successed;
			Notice notice = new Notice();
			notice.setNoticeId(noticeId);
			notice.setNoticeDetails(noticeDetails);
			notice.setNoticeTitle(noticeTitle);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			notice.setNoticeDate(df.format(new Date()));
			successed = noticeService.Updata(notice);
			if(successed){
			map.put("result", Boolean.TRUE);
			map.put("message", "更新成功");
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "更新失败！");
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/Delete")
	public ModelAndView Delete(int noticeId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		System.out.println(noticeId+"////////////////");
		try {
			boolean successed;
			successed = noticeService.Delete(noticeId);
			if(successed){
			map.put("result", Boolean.TRUE);
			map.put("message", "删除成功");
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "删除失败！");
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/SelectById")
	public ModelAndView SelectById(String id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			Notice notice = noticeService.SelectByid(Integer.parseInt(id));
			if(notice!=null){
			String publisher = noticeService.SelectPublisher(Integer.parseInt(id));
			if(publisher!=null){
			map.put("publisher", publisher);
			}
			map.put("result", Boolean.TRUE);
			map.put("notice", notice);	
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "获取失败！");
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	
}
