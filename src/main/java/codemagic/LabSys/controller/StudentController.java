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
	 
	 public HttpSession session;
	 public String message;
	 public	User user = new User();
	 
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
		public ModelAndView readStudentByStudNumber(int id,HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView();
			MappingJacksonJsonView view = new MappingJacksonJsonView();
			Map map = new HashMap();
			session = request.getSession();// 获取session
			user = (User) session.getAttribute("user");
			
		
			try {
				if (user == null) {
					
					map.put("result", Boolean.FALSE);
					map.put("message", "用户已经退出！请重新登录");
					message = "false";
				} else {
					    int UserId;
					    if(id==-1){
					    	UserId=user.getUserId();
					    	
					    }else{
					    	UserId=id;
					    	user=userService.findUserById(UserId);
					    }
					
						Student student;
						student = studentService.selectStuByUserId(UserId);
						
						StudentDetailInfo studentInfo=new StudentDetailInfo();
						String StudClass="未填写";
		             
						if(student.getStudClass()!=null){
							StudClass=student.getStudClass();
						}
		                
						studentInfo.setStudClass(StudClass);
						
						studentInfo.setStudNum(student.getStudNum());
						
						studentInfo.setUserEMail(user.getUserEMail());
						
						studentInfo.setUserPhone(user.getUserPhone());
						
						String name="未填写";
						if(user.getUserRealname()!=null){
							name=user.getUserRealname();
						}
						
						studentInfo.setUserRealname(name);
							
						String major="未填写";
						if(student.getStudMajor()!=null){
							
							major=student.getStudMajor();
						}
						
						studentInfo.setStudMajor(major);
						
						
						map.put("result", Boolean.TRUE);
						map.put("message", "执行成功！");
						map.put("student",studentInfo);
						map.put("user",user);
						message = "true";	
						
				}
			} catch (Exception e) {
				map.put("result", Boolean.FALSE);
				map.put("message", "执行出现出错！");
				message = "error";
				e.printStackTrace();
			} finally {
				view.setAttributesMap(map);
				mav.setView(view);
				return mav;
			}
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
		@RequestMapping("/updateStudent")
		public ModelAndView updateStudent(String name,String num,
			String major,String studClass,
				String email,String phone,
				HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView();
			MappingJacksonJsonView view = new MappingJacksonJsonView();
			Map map = new HashMap();
			session = request.getSession();// 获取session
			user = (User) session.getAttribute("user");
            
			try {if (user == null) {
				map.put("result", Boolean.FALSE);
				map.put("message", "用户已经退出！请重新登录");
				message = "other";
			} else {
				if (user.getUserType() == 1) {
					boolean result;
					result=true;
					int number=0;
					if(num!=""){
						number= Integer.parseInt(num);
					}
						
					
					result= studentService.updateStuByUserId(user
						.getUserId(),name,number,major,studClass,email,phone);
					user.setUserEMail(email);
					user.setUserPhone(phone);
					user.setUserRealname(name);
					
					boolean result2=userService.updateUser(user);
					if(result&&result2){
						map.put("result", Boolean.TRUE);
						map.put("message", "执行成功！");
						message = "true";
					}else{
						map.put("result", Boolean.FALSE);
						map.put("message", "更新失败！");
						message = "false";
					}
						
					
				
					
				}
					
					
			}
			} catch (Exception e) {
				map.put("result", Boolean.FALSE);
				map.put("message", "执行出现出错！");
				message = "error";
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
			session = request.getSession();// 获取session
			user = (User) session.getAttribute("user");
			try {
				if (user == null) {
					map.put("result", Boolean.FALSE);
					map.put("message", "用户已经退出！请重新登录");
					message = "other";
				} else {
					if (user.getUserType() == 1) {
						boolean result;
						result= studentService.updateStuByUserId(user
								.getUserId(),name,num,major,StudClass,email,phone);
						if(result){
							map.put("result", Boolean.TRUE);
							map.put("message", "执行成功！");
							message = "true";
						}else{
							map.put("result", Boolean.TRUE);
							map.put("message", "更新失败！");
							message = "false";
						}
							
						
					
						
					}
						
						
				}
			} catch (Exception e) {
				map.put("result", Boolean.FALSE);
				map.put("message", "执行出现出错！");
				message = "error";
				e.printStackTrace();
			} finally {
				view.setAttributesMap(map);
				mav.setView(view);
				return mav;
			}
		}

		

}
