package codemagic.LabSys.service;

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
}
