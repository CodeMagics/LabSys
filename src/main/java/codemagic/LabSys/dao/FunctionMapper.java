package codemagic.LabSys.dao;

import java.util.List;

import codemagic.LabSys.model.Function;

public interface FunctionMapper {
    int deleteByPrimaryKey(Integer funcId);

    int insert(Function record);

    int insertSelective(Function record);

    Function selectByPrimaryKey(Integer funcId);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);
    List<Function>selectListUserType(int userType);
}