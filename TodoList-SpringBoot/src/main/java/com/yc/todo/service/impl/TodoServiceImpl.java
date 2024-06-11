package com.yc.todo.service.impl;

import com.yc.todo.mapper.TodoMapper;
import com.yc.todo.model.Todo;
import com.yc.todo.model.TodoExample;
import com.yc.todo.service.TodoService;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {
    
    private TodoMapper todoMapper;

    //通过构造函数注入mapper
    @Autowired
    public TodoServiceImpl(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public long countByExample(TodoExample example) {
        return todoMapper.countByExample(example);
    }

    public int deleteByExample(TodoExample example) {
        return todoMapper.deleteByExample(example);
    }

    public int deleteByPrimaryKey(String id) {
        return todoMapper.deleteByPrimaryKey(id);
    }

    public int insert(Todo record) {
        return todoMapper.insert(record);
    }

    public int insertSelective(Todo record) {
        return todoMapper.insertSelective(record);
    }

    public List<Todo> selectByExample(TodoExample example) {
        return todoMapper.selectByExample(example);
    }

    public Todo selectByPrimaryKey(String id) {
        return todoMapper.selectByPrimaryKey(id);
    }

    public int updateByExampleSelective(@Param("record") Todo record, @Param("example") TodoExample example) {
        return todoMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(@Param("record") Todo record, @Param("example") TodoExample example) {
        return todoMapper.updateByExample(record, example);
    }

    public int updateByPrimaryKeySelective(Todo record) {
        return todoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Todo record) {
        return todoMapper.updateByPrimaryKey(record);
    }
}