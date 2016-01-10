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
import codemagic.LabSys.model.Task;
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
	public UserService getuserService() {
		return userService;
	}
	@Autowired
	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}
	public void setuserServiceService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 根据当前用户ID查看该用户的学习计划列表
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/showList")
	public ModelAndView showList(int page,int type,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
		try {
			List<Plan> plan = planService.ShowList(user.getUserId());
			if(!plan.isEmpty()){
				int recordCount = plan.size();// 总记录数
				int pageCount;// 总页数
				int temp = recordCount % 5;// 5条记录一页
				if (temp == 0) {
					pageCount = recordCount / 5;
				} else {
					pageCount = recordCount / 5 + 1;
				}
				
				List<Plan> pageList=new ArrayList<Plan>();
				int max=plan.size()>page*5?page*5:plan.size();
				for (int i = (page - 1) * 5; i <max; i++) {
					pageList.add(plan.get(i));
				}
				map.put("pageList", pageList);
				map.put("pageCount", pageCount);
				map.put("page", page);
				
			    map.put("result", Boolean.TRUE);
			    map.put("plan", plan);
			    map.put("user", user);
			
			
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "没有任务！");
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
	@SuppressWarnings({ "finally", "unchecked", "rawtypes" })
	@RequestMapping("/checkplan")
	public ModelAndView checkplan(String planId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			Plan plan=planService.CheckPlan(Integer.parseInt(planId));
			if(plan != null){
				String publisher = userService.findUserById(plan.getPlanPubliser()).getUserRealname();
				if(publisher!=null){
					
				}
			map.put("result", Boolean.TRUE);
			map.put("plan", plan);
			map.put("publisher", publisher);
			
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
	public ModelAndView updateplan(String planId, String planTitle, String planDetails,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean successed;
			Plan record = new Plan();
			record.setPlanId(Integer.parseInt(planId));
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
	public ModelAndView deleteplan(String planId,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			boolean result = planService.DeletePlan(Integer.parseInt(planId));
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