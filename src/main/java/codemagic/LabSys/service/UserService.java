package codemagic.LabSys.service;

import java.util.List;

import codemagic.LabSys.model.User;

public interface UserService {
	/**
	 * 通过主键查询用户
	 * @param id
	 * @return
	 */
	public User findUserById(int id);
	
	public User checkUser(String loginName, String passWord);

	public User login(String loginName, String passWord);
	
	public  void EditInfoByUserId(int userId,String password );
	
	public boolean updateUser(User user);
	
	public boolean addUser(User user);
	
	public boolean resetPassword(String account);
	
	public List<User> ShowList();
	
	public List<User> SelectByType(int type);
	
	public boolean Delete(int userId);
}
