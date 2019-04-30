package com.jnshu.DAO.Impl;

import com.alibaba.druid.pool.DruidDataSource;

import com.jnshu.DAO.StudentDAO;
import com.jnshu.Utils.DBUtil;
import com.jnshu.Utils.MyDataSource;
import com.jnshu.pojo.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/*
* jdbcTemplate所需代码
* */

public class StudentDAOImpl implements StudentDAO {

    //add 方法未使用JdbcTemplate模板来加载数据源datasource，未有连接池，使用原始的连接来连接数据库和增加数据

    @Override
    public void add(Student student) throws SQLException {
        //编写sql语句
        String sql = "" + "INSERT INTO student" + "(s_id,s_name,s_qq,s_course,update_at,s_school,s_link,s_flag,s_brother,s_source,create_at)"+"VALUE(" + "?,?,?,?,?,?,?,?,?,?,?)";

        try(
                //创建数据库连接
                Connection conn = DBUtil.getConnection();
                //预编译sql语句
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                )
        {
            //将student对象值传入sql语句中中
            preparedStatement.setLong(1, student.getS_id());
            preparedStatement.setString(2, student.getS_name());
            preparedStatement.setLong(3, student.getS_qq());
            preparedStatement.setLong(4, student.getS_course());
            preparedStatement.setString(5, student.getUpdate_at());
            preparedStatement.setString(6, student.getS_school());
            preparedStatement.setString(7, student.getS_link());
            preparedStatement.setString(8, student.getS_flag());
            preparedStatement.setLong(9, student.getS_brother());
            preparedStatement.setString(10,student.getS_source());
            preparedStatement.setString(11,student.getCreate_at());
            //执行sql语句
            preparedStatement.execute();
        }
    }

    //delete方法使用JdbcTemplate,并使用自行创建的数据源MyDataSource（轮子），有自行创建的连接池

    @Override
    public void delete(int s_id) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new MyDataSource());
        String sql = "delete from student where s_id=?";
        jdbcTemplate.update(sql,s_id);
    }

    //update方法使用JdbcTemplate,使用Druid作为数据源，有Druid的连接池

    @Override
    public void update(Student student) throws Exception {
        //使用配置的方式
        //Properties properties = new Properties();
        //properties.load(new FileInputStream("/db.properties"));
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidDataSourceFactory.createDataSource(properties));

        //使用DruidUtil工具
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtil.getDataSource());

        //使用硬编码方式
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        //设置密码
        dataSource.setPassword("admin");
        //设置驱动类名
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //设置初始化连接数
        dataSource.setInitialSize(8);
        //设置url
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/enroll?characterEncoding=UTF-8");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


        String sql = "update student set s_name=?,s_qq=?,s_course=?,update_at=?,s_school=?,s_link=?,s_flag =?,s_brother=?,s_source=?,create_at=? where s_id =?";
        jdbcTemplate.update(sql,student.getS_name(),student.getS_qq(),student.getS_course(),student.getUpdate_at(),student.getS_school(),student.getS_link(),student.getS_flag(),student.getS_brother(), student.getS_source(),student.getCreate_at(),student.getS_id());
    }

    //不做阐述
    @Override
    public Student findById(int s_id) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new MyDataSource());
        String sql="select * from student where s_id=?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Student>(){
            @Override
            public Student mapRow(ResultSet rs, int i) throws SQLException {
                Student s = new Student();
                s.setS_id(rs.getLong("s_id"));
                return s;
            }
        },s_id);
    }

    //不做阐述
    @Override
    public int findTotalCount() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new MyDataSource());
        String sql = "select count(*) from student";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;

    }

        //不做阐述
        @Override
        public List<Student> findAll() throws SQLException {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(new MyDataSource());
            String sql = "select * from student";
            List<Student> list = jdbcTemplate.query(sql,new RowMapper<Student>() {
                @Override
                public Student mapRow(ResultSet rs, int i) throws SQLException {
                    Student s = new Student();
                    s.setS_id(rs.getLong("s_id"));
                    return s;
                }
            });
            return list;
        }

}