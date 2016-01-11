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
import codemagic.LabSys.model.Student;
import codemagic.LabSys.model.Task;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.StudentService;
import codemagic.LabSys.service.UserService;
import codemagic.LabSys.service.FunctionService;

@Controller
@RequestMapping("/userController")
public class UserController {
	private UserService userService;
	private FunctionService functionService;
	public HttpSession session;
	public String message = null;
	public User user = new User();
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
			session = request.getSession();
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
					message = "true";
				}
				else{
					map.put("result", Boolean.FALSE);
					map.put("message", "用户名或者密码错误！");
					message = "false";
				}
			} catch (Exception e) {
				map.put("result", Boolean.FALSE);
				map.put("message", "执行出现出错！");
				e.printStackTrace();
				message = "error";
			}finally{
				view.setAttributesMap(map);
				mav.setView(view);
				mav.setViewName(message);
				return mav;
			}
		}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/showAuthorityList")
	public ModelAndView showAuthorityList(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		session = request.getSession();
	    User user = (User) session.getAttribute("user");
		try {
			//业务逻辑

			if(user!=null){
			
				
				List<Function> list=this.functionService.findFunListByUser(user);	
				map.put("result", Boolean.TRUE);
				map.put("user", user);
				map.put("functionList", list);
				map.put("message", "执行成功！");
				message = "true";
			}else {
				map.put("result", Boolean.FALSE);
				map.put("message", "用户已经退出！");
				message = "false";
			}
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！");
			message = "error";
			e.printStackTrace();
		}finally{
			
			view.setAttributesMap(map);
			mav.setView(view);
			mav.setViewName(message);
			return mav;
		}
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/showStuDeatilInfo")
	public ModelAndView showStuDeatilInfo(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		session = request.getSession();
	    User user = (User) session.getAttribute("user");
	   
		try {
			//业务逻辑

			if(user!=null){
			
				map.put("result", Boolean.TRUE);
				map.put("user", user);	
				map.put("message", "执行成功！");
				message = "true";
			}else {
				map.put("result", Boolean.FALSE);
				map.put("message", "用户已经退出！");
				message = "false";
			}
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！");
			e.printStackTrace();
		}finally{
			
			view.setAttributesMap(map);
			mav.setView(view);
			mav.setViewName(message);
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
	        session = request.getSession();
	        try {
	                                   // session = null; 使session对象为空
	            session.invalidate(); // 此时的退出只是使session对象失效 
	            map.put("result", Boolean.TRUE);
	            message = "true";
	        } catch (Exception e) {
	        	map.put("result", Boolean.FALSE);
	        } finally {
	        	view.setAttributesMap(map);
				mav.setView(view);
				mav.setViewName(message);
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
	            
	            session = request.getSession();
	            User user = (User) session.getAttribute("user");
	            map.put("result", Boolean.TRUE);
	            map.put("message", "success");
	            map.put("user",user);//返回user信息，用于比较与输入的当前密码是否一致
	            message = "true";
	           
	        } catch (Exception e) {
	            map.put("result", Boolean.FALSE);
	            map.put("message", "执行出现出错！");
	            e.printStackTrace();
	        } finally {
	            view.setAttributesMap(map);
	            mav.setView(view);
	            mav.setViewName(message);
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
				message = "true";
			} catch (Exception e) {
				map.put("result", Boolean.FALSE);
				map.put("message", "执行出现出错！");
				message = "error";
				e.printStackTrace();
			}finally{
				view.setAttributesMap(map);
				mav.setView(view);
				mav.setViewName(message);
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
	        session = request.getSession();
            User user = (User) session.getAttribute("user");
	        try {
	            // 业务逻辑	                                              
	            if (user.getUserType() != null) {    
	                    if (user.getUserId() != null) {
	                        userService.EditInfoByUserId(user.getUserId(),password); 
	                        map.put("result", Boolean.TRUE);
	                        map.put("message", "success");
	                        message = "true1";
	                     //   session.setAttribute("user",user.getUserPassword());//改变密码后重新设置session的值
	                    } else {
	                        map.put("result", Boolean.TRUE);
	                        map.put("message", "未找到信息");
	                        message = "true2";
	                    }
	                 
	            } else {
	                map.put("result", Boolean.FALSE);
	                map.put("message", "该用户没有可操作的权限！");
	                message = "false";
	            }
	        
	        } catch (Exception e) {
	            map.put("result", Boolean.FALSE);
	            map.put("message", "执行出现出错！");
	            message = "error";
	            e.printStackTrace();
	        } finally {
	            view.setAttributesMap(map);
	            mav.setView(view);
	            mav.setViewName(message);
	            return mav;
	        }
	    }
	 
	
	 
	 
	 @SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	    @RequestMapping("/Delete")
	    public ModelAndView Delete(int userId, HttpServletResponse response) {
	        ModelAndView mav = new ModelAndView();
	        MappingJacksonJsonView view = new MappingJacksonJsonView();
	        Map map = new HashMap();
	        try {
	        	
	        	
	            if (userService.Delete(userId)) 
	            { 
	            	map.put("result", Boolean.TRUE);
	            	map.put("message", "删除成功");
	            	message = "true";	                   
	            }
	            else 
	            {
	                map.put("result", Boolean.FALSE);
	                map.put("message", "删除失败");
	                message = "false";
	            }
	        
	        } catch (Exception e) {
	        	message = "error";
	            e.printStackTrace();
	        } finally {
	            view.setAttributesMap(map);
	            mav.setView(view);
	            mav.setViewName(message);
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
	        	user.setUserAccount(account);
	        	user.setUserPassword("123456");
	        	user.setUserType(Integer.parseInt(type));
	            if (userService.addUser(user)) 
	            { 
	            	map.put("result", Boolean.TRUE);
	            	map.put("message", "添加用户成功");
	            	map.put("user", user);
	            	message = "true";
	            }
	            else 
	            {
	                map.put("result", Boolean.FALSE);
	                map.put("message", "添加用户失败");
	                message = "false";
	            }
	        
	        } catch (Exception e) {
	        	message = "error";
	            e.printStackTrace();
	        } finally {
	            view.setAttributesMap(map);
	            mav.setView(view);
	            mav.setViewName(message);
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
	            	message = "true";
	            }
	            else 
	            {
	                map.put("result", Boolean.FALSE);
	                map.put("message", "重置密码失败");
	                message = "false";
	            }
	        
	        } catch (Exception e) {
	        	message = "error";
	            e.printStackTrace();
	        } finally {
	            view.setAttributesMap(map);
	            mav.setView(view);
	            mav.setViewName(message);
	            return mav;
	        }
	    }
	 
	 @SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	    @RequestMapping("/ShowList")
	    public ModelAndView ShowList(int page,String type,
	    		HttpServletRequest request,HttpServletResponse response) {
	        ModelAndView mav = new ModelAndView();
	        MappingJacksonJsonView view = new MappingJacksonJsonView();
	        Map map = new HashMap();
	        HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("user");
	        try {
	        	List<User> users = userService.ShowList();
	            if (!users.isEmpty()) 
	            { 
	            	int recordCount = users.size();// 总记录数
					int pageCount;// 总页数
					int temp = recordCount % 5;// 5条记录一页
					if (temp == 0) {
						pageCount = recordCount / 5;
					} else {
						pageCount = recordCount / 5 + 1;
					}
					
					List<User> pageList=new ArrayList<User>();
					int max=users.size()>page*5?page*5:users.size();
					for (int i = (page - 1) * 5; i <max; i++) {
						pageList.add(users.get(i));
					}
					map.put("pageList", pageList);
					map.put("pageCount", pageCount);
					map.put("page", page);
					
				    map.put("result", Boolean.TRUE);
				    map.put("users", users);
				    map.put("user", user);
				
				
	            	map.put("result", Boolean.TRUE);
	            	map.put("users", users);	
	            	message = "true";
	            }
	            else 
	            {
	                map.put("result", Boolean.FALSE);
	                map.put("message", "没有用户");
	                message = "false";
	            }
	        
	        } catch (Exception e) {
	        	message = "error";
	            e.printStackTrace();
	        } finally {
	            view.setAttributesMap(map);
	            mav.setView(view);
	            mav.setViewName(message);
	            return mav;
	        }
	    }
	 
	 @SuppressWarnings({ "unchecked", "finally", "rawtypes" })
	    @RequestMapping("/SelectByType")
	    public ModelAndView SelectByType(int page,String type,
	    		HttpServletRequest request,HttpServletResponse response) {
	        ModelAndView mav = new ModelAndView();
	        MappingJacksonJsonView view = new MappingJacksonJsonView();
	        Map map = new HashMap();
	        HttpSession session = request.getSession();
		    User user = (User) session.getAttribute("user");
	        try {
	        	List<User> users;
	        	if(Integer.parseInt(type)==0){
	        		users=userService.ShowList();
	        	}
	        	else{
	        		users = userService.SelectByType(Integer.parseInt(type));
	        		
	        	}
	        	
	            if (!users.isEmpty()) 
	            { 
	            	int recordCount = users.size();// 总记录数
					int pageCount;// 总页数
					int temp = recordCount % 5;// 5条记录一页
					if (temp == 0) {
						pageCount = recordCount / 5;
					} else {
						pageCount = recordCount / 5 + 1;
					}
					
					List<User> pageList=new ArrayList<User>();
					int max=users.size()>page*5?page*5:users.size();
					for (int i = (page - 1) * 5; i <max; i++) {
						pageList.add(users.get(i));
					}
					map.put("pageList", pageList);
					map.put("pageCount", pageCount);
					map.put("page", page);
	            	map.put("result", Boolean.TRUE);
	            	map.put("users", users);
	            	map.put("user", user);
	            	message = "true";
	            }
	            else 
	            {
	                map.put("result", Boolean.FALSE);
	                map.put("message", "没有用户");
	                message = "false";
	            }
	        
	        } catch (Exception e) {
	        	message = "error";
	            e.printStackTrace();
	        } finally {
	            view.setAttributesMap(map);
	            mav.setView(view);
	            mav.setViewName(message);
	            return mav;
	        }
	    }
}
