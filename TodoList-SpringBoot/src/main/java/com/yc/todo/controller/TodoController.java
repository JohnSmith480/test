package com.yc.todo.controller;

import com.yc.todo.model.Todo;
import com.yc.todo.model.TodoExample;
import com.yc.todo.service.TodoService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {
    
    private TodoService todoService;

    //通过构造器注入service
    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //查询数据
    @GetMapping("/todo")
    @ResponseBody
    public Map list(@RequestParam String userId,
                    @RequestParam String currentStatus,
                    @RequestParam int pageSize, @RequestParam Long pageNum) {
        //1 构造查询条件
        //1.1 创建查询条件类
        TodoExample example = new TodoExample();
        //1.2 创建条件构造器
        TodoExample.Criteria criteria = example.createCriteria();
        //1.3 通过criteria设置条件
        //...
        if(userId != null && !userId.isEmpty()){
            criteria.andUserIdEqualTo(userId);
        }
        if(currentStatus != null && !currentStatus.isEmpty()){
            criteria.andCurrentStatusEqualTo(currentStatus);
        }
        if(pageSize != 0 && pageSize > 0 && pageNum !=null && pageNum >0){
            example.setLimit(pageSize);//每页的数量
            example.setOffset((pageNum-1) * pageSize);//当前页的起始
        }
        //2 根据条件查询，如果没有查询条件，传入一个空的example即可
        List<Todo> list = todoService.selectByExample(example);
        Long totalSize = todoService.countByExample(null);

        //3.返回结果
        Map map=new HashMap();
        //总记录数
        map.put("totalSize", totalSize);
        //总页数
        map.put("totalPages",totalSize%pageSize==0?totalSize/pageSize:totalSize/pageSize+1);
        //每页数量
        map.put("pageSize",pageSize);
        //当前页码
        map.put("pageNum",pageNum);
        //当前页结果集
        map.put("list",list);

        return map;
    }

    //添加数据
    @PostMapping("/todo")
    @ResponseBody
    public Todo add(@RequestBody Todo todo) {
        //1 准备数据，例如设置todo主键等
        //...
        todo.setItemId(String.valueOf(System.currentTimeMillis() % 1000000000));

        todo.setCurrentStatus("0");
        todo.setCreatedTime(new Date());
        todo.setLastUpdatedTime(new Date());
        //2 插入数据
        todoService.insertSelective(todo);

        //3 把新添加的数据返回给调用者
        return todo;
    }

    //修改数据
    @PutMapping("/todo")
    @ResponseBody
    public Todo update(@RequestBody Todo todo) {
        //1 准备数据，例如设置todo的修改时间等
        //...
        todo.setLastUpdatedTime(new Date());
        
        //2 更新数据
        todoService.updateByPrimaryKeySelective(todo);
        
        //3 把新修改的数据返回给调用者
        return todoService.selectByPrimaryKey(todo.getItemId());
    }

    //删除数据
    @DeleteMapping("/todo/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") String itemId) {
        //1 根据主键删除数据
        todoService.deleteByPrimaryKey(itemId);
        
        //2 返回一个状态码给调用者
        return "200";
    }
}