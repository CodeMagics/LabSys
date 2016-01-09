package codemagic.LabSys.controller;

import java.text.SimpleDateFormat;
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
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.PlanService;
import codemagic.LabSys.service.UserService;

@Controller
@RequestMapping("/planController")
public class PlanController {
	private PlanService planService;
    private UserService userService;
	public PlanService getPlanService() {
		return planService;
	}
	@Autowired
	public void setUserService(PlanService planService) {
		this.planService = planService;
	}
	/**
	 * 根据当前用户ID查看该用户的学习计划列表
	 * @param userId 用户ID
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/showList")
	public ModelAndView showList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {
			List<Plan> planList = planService.ShowList(user.getUserId());
			if(planList != null){
			map.put("result", Boolean.TRUE);
			map.put("planList", planList);
			
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
	/**
	 * 创建新的学习计划
	 * @param record
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	@RequestMapping("/publishplan")
	public ModelAndView publishplan(String planTitle,String planDetails,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		HttpSession session = request.getSession();
		User planPubliser = (User) session.getAttribute("user");
		try {
			Plan record = new Plan();
			boolean successed;
			record.setPlanPubliser(planPubliser.getUserId());
			record.setPlanTitle(planTitle);
			record.setPlanDetails(planDetails);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			record.setPlanDate(df.format(new Date()));
			successed = planService.AddPlan(record);
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
	 * 查看一个学习计划
	 * @param planid 学习计划编号
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "finally", "unchecked", "rawtypes", "unused" })
	@RequestMapping("/checkplan")
	public ModelAndView checkplan(int planId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			Plan plan=planService.CheckPlan(planId);
			User user = userService.findUserById(plan.getPlanPubliser());
			if(plan != null){	
			map.put("result", Boolean.TRUE);
			map.put("plan", plan);
			map.put("planPublisher", user);
			
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
	 * 更新修改一个学习计划
	 * @param planid 学习计划编号
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	@RequestMapping("/updateplan")
	public ModelAndView updateplan(int planId, String planTitle, String planDetails,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean successed;
			Plan record = new Plan();
			record.setPlanId(planId);
			record.setPlanTitle(planTitle);
			record.setPlanDetails(planDetails);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			record.setPlanDate(df.format(new Date()));
			successed = planService.UpdatePlan(record);
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
	 * 删除一个学习计划
	 * @param planid 学习计划编号
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	@RequestMapping("/deleteplan")
	public ModelAndView deleteplan(int planId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean result = planService.DeletePlan(planId);
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
}