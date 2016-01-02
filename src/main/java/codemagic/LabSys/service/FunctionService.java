package codemagic.LabSys.service;

import java.util.List;

import codemagic.LabSys.model.Function;
import codemagic.LabSys.model.User;

public interface FunctionService {

	/*
	 * 功能列表
	 */
	List<Function> findFunListByUser(User user);
}
