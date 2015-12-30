package codemagic.LabSys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codemagic.LabSys.dao.UserMapper;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.UserService;

@Service("/userService")
public class UserServiceImpl implements UserService {
	private UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}
	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public User findUserById(int id) {
		// TODO Auto-generated method stub
		User user= new User();
		user=this.userMapper.selectByPrimaryKey(id);
		return user;
	}

}
