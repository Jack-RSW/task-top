package com.jnshu.mapper;

import com.jnshu.pojo.Student;

import java.util.List;


public interface StudentMapper {

    public Student findById(int id);

    public int addStudent(Student student);

    public boolean updateStudent(Student student);

    public boolean deleteById(int id);

    public List<Student> findAll();

}
