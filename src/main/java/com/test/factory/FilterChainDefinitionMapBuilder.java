package com.test.factory;

import java.util.HashMap;
import java.util.Map;

public class FilterChainDefinitionMapBuilder {

	public Map<String, Object> buildFilterChainDefinitionMap(){
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("/login.jsp", "anon");
		map.put("/login", "anon");
		map.put("/logout", "logout");
		map.put("/user.jsp", "authc,roles[user]");
		map.put("/admin.jsp", "authc,roles[系统管理员]");
		map.put("/list.jsp", "user");
		
		map.put("/**", "authc");
		
		return map;
	}
	
}
