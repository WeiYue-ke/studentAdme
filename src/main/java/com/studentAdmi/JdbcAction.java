package com.studentAdmi;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcAction {                                      //jdbc 的方法都封装在这个类里面啦

    private static DataSource ds ;                                      //定义连接池
    private static Connection conn ;                                  //定义jdbc连接
    static {                                                          //加载连接池
        final HikariConfig config = new HikariConfig();
        Logger logger = LoggerFactory.getLogger(JdbcAction.class);
        config.setJdbcUrl("jdbc:mysql://localhost:3306/studentadmi?useSSL=false&characterEncoding=utf8&serverTimezone=GMT");
        config.setUsername("root");
        config.setPassword("mysqlpassword");
        config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        ds = new HikariDataSource(config);
        logger.info("jdbc连接池就绪！");
        try {
            conn= ds.getConnection();
            logger.info("获取jdbc连接！");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error("HAVE ERROR!" + throwables);
        }
    }

        static int getNum() throws SQLException {                                 //判断数据库中的总记录数
        int num = 0;
        try(PreparedStatement ps = conn.prepareStatement(
                "SELECT COUNT(id) amount FROM students;")){
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    num = rs.getInt("amount");
                }
            }
            return num;
        }
    }

    static void addStudent( String name, String sex, String place, String code, String dept, String ban) throws SQLException{    //添加学生到数据库
        try(PreparedStatement ps = conn.prepareStatement(
                //这条sql语句如果不存在该学号的学生就会插入
                "INSERT INTO students ( name, sex, place, code, dept, class_name) SELECT ?,?,?,?,?,?  WHERE NOT EXISTS (SELECT * FROM students WHERE code = ? );")){
            ps.setObject(1, name); // 注意：索引从1开始
            ps.setObject(2, sex); //
            ps.setObject(3, place);
            ps.setObject(4, code);
            ps.setObject(5, dept);
            ps.setObject(6, ban);
            ps.setObject(7, code);
            int n = ps.executeUpdate(); // 返回1
        }
    }

    static String[]  presentResult(String code) throws SQLException{        //判断数据库中是否存在该学生,用数组来调用方判断
        int num = 0;
        try(PreparedStatement ps = conn.prepareStatement(
                "SELECT name, code  FROM students where code =? ;")){
            ps.setObject(1,  code);
            try (ResultSet rs = ps.executeQuery()) {
                String [] message = new String[2];
                while (rs.next()) {
                    message[0] = rs.getString("name");
                    message[1] = rs.getString("code");
                }
                return message;
            }
        }
    }

    static void delectStudent(String name, String code) throws SQLException{        //删除
        try(PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM students WHERE name = ? AND code =? ;")){
            ps.setObject(1, name);
            ps.setObject(2, code);
            int n = ps.executeUpdate();
        }
    }

    static String[] findStudent(String code) throws SQLException{
        int num = 0;
        try(PreparedStatement ps = conn.prepareStatement(
                "SELECT name, sex, place, code, dept, class_name  FROM students where code =? ;")){
            ps.setObject(1,  code);
            try (ResultSet rs = ps.executeQuery()) {
                String [] message = new String[6];
                while (rs.next()) {
                    message[0] = rs.getString("name");
                    message[1] = rs.getString("sex");
                    message[2] = rs.getString("place");
                    message[3] = rs.getString("code");
                    message[4] = rs.getString("dept");
                    message[5] = rs.getString("class_name");
                }
                return message;
            }
        }
    }

    static int changeStudent(String name, String sex, String place, String code, String dept, String ban) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement(
                "UPDATE  students SET name = ? ,sex = ?, place = ?, code = ?, dept = ?, class_name = ? WHERE code = ? ;")){
            ps.setObject(1, name);
            ps.setObject(2, sex);
            ps.setObject(3, place);
            ps.setObject(4, code);
            ps.setObject(5, dept);
            ps.setObject(6, ban);
            ps.setObject(7, code);
            int n = ps.executeUpdate();
            return n;
        }
    }
    static String[][] getWhole() throws SQLException {
      String[][] result = new String[JdbcAction.getNum()][6];
        try(PreparedStatement ps = conn.prepareStatement(
                "SELECT name, sex, place, code, dept, class_name  FROM students ;")){
            try (ResultSet rs = ps.executeQuery()) {
                int index = 0;
                while (rs.next()) {
                    result[index][0] = rs.getString("name");
                    result[index][1] = rs.getString("sex");
                    result[index][2] = rs.getString("place");
                    result[index][3] = rs.getString("code");
                    result[index][4] = rs.getString("dept");
                    result[index][5] = rs.getString("class_name");
                    index++;
                }
                return result;
            }
        }
    }

    static void closeJdbc() throws SQLException {
        conn.close();
    }

}
