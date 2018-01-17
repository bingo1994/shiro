package com.test.service;

import java.util.List;

import com.test.entity.UserEntity;

public interface UserService {

	/**
	 * 查询所有用户
	 * @return
	 */
	List<UserEntity> getUserList();
	
	/**
	 * 根据用户id查询
	 * @param uid
	 * @return
	 */
	UserEntity queryUserById(Integer uid);
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	Integer saveUser(UserEntity user);
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	Integer updateUser(UserEntity user);
	
	/**
	 * 删除用户
	 * @param uid
	 * @return
	 */
	boolean deleteUser(Integer uid);
}
