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

import codemagic.LabSys.model.Plan;
import codemagic.LabSys.model.Summary;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.SummaryService;
import codemagic.LabSys.service.UserService;

@Controller
@RequestMapping("/summaryController")
public class SummaryController {
	private SummaryService summaryService;
	private UserService userService;
	
	public SummaryService getsummaryService() {
		return summaryService;
	}
	public UserService getuserService() {
		return userService;
	}
	@Autowired
	public void setsummaryService(SummaryService summaryService) {
		this.summaryService = summaryService;
	}
	public void setuserServiceService(UserService userService) {
		this.userService = userService;
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
	public ModelAndView PublishSummary(String sumTitle,String sumDetails, 
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		HttpSession session = request.getSession();
		User sumPubliser = (User) session.getAttribute("user");
		try {
			boolean successed;
			Summary record = new Summary();
			record.setSumPubliser(sumPubliser.getUserId());
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
	 * @param sumId 学习总结编号
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	@RequestMapping("/deletesummary")
	public ModelAndView DeleteSummary(String sumId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean result = summaryService.DeleteSummary(Integer.parseInt(sumId));
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
	 * @param sumid 学习总结编号
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	@RequestMapping("/updatesummary")
	public ModelAndView UpdateSummary(String sumId, String sumTitle,String sumDetails, 
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean successed;
			Summary record = new Summary();
			record.setSumId(Integer.parseInt(sumId));
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
	 * @param sumId 学习计划编号
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	@RequestMapping("/checksummary")
	public ModelAndView CheckSummary(String sumId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			Summary summary = new Summary();
			summary = summaryService.CheckSummary(Integer.parseInt(sumId));
			if(summary != null){
				String publisher = userService.findUserById(summary.getSumPubliser()).getUserRealname();
				if(publisher != null)
				{
			map.put("result", Boolean.TRUE);
			map.put("summary", summary);
			map.put("sumpublisher", publisher);
				}
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
	public ModelAndView ShowList(int page,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {
			List<Summary> summarys = summaryService.ShowList(user.getUserId());
			if(summarys != null){
				int recordCount = summarys.size();// 总记录数
				int pageCount;// 总页数
				int temp = recordCount % 5;// 5条记录一页
				if (temp == 0) {
					pageCount = recordCount / 5;
				} else {
					pageCount = recordCount / 5 + 1;
				}
				
				List<Summary> pageList=new ArrayList<Summary>();
				int max=summarys.size()>page*5?page*5:summarys.size();
				for (int i = (page - 1) * 5; i <max; i++) {
				pageList.add(summarys.get(i));
				}
				map.put("pageList", pageList);
				map.put("pageCount", pageCount);
				map.put("page", page);
				
				
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