package codemagic.LabSys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import codemagic.LabSys.model.Notice;
import codemagic.LabSys.service.NoticeService;

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
	public ModelAndView ShowList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			List<Notice> notices = noticeService.ShowList();
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
	public ModelAndView Publish(int noticePublisher, String noticeDetails, String noticeTitle, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
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
			map.put("notices", "发布成功");
			
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
			map.put("notices", "更新成功");
			
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
		try {
			boolean successed;
			successed = noticeService.Delete(noticeId);
			if(successed){
			map.put("result", Boolean.TRUE);
			map.put("notices", "删除成功");
			
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
	
}
