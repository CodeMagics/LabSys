package codemagic.LabSys.service;

import java.util.List;

import codemagic.LabSys.model.Plan;

public interface PlanService {

	/**
	 * 添加学习计划
	 * @return
	 */
	boolean addPlan(Plan plan);
	/**
	 * 删除学习计划
	 * @param planid
	 * @return
	 */
	 boolean deletePlan(int planid);
	/**
	 * 查看学习计划
	 * @param planid
	 * @return
	 */
	 Plan checkPlan(int planid);
	/**
	 * 显示学习计划列表
	 * @param userid
	 * @return
	 */
	 List<Plan> showList(int  userid);
	/**
	 * 修改学习列表
	 * @param planid
	 * @return
	 */
	 boolean updatePlan(int planid);

}
