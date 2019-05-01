package com.jnshu.DAO;

import com.jnshu.pojo.Student;

import java.sql.SQLException;
import java.util.List;

/*
* jdbcTemplate所需代码
* */

public interface StudentDAO {
    public void add(Student student) throws SQLException;
    public void delete(int s_id) throws SQLException;
    public void update(Student student) throws Exception;
    public Student findById(int s_id) throws SQLException;
    public int findTotalCount() throws SQLException;
    public List<Student> findAll() throws SQLException;

}
