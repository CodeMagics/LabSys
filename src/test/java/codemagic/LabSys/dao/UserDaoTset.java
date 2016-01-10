package codemagic.LabSys.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;  
 
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import codemagic.LabSys.model.User;

@RunWith(SpringJUnit4ClassRunner.class)   
public class UserDaoTset {

	@Autowired  
    private UserMapper userDao;  
  
    @Test  
    public void test() {  
        User user = new User();  
        
    }  
  

}
