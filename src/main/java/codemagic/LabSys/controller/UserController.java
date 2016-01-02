package codemagic.LabSys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import codemagic.LabSys.model.Function;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.UserService;
import codemagic.LabSys.service.FunctionService;

@Controller
@RequestMapping("/userController")
public class UserController {
	private UserService userService;
	private FunctionService functionService;

	public FunctionService getFunctionService() {
		return functionService;
	}
	@Autowired
	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}
	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
//	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
//	@RequestMapping("/login")
//	public ModelAndView login(String loginname, String password,
//			HttpServletRequest request,Http) {
//		ModelAndView mav = new ModelAndView();
//		MappingJacksonJsonView view = new MappingJacksonJsonView();
//		Map map = new HashMap();
//		HttpSession session = request.getSession();
//		try {
//			
//			User user = userService.login(loginname,password);
//			if(user != null){
//			session.setAttribute("user", user);
//			map.put("result", Boolean.TRUE);
//			map.put("message", "登陆成功！");
//			map.put("user", user);
//
//			
//			} else {
//				map.put("result", Boolean.FALSE);
//				map.put("message", "登陆失败！");
//			}
//			
//			
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		} finally {
//			view.setAttributesMap(map);
//			mav.setView(view);
//			return mav;
//		}
//	}
	
	 @SuppressWarnings({ "finally", "unchecked" })
		@RequestMapping("/login")
	    public ModelAndView selectUserById(String loginname,String password, HttpServletRequest request,HttpServletResponse response) {
	    	ModelAndView mav = new ModelAndView();
			MappingJacksonJsonView view = new MappingJacksonJsonView();
			@SuppressWarnings("rawtypes")
			Map map = new HashMap();
			HttpSession session = request.getSession();
			try {
				User user=new User();
				user=userService.login(loginname,password);
				if(user!=null){
					session.setAttribute("user", user);
					//System.out.println("*****"+user.getUserAccount());
					//System.out.println("*****"+((User) session.getAttribute("user")).getUserAccount());
					
					map.put("result", Boolean.TRUE);
					map.put("message", "成功！");
					map.put("user", user);
				}
				else{
					map.put("result", Boolean.FALSE);
					map.put("message", "用户名或者密码错误！");
				}
			} catch (Exception e) {
				map.put("result", Boolean.FALSE);
				map.put("message", "执行出现出错！");
				e.printStackTrace();
			}finally{
				view.setAttributesMap(map);
				mav.setView(view);
				return mav;
			}
		}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/showAuthorityList")
	public ModelAndView showAuthorityList(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	   
		try {
			//业务逻辑

			if(user!=null){
			
				System.out.println(user.getUserType()+"?/////////////");
				List<Function> list=this.functionService.findFunListByUser(user);	
				map.put("result", Boolean.TRUE);
				map.put("user", user);
				map.put("functionList", list);
				map.put("message", "执行成功！");
			}else {
				map.put("result", Boolean.FALSE);
				map.put("message", "用户已经退出！");
			}
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！");
			e.printStackTrace();
		}finally{
			
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}

}
