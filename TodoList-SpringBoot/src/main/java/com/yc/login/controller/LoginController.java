package com.yc.login.controller;


import com.yc.user.model.User;

import java.util.List;

import com.yc.user.model.UserExample;
import com.yc.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
	private UserService userService;

	public LoginController(UserService userService) {
		this.userService = userService;
	}

	//处理登录
	//@RequestMapping(value = "/login", method = RequestMethod.POST)
	@PostMapping("/login")
	@ResponseBody
	public Object login(@RequestBody User user, HttpServletResponse response) {
// 获取用户名和密码
		String loginId = user.getLoginId();
		String password = user.getCurrentPassword();
// 从数据库中获取用户名和密码后进行判断
		if (loginId != null && password != null) {
			//Mybatis的提供了Example对象，可以设置查询条件
			UserExample example = new UserExample();
			//创建一个条件对象
			UserExample.Criteria criteria = example.createCriteria();
			criteria.andLoginIdEqualTo(loginId);
			criteria.andCurrentPasswordEqualTo(password);
			List<User> list = userService.selectByExample(example);
//根据给定的用户名和密码查询
//如果有结果，证明用户名和密码正确
			if (list.size() > 0) {
//返回用户信息
				User u = list.get(0);
				return u;
			}
		}
//登录失败，在前端登录用这个代码判断是否成功
		response.setStatus(500);
		return "err500";
	}

}
