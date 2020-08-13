package com.studentAdmi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrimePanel extends JPanel {
    private MyButton addButton;            // 增按钮
    private MyButton delButton;            // 删按钮
    private MyButton findButton;               // 查按钮
    private MyButton changeButton;             //改按钮
    private MyButton wholeButton;         //全部按钮



    public PrimePanel(int x, int y, int width, int height) {     //构造方法
        super();
        this.setLayout(null);                                         //清空布局器
        this.setBounds(x, y, width, height);
        this.setFont(new Font("serif", Font.BOLD, 23));      //字体
        this.setForeground(Color.pink);                        //字体颜色
        this.addButtons();
        this.addListen();

    }

    @Override
    public void paintComponent(Graphics g) {        // 重写paintComponent(Graphics g)方法以图片进行绘制。
        super.paintComponents(g);
        Image backgroundImage = new ImageIcon("src\\main\\java\\img\\background.jpg").getImage();   //取得图片并绘制。
        g.drawImage(backgroundImage, 0, 0, Main.mainWindow.getWidth(),
        		Main.mainWindow.getHeight(), Main.mainWindow);
        g.drawString("《Java 程序设计基础》课程实训" , 220, 150);
        g.drawString("软件1901  韦烨" , 320, 190);
    }

    private void addButtons(){                             //加入按钮
        addButton = new MyButton(160,280,80,40, "增加");
        this.add(addButton);

        delButton = new MyButton(260,280,80,40, "删除");
        this.add(delButton);

        findButton = new MyButton(360,280,80,40, "查找");
        this.add(findButton);

        changeButton = new MyButton(460,280,80,40 ,"修改");
        this.add(changeButton);

        wholeButton = new MyButton(560,280,80,40 ,"全部");
        this.add(wholeButton); 
    }

    private void addListen(){                                            //  加入监听
        addButton.addActionListener(new AddListener());
        delButton.addActionListener(new DelListener());
        findButton.addActionListener(new FindListener());
        changeButton.addActionListener(new ChangeListener());
        wholeButton.addActionListener(new WholeListener());
 
    }

    private class AddListener implements ActionListener {               //监听添加按钮
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddWin();
        }
    }

    private class DelListener implements ActionListener {               //监听删除按钮
        @Override
        public void actionPerformed(ActionEvent e) {
            new DelWin();
        }
    }

    private class FindListener implements ActionListener {               //监听查找按钮
        @Override
        public void actionPerformed(ActionEvent e) {
            new FindWin();
        }
    }

    private class ChangeListener implements ActionListener {               //监听修改按钮
        @Override
        public void actionPerformed(ActionEvent e) {
        	new ChangeWin();
        }
    }
    
    private class WholeListener implements ActionListener{
    	@Override
    	public void actionPerformed(ActionEvent e) {
    	    new WholeWin();
    	}
    }

}

