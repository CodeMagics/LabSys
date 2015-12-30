package codemagic.LabSys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import codemagic.LabSys.model.User;
import codemagic.LabSys.service.UserService;

@Controller
@RequestMapping("/userController")
public class UserController {
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/login")
	public ModelAndView login(String loginname, String password,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			User user = userService.login(loginname,password);
			if(user != null){
			map.put("result", Boolean.TRUE);
			map.put("message", "登陆成功！");
			map.put("user", user);
			
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "登陆失败！");
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
