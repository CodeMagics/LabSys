package codemagic.LabSys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import codemagic.LabSys.model.Plan;
import codemagic.LabSys.service.PlanService;

public class PlanController {
	private PlanService planService;

	public PlanService getPlanService() {
		return planService;
	}
	@Autowired
	public void setUserService(PlanService planService) {
		this.planService = planService;
	}
	/**
	 * 根据当前用户ID查看该用户的学习计划列表
	 * @param userid 用户ID
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/showList")
	public ModelAndView showList(int userid,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			List<Plan> plans = planService.showList(userid);
			if(plans != null){
			map.put("result", Boolean.TRUE);
			map.put("plans", plans);
			
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
	public ModelAndView publishplan(Plan record,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean result=planService.addPlan(record);
			if(result != true){
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
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	@RequestMapping("/checkplan")
	public ModelAndView checkplan(int planid,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			Plan plan=planService.checkPlan(planid);
			if(plan != null){
			map.put("result", Boolean.TRUE);
			map.put("plan", plan);
			
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
	public ModelAndView updateplan(int planid,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			
			boolean result = planService.updatePlan(planid);
			if(result == true){
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
	public ModelAndView deleteplan(int planid,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean result = planService.deletePlan(planid);
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
