package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.test.entity.UserEntity;

@Repository
public interface UserMapper {

	/**
	 * 查询所有用户
	 * @return
	 */
	@Select("select * from tb_user")
	List<UserEntity> getUserList();
	
	/**
	 * 根据用户id查询
	 * @param uid
	 * @return
	 */
	@Select("select * from tb_user where user_id=#{uid}")
	UserEntity queryUserById(Integer uid);
	
	/**
	 * 根据登陆账号查询用户信息
	 * @param acount
	 * @return
	 */
	@Select("select * from tb_user where account=#{account}")
	UserEntity queryByAccount(String account);
	
	/**
	 * 根据登陆账号查询用户拥有的角色
	 * @param account
	 * @return
	 */
	@Select("select r.role_name from tb_user u ,tb_user_role ur,tb_role r where r.role_id=ur.role_id"
			+ " AND ur.user_id=u.user_id and u.account=#{account}")
	List<String> queryRoleByAccount(String account);
	
	/**
	 * 根据登陆账号查询用户权限
	 * @param account
	 * @return
	 */
	@Select("select res.permission from tb_user u ,tb_user_role ur,tb_role r,tb_role_resource rr,tb_resource res "
			+ " where res.res_id=rr.res_id and rr.role_id =r.role_id and r.role_id=ur.role_id AND "
			+ "ur.user_id=u.user_id and u.account=#{account}")
	List<String> queryPermissionByAccount(String account);
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	@Insert("insert into tb_user(user_id,account,password,user_name,user_sex,user_age,user_address) "
			+ "values(#{user_id},#{account},#{password},#{user_name},#{user_sex},#{user_age},#{user_address})")
	Integer saveUser(UserEntity user);
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@Update("update tb_user set password=#{password},user_name=#{user_name},"
			+ "user_sex=#{user_sex},user_age=#{user_age},user_address=#{user_address} where user_id=#{user_id}")
	Integer updateUser(UserEntity user);
	
	/**
	 * 根据用户id删除用户
	 * @param uid
	 * @return
	 */
	@Delete("delete from tb_user where user_id=#{uid}")
	Integer deleteUserById(Integer uid);
	
	/**
	 * 查看用户拥有几个角色
	 * @param uid
	 * @return
	 */
	@Select("select role_id from tb_user_role where user_id=#{uid}")
	List<Integer> getRolesByUid(Integer uid);
	
	/**
	 * 根据用户id删除用户角色中间表
	 * @param uid
	 * @return
	 */
	@Delete("delete from tb_user_role ur where ur.user_id=#{uid}")
	Integer delteRoleByUid(Integer uid);
	
	/**
	 * 根据角色id查询角色权限中间表
	 * @param rid
	 * @return
	 */
	@Select("select count(*) from tb_role_resource where role_id=#{rid}")
	Integer getResourcesCountByUid(Integer rid);
	
	/**
	 * 根据角色id删除角色权限中间表
	 * @param rid
	 * @return
	 */
	@Delete("delete from tb_role_resource where role_id=#{rid}")
	Integer delteResourceByUid(Integer rid);
}
