package SwingGUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/*
 * @Author: qph
 * @Date: 2019/10/22 20:37
 * @description: SwingGUIs
 */
public class InsertBook extends JFrame {
    public final static String USER = "root";
    public final static String PASSWORD = "19920124";
    public final static String URL = "jdbc:mysql://localhost:3306/librarydemo?useSSL=false";

    public InsertBook() {
        Container c = getContentPane();
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(450, 300, 500, 400);
        setTitle("添加图书");

        JLabel l1 = new JLabel("图书名称");
        l1.setBounds(120, 100, 80, 25);
        JLabel l2 = new JLabel("图书作者");
        l2.setBounds(120, 130, 80, 25);
        JLabel l3 = new JLabel("添加数量");
        l3.setBounds(120, 160, 80, 25);
        JLabel l4 = new JLabel("图书分类");
        l4.setBounds(120, 190, 80, 25);

        JTextField bookName = new JTextField();
        bookName.setBounds(210, 100, 165, 25);
        JTextField author = new JTextField();
        author.setBounds(210, 130, 165, 25);
        JTextField sum = new JTextField();
        sum.setBounds(210, 160, 165, 25);

        JComboBox<String> categories = new JComboBox<String>();
        categories.setBounds(210, 190, 35, 25);
        String[] items = {"A", "B", "C"};
        ComboBoxModel<String> cm = new DefaultComboBoxModel<>(items);//下拉列表模型
        categories.setModel(cm);//向列表中添加数据模型
        categories.setEditable(true);//下拉列表里的值是否可以编辑

        JButton insertbutton = new JButton("添加");
        insertbutton.setBounds(210, 250, 60, 30);
        JButton resetButton = new JButton("重置");
        resetButton.setBounds(300, 250, 60, 30);

        insertbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1 = new String(bookName.getText().toString());
                String s2 = new String(author.getText().toString());
                String s3 = new String(sum.getText().toString());
                String s4 = new String(categories.getSelectedItem().toString());
                Connection conn = null;
                PreparedStatement stmt = null;
                try {
                    //加载驱动程序
                    Class.forName("com.mysql.jdbc.Driver");
                    //获得数据库连接
                    conn = DriverManager.getConnection(URL, USER, PASSWORD);
                    String sql1 = "INSERT INTO books(bookname,author,categories,sum)VALUES(?,?,?,?)";
                    stmt = conn.prepareStatement(sql1);
                    stmt.setString(1, s1);
                    stmt.setString(2, s2);
                    stmt.setString(3, s4);
                    stmt.setString(4, s3);

                    int rs = stmt.executeUpdate();
                    if (rs > 0) {
                        JOptionPane.showMessageDialog(null, "修改成功");
                        new LibraryMain();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "修改失败");

                    }

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
                bookName.setText("");
                author.setText("");
                sum.setText("");
                categories.setSelectedItem("A");
            }
        });

        c.add(l1);
        c.add(l2);
        c.add(l3);
        c.add(l4);
        c.add(bookName);
        c.add(author);
        c.add(sum);
        c.add(categories);
        c.add(insertbutton);
        c.add(resetButton);


        setVisible(true);
    }

    public static void main(String[] args) {
        new InsertBook();
    }
}
