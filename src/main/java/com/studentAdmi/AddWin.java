package com.studentAdmi;

import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class AddWin extends JFrame {
                                //标签和文本框
	private Logger logger = LoggerFactory.getLogger(getClass());
	

	
    private MyLabel nameLabel;
    private MyTextFiled nameText;

    private MyLabel sexLabel;
    private MyTextFiled sexText;

    private MyLabel placeLabel;
    private MyTextFiled placeText;

    private MyLabel codeLabel;
    private MyTextFiled codeText;

    private MyLabel deptLabel;
    private MyTextFiled deptText;

    private MyLabel banLabel;
    private MyTextFiled banText;

    private MyButton OKButton;            //确认按钮
    private MyButton clearButton;         //清空按钮

    AddWin(){
        super();
        this.setIconImage(Main.icon.getImage());
        this.setSize(500,600);
        this.setLayout(null);                    //清空布局器
        this.setResizable(false);               //不可改变大小
        this.setVisible(true);
        this.setLocation(450,200);    //设置窗口生成位置
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("添加学生信息(不能有空格)");
        this.getContentPane().setBackground(Color.pink);
        
        this.addPart();
        this.addListen();
    }

   private void addPart(){                            //添加组件
        nameLabel = new MyLabel(80, 15, 80, 40, "姓名");
        this.add(nameLabel);
        nameText = new MyTextFiled(140, 20, 200, 30);
        this.add(nameText);

        sexLabel = new MyLabel(80, 75, 80, 40, "性别");
        this.add(sexLabel);
        sexText = new MyTextFiled(140, 80, 200,30);
        this.add(sexText);

        placeLabel = new MyLabel(80, 135, 80, 40, "籍贯");
        this.add(placeLabel);
        placeText = new MyTextFiled(140, 140, 200, 30);
        this.add(placeText);

        codeLabel = new MyLabel(80, 195, 80, 40, "学号");
        this.add(codeLabel);
        codeText = new MyTextFiled(140, 200, 200, 30);
        this.add(codeText);

        deptLabel = new MyLabel(80, 255, 80, 40, "系别");
        this.add(deptLabel);
        deptText = new MyTextFiled(140, 260, 200,30);
        this.add(deptText);

        banLabel = new MyLabel(80, 315, 80, 40, "班级");
        this.add(banLabel);
        banText = new MyTextFiled(140, 320, 200, 30);
        this.add(banText);

        OKButton =new MyButton(40, 420, 80, 40, "确认");
        this.add(OKButton);
        clearButton =new MyButton(360, 420, 80, 40, "清空");
        this.add(clearButton);

    }

    private void addListen(){
        OKButton.addActionListener(new OKListener());
        clearButton.addActionListener(new clearListener());
    }

   private class OKListener implements ActionListener {               //监听确定按钮
       @Override
       public void actionPerformed(ActionEvent e){
           if (nameText.getText().length() == 0 || nameText.getText().contains(" ")) {
               JOptionPane.showMessageDialog(null, "名字未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE); //弹出提示框
           } else if (sexText.getText().length() == 0 || sexText.getText().contains(" ")) {
               JOptionPane.showMessageDialog(null, "性别未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE); //弹出提示框
           } else if (placeText.getText().length() == 0 || placeText.getText().contains(" ")) {
               JOptionPane.showMessageDialog(null, "籍贯未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE); //弹出提示框
           } else if (codeText.getText().length() == 0 || codeText.getText().contains(" ")) {
               JOptionPane.showMessageDialog(null, "学号未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE); //弹出提示框
           } else if (deptText.getText().length() == 0 || deptText.getText().contains(" ")) {
               JOptionPane.showMessageDialog(null, "系别未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE); //弹出提示框
           } else if (banText.getText().length() == 0 || banText.getText().contains(" ")) {
               JOptionPane.showMessageDialog(null, "班别未输入或空格非法", "提示", JOptionPane.WARNING_MESSAGE); //弹出提示框
           } else if (sexText.getText().equals("男") || sexText.getText().equals("女")) {
        	   int startNum = 0;
        	   try {
        	       startNum = JdbcAction.getNum();             // 初始的学生人数
                   JdbcAction.addStudent( nameText.getText(), sexText.getText(), placeText.getText(), codeText.getText(), deptText.getText(), banText.getText()); //把学生加进去
                       if (JdbcAction.getNum() == (startNum + 1)) {
                           JOptionPane.showMessageDialog(null, "添加成功！", "提示", JOptionPane.WARNING_MESSAGE); //弹出提示框
                           logger.info( "ADD【" + nameText.getText() + " : " +codeText.getText() + "】" + "增加了一个记录！");
                       } else {
                       JOptionPane.showMessageDialog(null, "该学号信息已存在！", "提示", JOptionPane.WARNING_MESSAGE); //弹出提示框
                            }
			} catch (Exception e3) {
				// TODO: handle exception
                   e3.printStackTrace();
                   logger.error("HAVE ERROR!" + e3);
			}
           } else {
               JOptionPane.showMessageDialog(null, "请输入正确的性别", "提示", JOptionPane.WARNING_MESSAGE); //弹出提示框
           }
       }
   }

   private class clearListener implements ActionListener {            //监听清空按钮
        @Override
        public void actionPerformed(ActionEvent e) {
            nameText.setText("");
            sexText.setText("");
            placeText.setText("");
            deptText.setText("");
            codeText.setText("");
            banText.setText("");
        }
    }
}

