package com.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import com.test.entity.UserEntity;
import com.test.mapper.UserMapper;
import com.test.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserMapper userMapper;

	public List<UserEntity> getUserList() {
		return userMapper.getUserList();
	}

	public UserEntity queryUserById(Integer uid) {
		return userMapper.queryUserById(uid);
	}

	public Integer saveUser(UserEntity user) {
		String hashAlgorithmName = "MD5";
		Object credentials = user.getPassword();
		Object salt = ByteSource.Util.bytes(user.getAccount());;
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result+"---------------------------"+result.toString());
		user.setPassword(result.toString());
		return userMapper.saveUser(user);
	}

	public Integer updateUser(UserEntity user) {
		System.out.println(user.getPassword().length());
		if(user.getPassword().length()<12){
			String hashAlgorithmName = "MD5";
			Object credentials = user.getPassword();
			Object salt = ByteSource.Util.bytes(user.getAccount());;
			int hashIterations = 1024;
			Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
			System.out.println(result+"---------------------------"+result.toString());
			user.setPassword(result.toString());
		}
		
		return userMapper.updateUser(user);
	}

	public boolean deleteUser(Integer uid) {
		boolean flag=false;
		List<Integer>rlist=userMapper.getRolesByUid(uid);
		if(rlist.size()>0){
			for(Integer i:rlist){
				int count=userMapper.getResourcesCountByUid(i);
				if(count>0){
					for(int j=1;j<=count;j++){
						userMapper.delteResourceByUid(i);
					}
				}
					userMapper.delteRoleByUid(uid);
			}
		}
		int g=userMapper.deleteUserById(uid);
		if(g>0){
			flag=true;
		}
		return flag;
	}}
