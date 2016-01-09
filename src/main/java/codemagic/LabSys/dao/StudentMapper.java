package codemagic.LabSys.dao;

import codemagic.LabSys.model.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer studId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    /*
     * 通过学生的用户id查询
     */
    Student selectByStudUserId(Integer studUserid);
    /*
     * 
     */
    int updateByStudUserId(Integer studId,String userRealname,Integer studNum,String studMajor,
			String studClass,String userEMail, String userPhone);
}