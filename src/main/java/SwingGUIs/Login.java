package SwingGUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


/*
 * @Author: qph
 * @Date: 2019/10/18 10:31
 * @description: SwingGUIs
 */
public class Login extends JFrame {
    public final static String USER = "root";
    public final static String PASSWORD = "19920124";
    public final static String URL = "jdbc:mysql://localhost:3306/librarydemo?useSSL=false";


    public Login() {

        Container c = getContentPane();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(400,250);
        setBounds(300, 250, 400, 250);
        setTitle("用户登录界面");
        c.setLayout(null);
        /*
         * 创建标签
         */
        JLabel l1 = new JLabel("用户名：");
        l1.setBounds(50, 40, 80, 25);
        JLabel l2 = new JLabel("密码：");
        l2.setBounds(50, 70, 80, 25);
        /*
         创建文本域和密码域
         */
        JTextField name = new JTextField();
        name.setBounds(140, 40, 165, 25);
        JPasswordField password = new JPasswordField();
        password.setBounds(140, 70, 165, 25);

        /*
         * 创建登录按钮
         */
        JButton loginbutton = new JButton("登录");
        loginbutton.setBounds(100, 130, 60, 30);
        loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1 = name.getText().toString();
                String s2 = new String(password.getPassword());
                Connection conn = null;
                PreparedStatement stmt = null;
                try {
                    //加载驱动程序
                    Class.forName("com.mysql.jdbc.Driver");
                    //获得数据库连接
                    conn = DriverManager.getConnection(URL, USER, PASSWORD);
                    String sql1 = "SELECT * FROM user where username=? and password=?";
                    stmt = conn.prepareStatement(sql1);
                    stmt.setString(1, s1);
                    stmt.setString(2, s2);
                    ResultSet rs = stmt.executeQuery();
                    if (s1.length() != 0 && s2.length() != 0) {
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null, "登陆成功");
                            new LibraryMain();
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "账号或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "请输入帐号或密码", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (Exception e1) {
                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                    System.exit(0);
                }


            }
        });

        JButton resetButton = new JButton("重置");
        resetButton.setBounds(200, 130, 60, 30);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                password.setText("");
            }
        });


        c.add(l1);
        c.add(name);
        c.add(l2);
        c.add(password);
        c.add(loginbutton);
        c.add(resetButton);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}
