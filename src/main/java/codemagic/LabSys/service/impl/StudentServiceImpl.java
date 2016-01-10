package codemagic.LabSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codemagic.LabSys.dao.StudentMapper;
import codemagic.LabSys.model.Student;
import codemagic.LabSys.model.User;
import codemagic.LabSys.service.StudentService;

@Service("/studentService")
public class StudentServiceImpl implements StudentService {
	private StudentMapper studentMapper;
	public StudentMapper getStudentMapper() {
		return studentMapper;
	}
	@Autowired
	public void setStudentMapper(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;		
		
	}
	

	@SuppressWarnings("finally")
	public Student selectStuByUserId(int id) {
		Student student = null;
		try {
		    
			student = studentMapper.selectByStudUserId(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return student;
		}
	
	}
	public boolean updateStuByUserId(int id,String name,int num,String major,
			String StudClass,String email, String phone) {
		boolean result = false;
		try {
		    
			int type = studentMapper.updateByStudUserId(id,name,num,major,StudClass,email,phone);
			if(type==1){
				result=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("finally")
	public List<Student> showList() {
		// TODO Auto-generated method stub
		List<Student> list = new ArrayList<Student>();
		try{
			list=studentMapper.showList();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return list;
		}
	}
	
	
}
