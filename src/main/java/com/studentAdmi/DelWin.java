package com.studentAdmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DelWin extends JFrame {        //通过姓名和学号来删除学生信息
    private Logger logger = LoggerFactory.getLogger(getClass());
    private MyLabel nameLabel;
    private MyTextFiled nameText;

    private MyLabel codeLabel;
    private MyTextFiled codeText;

    private MyButton OKButton;            //确认按钮
    private MyButton clearButton;         //清空按钮
    DelWin(){
        super();
        this.setIconImage(Main.icon.getImage());
        this.setSize(500, 300);
        this.setLayout(null);                    //清空布局器
        this.setResizable(false);               //不可改变大小
        this.setLocation(400,200);         //设置窗口生成位置
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("删除学生信息");
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

    private class OKListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int startNum = 0;            // 初始的学生数量
            try {
                startNum = JdbcAction.getNum();          //缓存返回值，避免多次调用
                String[] momentMessage = JdbcAction.presentResult(codeText.getText());
                if (momentMessage[1] != null){   //判断null
                    if(momentMessage[0].equals(nameText.getText()) && momentMessage[1].equals(codeText.getText())){     // 判断是否存在以这个学号和名字是否一致
                        JdbcAction.delectStudent(nameText.getText(), codeText.getText());
                        if(JdbcAction.getNum() == (startNum - 1)){       // 判断数据库大小是否变小了
                            JOptionPane.showMessageDialog(null, "删除成功！", "提示",JOptionPane.WARNING_MESSAGE);   //弹出提示框
                            logger.info("DELECT【" + nameText.getText() + " : " +codeText.getText() + "】" + "删除了一个记录！");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "名字与该学号不匹配", "提示",JOptionPane.WARNING_MESSAGE);   //弹出提示框
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "没有该学号信息", "提示",JOptionPane.WARNING_MESSAGE);   //弹出提示框
                }

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                logger.error("HAVE ERROR!" + sqlException );
            }
        }
    }

    private class ClearListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            nameText.setText("");
            codeText.setText("");
        }
    }

}
