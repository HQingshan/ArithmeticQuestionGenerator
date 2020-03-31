package com.zhouzhou;

import javax.swing.*;
import java.awt.*;

public class MyappView extends JFrame {

    // 定义组件
    JPanel jp1, jp2, jp3;
    JLabel jlb1, jlb2;
    JButton jb1, jb2;
    JTextField jtf1;
    JPasswordField jpf1;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyappView d1 = new MyappView();

    }

    // 构造函数
    public MyappView() {

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jlb1 = new JLabel("输入生成题目的个数");
        jlb2 = new JLabel("输入生成题目数值范围");

        jb1 = new JButton("确定");
        jb2 = new JButton("取消");

        jtf1 = new JTextField(10);

        jpf1 = new JPasswordField(10);// 设置布局管理(上面忘记：extends JFrame，这里出错了)
        this.setLayout(new GridLayout(3, 1));

        // 加入各个组件
        jp1.add(jlb1);
        jp1.add(jtf1);

        jp2.add(jlb2);
        jp2.add(jpf1);

        jp3.add(jb1);
        jp3.add(jb2);

        // 加入到JFrame
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        this.setSize(250, 150);
        this.setTitle("算术生成器");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}