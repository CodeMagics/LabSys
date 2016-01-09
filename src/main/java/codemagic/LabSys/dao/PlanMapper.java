package codemagic.LabSys.dao;

import java.util.List;

import codemagic.LabSys.model.Plan;

public interface PlanMapper {
    int deleteByPrimaryKey(Integer planId);

    int insert(Plan record);

    int insertSelective(Plan record);

    Plan selectByPrimaryKey(Integer planId);

    int updateByPrimaryKeySelective(Plan record);

    int updateByPrimaryKey(Plan record);

	List<Plan> selectByPublisherID(int userid);
}