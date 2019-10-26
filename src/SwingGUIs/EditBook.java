package SwingGUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/*
 * @Author: qph
 * @Date: 2019/10/24 9:09
 * @description: SwingGUIs
 */
public class EditBook extends JFrame {
    public final static String USER = "root";
    public final static String PASSWORD = "19920124";
    public final static String URL = "jdbc:mysql://localhost:3306/librarydemo?useSSL=false";

    public EditBook() {
        Container c = getContentPane();
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("编辑图书");
        setBounds(450, 300, 500, 400);

        JLabel l1 = new JLabel("要修改的书号");
        l1.setBounds(95, 100, 80, 25);
        JLabel l2 = new JLabel("图书名称");
        l2.setBounds(120, 130, 80, 25);
        JLabel l3 = new JLabel("图书作者");
        l3.setBounds(120, 160, 80, 25);
        JLabel l4 = new JLabel("添加数量");
        l4.setBounds(120, 190, 80, 25);
        JLabel l5 = new JLabel("图书分类");
        l5.setBounds(120, 220, 80, 25);

        JTextField bookId = new JTextField();
        bookId.setBounds(210, 100, 165, 25);
        JTextField bookName = new JTextField();
        bookName.setBounds(210, 130, 165, 25);
        JTextField author = new JTextField();
        author.setBounds(210, 160, 165, 25);
        JTextField sum = new JTextField();
        sum.setBounds(210, 190, 165, 25);

        JComboBox<String> categories = new JComboBox<String>();
        categories.setBounds(210, 220, 35, 25);
        String[] items = {"A", "B", "C"};
        ComboBoxModel<String> cm = new DefaultComboBoxModel<>(items);//下拉列表模型
        categories.setModel(cm);//向列表中添加数据模型
        categories.setEditable(true);//下拉列表里的值是否可以编辑

        JButton comfirmButton = new JButton("确定");
        comfirmButton.setBounds(400, 100, 60, 25);
        JButton insertbutton = new JButton("修改");
        insertbutton.setBounds(210, 280, 60, 30);
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(300, 280, 60, 30);

        comfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = bookId.getText().toString();
                Connection conn = null;
                PreparedStatement stmt = null;
                //加载驱动程序81687a79b5f141218fa116966f844aec
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    //获得数据库连接
                    conn = DriverManager.getConnection(URL, USER, PASSWORD);
                    String sql1 = "SELECT * FROM books WHERE bookid=?";
                    stmt = conn.prepareStatement(sql1);
                    stmt.setString(1, s);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        bookName.setText(rs.getString("bookname"));
                        author.setText(rs.getString("author"));
                        sum.setText(rs.getString("sum"));
                        categories.setSelectedItem(rs.getString("categories"));
                    } else {
                        JOptionPane.showMessageDialog(null, "该书不存在", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        c.add(l1);
        c.add(l2);
        c.add(l3);
        c.add(l4);
        c.add(l5);
        c.add(bookName);
        c.add(bookId);
        c.add(categories);
        c.add(sum);
        c.add(insertbutton);
        c.add(comfirmButton);
        c.add(cancelButton);
        c.add(author);

        setVisible(true);
    }

    public static void main(String[] args) {
        new EditBook();
    }
}
