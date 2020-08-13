package com.studentAdmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class ChangeWin extends JFrame{
    private Logger logger = LoggerFactory.getLogger(getClass());

    private MyLabel nameLabel;
    private MyTextFiled nameText;

    private MyLabel codeLabel;
    private MyTextFiled codeText;

    private MyButton OKButton;            //确认按钮
    private MyButton clearButton;         //清空按钮

    ChangeWin(){
        super();
        this.setIconImage(Main.icon.getImage());
        this.setSize(500, 300);
        this.setLayout(null);                    //清空布局器
        this.setResizable(false);               //不可改变大小
        this.setLocation(400,200);         //设置窗口生成位置
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("请输入学号和姓名");
        this.getContentPane().setBackground(Color.pink);
        this.setVisible(true);

        this.addPart();
        this.addListen();
    }

    private void addPart(){

        nameLabel = new MyLabel(80, 15, 80, 40, "姓名");
        this.add(nameLabel);
        nameText = new MyTextFiled(140, 20, 200, 30);
        this.add(nameText);

        codeLabel = new MyLabel(80, 65, 80, 40, "学号");
        this.add(codeLabel);
        codeText = new MyTextFiled(140, 70, 200, 30);
        this.add(codeText);

        OKButton =new MyButton(40, 200, 80, 40, "确认");
        this.add(OKButton);
        clearButton =new MyButton(360, 200, 80, 40, "清空");
        this.add(clearButton);
    }

    private void addListen(){
        OKButton.addActionListener(new OKListener());
        clearButton.addActionListener(new ClearListener());
    }
    
    private class OKListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String[] foundStudent = JdbcAction.findStudent(codeText.getText());
                if (foundStudent[3] != null) {        // 3是学号，0是姓名
                    if (foundStudent[0].equals(nameText.getText()) && foundStudent[3].equals(codeText.getText())){
                        new ChangedWin(foundStudent[0], foundStudent[1], foundStudent[2], foundStudent[3], foundStudent[4], foundStudent[5]);
                    }else {
                        JOptionPane.showMessageDialog(null, "名字与该学号不匹配", "提示",JOptionPane.WARNING_MESSAGE);   //弹出提示框
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "没有该学号信息", "提示",JOptionPane.WARNING_MESSAGE);   //弹出提示框
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                logger.error("HAVE ERROR!" + sqlException);
            }
        }
    }

    private class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nameText.setText("");
            codeText.setText("");
        }
    }
}
