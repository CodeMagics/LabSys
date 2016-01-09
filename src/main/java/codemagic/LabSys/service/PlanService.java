package codemagic.LabSys.service;

import java.util.List;

import codemagic.LabSys.model.Plan;

public interface PlanService {

	/**
	 * 添加学习计划
	 * @return
	 */
	public boolean addPlan(Plan plan);
	/**
	 * 删除学习计划
	 * @param planid
	 * @return
	 */
	public boolean deletePlan(int planid);
	/**
	 * 查看学习计划
	 * @param planid
	 * @return
	 */
	public Plan checkPlan(int planid);
	/**
	 * 显示学习计划列表
	 * @param userid
	 * @return
	 */
	public List<Plan> showList(int  userid);
	/**
	 * 修改学习列表
	 * @param planid
	 * @return
	 */
	public boolean updatePlan(int planid);

}
