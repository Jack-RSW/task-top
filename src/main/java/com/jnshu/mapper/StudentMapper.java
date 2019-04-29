package com.jnshu.mapper;

import com.jnshu.pojo.Student;

import java.util.List;


public interface StudentMapper {

    public Student findById(int id);

    public void addStudent(Student student);

    public void updateStudent(Student student);

    public void deleteById(int id);

    public List<Student> findAll();

}
