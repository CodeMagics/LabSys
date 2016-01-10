package codemagic.LabSys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codemagic.LabSys.dao.PlanMapper;
import codemagic.LabSys.model.Plan;
import codemagic.LabSys.service.PlanService;

@Service("/planService")
public class PlanServiceImpl implements PlanService {
	private PlanMapper planMapper;
	
	public PlanMapper getPlanMapper() {
		return planMapper;
	}

	@Autowired
	public void setPlanMapper(PlanMapper planMapper) {
		this.planMapper = planMapper;
	}
	
	public boolean AddPlan(Plan record) {
		// TODO Auto-generated method stub
		try{
			planMapper.insert(record);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean DeletePlan(int planId) {
		// TODO Auto-generated method stub
		try{
			planMapper.deleteByPrimaryKey(planId);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("finally")
	public Plan CheckPlan(int planId) {
		// TODO Auto-generated method stub
		Plan plan = new Plan();
		try{
			planMapper.selectByPrimaryKey(planId);

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return plan;
		}
	}

	@SuppressWarnings({ "finally" })
	public List<Plan> ShowList(int userId) {
		// TODO Auto-generated method stub
		List<Plan> plans = new ArrayList<Plan>();
		try {

			plans = planMapper.selectByPublisherID(userId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return plans;
		}
	}

	public boolean UpdatePlan(Plan record) {
		// TODO Auto-generated method stub
		try {
			planMapper.updateByPrimaryKey(record);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


}