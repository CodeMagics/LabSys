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
	
	@SuppressWarnings("finally")
	public User checkUser(String loginName, String passWord) {
		User result = null;
		try {
			User record = new User();
			record.setUserAccount(loginName);
			record.setUserPassword(passWord);
			
			result = userMapper.checkUser(record);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			return result;
		}
	}
	
	
	@SuppressWarnings("finally")
	public User login(String loginName, String passWord) {
		User user = null;
		try {
			User record = new User();
			record.setUserAccount(loginName);
			record.setUserPassword(passWord);
			user = userMapper.selectByNameAndPwd(record);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return user;
		}
	}
	

    @SuppressWarnings("finally")
    public void EditInfoByUserId(int userId, String password) {
        User user = new User();
        try {
            user = userMapper.selectByPrimaryKey(userId);
            user.setUserPassword(password);
            //userMapper.updateByPrimaryKeySelective(user);
            userMapper.updateByUserid(userId,password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return;
        }

    }

}
