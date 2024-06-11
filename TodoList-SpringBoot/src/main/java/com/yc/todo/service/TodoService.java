package com.yc.todo.service;

import com.yc.todo.model.Todo;
import com.yc.todo.model.TodoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TodoService {
    //根据条件汇总记录数
    long countByExample(TodoExample example);

    //根据条件删除
    int deleteByExample(TodoExample example);

    //根据主键删除
    int deleteByPrimaryKey(String id);

    //全字段插入：把model所有字段拼装到SQL语句，不管model字段是否有值
    int insert(Todo record);

    //有选择插入：只把model有值的字段拼装到插入SQL语句
    int insertSelective(Todo record);

    //根据条件查询，如果有blob/clob字段，不包含blob/clob字段内容
    List<Todo> selectByExample(TodoExample example);

    //根据主键查询
    Todo selectByPrimaryKey(String id);

    //根据条件有选择的更新：只把model有值的字段拼装到更新SQL语句
    int updateByExampleSelective(@Param("record") Todo record, @Param("example") TodoExample example);

    //根据条件更新：把mode全部字段拼装到更新SQL语句，如果有blob/clob字段，不包含blob/clob字段内容
    int updateByExample(@Param("record") Todo record, @Param("example") TodoExample example);

    //根据主键有选择的更新：只把model有值的字段拼装到更新SQL语句，如果有blob/clob字段，不包含blob/clob字段内容
    int updateByPrimaryKeySelective(Todo record);

    //根据主键更新：把model全部的字段拼装到更新SQL语句，如果有blob/clob字段，不包含blob/clob字段内容
    int updateByPrimaryKey(Todo record);
}