package SwingGUIs;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import SwingGUIs.Global;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;


/*
 * @Author: qph
 * @Date: 2019/10/18 10:35
 * @description: SwingGUIs
 */
public class LibraryMain extends JFrame {
    public final static String USER = "root";
    public final static String PASSWORD = "19920124";
    public final static String URL = "jdbc:mysql://localhost:3306/librarydemo?useSSL=false";

    public LibraryMain() {
        Container c = getContentPane();
        setTitle("图书管理系统");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(300, 50, 800, 1000);
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
//        JMenuItem getBook = new JMenuItem("查找图书");
        JMenuItem newBook = new JMenuItem("增加图书");
        JMenuItem editBook = new JMenuItem("编辑图书");
        JMenuItem delBook = new JMenuItem("删除图书");

        mUser.add(modifyPassword);
        mUser.add(logOut);
//        mBook.add(getBook);
        mBook.add(newBook);
        mBook.add(editBook);
        mBook.add(delBook);
        /*
         * 给登出选项指定目标页面
         */


        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();

            }
        });
        /*
         * 给修改密码按钮添加事件
         */
        modifyPassword.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new modifyPassword();
            }
        });
        /*
         * 给添加图书按钮添加事件
         */
        newBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertBook();
            }
        });
        /*
         * 给编辑图书按钮添加事件
         */
        editBook.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new EditBook();
            }
        });

        mb.add(mUser);
        mb.add(mBook);

        setJMenuBar(mb);


        /*
         * 主页的对数据库中所有图书的展示
         */
        JTable table = new JTable();
        table.setBounds(100, 100, 600, 500);


        Object[] head = new Object[]{
                "id", "书名", "作者", "类别", "数量"
        };




        /*
         * 添加翻页按键（首页，上一页，下一页，尾页）
         */

        JButton firstPage = new JButton("首页");
        firstPage.setBounds(160, 280, 100, 50);
        JButton previousPage = new JButton("上一页");
        previousPage.setBounds(280, 280, 100, 50);
        JButton nextPage = new JButton("下一页");
        nextPage.setBounds(400, 280, 100, 50);
        JButton lastPage = new JButton("尾页");
        lastPage.setBounds(520, 280, 100, 50);
        firstPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Global.currentPage = 1;
                new LibraryMain();
            }
        });

        nextPage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (Global.currentPage < Global.pageSize) {
                    Global.currentPage++;
                    new LibraryMain();
                }
            }
        });
        previousPage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (Global.currentPage > 1) {
                    Global.currentPage--;
                    new LibraryMain();
                }
            }
        });

        lastPage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Global.currentPage = Global.pageSize;
                new LibraryMain();
            }
        });

        DefaultTableModel model = new DefaultTableModel(head, 0);
        ArrayList<Book> book = queryAllBook(Global.currentPage);
        for (int i = 0; i < book.size(); i++) {
            int bookid = book.get(i).getBookid();
            String bookname = book.get(i).getBookname();
            String author = book.get(i).getAuthor();
            String categories = book.get(i).getCategories();
            int sum = book.get(i).getSum();
            model.addRow(new Object[]{bookid, bookname, author, categories, sum});
        }
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        table.setModel(model);

        table.setRowHeight(45);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 800, 245);

        /*
         * 编写图表相关内容，统计ABC三种图书的比例，用饼状图
         */


//        DefaultPieDataset dataset = new DefaultPieDataset();
//        dataset.setValue("A类", Global.ASum);
//        dataset.setValue("B类", Global.BSum);
//        dataset.setValue("C类", Global.CSum);
//
//        JFreeChart chart = ChartFactory.createPieChart("图书分类", dataset,
//                true, true, false);
//        CategoryPlot plot = (CategoryPlot) chart.getPlot();
//        CategoryAxis categoryAxis = plot.getDomainAxis();
//        categoryAxis.setLabelFont(new Font("仿宋", Font.ROMAN_BASELINE, 12));
//        ChartPanel cp = new ChartPanel(chart);
//        cp.setBounds(0,400,800,400);
//
////        c.add(new ChartPanel(chart));
//
//        ChartFrame chartFrame = new ChartFrame("某公司人员组织结构图", chart);
//        chartFrame.pack();
//        JScrollPane chartPane = new JScrollPane(cp);
//        chartPane.setBounds(0,400,800,245);
//
//        c.add(chartPane);


        c.add(firstPage);
        c.add(previousPage);
        c.add(nextPage);
        c.add(lastPage);
        c.add(scrollPane);


        setVisible(true);
    }

    public class Book {
        private int bookid;
        private String bookname;
        private String author;
        private String categories;
        private int sum;

        public int getBookid() {
            return bookid;
        }

        public void setBookid(int bookid) {
            this.bookid = bookid;
        }

        public String getBookname() {
            return bookname;
        }

        public void setBookname(String bookname) {
            this.bookname = bookname;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCategories() {
            return categories;
        }

        public void setCategories(String categories) {
            this.categories = categories;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }
    }


    //查询所有用户
    public ArrayList<Book> queryAllBook(int n) {

        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "select * from books";
        ArrayList<Book> list = new ArrayList<Book>();
        try {
            //加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //获得数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookid(rs.getInt(1));
                book.setBookname(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setCategories(rs.getString(4));
                book.setSum(rs.getInt(5));
                list.add(book);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int k = 0; k < list.size(); k++) {
            if (list.get(k).getCategories() == "A") {
                Global.ASum += list.get(k).getSum();
                break;
            }
            if (list.get(k).getCategories() == "B") {
                Global.BSum += list.get(k).getSum();
                break;
            }
            if (list.get(k).getCategories() == "C") {
                Global.CSum += list.get(k).getSum();
                break;
            }
        }
        ArrayList<Book> sublist = new ArrayList<>();
        Global.pageSize = list.size() / 5 + 1;
        int startIndex = 5 * (n - 1);
        int endIndex;

        endIndex = 5 * n - 1;
        if (n == Global.pageSize) {
            endIndex = list.size() - 1;
        }
        for (int j = startIndex; j <= endIndex; j++) {
            sublist.add(list.get(j));

        }
        return sublist;
    }


    public static void main(String[] args) {
        new LibraryMain();
    }
}
