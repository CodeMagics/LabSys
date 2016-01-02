package codemagic.LabSys.service;

import java.util.List;

import codemagic.LabSys.model.Summary;

public interface SummaryService {
   
	 /**
     * 添加一条学习总结
     * @param record 新增的学习总结
     * @return true/false 是否添加成功
     */
	boolean addsummary(Summary record);

	/**
     * 删除一条学习总结
     * @param summaryid 学习总结编号
     * @return true/false 是否删除成功
     */
	boolean deletesummary(int summaryid);

    /**
     * 修改一条学习总结
     * @param summaryid 学习总结编号
     * @return true/false 是否修改成功
     */
	boolean updatesummary(int summaryid);

    /**
     * 查看一条学习总结
     * @param summaryid 学习总结编号
     * @return summary 一条总结记录
     */
	Summary checksummary(int summaryid);

	 /**
     * 显示一位用户的学习总结列表
     * @param userid 用户编号
     * @return List<Summary> 学习总结列表
     */
	List<Summary> showList(int userid);


}
