package codemagic.LabSys.dao;

import java.util.List;

import codemagic.LabSys.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int updateByUserid(String userPassword,Integer userId);
    /*
     * 输入用户信息
     * return 判断用户是否存在
     */
    User checkUser(User record);
    /*
     * 输入用户名和密码
     * @return 用户
     */
   	User selectByNameAndPwd(User record);
   	
   	int updatePassword(String userPassword, String userAccount);
   	
	List<User> ShowList();
	
	List<User> SelectByType(Integer type);
	
	int addUser(User user);
}