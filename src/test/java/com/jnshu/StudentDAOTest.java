package com.jnshu;

import com.jnshu.DAO.StudentDAO;
import com.jnshu.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StudentDAOTest {

    @Resource(name = "StudentDAO")
    private StudentDAO sd;

    @Test //添加测试
    public void addTest() throws SQLException {
        Student stu = new Student();

        stu.setS_id(10);
        stu.setS_name("大虾米");
        stu.setS_qq(123456789);
        stu.setS_course(1);
        stu.setUpdate_at("20190425");
        stu.setS_school("湖南师范大学");
        stu.setS_link("https://www.ffdd.com");
        stu.setS_flag("好好学习，day day up!!!");
        stu.setS_brother(1);
        stu.setS_source("知乎");
        stu.setCreate_at("20190425");
        sd.add(stu);

    }

    @Test //更改测试
    public void updateTest() throws Exception {
        Student stu = new Student();

        stu.setS_id(3);
        stu.setS_name("小虾米");
        stu.setS_qq(987654321);
        stu.setS_course(1);
        stu.setUpdate_at("20190425");
        stu.setS_school("湖南大学");
        stu.setS_link("https://www.baidu.com/");
        stu.setS_flag("激活!!!");
        stu.setS_brother(1);
        stu.setS_source("知乎");
        stu.setCreate_at("20190425");
        sd.update(stu);
    }

    @Test //删除测试
    public void deleteTest()throws SQLException{
        sd.delete(13);
    }

    @Test //单个查询测试
    public void findByIdTest()throws SQLException{
        System.out.println(sd.findById(1));
    }

    @Test //总数查询测试
    public void findTotalCountTest()throws SQLException{
        System.out.println(sd.findTotalCount());
    }

    @Test //查询所有测试
    public void findAllTest()throws SQLException{
        System.out.println(sd.findAll());
    }
}