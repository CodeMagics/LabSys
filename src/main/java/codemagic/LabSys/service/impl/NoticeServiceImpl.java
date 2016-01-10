package codemagic.LabSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codemagic.LabSys.dao.NoticeMapper;
import codemagic.LabSys.model.Notice;
import codemagic.LabSys.model.Task;
import codemagic.LabSys.service.NoticeService;

@Service("/noticeService")
public class NoticeServiceImpl implements NoticeService{

	private NoticeMapper noticeMapper;

	public NoticeMapper getNoticeMapper() {
		return noticeMapper;
	}
	
	@Autowired
	public void setNoticeMapper(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	@SuppressWarnings("finally")
	public List<Notice> ShowList() {
		// TODO Auto-generated method stub
		List<Notice> list = new ArrayList<Notice>();
		try{
			list = noticeMapper.selectAll();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return list;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Notice> SelectList(int userId) {
		// TODO Auto-generated method stub
		List<Notice> list = new ArrayList<Notice>();
		try{
			list = noticeMapper.selectByPublisher(userId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return list;
		}
	}

	public boolean Publish(Notice notice) {
		// TODO Auto-generated method stub
		try{
			noticeMapper.insert(notice);
			return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
	}

	public boolean Updata(Notice notice) {
		// TODO Auto-generated method stub
		try{
		noticeMapper.updateByPrimaryKey(notice);
		return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean Delete(int noticeId) {
		// TODO Auto-generated method stub
		try{
		noticeMapper.deleteByPrimaryKey(noticeId);
		return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings("finally")
	public Notice SelectByid(int noticeId) {
		// TODO Auto-generated method stub
		Notice notice = null;
		try{
			notice = noticeMapper.selectByPrimaryKey(noticeId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			System.out.println("////"+notice.getNoticeTitle());
			return notice;
			
		}
	}
	
	@SuppressWarnings("finally")
	public String SelectPublisher(int noticeId) {
		// TODO Auto-generated method stub4Task task = null;
		String publisher = null;
		try{
			Notice notice=null;
			notice= noticeMapper.selectPublisher(noticeId);
			publisher=notice.getUserRealname();
			System.out.println(publisher+"/////////////////////////////");
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return publisher;
		}
	}


	
}
