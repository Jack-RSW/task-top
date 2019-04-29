package com.jnshu.myTest;

import com.jnshu.mapper.StudentMapper;
import com.jnshu.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TMain {
    private static ApplicationContext context;
    private static StudentMapper studentMapper;

    public static void main(String[] args) {

        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        studentMapper = context.getBean(StudentMapper.class);

        //测试一下连接DB中断后TryCatch是否能正常处理(使用findById方法)。
        tryTest(10);

        //测试一下不关闭连接池的时候，在Main函数里写1000个循环调用会出现什么情况。
        //addNStudent(1000);
    }

    private static void tryTest(int id) {
        try {
            studentMapper.findById(id);
            System.out.println(studentMapper.findById(id).getS_name());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("查询失败");
        }
    }

    private static void addNStudent(int count) {
        long start = System.currentTimeMillis();
        System.out.println("*****************插入"+count+"个对象*************************");
        for (int i = 1; i <count ; i++) {
            Student stu = new Student();
            //注意使用数据库id为自增，不需设置s_id
            stu.setS_name("小大米");
            stu.setS_qq(5515123);
            stu.setS_course(1);
            stu.setUpdate_at("20190425");
            stu.setS_school("高高大学");
            stu.setS_link("https://www.baidu.com/");
            stu.setS_flag("丢失!!!");
            stu.setS_brother(1);
            stu.setS_source("知乎");
            stu.setCreate_at("20190425");
            studentMapper.addStudent(stu);
        }
        long end = System.currentTimeMillis();
        System.out.println("程序执行时间："+(end-start)/1000+" 秒");
    }

}