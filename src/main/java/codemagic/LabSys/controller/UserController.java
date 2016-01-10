package codemagic.LabSys.controller;


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
import codemagic.LabSys.model.Student;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.StudentService;
import codemagic.LabSys.service.UserService;
import codemagic.LabSys.service.FunctionService;

@Controller
@RequestMapping("/userController")
public class UserController {
	private UserService userService;
	private FunctionService functionService;
	private StudentService studentService;

	public StudentService getStudentService() {
		return studentService;
	}
	@Autowired
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
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

	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/showStuDeatilInfo")
	public ModelAndView showStuDeatilInfo(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	   
		try {
			//业务逻辑

			if(user!=null){
			
				map.put("result", Boolean.TRUE);
				map.put("user", user);	
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
	
	 @SuppressWarnings({ "rawtypes", "unchecked", "finally"  })
	    @RequestMapping("/logout")
	    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
	    	ModelAndView mav = new ModelAndView();
			MappingJacksonJsonView view = new MappingJacksonJsonView();
			Map map = new HashMap();
	    	// 清空的session对象
	        HttpSession session = request.getSession();
	        try {
	                                   // session = null; 使session对象为空
	            session.invalidate(); // 此时的退出只是使session对象失效 
	            map.put("result", Boolean.TRUE);
	        } catch (Exception e) {
	        	map.put("result", Boolean.FALSE);
	        } finally {
	        	view.setAttributesMap(map);
				mav.setView(view);
				return mav;
	        }
	    }
	 
	 @SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	    @RequestMapping("/checkPassword")// 验证输入的当前密码是否正确
	    public ModelAndView CheckPassword( HttpServletRequest request,
	            HttpServletResponse response) {
	        ModelAndView mav = new ModelAndView();
	        MappingJacksonJsonView view = new MappingJacksonJsonView();
	        Map map = new HashMap();
	        try {
	            
	            HttpSession session = request.getSession();
	            User user = (User) session.getAttribute("user");
	            map.put("result", Boolean.TRUE);
	            map.put("message", "success");
	            map.put("user",user);//返回user信息，用于比较与输入的当前密码是否一致
	           
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
		@RequestMapping("/showUser")
		public ModelAndView showUser(int id, HttpServletRequest request) {
			ModelAndView mav = new ModelAndView();
			MappingJacksonJsonView view = new MappingJacksonJsonView();
			Map map = new HashMap();
			try {
				//业务逻辑
				User user = userService.findUserById(id);
				map.put("result", Boolean.TRUE);
				map.put("message", "success");
				map.put("user", user);
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
	    
	 
	 @SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	    @RequestMapping("/editPassword")//修改用户密码
	    public ModelAndView EditPassword(String password, HttpServletRequest request,
	            HttpServletResponse response) {
	        ModelAndView mav = new ModelAndView();
	        MappingJacksonJsonView view = new MappingJacksonJsonView();
	        Map map = new HashMap();
	        try {
	            // 业务逻辑
	            HttpSession session = request.getSession();
	            User user = (User) session.getAttribute("user");                                  
	            if (user.getUserType() != null) {
	                     int userId= user.getUserId();     
	                    if (user.getUserId() != null) {
	                        userService.EditInfoByUserId(userId,password); 
	                        map.put("result", Boolean.TRUE);
	                        map.put("message", "success");
	                     //   session.setAttribute("user",user.getUserPassword());//改变密码后重新设置session的值
	                    } else {
	                        map.put("result", Boolean.TRUE);
	                        map.put("message", "未找到信息");
	                    }
	                 
	            } else {
	                map.put("result", Boolean.FALSE);
	                map.put("message", "该用户没有可操作的权限！");
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
	 
	 @SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	    @RequestMapping("/showStudentList")//显示学生列表
	    public ModelAndView showStudentList(HttpServletResponse response) {
	        ModelAndView mav = new ModelAndView();
	        MappingJacksonJsonView view = new MappingJacksonJsonView();
	        Map map = new HashMap();
	        List<Student> students = studentService.showList();
 	        try {
	        	
	            if (!students.isEmpty()) 
	            { 
	            	map.put("result", Boolean.TRUE);
	            	map.put("students", students);	                   
	            }
	            else 
	            {
	                map.put("result", Boolean.FALSE);
	                map.put("message", "没有学生");
	            }
	        
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            view.setAttributesMap(map);
	            mav.setView(view);
	            return mav;
	        }
	    }
	 
	 @SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	    @RequestMapping("/addUser")
	    public ModelAndView addUser(String account, String type, HttpServletResponse response) {
	        ModelAndView mav = new ModelAndView();
	        MappingJacksonJsonView view = new MappingJacksonJsonView();
	        Map map = new HashMap();
	        try {
	        	User user = new User();
	        	user.setUserAccount(account);
	        	user.setUserPassword("123456");
	        	user.setUserType(Integer.parseInt(type));
	            if (userService.addUser(user)) 
	            { 
	            	map.put("result", Boolean.TRUE);
	            	map.put("user", user);	                   
	            }
	            else 
	            {
	                map.put("result", Boolean.FALSE);
	                map.put("message", "添加用户失败");
	            }
	        
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            view.setAttributesMap(map);
	            mav.setView(view);
	            return mav;
	        }
	    }
	 
	 @SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	    @RequestMapping("/resetPassword")
	    public ModelAndView resetPassword(String account, HttpServletResponse response) {
	        ModelAndView mav = new ModelAndView();
	        MappingJacksonJsonView view = new MappingJacksonJsonView();
	        Map map = new HashMap();
	        try {
	            if (userService.resetPassword(account)) 
	            { 
	            	map.put("result", Boolean.TRUE);
	            	map.put("message", "重置密码成功");	                   
	            }
	            else 
	            {
	                map.put("result", Boolean.FALSE);
	                map.put("message", "重置密码失败");
	            }
	        
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            view.setAttributesMap(map);
	            mav.setView(view);
	            return mav;
	        }
	    }
	 
	 @SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	    @RequestMapping("/ShowList")
	    public ModelAndView ShowList(HttpServletResponse response) {
	        ModelAndView mav = new ModelAndView();
	        MappingJacksonJsonView view = new MappingJacksonJsonView();
	        Map map = new HashMap();
	        try {
	        	List<User> users = userService.ShowList();
	            if (!users.isEmpty()) 
	            { 
	            	map.put("result", Boolean.TRUE);
	            	map.put("users", users);	                   
	            }
	            else 
	            {
	                map.put("result", Boolean.FALSE);
	                map.put("message", "没有用户");
	            }
	        
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            view.setAttributesMap(map);
	            mav.setView(view);
	            return mav;
	        }
	    }
	 
	 @SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	    @RequestMapping("/SelectByType")
	    public ModelAndView SelectByType(String type, HttpServletResponse response) {
	        ModelAndView mav = new ModelAndView();
	        MappingJacksonJsonView view = new MappingJacksonJsonView();
	        Map map = new HashMap();
	        try {
	        	int type_int = Integer.parseInt(type);
	        	List<User> users;
	        	if(type_int == 0)
	        	{
	        		users = userService.ShowList();
	        	}
	        	else
	        	{
	        		users = userService.SelectByType(Integer.parseInt(type));
	        	}
	            if (!users.isEmpty()) 
	            { 
	            	map.put("result", Boolean.TRUE);
	            	map.put("users", users);	                   
	            }
	            else 
	            {
	                map.put("result", Boolean.FALSE);
	                map.put("message", "没有用户");
	            }
	        
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            view.setAttributesMap(map);
	            mav.setView(view);
	            return mav;
	        }
	    }
	 
	 @SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	    @RequestMapping("/Delete")
	    public ModelAndView Delete(String id, HttpServletResponse response) {
	        ModelAndView mav = new ModelAndView();
	        MappingJacksonJsonView view = new MappingJacksonJsonView();
	        Map map = new HashMap();
	        try {
	        	boolean successed = userService.Delete(Integer.parseInt(id));
	            if (successed) 
	            { 
	            	map.put("result", Boolean.TRUE);
	            	map.put("message", "删除成功");	                   
	            }
	            else 
	            {
	                map.put("result", Boolean.FALSE);
	                map.put("message", "删除失败");
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
