package codemagic.LabSys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import codemagic.LabSys.dao.PlanMapper;
import codemagic.LabSys.model.Plan;
import codemagic.LabSys.service.PlanService;

@Service("/planService")
public class PlanServiceImpl implements PlanService {
	private PlanMapper planMapper;
	
	public boolean addPlan(Plan record) {
		// TODO Auto-generated method stub
		try{
			planMapper.insert(record);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deletePlan(int planid) {
		// TODO Auto-generated method stub
		try{
			planMapper.deleteByPrimaryKey(planid);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("finally")
	public Plan checkPlan(int planid) {
		// TODO Auto-generated method stub
		Plan plan = null;
		try{
			planMapper.selectByPrimaryKey(planid);

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return plan;
		}
	}

	@SuppressWarnings({ "finally" })
	public List<Plan> showList(int userid) {
		// TODO Auto-generated method stub
		List<Plan> plans = null;
		try {

			plans = planMapper.selectByPublisherID(userid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return plans;
		}
	}

	public boolean updatePlan(int planid) {
		// TODO Auto-generated method stub
		try {
			//临时变量，待数据库稳定后删除修改
            Plan record = null;
			planMapper.updateByPrimaryKey(record);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
