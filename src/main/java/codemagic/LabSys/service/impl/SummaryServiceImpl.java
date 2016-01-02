package codemagic.LabSys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import codemagic.LabSys.dao.SummaryMapper;
import codemagic.LabSys.model.Summary;
import codemagic.LabSys.service.SummaryService;

@Service("/summaryService")
public class SummaryServiceImpl implements SummaryService {

	private SummaryMapper summaryMapper;
	public boolean addsummary(Summary record) {
		// TODO Auto-generated method stub
		try{
			summaryMapper.insert(record);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deletesummary(int summaryid) {
		// TODO Auto-generated method stub
		try{
			summaryMapper.deleteByPrimaryKey(summaryid);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updatesummary(int summaryid) {
		// TODO Auto-generated method stub
		try{
			Summary record=null;
			summaryMapper.updateByPrimaryKey(record);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("finally")
	public Summary checksummary(int summaryid) {
		// TODO Auto-generated method stub
		Summary summary = null;
		try{
			summary = summaryMapper.selectByPrimaryKey(summaryid);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return summary;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Summary> showList(int userid) {
		// TODO Auto-generated method stub
		List<Summary> summarylsit = null;
		try{
			summarylsit = summaryMapper.selectByPublisherID(userid);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return summarylsit;
		}
	}

}
