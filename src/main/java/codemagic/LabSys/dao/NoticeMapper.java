package codemagic.LabSys.dao;

import java.util.List;

import codemagic.LabSys.model.Notice;
import codemagic.LabSys.model.Task;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer noticeId);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer noticeId);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
    
    List<Notice> selectAll();
    
    List<Notice> selectByPublisher(Integer userId);
    
    Notice selectPublisher(Integer noticeId);
}