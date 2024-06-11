package com.yc.user.controller;

import com.yc.user.model.User;
import com.yc.user.model.UserExample;
import com.yc.user.service.UserService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import cn.hutool.core.util.IdUtil;


@Controller
public class UserController {

	private UserService userService;

	//通过构造器注入service
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	//查询数据
	@GetMapping("/user")
	@ResponseBody
	public List<User> list() {
		//1 构造查询条件
		//1.1 创建查询条件类
		UserExample example = new UserExample();
		//1.2 创建条件构造器
		UserExample.Criteria criteria = example.createCriteria();
		//1.3 通过criteria设置条件
		//...

		//2 根据条件查询，如果没有查询条件，传入一个空的example即可
		List<User> list = userService.selectByExample(example);

		//3 返回结果
		return list;
	}

	//添加数据
	@PostMapping("/user")
	@ResponseBody
	public User add(@RequestBody User user) {
		//1 准备数据，例如设置user主键等
		//...
		//user.setUserId(IdUtil.nextId().toString());
		//user.setUserId(String.valueOf(IdUtil.nextId()));
		user.setUserId(IdUtil.fastUUID().toString());

		user.setCurrentStatus("0");//0-正常状态;1-禁用状态
        //user.setCreatedTime(ZonedDateTime.now());
        //user.setLastUpdatedTime(ZonedDateTime.now());
		// 将ZonedDateTime转换为Date
		//user.setCreatedTime(Date.from(user.getCreatedTime().toInstant()));
		//user.setLastUpdatedTime(Date.from(user.getLastUpdatedTime().toInstant()));
		user.setCreatedTime(new Date());
		user.setLastUpdatedTime(new Date());

        //user.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        //user.setLastUpdatedTime(new Timestamp(System.currentTimeMillis()));

		//2 插入数据
		userService.insertSelective(user);

		//3 把新添加的数据返回给调用者
		return user;
	}

	//修改数据
	@PutMapping("/user")
	@ResponseBody
	public User update(@RequestBody User user) {
		//1 准备数据，例如设置user的修改时间等
		//...
		if (user.getCurrentPassword() == "") {
			user.setCurrentPassword(null);
		}
		user.setLastUpdatedTime(new Date());
		//2 更新数据
		userService.updateByPrimaryKeySelective(user);

		//3 把新修改的数据返回给调用者
		return userService.selectByPrimaryKey(user.getUserId());
	}

	//删除数据
	@DeleteMapping("/user/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String userId) {
		//1 根据主键删除数据
		userService.deleteByPrimaryKey(userId);

		//2 返回一个状态码给调用者
		return "200";
	}
}