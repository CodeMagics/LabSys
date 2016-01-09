package codemagic.LabSys.service;

import codemagic.LabSys.model.Student;

public interface StudentService {
	/*
	 * 通过学生的用户id进行查找
	 */
	Student selectStuByUserId(int id);
	/*
	 * 更新学生信息
	 */
	boolean updateStuByUserId(int id,String name,int num,String major,
			String StudClass,String email, String phone);
}
