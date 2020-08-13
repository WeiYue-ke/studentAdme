package com.studentAdmi;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Arrays;

public class JdbcActionTest {

    Logger logger =  LoggerFactory.getLogger(getClass());
    @org.junit.Test
    public void getNum() throws SQLException {
        System.out.println(JdbcAction.getNum());

    }

    @org.junit.Test
    public void addStudent() throws SQLException {
        System.out.println(JdbcAction.getNum());
        JdbcAction.addStudent("sdf","男", "xcv","45645678","xcvxzvz", "vxczvz");
        System.out.println(JdbcAction.getNum());

    }

    @org.junit.Test
    public void presentResult() throws SQLException {
        System.out.println(JdbcAction.presentResult("123456")[0]);
        System.out.println(JdbcAction.presentResult("123456")[1]);

    }

    @Test
    public void delectStudent() {  //判断两个数组中每个索引的对应元素是否相等
        String[] aa = {"111","5555", "8888"};
        String[] bb = {"111","5555", "8888"};
        System.out.println(Arrays.equals(aa,bb));

    }

    @Test
    public void findStudent() throws SQLException {
        for (String i :JdbcAction.findStudent("987654")){
            System.out.println(i);

        }
    }

    @Test
    public void changeStudent() throws SQLException {
        System.out.println(JdbcAction.changeStudent("小静", "女" , "南宁", "987654", "信电系", "软件1901"));
        System.out.println(JdbcAction.getNum());

    }

    void closeJDBC(){
        try {
            JdbcAction.closeJdbc();
            logger.info("jdbc连接关闭！");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            logger.error("HAVE ERROR!" + sqlException);

        }
    }


}