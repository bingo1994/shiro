package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.entity.UserEntity;
import com.test.service.UserService;

@Controller
public class UserController {

	@Resource
	UserService userService;
	
	/**
	 * 用户登陆
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	public String login(@RequestParam("userName")String userName,@RequestParam("password")String password){
		Subject currentUser=SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()){
			// 把用户名和密码封装为 UsernamePasswordToken 对象
			UsernamePasswordToken token=new UsernamePasswordToken(userName,password);
			try{
				currentUser.login(token);
			}catch(AuthenticationException e){
				System.out.println("登录失败: " +e.getMessage());
			}
		}
		return "redirect:index.jsp";
	}
	/**
	 * 查询所有用户
	 * @param model
	 * @return
	 */
	@RequestMapping("/userList")
	public String getUserList(Model model){
		List<UserEntity>list=userService.getUserList();
		model.addAttribute("list", list);
		return "forward:list.jsp";
	}
	
	/**
	 * 保存用户
	 * @param user
	 * @param response
	 */
	@RequestMapping("/save")
	public void saveUser(UserEntity user,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out=response.getWriter();
			int i=userService.saveUser(user);
			if(i>0){
				out.println("<script>alert('添加成功！');location.href='add.jsp'</script>");
			}else{
				out.println("<script>alert('添加失败！');location.href='add.jsp'</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据用户id查询
	 * @param uid
	 * @param model
	 * @return
	 */
	@RequestMapping("/user")
	public String getUser(@RequestParam("uid")Integer uid,Model model){
		UserEntity user=userService.queryUserById(uid);
		model.addAttribute("user", user);
		return "forward:update.jsp";
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @param response
	 */
	@RequestMapping("/modify")
	public void updateUser(UserEntity user,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		int i=userService.updateUser(user);
		try {
			PrintWriter out=response.getWriter();
			if(i>0){
				out.println("<script>alert('修改成功！');location.href='user?uid="+user.getUser_id()+"'</script>");
			}else{
				out.println("<script>alert('修改失败！');location.href='user?uid="+user.getUser_id()+"'</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/delete")
	public void deleteUser(@RequestParam("uid")Integer uid,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out=response.getWriter();
			boolean flag=userService.deleteUser(uid);
			if(flag){
				out.println("<script>alert('删除成功！');location.href='userList'</script>");
			}else{
				out.println("<script>alert('删除失败！');location.href='userList'</script>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
