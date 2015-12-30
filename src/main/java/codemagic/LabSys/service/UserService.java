package codemagic.LabSys.service;

import codemagic.LabSys.model.User;

public interface UserService {
	/**
	 * 通过主键查询用户
	 * @param id
	 * @return
	 */
	public User findUserById(int id);
}
