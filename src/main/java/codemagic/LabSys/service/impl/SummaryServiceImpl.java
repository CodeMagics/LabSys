package codemagic.LabSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codemagic.LabSys.dao.SummaryMapper;
import codemagic.LabSys.model.Summary;
import codemagic.LabSys.service.SummaryService;

@Service("/summaryService")
public class SummaryServiceImpl implements SummaryService {

	private SummaryMapper summaryMapper;
	
	public SummaryMapper getSummaryMapper() {
		return summaryMapper;
	}

	@Autowired
	public void setSummaryMapper(SummaryMapper summaryMapper) {
		this.summaryMapper = summaryMapper;
	}
	
	public boolean AddSummary(Summary record) {
		// TODO Auto-generated method stub
		try{
			summaryMapper.insert(record);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean DeleteSummary(int sumId) {
		// TODO Auto-generated method stub
		try{
			summaryMapper.deleteByPrimaryKey(sumId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean UpdateSummary(Summary record) {
		// TODO Auto-generated method stub
		try{
			summaryMapper.updateByPrimaryKey(record);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("finally")
	public Summary CheckSummary(int sumId) {
		// TODO Auto-generated method stub
		Summary summary = new Summary();
		try{
			summary = summaryMapper.selectByPrimaryKey(sumId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return summary;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Summary> ShowList(int userId) {
		// TODO Auto-generated method stub
		List<Summary> summarylsit = new ArrayList<Summary>();
		try{
			summarylsit = summaryMapper.selectByPublisherID(userId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return summarylsit;
		}
	}

}