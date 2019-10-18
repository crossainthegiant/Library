package SwingGUIs;

import javax.swing.*;
import java.awt.*;

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
        setBounds(50,50,200,200);
        JLabel jl = new JLabel("你来啦？");
        c.add(jl);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LibraryMain();
    }
}
