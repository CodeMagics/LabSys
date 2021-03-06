package codemagic.LabSys.service;

import java.util.List;

import codemagic.LabSys.model.Notice;
import codemagic.LabSys.model.Task;

public interface NoticeService {
	/**
	 * 显示所有公告
	 * @return
	 */
	public List<Notice> ShowList();
	
	/**
	 * 根据userid查询该用户发布的所有公告
	 * @param userid
	 * @return
	 */
	public List<Notice> SelectList(int userId);
	
	/**
	 * 发布公告
	 * @param notice
	 * @return
	 */
	public boolean Publish(Notice notice);
	
	/**
	 * 修改公告
	 * @param notice
	 * @return
	 */
	public boolean Updata(Notice notice);
	
	public boolean Delete(int noticeId);
	
	
    public Notice SelectByid(int noticeId);
	
	/**
	 * 根据taskid查询发布者
	 * @param Id
	 * @return
	 */
	public String SelectPublisher(int noticeId);
}
