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

import codemagic.LabSys.model.Student;
import codemagic.LabSys.model.StudentDetailInfo;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.StudentService;
import codemagic.LabSys.service.UserService;

@Controller
@RequestMapping("/studentController")
public class StudentController {
	
	 private UserService userService;
	 private StudentService studentService;
		public UserService getUserService() {
	        return userService;
	    }
		@Autowired
	    public void setUserService(UserService userService) {
	        this.userService = userService;
	    }
		
		public StudentService getStudentService() {
			return studentService;
		}

		@Autowired
		public void setStudentService(StudentService studentService) {
			this.studentService = studentService;
		}
		

		@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
		@RequestMapping("/readStudentByStudNumber")
		public ModelAndView readStudentByStudNumber(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView();
			MappingJacksonJsonView view = new MappingJacksonJsonView();
			Map map = new HashMap();
			User user = new User();
			HttpSession session = request.getSession();// 获取session
			user = (User) session.getAttribute("user");
            
			try {// 查reuserrol 判断角色
				if (user == null) {
					map.put("result", Boolean.FALSE);
					map.put("message", "用户已经退出！请重新登录");

				} else {
					if (user.getUserType() == 1) {
						Student student;
						student = studentService.selectStuByUserId(user
								.getUserId());
						StudentDetailInfo studentInfo=new StudentDetailInfo();
						studentInfo.setStudClass(student.getStudClass());
						studentInfo.setStudNum(student.getStudNum());
						studentInfo.setUserEMail(user.getUserEMail());
						studentInfo.setUserPhone(user.getUserPhone());
						studentInfo.setUserRealname(user.getUserRealname());
						studentInfo.setStudMajor(student.getStudMajor());
						map.put("result", Boolean.TRUE);
						map.put("message", "执行成功！");
						map.put("student",studentInfo);
						
						
					}
						
						
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
		@RequestMapping("/updateInfo")
		public ModelAndView updateStudentInfo(String name,int num,String major,
				String StudClass,String email,
				String phone,HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView();
			MappingJacksonJsonView view = new MappingJacksonJsonView();
			Map map = new HashMap();
			User user = new User();
			HttpSession session = request.getSession();// 获取session
			user = (User) session.getAttribute("user");
            System.out.println("_______");
			try {
				if (user == null) {
					map.put("result", Boolean.FALSE);
					map.put("message", "用户已经退出！请重新登录");

				} else {
					if (user.getUserType() == 1) {
						boolean result;
						result= studentService.updateStuByUserId(user
								.getUserId(),name,num,major,StudClass,email,phone);
						if(result){
							map.put("result", Boolean.TRUE);
							map.put("message", "执行成功！");
						}else{
							map.put("result", Boolean.TRUE);
							map.put("message", "更新失败！");
						}
							
						
					
						
					}
						
						
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

		

}
