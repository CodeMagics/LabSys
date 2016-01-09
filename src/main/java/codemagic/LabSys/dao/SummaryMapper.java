package codemagic.LabSys.dao;

import java.util.List;

import codemagic.LabSys.model.Summary;

public interface SummaryMapper {
    int deleteByPrimaryKey(Integer sumId);

    int insert(Summary record);

    int insertSelective(Summary record);

    Summary selectByPrimaryKey(Integer sumId);

    int updateByPrimaryKeySelective(Summary record);

    int updateByPrimaryKey(Summary record);

	List<Summary> selectByPublisherID(Integer userid);
}