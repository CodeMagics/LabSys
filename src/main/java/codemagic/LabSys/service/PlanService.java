package codemagic.LabSys.service;

import java.util.List;

import codemagic.LabSys.model.Plan;

public interface PlanService {

    /**
     * 添加学习计划
     * @param record 学习计划
     * @return
     */
	boolean AddPlan(Plan record);
	/**
	 * 删除学习计划
	 * @param planid
	 * @return
	 */
	 boolean DeletePlan(int planId);
	/**
	 * 查看学习计划
	 * @param planid
	 * @return
	 */
	 Plan CheckPlan(int planId);
	/**
	 * 显示学习计划列表
	 * @param userid
	 * @return
	 */
	 List<Plan> ShowList(int  userId);
	/**
	 * 修改学习列表
	 * @param planid
	 * @return
	 */
	 boolean UpdatePlan(Plan record);

}