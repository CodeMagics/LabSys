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

import codemagic.LabSys.model.Summary;
import codemagic.LabSys.service.SummaryService;

public class SummaryController {
	private SummaryService summaryService;

	public SummaryService getsummaryService() {
		return summaryService;
	}
	@Autowired
	public void setUserService(SummaryService summaryService) {
		this.summaryService = summaryService;
	}
	/**
	 * 创建新的学习总结
	 * @param userId  用户编号
	 * @param summaryTitle  总结标题
	 * @param summaryDetails  总结详情
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	@RequestMapping("/publishsummary")
	public ModelAndView PublishSummary(int sumPubliser,String sumTitle,String sumDetails, 
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean successed;
			Summary record = new Summary();
			record.setSumPubliser(sumPubliser);
			record.setSumTitle(sumTitle);
			record.setSumDetails(sumDetails);
			successed = summaryService.AddSummary(record);
			if(successed != true){
			map.put("result", Boolean.TRUE);
			map.put("message", "创建成功！");
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "创建失败！");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	/**
	 * 删除一个学习总结
	 * @param summaryid 学习总结编号
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	@RequestMapping("/deletesummary")
	public ModelAndView DeleteSummary(int summaryId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean result = summaryService.DeleteSummary(summaryId);
			if(result == true){
			map.put("result", Boolean.TRUE);
			map.put("message", "删除成功！");
			
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
	
	/**
	 * 修改一个学习计划
	 * @param summaryid 学习总结编号
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	@RequestMapping("/updatesummary")
	public ModelAndView UpdateSummary(int sumId, String sumTitle,String sumDetails, 
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean successed;
			Summary record = new Summary();
			record.setSumId(sumId);
			record.setSumTitle(sumTitle);
			record.setSumDetails(sumDetails);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			record.setSumDate(df.format(new Date()));
			successed = summaryService.UpdateSummary(record);
			if(successed == true){
			map.put("result", Boolean.TRUE);
			map.put("message", "修改成功！");
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "修改失败！");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	/**
	 * 查看一个学习计划
	 * @param summaryid 学习计划编号
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	@RequestMapping("/checksummary")
	public ModelAndView CheckSummary(int summaryId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			Summary summary = new Summary();
			summary = summaryService.CheckSummary(summaryId);
			if(summary != null){
			map.put("result", Boolean.TRUE);
			map.put("summary", summary);
			
			} else {
				map.put("result", Boolean.FALSE);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	/**
	 * 根据当前用户ID查看该用户的学习计划列表
	 * @param userid 用户ID
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/showList")
	public ModelAndView ShowList(int userId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			List<Summary> summarys = summaryService.ShowList(userId);
			if(summarys != null){
			map.put("result", Boolean.TRUE);
			map.put("summarys", summarys);
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "没有任何学习计划！");
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
