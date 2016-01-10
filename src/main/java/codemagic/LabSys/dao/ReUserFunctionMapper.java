package codemagic.LabSys.dao;

import codemagic.LabSys.model.ReUserFunction;

public interface ReUserFunctionMapper {
    int deleteByPrimaryKey(Integer reufId);

    int insert(ReUserFunction record);

    int insertSelective(ReUserFunction record);

    ReUserFunction selectByPrimaryKey(Integer reufId);

    int updateByPrimaryKeySelective(ReUserFunction record);

    int updateByPrimaryKey(ReUserFunction record);
}