package com.jnshu;

import com.jnshu.mapper.StudentMapper;
import com.jnshu.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StudentTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void TestaddStduent(){
        try {
            Student stu = new Student();
            stu.setS_id(1025);
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
            studentMapper.addStudent(stu);
            System.out.println("增加成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("增加失败");
        }

    }

    @Test
    public void TestdeleteStduentById(){
        try {
            studentMapper.deleteById(1023);
            System.out.println("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("删除失败");
        }
    }

    @Test
    public void TestfindById(){
        Student stu = studentMapper.findById(1);
        System.out.println(stu.getS_name());
    }

    @Test
    public void TestUpdateStduent(){
        try {
            Student stu = new Student();
            stu.setS_id(5);
            stu.setS_name("小米子");
            stu.setS_qq(45661223);
            stu.setS_course(1);
            stu.setUpdate_at("20190425");
            stu.setS_school("中年大学");
            stu.setS_link("https://www.stupid.com/");
            stu.setS_flag("斗鱼!!!");
            stu.setS_brother(1);
            stu.setS_source("知乎");
            stu.setCreate_at("20190425");
            studentMapper.updateStudent(stu);
            System.out.println("更新成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("更新失败");
        }

    }

    @Test
    public void testFindAll(){
        List<Student> list = studentMapper.findAll();
        for (Student s : list) {
            System.out.println(s);
        }
    }

}
