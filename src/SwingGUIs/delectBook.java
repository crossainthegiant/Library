package SwingGUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/*
 * @Author: qph
 * @Date: 2019/10/25 12:16
 * @description: SwingGUIs
 */
public class delectBook extends JFrame {
    public final static String USER = "root";
    public final static String PASSWORD = "19920124";
    public final static String URL = "jdbc:mysql://localhost:3306/librarydemo?useSSL=false";

    public delectBook() {
        setLayout(null);
        setTitle("删除图书");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(450, 300, 480, 180);
        Container c = getContentPane();

        JLabel l1 = new JLabel("要删除的书号");
        l1.setBounds(50, 50, 80, 25);

        JTextField bookId = new JTextField();
        bookId.setBounds(170, 50, 165, 25);

        JButton comfirmButton = new JButton("确定");
        comfirmButton.setBounds(360, 50, 60, 25);

        c.add(l1);
        c.add(bookId);
        c.add(comfirmButton);

        comfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1 = bookId.getText();
                Connection conn = null;
                PreparedStatement stmt = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    //获得数据库连接
                    conn = DriverManager.getConnection(URL, USER, PASSWORD);
                    String sql1 = "DELETE FROM books WHERE bookid=?";
                    stmt = conn.prepareStatement(sql1);
                    stmt.setString(1, s1);
                    int rs = stmt.executeUpdate();
                    if (rs > 0) {
                        JOptionPane.showMessageDialog(null, "删除成功");
                        new LibraryMain();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "该书不存在", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        setVisible(true);
    }

    public static void main(String[] args) {
        new delectBook();
    }
}
