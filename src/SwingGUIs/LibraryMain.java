package SwingGUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/*
 * @Author: qph
 * @Date: 2019/10/18 10:35
 * @description: SwingGUIs
 */
public class LibraryMain extends JFrame {
    public LibraryMain(){
        Container c = getContentPane();
        setTitle("图书管理系统");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(300,50,800,1000);
//        JLabel jl = new JLabel("你来啦？");
//        jl.setBounds(50,50,200,200);
//        c.add(jl);
        /*
        * 设置左上角两个下拉菜单
        */
        JMenuBar mb = new JMenuBar();//菜单栏

        JMenu mUser = new JMenu("用户管理");
        JMenu mBook = new JMenu("图书管理");
        /*
        * 添加各个菜单栏里面的内容
        */
        JMenuItem modifyPassword = new JMenuItem("修改密码");
        JMenuItem logOut = new JMenuItem("退出登录");
        JMenuItem getBook = new JMenuItem("查找图书");
        JMenuItem newBook= new JMenuItem("增加图书");
        JMenuItem editBook = new JMenuItem("编辑图书");
        JMenuItem delBook = new JMenuItem("删除图书");

        mUser.add(modifyPassword);
        mUser.add(logOut);
        mBook.add(getBook);
        mBook.add(newBook);
        mBook.add(editBook);
        mBook.add(delBook);
        /*
        * 给登出选项指定目标页面
        */
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Login();
                    dispose();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        /*
        * 给修改密码按钮添加事件
        */
        modifyPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new modifyPassword();
            }
        });

        mb.add(mUser);
        mb.add(mBook);

        setJMenuBar(mb);


//        JComboBox<String> userManage = new JComboBox<>();
//        String[] items1 = {"用户管理","修改密码","退出登录"};
//        ComboBoxModel<String> cm1 = new DefaultComboBoxModel<>(items1);//下拉列表模型
//        userManage.setModel(cm1);//向列表中添加数据模型
//        userManage.setBounds(10,50,100,40);
//
//        /*
//        * 图书管理菜单
//        */
//        JComboBox<String> bookManage = new JComboBox<>();
//        String[] items2 = {"查询图书","增加图书","编辑图书","删除图书"};
//        ComboBoxModel<String> cm2 = new DefaultComboBoxModel<>(items2);//下拉列表模型
//        userManage.setModel(cm2);//向列表中添加数据模型
//        userManage.setBounds(110,0,100,40);
//
//        c.add(userManage);
//        c.add(bookManage);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LibraryMain();
    }
}
