package codemagic.LabSys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import codemagic.LabSys.model.Notice;
import codemagic.LabSys.service.NoticeService;

@Service("/noticeService")
public class NoticeServiceImpl implements NoticeService{

	public List<Notice> ShowList() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Notice> SelectList(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean Publish(Notice notice) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean Updata(int noticeid, Notice notice) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
