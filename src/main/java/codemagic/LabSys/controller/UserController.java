package codemagic.LabSys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import codemagic.LabSys.model.User;
import codemagic.LabSys.service.UserService;

@Controller
public class UserController {
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	@RequestMapping("/selectUserById")
	public ModelAndView selectUserById(int id, HttpServletRequest request,HttpServletResponse response) {
	    	ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		try {
			User user= new User();
			user=this.userService.findUserById(id);
			if(user != null){
				map.put("result", Boolean.TRUE);
				
			}else{
				map.put("result", Boolean.FALSE);
			}
			
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			e.printStackTrace();
		}finally{
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
}
