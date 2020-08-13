package com.studentAdmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Main {
    static MainWindow mainWindow ;
    static ImageIcon icon = new ImageIcon("src\\main\\java\\img\\sigh.jpg");
    private Main(){}
    private JdbcAction jdbcAction = null;
    public static void main(String[] args) {
        mainWindow = new MainWindow();
    }
}

class MainWindow extends JFrame {
    Logger logger = LoggerFactory.getLogger(getClass());
    static PrimePanel HomePanel;
    MainWindow(){
        super();
        this.setSize(800, 500);
        this.setIconImage(Main.icon.getImage());
        this.setLayout(null);                    //清空布局器
        HomePanel = new PrimePanel(0, 0, 800, 500);
        this.getContentPane().add(HomePanel);
        this.setResizable(false);               //不可改变大小
        this.setLocation(300,150);         //设置窗口生成位置
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("学生信息管理系统");
        this.setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    JdbcAction.closeJdbc();
                    logger.info("jdbc连接关闭！");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                    logger.error("HAVE ERROR!" + sqlException);

                }

            }

        });
    }
}


