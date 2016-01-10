package codemagic.LabSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codemagic.LabSys.dao.FunctionMapper;
import codemagic.LabSys.model.Function;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.FunctionService;



@Service("functionService")
public class functionServiceImpl implements FunctionService {
	private FunctionMapper functionMapper;
	public FunctionMapper getFunctionMapper() {
		return functionMapper;
	}

	@Autowired
	public void setFunctionMapper(FunctionMapper functionMapper) {
		this.functionMapper = functionMapper;
	}

	@SuppressWarnings("finally")
	public List<Function> findFunListByUser(User user) {
		 List<Function> list=new ArrayList<Function>();
	        try{
	            list= functionMapper.selectListUserType(user.getUserType());  
	            
	        }catch (Exception e) {
	            e.printStackTrace();
	        }finally{
	        	if(list==null)
	        		System.out.print("###########");
	        	else
	        		System.out.print("duide ###");
	            return list;
	        }
	        
	}

 /*   @SuppressWarnings("finally")
    public Function findFunctionById(Integer funcId) {
        Function list=new Function();
        try{
            list= functionMapper.selectByPrimaryKey(funcId);           
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            return list;
        }
    }*/
    
  

}
