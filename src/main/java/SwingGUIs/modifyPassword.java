package SwingGUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * @Author: qph
 * @Date: 2019/10/20 19:29
 * @description: SwingGUIs
 */
public class modifyPassword extends JFrame {
    public final static String USER = "root";
    public final static String PASSWORD = "19920124";
    public final static String URL = "jdbc:mysql://localhost:3306/librarydemo?useSSL=false";

    public modifyPassword() {
        Container c = getContentPane();
        setBounds(450, 300, 500, 400);
        setTitle("修改用户密码");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
//        JLabel jl = new JLabel("helloworld");
//        jl.setBounds(50,50,50,50);
//        c.add(jl);
        JLabel l1 = new JLabel("旧密码：");
        l1.setBounds(120, 100, 80, 25);
        JLabel l2 = new JLabel("新密码：");
        l2.setBounds(120, 130, 80, 25);
        JLabel l3 = new JLabel("确认新密码：");
        l3.setBounds(120, 160, 80, 25);

        JPasswordField oldpsd = new JPasswordField();
        oldpsd.setBounds(210, 100, 165, 25);
        JPasswordField newpsd = new JPasswordField();
        newpsd.setBounds(210, 130, 165, 25);
        JPasswordField newpsd2 = new JPasswordField();
        newpsd2.setBounds(210, 160, 165, 25);

        JButton modifybutton = new JButton("修改");
        modifybutton.setBounds(210, 220, 60, 30);
        JButton resetButton = new JButton("重置");
        resetButton.setBounds(300, 220, 60, 30);

        modifybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s1 = new String(oldpsd.getPassword());
                String s2 = new String(newpsd.getPassword());
                String s3 = new String(newpsd2.getPassword());
                Connection conn = null;
                PreparedStatement stmt = null;
                try {
                    //加载驱动程序
                    Class.forName("com.mysql.jdbc.Driver");
                    //获得数据库连接
                    conn = DriverManager.getConnection(URL, USER, PASSWORD);
                    String sql1 = "SELECT * FROM user where username=? and password=?";
                    stmt = conn.prepareStatement(sql1);
                    stmt.setString(1, "admin");
                    stmt.setString(2, s1);

                    ResultSet rs = stmt.executeQuery();
                    if (s1.length() != 0 && s2.length() != 0 && s3.length() != 0) {
                        if (rs.next()) {
                            if (s2.equals(s3)) {

                                String sql2 = "UPDATE user SET password=? WHERE username=?";
                                stmt = conn.prepareStatement(sql2);
                                stmt.setString(1, s2);
                                stmt.setString(2, "admin");
                                int rs1 = stmt.executeUpdate();
                                if (rs1 > 0) {
                                    JOptionPane.showMessageDialog(null, "修改成功");
                                    new LibraryMain();
                                    dispose();
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "请输入相同的新密码", "错误", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "请输入旧密码和新密码", "错误", JOptionPane.ERROR_MESSAGE);
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
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oldpsd.setText("");
                newpsd.setText("");
                newpsd2.setText("");
            }
        });

        c.add(l1);
        c.add(l2);
        c.add(l3);
        c.add(oldpsd);
        c.add(newpsd);
        c.add(newpsd2);
        c.add(modifybutton);
        c.add(resetButton);
        setVisible(true);
    }

    public static void main(String[] args) {
        new modifyPassword();
    }
}
