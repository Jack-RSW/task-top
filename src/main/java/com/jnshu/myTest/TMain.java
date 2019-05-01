package com.jnshu.myTest;

import com.jnshu.mapper.StudentMapper;
import com.jnshu.pojo.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;

public class TMain {
    private static StudentMapper studentMapper;

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        studentMapper = context.getBean(StudentMapper.class);

        //测试一下连接DB中断后TryCatch是否能正常处理(使用findById方法)。
        //tryTest(10);

        //测试一下不关闭连接池的时候，在Main函数里写1000个循环调用会出现什么情况。
        //创建一个对象，向服务器提交一次，效率太低，不建议
        //addNStudent(1000000);

        //预编译SQL语句，批量操作+事务
        MysqlAddBatch(1000000);


    }

    private static void MysqlAddBatch(int total) {

        String sql ="insert into student value(null,?,?,?,?,?,?,?,?,?,?)";
        String url = "jdbc:mysql://localhost:3306/enroll?useServerPrepStmts=false&rewriteBatchedStatements=true&useSSL=false&characterencoding=utf-8";
        //useServerPrepStmts也就是预编译提交，这里设置成了false就是阻止了程序默认的自动编译没一条SQL语句，
        /*rewriteBatchedStatement就是批量处理的核心语句了，因为jdbc在默认情况下是将这个方法的参数值设置称为false的，
        当我们在连接声明的时候将其设置为true时，程序就能执行批次处理语句了，这里是批量插入的关键所在，如果去掉的话就只能执行普通批次的插入了*/
        String name = "root";
        String password ="admin";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,name,password);
            conn.setAutoCommit(false);//这里将系统默认的自动事务提交关闭了，所以程序执行的时候，插入数据不会实时动态提交到mysql
            PreparedStatement ps = conn.prepareStatement(sql);//预编译SQL语句
            long startTime=System.currentTimeMillis();
            for (int i = 1; i <= total; i++) {
                ps.setString(1,"小小小大米");
                ps.setLong(2,5515123);
                ps.setLong(3,1);
                ps.setString(4,"20190425");
                ps.setString(5,"高高大学");
                ps.setString(6,"https://www.baidu.com/");
                ps.setString(7,"丢失!!!");
                ps.setLong(8,1);
                ps.setString(9,"知乎");
                ps.setString(10,"20190425");
               ps.addBatch();
              }
            ps.executeBatch();
            conn.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println("OK,用时：" + (endTime - startTime)/1000+" 秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean addNStudent(int count) {

        try {
            //问题：插入一个对象，提交一次到数据库
            long start = System.currentTimeMillis();
            System.out.println("*****************插入" + count + "个对象*************************");
            Student stu = new Student();
            for (int i = 1; i <  count; i++) {
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
            System.out.println("程序执行时间：" + (end - start) / 1000 + " 秒");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

}

