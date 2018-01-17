package com.test.realms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.test.entity.UserEntity;
import com.test.mapper.UserMapper;


public class ShiroRealm extends AuthorizingRealm{

	@Resource
	UserMapper userMapper;
	
	//授权会被 shiro 回调的方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		//1. 从 PrincipalCollection 中来获取登录用户的信息
		Object principal = principals.getPrimaryPrincipal();
		//2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
		List<String> rlist=userMapper.queryRoleByAccount(principal.toString());
		Set<String> roles = new HashSet<String>();
		for(String s:rlist){
			System.out.println(s);
			roles.add(s);
		}
		List<String>plist=userMapper.queryPermissionByAccount(principal.toString());
		Set<String> permissions=new HashSet<String>();
		for(String p:plist){
			System.out.println(p);
			permissions.add(p);
		}
		//3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性.
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		//权限
		info.setStringPermissions(permissions);
		
		//4. 返回 SimpleAuthorizationInfo 对象. 
		return info;
	
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1. 把 AuthenticationToken 转换为 UsernamePasswordToken 
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		//2. 从 UsernamePasswordToken 中来获取 username
		String account = upToken.getUsername();
		
		//3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
		UserEntity user=userMapper.queryByAccount(account);
		//System.out.println("从数据库中获取 username: " + username + " 所对应的用户信息.");
		System.out.println(user.getAccount());
		
		//6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
		//以下信息是从数据库中获取的.
		//1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.存入的是username取的就是username,存的是user对象取的就是user对象 
		Object principal = null;
		//2). credentials: 密码. 
		Object credentials = null; //"fc1709d0a95a6be30bc5926fdb7f22f4";
		//3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
		String realmName = getName();
		//4). 盐值. 
		ByteSource credentialsSalt =null;
		if(user!=null){
			principal=user.getAccount();
			credentials=user.getPassword();
			credentialsSalt = ByteSource.Util.bytes(user.getAccount());
			Session currentSession = SecurityUtils.getSubject().getSession();
			currentSession.setAttribute("USER", user);
		}
		
		SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal, credentials, realmName);
		info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);

		return info;
	
	}
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("d");;
		int hashIterations = 1024;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}
}
